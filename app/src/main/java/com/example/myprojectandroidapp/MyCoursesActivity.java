package com.example.myprojectandroidapp;

import android.content.Intent;
import android.os.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myprojectandroidapp.ds.Course;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.example.myprojectandroidapp.Constants.*;

public class MyCoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            try {
                String response = RESTController.sendGet(COURSE_ALL_URL);
                System.out.println(response);
                handler.post(() -> {
                    if (!response.equals("") && !response.equals("Error")) {

                        Gson builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create();
                        Type courseListType = new TypeToken<List<Course>>() {
                        }.getType();
                        final List<Course> courseListFromJson = builder.fromJson(response, courseListType);

                        ListView courseList = findViewById(R.id.courseList);
                        ArrayAdapter<Course> arrayAdapter = new ArrayAdapter<>(MyCoursesActivity.this, android.R.layout.simple_list_item_1, courseListFromJson);
                        System.out.println(response);
                        courseList.setAdapter(arrayAdapter);

                        courseList.setOnItemClickListener(((parent, view, position, id) -> {
                            Toast.makeText(MyCoursesActivity.this, "Showing project " + courseListFromJson.get(position).getId() + " data", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MyCoursesActivity.this, FolderList.class);
                            intent.putExtra("CourseId", courseListFromJson.get(position).getId());
                            startActivity(intent);
                        }));
                    }

                });
                Intent intent = getIntent();
                String userData = intent.getStringExtra("UserInfo");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
