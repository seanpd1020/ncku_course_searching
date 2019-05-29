package com.example.user.ncku_course_search;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private boolean loading = true;
    private String home = "http://course-query.acad.ncku.edu.tw/qry/";
    private String search = "http://course-query.acad.ncku.edu.tw/qry/qry001.php?dept_no=";
    TextView title;
    List<String> s1_array_list = new ArrayList<String>();
    String[] s1;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(loading == true)
                return false;
            else {
                android.support.v4.app.Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        selectedFragment = new HomeFragment(s1);
                        break;
                    case R.id.navigation_notifications:
                        selectedFragment = new NotificationsFragment();
                        break;
                    case R.id.navigation_ClassTimetable:
                        selectedFragment = new ClassTimetableFragment();
                        break;
                    case R.id.navigation_MyAccount:
                        selectedFragment = new MyAccountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //設定隱藏標題
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        title = findViewById(R.id.title);
        //Typeface type = Typeface.createFromAsset(title.getContext().getAssets(), "newfont.ttf");
        //title.setTypeface(type);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //navigation.setItemBackgroundResource();

        Intent intent = new Intent(this,TrackingRemainSeatsService.class);
        intent.putExtra("time",50);
        startService(intent);

        //get string array list
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Document doc = Jsoup.connect(home).get();
                    Elements links = doc.select("a[href]");
                    for(int i=32;i<links.size();++i){
                        s1_array_list.add(links.get(i).text());
                    }
                    s1 = new String[s1_array_list.size()];
                    s1_array_list.toArray(s1);

                    //update SharedPreferences
                    SharedPreferences getsh = getSharedPreferences("sharedprefs", Context.MODE_APPEND);
                    String dp = getsh.getString("dep_code","");
                    String n = getsh.getString("no","");
                    String rs = getsh.getString("remain_seats","");
                    if(!dp.isEmpty()) {
                        String dep_code[] = dp.split(",");
                        String no[] = n.split(",");
                        String remain_seats[] = rs.split(",");
                        for (int i=0;i<dep_code.length;++i) {
                            Document doc2 = Jsoup.connect(search + dep_code[i]).execute().parse();
                            Elements links2 = doc2.select("TR[class='course_y1']");
                            for (Element link3 : links2) {
                                Elements links3 = link3.select("TD");
                                String item_info[] = new String[25];
                                int k = 0;
                                for (Element link4 : links3) {
                                    item_info[k++] = link4.text();
                                }
                                if(item_info[2] == no[i]){
                                    remain_seats[i] = item_info[15];
                                }
                            }

                            links2 = doc2.select("TR[class='course_y0']");
                            for (Element link3 : links2) {
                                Elements links3 = link3.select("TD");
                                String item_info[] = new String[25];
                                int k = 0;
                                for (Element link4 : links3) {
                                    item_info[k++] = link4.text();
                                }
                                if(item_info[2] == no[i]){
                                    remain_seats[i] = item_info[15];
                                }
                            }

                            links2 = doc2.select("TR[class='course_y2']");
                            for (Element link3 : links2) {
                                Elements links3 = link3.select("TD");
                                String item_info[] = new String[25];
                                int k = 0;
                                for (Element link4 : links3) {
                                    item_info[k++] = link4.text();
                                }
                                if(item_info[2] == no[i]){
                                    remain_seats[i] = item_info[15];
                                }
                            }

                            links2 = doc2.select("TR[class='course_y3']");
                            for (Element link3 : links2) {
                                Elements links3 = link3.select("TD");
                                String item_info[] = new String[25];
                                int k = 0;
                                for (Element link4 : links3) {
                                    item_info[k++] = link4.text();
                                }
                                if(item_info[2] == no[i]){
                                    remain_seats[i] = item_info[15];
                                }
                            }

                            links2 = doc2.select("TR[class='course_y4']");
                            for (Element link3 : links2) {
                                Elements links3 = link3.select("TD");
                                String item_info[] = new String[25];
                                int k = 0;
                                for (Element link4 : links3) {
                                    item_info[k++] = link4.text();
                                }
                                if(item_info[2] == no[i]){
                                    remain_seats[i] = item_info[15];
                                }
                            }
                        }
                        SharedPreferences setsh = getSharedPreferences("sharedprefs",MODE_PRIVATE);
                        SharedPreferences.Editor edit = setsh.edit();
                        rs = "";
                        for(int i=0;i<remain_seats.length;++i){
                            if(!remain_seats[i].isEmpty()){
                                if(rs.isEmpty())
                                    rs = remain_seats[i];
                                else
                                    rs = rs + "," + remain_seats[i];
                            }
                        }
                        edit.putString("remain_seats",rs);
                        edit.commit();
                    }
                }catch(IOException e) {
                    s1_array_list.add("ERROR");
                }
            }
        });
        th.start();

        Runnable runTimerStop = new Runnable()
        {
            @Override
            public void run()
            {
                loading = false;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment(s1)).commitAllowingStateLoss();
            }
        };
        Handler myHandler = new Handler();
        myHandler.postDelayed(runTimerStop,8*1000);

        try{
            th.join();
        }catch(InterruptedException e){
            Log.d("TAG", "onCreate: "+e.getMessage());
        }
    }

}
