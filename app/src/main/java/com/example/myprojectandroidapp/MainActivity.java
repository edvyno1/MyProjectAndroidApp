package com.example.myprojectandroidapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.example.myprojectandroidapp.Constants.USER_LOGIN_URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void validate(View view) {

        EditText login = findViewById(R.id.loginText);
        EditText psw = findViewById(R.id.pswField);
        String data = "{\"login\":\"" + login.getText().toString() + "\", \"password\": \"" + psw.getText().toString() + "\"}";

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
               String response = RESTController.sendPost(USER_LOGIN_URL, data);
                handler.post(() ->{
                    System.out.println(response);

                    try {
                        if (!response.equals("")  && !response.equals("Wrong credentials or no such user")) {
                            Intent intent = new Intent(MainActivity.this, MyCoursesActivity.class);
                            intent.putExtra("UserInfo", response);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Errors while authenticating", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }
}

