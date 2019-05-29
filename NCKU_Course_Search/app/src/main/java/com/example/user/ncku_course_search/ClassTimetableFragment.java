package com.example.user.ncku_course_search;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import static android.content.Context.MODE_APPEND;


/**
 * Created by User on 2019/5/19.
 */

public class ClassTimetableFragment extends Fragment {
    private int gridHeight,gridWidth;
    private RelativeLayout layout;
    private RelativeLayout tmpLayout;
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.fragment_classtimetable,container,false);

        final Button bt = v.findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridWidth = tmpLayout.getWidth();
                gridHeight = tmpLayout.getHeight()/18;

                SharedPreferences getsh = getContext().getSharedPreferences("classtimetable",MODE_APPEND);
                String tcn = getsh.getString("timetable_course_name","");
                String tt = getsh.getString("timetable_time","");
                String tc = getsh.getString("timetable_classroom","");
                String tcn_list[] = tcn.split("/");
                String tt_list[] = tt.split("/");
                String tc_list[] = tc.split("/");
                if(!tc.isEmpty()) {
                    int time_not_defined_column = 1, time_not_defined_row = 17;
                    for (int k = 0; k < tc_list.length; ++k) {
                        if (tt_list[k].contains("未定")) {
                            addView(time_not_defined_column, time_not_defined_row, time_not_defined_row, tcn_list[k] + "(" + tc_list[k] + ")");
                            time_not_defined_column++;
                            if (time_not_defined_column == 6 && time_not_defined_row == 17) {
                                time_not_defined_column = 1;
                                time_not_defined_row = 18;
                            } else if (time_not_defined_column == 6 && time_not_defined_row == 18) {
                                continue;
                            }
                        } else {
                            String time_array[] = tt_list[k].split(" ");
                            String pass_time[] = new String[10];
                            int j=0;int second_i=0;
                            for (int i = 0; i < time_array.length; ++i) {
                                if (time_array[i].length() == 6) {
                                    pass_time[j++] = time_array[i].substring(1,2);
                                    pass_time[j++] = time_array[i].substring(3,4);
                                    pass_time[j++] = time_array[i].substring(5,6);
                                    //addView2(Integer.parseInt(time_array[i].substring(1, 2)), time_array[i].substring(3, 4), time_array[i].substring(5, 6), tcn_list[k] + "(" + tc_list[k] + ")");
                                } else {
                                    pass_time[j++] = time_array[i].substring(1,2);
                                    pass_time[j++] = time_array[i].substring(3,4);
                                    pass_time[j++] = time_array[i].substring(3,4);
                                    //addView2(Integer.parseInt(time_array[i].substring(1, 2)), time_array[i].substring(3, 4), time_array[i].substring(3, 4), tcn_list[k] + "(" + tc_list[k] + ")");
                                }
                                second_i = Integer.parseInt(time_array[i].substring(1,2));
                            }
                            if(time_array.length == 1){
                                pass_time[j++] = "1";
                                pass_time[j++] = "0";
                                pass_time[j++] = "0";
                            }
                            addView2(Integer.parseInt(pass_time[0]),pass_time[1],pass_time[2],second_i,pass_time[4],pass_time[5],tcn_list[k] + "(" + tc_list[k] + ")");
                        }
                    }
                }
            }
        });
        tmpLayout = (RelativeLayout) v.findViewById(R.id.Monday);



        Runnable runTimerStop = new Runnable()
        {
            @Override
            public void run()
            {

            }
        };
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                gridWidth = tmpLayout.getWidth();
                gridHeight = tmpLayout.getHeight()/18;

                SharedPreferences getsh = getContext().getSharedPreferences("classtimetable",MODE_APPEND);
                String tcn = getsh.getString("timetable_course_name","");
                String tt = getsh.getString("timetable_time","");
                String tc = getsh.getString("timetable_classroom","");
                String tcn_list[] = tcn.split("/");
                String tt_list[] = tt.split("/");
                String tc_list[] = tc.split("/");
                if(!tc.isEmpty()) {
                    int time_not_defined_column = 1, time_not_defined_row = 17;
                    for (int k = 0; k < tc_list.length; ++k) {
                        if (tt_list[k].contains("未定")) {
                            addView(time_not_defined_column, time_not_defined_row, time_not_defined_row, tcn_list[k] + "(" + tc_list[k] + ")");
                            time_not_defined_column++;
                            if (time_not_defined_column == 6 && time_not_defined_row == 17) {
                                time_not_defined_column = 1;
                                time_not_defined_row = 18;
                            } else if (time_not_defined_column == 6 && time_not_defined_row == 18) {
                                continue;
                            }
                        } else {
                            String time_array[] = tt_list[k].split(" ");
                            String pass_time[] = new String[10];
                            int j=0;int second_i=0;
                            for (int i = 0; i < time_array.length; ++i) {
                                if (time_array[i].length() == 6) {
                                    pass_time[j++] = time_array[i].substring(1,2);
                                    pass_time[j++] = time_array[i].substring(3,4);
                                    pass_time[j++] = time_array[i].substring(5,6);
                                    //addView2(Integer.parseInt(time_array[i].substring(1, 2)), time_array[i].substring(3, 4), time_array[i].substring(5, 6), tcn_list[k] + "(" + tc_list[k] + ")");
                                } else {
                                    pass_time[j++] = time_array[i].substring(1,2);
                                    pass_time[j++] = time_array[i].substring(3,4);
                                    pass_time[j++] = time_array[i].substring(3,4);
                                    //addView2(Integer.parseInt(time_array[i].substring(1, 2)), time_array[i].substring(3, 4), time_array[i].substring(3, 4), tcn_list[k] + "(" + tc_list[k] + ")");
                                }
                                second_i = Integer.parseInt(time_array[i].substring(1,2));
                            }
                            if(time_array.length == 1){
                                pass_time[j++] = "1";
                                pass_time[j++] = "0";
                                pass_time[j++] = "0";
                            }
                            addView2(Integer.parseInt(pass_time[0]),pass_time[1],pass_time[2],second_i,pass_time[4],pass_time[5],tcn_list[k] + "(" + tc_list[k] + ")");
                        }
                    }
                }
            }
        };
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        });
        th.start();
        return v;
    }
    private TextView createTv2(String start, String end, String text){
        int start_index,end_index;
        if(start.contains("N"))
            start_index = 6;
        else if(start.contains("A"))
            start_index = 12;
        else if(start.contains("B"))
            start_index = 13;
        else if(start.contains("C"))
            start_index = 14;
        else if(start.contains("D"))
            start_index = 15;
        else if(start.contains("E"))
            start_index = 16;
        else if(Integer.parseInt(start) >=1 && Integer.parseInt(start) <=4)
            start_index = Integer.parseInt(start)+1;
        else
            start_index = Integer.parseInt(start)+2;
        if(end.contains("N"))
            end_index = 6;
        else if(end.contains("A"))
            end_index = 12;
        else if(end.contains("B"))
            end_index = 13;
        else if(end.contains("C"))
            end_index = 14;
        else if(end.contains("D"))
            end_index = 15;
        else if(end.contains("E"))
            end_index = 16;
        else if(Integer.parseInt(end) >=1 && Integer.parseInt(end) <=4)
            end_index = Integer.parseInt(end)+1;
        else
            end_index = Integer.parseInt(end)+2;

        TextView tv = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridWidth,gridHeight*(end_index-start_index+1));
        tv.setY(gridHeight*(start_index-1));
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        tv.setText(text);
        return tv;
    }
    private void addView2(int i,String start1,String end1,int i2,String start2,String end2,String text){
        TextView tv,tv2;
        switch (i){
            case 1:
                layout = (RelativeLayout) v.findViewById(R.id.Monday);
                break;
            case 2:
                layout = (RelativeLayout) v.findViewById(R.id.Tuesday);
                break;
            case 3:
                layout = (RelativeLayout) v.findViewById(R.id.Wednesday);
                break;
            case 4:
                layout = (RelativeLayout) v.findViewById(R.id.Thursday);
                break;
            case 5:
                layout = (RelativeLayout) v.findViewById(R.id.Friday);
                break;
        }
        tv= createTv2(start1,end1,text);
        Random r = new Random();
        int red = r.nextInt(200),green = r.nextInt(200),blue = r.nextInt(200);
        tv.setBackgroundColor(Color.argb(80,red,green,blue));
        layout.addView(tv);
        switch (i2){
            case 1:
                layout = (RelativeLayout) v.findViewById(R.id.Monday);
                break;
            case 2:
                layout = (RelativeLayout) v.findViewById(R.id.Tuesday);
                break;
            case 3:
                layout = (RelativeLayout) v.findViewById(R.id.Wednesday);
                break;
            case 4:
                layout = (RelativeLayout) v.findViewById(R.id.Thursday);
                break;
            case 5:
                layout = (RelativeLayout) v.findViewById(R.id.Friday);
                break;
        }
        tv2=createTv2(start2,end2,text);
        tv2.setBackgroundColor(Color.argb(80,red,green,blue));
        if(!(start2.contains("0") && end2.contains("0")))
            layout.addView(tv2);
    }

    private TextView createTv(int start, int end, String text){
        TextView tv = new TextView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridWidth,gridHeight*(end-start+1));
        tv.setY(gridHeight*(start-1));
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        tv.setText(text);
        return tv;
    }
    private void addView(int i,int start,int end,String text){
        TextView tv;
        switch (i){
            case 1:
                layout = (RelativeLayout) v.findViewById(R.id.Monday);
                break;
            case 2:
                layout = (RelativeLayout) v.findViewById(R.id.Tuesday);
                break;
            case 3:
                layout = (RelativeLayout) v.findViewById(R.id.Wednesday);
                break;
            case 4:
                layout = (RelativeLayout) v.findViewById(R.id.Thursday);
                break;
            case 5:
                layout = (RelativeLayout) v.findViewById(R.id.Friday);
                break;
        }
        tv= createTv(start,end,text);
        Random r = new Random();
        tv.setBackgroundColor(Color.argb(80,r.nextInt(200),r.nextInt(200),r.nextInt(200)));
        layout.addView(tv);
    }
}
