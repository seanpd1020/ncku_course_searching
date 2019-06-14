package com.example.user.ncku_course_search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.user.ncku_course_search.MyOwnAdapter.EXTRA_TARGET_DEPARTMENT_CODE;

public class CourseDetail extends AppCompatActivity {
    RecyclerView r1;
    CourseItemAdapter ad;
    CourseItem items[];
    List<CourseItem> item_array_list = new ArrayList<CourseItem>();
    View decorView;
    ScrollLinearLayoutManager mscrolllinearlayoutmanager;
    float downX,screen_width,screen_height;
    private TextView textView;
    private String home = "http://course-query.acad.ncku.edu.tw/qry/qry001.php?dept_no=";
    private String target;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        //設定隱藏標題
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        decorView = getWindow().getDecorView();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screen_width = metrics.widthPixels;
        screen_height = metrics.heightPixels;

        Intent intent = getIntent();
        String title = intent.getStringExtra(EXTRA_TARGET_DEPARTMENT_CODE);
        textView = findViewById(R.id.textView2);
        textView.setText(title);
        target = title.substring(2,4);

        HomeFragment.pb.setVisibility(View.INVISIBLE);

        //get target course search
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Document doc2 = Jsoup.connect(home+target).execute().parse();
                    Elements links2 = doc2.select("TR[class='course_y1']");
                    for(Element link3 : links2){
                        Elements links3 = link3.select("TD");
                        String item_info[] = new String[25];
                        int i=0;
                        for(Element link4 : links3){
                            item_info[i++] = link4.text();
                        }
                        item_array_list.add(new CourseItem(item_info[0],item_info[1],item_info[2],item_info[3],item_info[4],item_info[5],
                                item_info[6],item_info[7],item_info[8],item_info[9],item_info[10],item_info[11],item_info[12],item_info[13],
                                item_info[14],item_info[15],item_info[16],item_info[17],item_info[18],item_info[19],item_info[20],item_info[21],
                                item_info[22],item_info[23]));
                    }

                    links2 = doc2.select("TR[class='course_y0']");
                    for(Element link3 : links2){
                        Elements links3 = link3.select("TD");
                        String item_info[] = new String[25];
                        int i=0;
                        for(Element link4 : links3){
                            item_info[i++] = link4.text();
                        }
                        item_array_list.add(new CourseItem(item_info[0],item_info[1],item_info[2],item_info[3],item_info[4],item_info[5],
                                item_info[6],item_info[7],item_info[8],item_info[9],item_info[10],item_info[11],item_info[12],item_info[13],
                                item_info[14],item_info[15],item_info[16],item_info[17],item_info[18],item_info[19],item_info[20],item_info[21],
                                item_info[22],item_info[23]));
                    }

                    links2 = doc2.select("TR[class='course_y2']");
                    for(Element link3 : links2){
                        Elements links3 = link3.select("TD");
                        String item_info[] = new String[25];
                        int i=0;
                        for(Element link4 : links3){
                            item_info[i++] = link4.text();
                        }
                        item_array_list.add(new CourseItem(item_info[0],item_info[1],item_info[2],item_info[3],item_info[4],item_info[5],
                                item_info[6],item_info[7],item_info[8],item_info[9],item_info[10],item_info[11],item_info[12],item_info[13],
                                item_info[14],item_info[15],item_info[16],item_info[17],item_info[18],item_info[19],item_info[20],item_info[21],
                                item_info[22],item_info[23]));
                    }

                    links2 = doc2.select("TR[class='course_y3']");
                    for(Element link3 : links2){
                        Elements links3 = link3.select("TD");
                        String item_info[] = new String[25];
                        int i=0;
                        for(Element link4 : links3){
                            item_info[i++] = link4.text();
                        }
                        item_array_list.add(new CourseItem(item_info[0],item_info[1],item_info[2],item_info[3],item_info[4],item_info[5],
                                item_info[6],item_info[7],item_info[8],item_info[9],item_info[10],item_info[11],item_info[12],item_info[13],
                                item_info[14],item_info[15],item_info[16],item_info[17],item_info[18],item_info[19],item_info[20],item_info[21],
                                item_info[22],item_info[23]));
                    }

