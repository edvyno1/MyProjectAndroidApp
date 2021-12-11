package com.example.myprojectandroidapp;

import android.content.Intent;
import android.os.*;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
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
        toggleFragView(true);
        //Uzpildytu duomenimis is karto
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            //atitinka doInBackground
            try {
                String response = RESTController.sendGet(COURSE_ALL_URL);
                handler.post(() -> {
                    if (!response.equals("") && !response.equals("Error")) {
                        //Uzpildysiu sarasa pagal db duomenis
                        //Zinau, kad gausiu JSON formatu

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
                            toggleFragView(false);
                         /*   Intent intent = new Intent(MyProjectsActivity.this, TaskList.class);
                            intent.putExtra("ProjectId", projectListFromJson.get(position).getId());
                            startActivity(intent);*/
                        }));
                    }

                });

       /* Projects projects = new Projects();
        projects.execute();*/


                Intent intent = getIntent();
                String userData = intent.getStringExtra("UserInfo");
                System.out.println("antro lango info" + userData);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

/*    private final class Projects extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                return RESTController.sendGet(PROJECT_ALL_URL);
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            System.out.println(response);
            try {
                if (!response.equals("") && !response.equals("Error")) {
                    //Uzpildysiu sarasa pagal db duomenis
                    //Zinau, kad gausiu JSON formatu

                    Gson builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                            .create();
                    Type projectListType = new TypeToken<List<Project>>() {
                    }.getType();
                    final List<Project> projectListFromJson = builder.fromJson(response, projectListType);

                    ListView projectList = findViewById(R.id.projectList);
                    ArrayAdapter<Project> arrayAdapter = new ArrayAdapter<>(MyProjectsActivity.this, android.R.layout.simple_list_item_1, projectListFromJson);
                    projectList.setAdapter(arrayAdapter);

                    projectList.setOnItemClickListener(((parent, view, position, id) -> {
                        Toast.makeText(MyProjectsActivity.this, "Showing project " + projectListFromJson.get(position).getId() + " data", Toast.LENGTH_LONG).show();
    *//*                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fullFrag, new FullscreenFragment());
                        ft.commit();*//*
                        Intent intent = new Intent(MyProjectsActivity.this, TaskList.class);
                        intent.putExtra("ProjectId", projectListFromJson.get(position).getId());
                        startActivity(intent);
                    }));
                }
            } catch (Exception e) {
                e.printStackTrace();


            }
        }
    }*/
    }

    private void toggleFragView(boolean hide){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new CourseFragment();
        ft.replace(R.id.fragmentContainerView, fragment);
        if(hide) ft.hide(fragment); else ft.show(fragment);
        ft.commit();
    }

    public void closeFrag(View view) {
        System.out.println("huh2");
        toggleFragView(true);
    }
}
