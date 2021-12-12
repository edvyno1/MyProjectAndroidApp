package com.example.myprojectandroidapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myprojectandroidapp.ds.Folder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.example.myprojectandroidapp.Constants.*;

public class FolderList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_list);
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        Intent currentIntent = getIntent();
        int courseId = currentIntent.getIntExtra("CourseId", 0);

        executor.execute(() -> {
            try {
                String response = RESTController.sendGet(FOLDERS_BY_COURSE_URL + courseId);
                System.out.println(response);
                handler.post(() -> {
                    if (!response.equals("") && !response.equals("Error")) {
                        Gson builder = new GsonBuilder().create();

                        Type folderListType = new TypeToken<List<Folder>>() {
                        }.getType();
                        final List<Folder> folderListFromJson = builder.fromJson(response, folderListType);

                        ListView folderList = findViewById(R.id.folderList);
                        ArrayAdapter<Folder> arrayAdapter = new ArrayAdapter<>(FolderList.this, android.R.layout.simple_list_item_1, folderListFromJson);
                        folderList.setAdapter(arrayAdapter);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void deleteCourse(View view) {
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        Intent currentIntent = getIntent();
        int courseId = currentIntent.getIntExtra("CourseId", 0);

        executor.execute(() -> {
            try {
                String response = RESTController.sendDelete(COURSE_DELETE + courseId);
                System.out.println(response);
                handler.post(() -> {
                    if (response.equals("Success")) {
                        Intent intent = new Intent(FolderList.this, MyCoursesActivity.class);
                        startActivity(intent);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}