                    links2 = doc2.select("TR[class='course_y4']");
                    for(Element link3 : links2){
                        Elements links3 = link3.select("TD");
                        String item_info[] = new String[25];
                        int i=0;
                        for(Element link4 : links3){
                            item_info[i++] = link4.text();
                        }
                        item_array_list.add(new CourseItem(item_info[0],item_info[1],item_info[2],item_info[3],item_info[4],item_info[5],
                                item_info[6],item_info[7],item_info[8],item_info[9],item_info[10],item_info[11],item_info[12],item_info[13],
                                item_info[14],item_info[15],item_info[16],item_info[17],item_info[18],item_info[19],item_info[20],item_info[21],
                                item_info[22],item_info[23]));
                    }
                    items = new CourseItem[item_array_list.size()];
                    item_array_list.toArray(items);
                }catch (IOException e){
                    Log.d("error55555", "run: "+e.getMessage());
                }
                /*
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //textView.setText(builder.toString());
                    }
                });*/
            }
        });
        th.start();

        try{
            th.join(10000);
        }catch (InterruptedException e){
            Log.d("error66666", "run: "+e.getMessage());
        }

        r1 = (RecyclerView)findViewById(R.id.ItemRecyclerView);
        ad = new CourseItemAdapter(getApplicationContext(),items);
        r1.setAdapter(ad);
        mscrolllinearlayoutmanager = new ScrollLinearLayoutManager(getApplicationContext());
        r1.setLayoutManager(mscrolllinearlayoutmanager);
        DividerItemDecoration divider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this,R.drawable.my_divider));
        r1.addItemDecoration(divider);
        r1.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                if(e.getAction() == MotionEvent.ACTION_DOWN) {
                    downX = e.getX();
                    //rv.setNestedScrollingEnabled(false);
                }
                else if(e.getAction() == MotionEvent.ACTION_MOVE){
                    float moveDistanceX = e.getX() - downX;
                    if(moveDistanceX > 10){
                        mscrolllinearlayoutmanager.setmCanVerticalScroll(false);
                        decorView.setX(moveDistanceX);
                    }
                }else if(e.getAction() == MotionEvent.ACTION_UP){
                    mscrolllinearlayoutmanager.setmCanVerticalScroll(true);
                    float moveDistanceX = e.getX() - downX;
                    if(moveDistanceX > screen_width / 2){
                        //finish();
                        continueMove(moveDistanceX);
                    }else{
                        //decorView.setX(0);
                        if(moveDistanceX > 0)
                            rebackToLeft(moveDistanceX);
                    }
                    //rv.setNestedScrollingEnabled(true);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN)
            downX = event.getX();
        else if(event.getAction() == MotionEvent.ACTION_MOVE){
            float moveDistanceX = event.getX() - downX;
            if(moveDistanceX > 10){
                mscrolllinearlayoutmanager.setmCanVerticalScroll(false);
                decorView.setX(moveDistanceX);
            }
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            mscrolllinearlayoutmanager.setmCanVerticalScroll(true);
            float moveDistanceX = event.getX() - downX;
            if(moveDistanceX > screen_width / 2){
                //finish();
                continueMove(moveDistanceX);
            }else{
                //decorView.setX(0);
                if(moveDistanceX > 0)
                    rebackToLeft(moveDistanceX);
            }
        }
        return super.onTouchEvent(event);
    }

    private void continueMove(float moveDistanceX){
        ValueAnimator anim = ValueAnimator.ofFloat(moveDistanceX, screen_width);
        anim.setDuration(500);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) (animation.getAnimatedValue());
                decorView.setX(x);
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }
        });
    }
    private void rebackToLeft(float moveDistanceX){
        ObjectAnimator.ofFloat(decorView, "X", moveDistanceX, 0).setDuration(300).start();
    }
}

