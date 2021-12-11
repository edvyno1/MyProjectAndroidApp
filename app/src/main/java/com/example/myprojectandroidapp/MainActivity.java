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
        CheckBox userType = findViewById(R.id.userType);
        String type = userType.isChecked() ? "C" : "P";
        //Mano request body, kuri siusiu @RequestMapping(value = "/users/userLogin", method = RequestMethod.POST)
        String data = "{\"login\":\"" + login.getText().toString() + "\", \"password\": \"" + psw.getText().toString() + "\"}";

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            //atitinka doInBackground
            try {
               String response = RESTController.sendPost(USER_LOGIN_URL, data);
                handler.post(() ->{
                    //atitinka onPostExecute
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
        /*UserValidation userValidation = new UserValidation();
        userValidation.execute(data);*/
    }

/*    private final class UserValidation extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Sending data", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String params = strings[0];
            try {
                return RESTController.sendPost(USER_LOGIN_URL, params);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);

            System.out.println(result);

            try {
                if (!result.equals("") && !result.equals("Error") && !result.equals("Wrong credentials or no such user")) {
                    Intent intent = new Intent(MainActivity.this, MyProjectsActivity.class);
                    intent.putExtra("UserInfo", result);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Errors while authenticating", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/
}

