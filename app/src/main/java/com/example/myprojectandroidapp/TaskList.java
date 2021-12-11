package com.example.myprojectandroidapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myprojectandroidapp.ds.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.example.myprojectandroidapp.Constants.*;

public class TaskList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        Intent currentIntent = getIntent();
        int courseId = currentIntent.getIntExtra("CourseId", 0);

        executor.execute(() -> {
            //atitinka doInBackground
            try {
                String response = RESTController.sendGet(TASKS_BY_PROJECT_URL + courseId);
                handler.post(() -> {
                    if (!response.equals("") && !response.equals("Error")) {
                        Gson builder = new GsonBuilder().create();
                        Type courseListType = new TypeToken<List<Task>>() {
                        }.getType();
                        final List<Task> courseListFromJson = builder.fromJson(response, courseListType);

                        ListView courseList = findViewById(R.id.courseList);
                        ArrayAdapter<Task> arrayAdapter = new ArrayAdapter<>(TaskList.this, android.R.layout.simple_list_item_1, courseListFromJson);
                        courseList.setAdapter(arrayAdapter);
                    }
                });

       /* Courses courses = new Courses();
        courses.execute();*/

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
                        Intent intent = new Intent(TaskList.this, MyCoursesActivity.class);
                        startActivity(intent);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}