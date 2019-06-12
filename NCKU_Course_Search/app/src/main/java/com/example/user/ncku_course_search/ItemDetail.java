package com.example.user.ncku_course_search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;

import static com.example.user.ncku_course_search.CourseItemAdapter.EXTRA_COURSEITEM;

public class ItemDetail extends AppCompatActivity {
    TextView dep_name,dep_code,no,course_system_code,class_code,class_no,grade,category,group,en_teaching,course_name,compulsory;
    TextView teacher_name,selected_seats,remain_seats,times,classroom,remarks,restricted_condition,exp_involved,course_attr_code;
    TextView cross_domain,moocs,credit;
    CheckBox checkBox,checkBox2;
    View decorView;
    ScrollView scrollview;
    Button bt_nckuhub;
    String EXTRA_COURSE_NAME = "coursename";
    float downX,screen_width,screen_height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        //設定隱藏標題
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        bt_nckuhub = findViewById(R.id.button_nckuhub);

        scrollview = findViewById(R.id.scroll);

        scrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN)
                    downX = motionEvent.getX();
                else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                    float moveDistanceX = motionEvent.getX() - downX;
                    if(moveDistanceX > 0){
                        decorView.setX(moveDistanceX);
                    }
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    float moveDistanceX = motionEvent.getX() - downX;
                    if(moveDistanceX > screen_width / 2){
                        //finish();
                        continueMove(moveDistanceX);
                    }else{
                        //decorView.setX(0);
                        if(moveDistanceX > 0)
                            rebackToLeft(moveDistanceX);
                    }
                }
                return false;
            }
        });
        decorView = getWindow().getDecorView();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screen_width = metrics.widthPixels;
        screen_height = metrics.heightPixels;

        checkBox = findViewById(R.id.checkbox);
        checkBox2 = findViewById(R.id.checkbox2);
        dep_name = findViewById(R.id.dep_name);
        dep_code = findViewById(R.id.dep_code);
        no = findViewById(R.id.no);
        course_system_code = findViewById(R.id.course_system_code);
        class_code = findViewById(R.id.class_code);
        class_no = findViewById(R.id.class_no);
        grade = findViewById(R.id.grade);
        category = findViewById(R.id.category);
        group = findViewById(R.id.group);
        en_teaching = findViewById(R.id.en_teaching);
        course_name = findViewById(R.id.course_name);
        compulsory = findViewById(R.id.compulsory);
        teacher_name = findViewById(R.id.teacher_name);
        selected_seats = findViewById(R.id.selected_seats);
        remain_seats = findViewById(R.id.remain_seats);
        times = findViewById(R.id.times);
        classroom = findViewById(R.id.classroom);
        remarks = findViewById(R.id.remarks);
        restricted_condition = findViewById(R.id.restricted_condition);
        exp_involved = findViewById(R.id.exp_involved);
        course_attr_code = findViewById(R.id.course_attr_code);
        cross_domain = findViewById(R.id.cross_domain);
        moocs = findViewById(R.id.moocs);
        credit = findViewById(R.id.credit);

        Intent intent = getIntent();
        CourseItem item = intent.getParcelableExtra(EXTRA_COURSEITEM);
        String s[] = item.dep_name.split(" ");
        dep_name.setText(s[0]);
        dep_code.setText("系號:"+item.dep_code);
        no.setText("序號:"+item.no);
        if(item.no.isEmpty())checkBox.setVisibility(View.INVISIBLE);
        course_system_code.setText("課程系統碼:"+item.course_system_code);
        class_code.setText("分班碼:"+item.class_code);
        class_no.setText("班別:"+item.class_no);
        grade.setText("年級:"+item.grade);
        category.setText("類別:"+item.category);
        group.setText("組別:"+item.group);
        en_teaching.setText("英授:"+item.en_teaching);
        course_name.setText(item.course_name);
        compulsory.setText(item.compulsory);
        teacher_name.setText(item.teacher_name);
        selected_seats.setText("已選課人數:"+item.selected_seats);
        remain_seats.setText("餘額:"+item.remain_seats);
        times.setText(item.times);
        classroom.setText("教室:"+item.classroom);
        remarks.setText("備註:"+item.remarks);
        restricted_condition.setText("限選條件:"+item.restricted_condition);
        exp_involved.setText("業界專家參與:"+item.exp_involved);
        course_attr_code.setText("課程屬性碼:"+item.course_attr_code);
        cross_domain.setText("跨領域學分學程:"+item.cross_domain);
        moocs.setText("Moocs:"+item.moocs);
        credit.setText("學分:"+item.credit);

        String target = item.remain_seats;
        if(target.contains("額滿"))
            remain_seats.setTextColor(Color.GRAY);
        else if(target.contains("洽師培中心") || target.contains("洽系辦") || target.contains("洽不分系學程"))
            remain_seats.setTextColor(Color.DKGRAY);
        else if(Integer.parseInt(target) < 10)
            remain_seats.setTextColor(Color.RED);
        else if(Integer.parseInt(target) < 50)
            remain_seats.setTextColor(Color.MAGENTA);
        else
            remain_seats.setTextColor(Color.GREEN);

        SharedPreferences getsh = getSharedPreferences("sharedprefs",MODE_APPEND);
        String dp = getsh.getString("dep_code","");
        String n = getsh.getString("no","");
        String dep_code_list[] = dp.split(",");
        String no_list[] = n.split(",");

        boolean ch = true;
        for(int i=0;i<dep_code_list.length;++i){
            if(dep_code_list[i].contains(dep_code.getText().toString().split(":")[1])){
                if(no_list[i].contains(no.getText().toString().split(":")[1])){
                    checkBox.setChecked(true);
                    checkBox.setText("取消追蹤(゜皿。)");
                    ch = false;
                }
            }
        }
        if(ch){
            checkBox.setChecked(false);
            checkBox.setText("點擊追蹤(つ´ω`)つ");
        }
        getsh = getSharedPreferences("classtimetable",MODE_APPEND);
        String tcn = getsh.getString("timetable_course_name","");
        String tc = getsh.getString("timetable_classroom","");
        String timetable_course_name_list[] = tcn.split("/");
        String timetable_classroom_list[] = tc.split("/");

        boolean ch2 = true;
        for(int i=0;i<timetable_course_name_list.length;++i){
            if(timetable_course_name_list[i].contains(course_name.getText().toString())){
                if(classroom.getText().toString().split(":").length < 2){
                    if(timetable_classroom_list[i].contains("  ")){
                        checkBox2.setChecked(true);
                        checkBox2.setText("從課表移除ヾ(；ﾟ(OO)ﾟ)ﾉ");
                        ch2 = false;
                    }
                }
                else {
                    if (timetable_classroom_list[i].contains(classroom.getText().toString().split(":")[1])) {
                        checkBox2.setChecked(true);
                        checkBox2.setText("從課表移除ヾ(；ﾟ(OO)ﾟ)ﾉ");
                        ch2 = false;
                    }
                }
            }
        }
        if(ch2){
            checkBox2.setChecked(false);
            checkBox2.setText("加入課表ヽ(́◕◞౪◟◕‵)ﾉ");
        }
        bt_nckuhub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NCKUhubActivity.class);
                intent.putExtra(EXTRA_COURSE_NAME, course_name.getText().toString());
                startActivity(intent);
            }
        });
    }

    public void add_remove_classtimetable(View view){
        //get SharedPreferences
        SharedPreferences getsh = getSharedPreferences("classtimetable",MODE_APPEND);
        String tcn = getsh.getString("timetable_course_name","");
        String tt = getsh.getString("timetable_time","");
        String tc = getsh.getString("timetable_classroom","");
        //set SharedPreferences
        SharedPreferences setsh = getSharedPreferences("classtimetable",MODE_PRIVATE);
        SharedPreferences.Editor edit = setsh.edit();
        if(checkBox2.isChecked()){
            checkBox2.setText("從課表移除ヾ(；ﾟ(OO)ﾟ)ﾉ");

            if(tcn.isEmpty())
                edit.putString("timetable_course_name",course_name.getText().toString());
            else
                edit.putString("timetable_course_name",tcn + "/" + course_name.getText().toString());
            if(tt.isEmpty()) {
                edit.putString("timetable_time", times.getText().toString());
            }
            else {
                edit.putString("timetable_time", tt + "/" + times.getText().toString());
            }
            if(tc.isEmpty())
                if(classroom.getText().toString().split(":").length < 2)
                    edit.putString("timetable_classroom", "  ");
                else
                    edit.putString("timetable_classroom", classroom.getText().toString().split(":")[1]);
            else
                if(classroom.getText().toString().split(":").length < 2)
                    edit.putString("timetable_classroom",tc + "/" + "  ");
                else
                    edit.putString("timetable_classroom",tc + "/" + classroom.getText().toString().split(":")[1]);
        }
        else{
            checkBox2.setText("加入課表ヽ(́◕◞౪◟◕‵)ﾉ");

            String timetable_course_name_list[] = tcn.split("/");
            String timetable_time_list[] = tt.split("/");
            String timetable_classroom_list[] = tc.split("/");
            for(int i=0;i<timetable_course_name_list.length;++i){
                if(timetable_course_name_list[i].contains(course_name.getText().toString())){
                    if(classroom.getText().toString().split(":").length < 2){
                        if(timetable_classroom_list[i].contains("  ")){
                            timetable_course_name_list[i] = "";
                            timetable_time_list[i] = "";
                            timetable_classroom_list[i] = "  ";
                        }
                    }
                    else {
                        if (timetable_classroom_list[i].contains(classroom.getText().toString().split(":")[1])) {
                            timetable_course_name_list[i] = "";
                            timetable_time_list[i] = "";
                            timetable_classroom_list[i] = "  ";
                        }
                    }
                }
            }

            tcn = "";
            tt = "";
            tc = "";

            for(int i=0;i<timetable_course_name_list.length;++i){
                if(!timetable_course_name_list[i].isEmpty()){
                    if(tcn.isEmpty())
                        tcn = timetable_course_name_list[i];
                    else
                        tcn = tcn + "/" + timetable_course_name_list[i];
                    if(tt.isEmpty())
                        tt = timetable_time_list[i];
                    else
                        tt = tt + "/" + timetable_time_list[i];
                    if(tc.isEmpty())
                        tc = timetable_classroom_list[i];
                    else
                        tc = tc + "/" + timetable_classroom_list[i];
                }
            }
            edit.putString("timetable_course_name",tcn);
            edit.putString("timetable_time",tt);
            edit.putString("timetable_classroom",tc);
        }
        edit.commit();
    }

    public void track_untrack_course(View view) {
        //get SharedPreferences
        SharedPreferences getsh = getSharedPreferences("sharedprefs",MODE_APPEND);
        String dp = getsh.getString("dep_code","");
        String n = getsh.getString("no","");
        String rs = getsh.getString("remain_seats","");
        String cn = getsh.getString("course_name","");
        //set SharedPreferences
        SharedPreferences setsh = getSharedPreferences("sharedprefs",MODE_PRIVATE);
        SharedPreferences.Editor edit = setsh.edit();

        if(checkBox.isChecked()){
            checkBox.setText("取消追蹤(゜皿。)");

            if(dp.isEmpty())
                edit.putString("dep_code",dep_code.getText().toString().split(":")[1]);
            else
                edit.putString("dep_code",dp + "," + dep_code.getText().toString().split(":")[1]);
            if(n.isEmpty())
                edit.putString("no",no.getText().toString().split(":")[1]);
            else
                edit.putString("no",n + "," + no.getText().toString().split(":")[1]);
            if(rs.isEmpty())
                edit.putString("remain_seats",remain_seats.getText().toString().split(":")[1]);
            else
                edit.putString("remain_seats",rs + "," + remain_seats.getText().toString().split(":")[1]);
            if(cn.isEmpty())
                edit.putString("course_name",course_name.getText().toString());
            else
                edit.putString("course_name",cn + "," + course_name.getText().toString());
        }
        else{
            checkBox.setText("點擊追蹤(つ´ω`)つ");

            String dep_code_list[] = dp.split(",");
            String no_list[] = n.split(",");
            String remain_seats_list[] = rs.split(",");
            String course_name_list[] = cn.split(",");
            for(int i=0;i<dep_code_list.length;++i){
                if(dep_code_list[i].contains(dep_code.getText().toString().split(":")[1])){
                    if(no_list[i].contains(no.getText().toString().split(":")[1])){
                        dep_code_list[i] = "";
                        no_list[i] = "";
                        remain_seats_list[i] = "";
                        course_name_list[i] = "";
                    }
                }
            }

            dp = "";
            n = "";
            rs = "";
            cn = "";

            for(int i=0;i<dep_code_list.length;++i){
                if(!dep_code_list[i].isEmpty()){
                    if(dp.isEmpty())
                        dp = dep_code_list[i];
                    else
                        dp = dp + "," + dep_code_list[i];
                    if(n.isEmpty())
                        n = no_list[i];
                    else
                        n = n + "," + no_list[i];
                    if(rs.isEmpty())
                        rs = remain_seats_list[i];
                    else
                        rs = rs + "," + remain_seats_list[i];
                    if(cn.isEmpty())
                        cn = course_name_list[i];
                    else
                        cn = cn + "," + course_name_list[i];
                }
            }
            edit.putString("dep_code",dp);
            edit.putString("no",n);
            edit.putString("remain_seats",rs);
            edit.putString("course_name",cn);
        }
        edit.commit();
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
