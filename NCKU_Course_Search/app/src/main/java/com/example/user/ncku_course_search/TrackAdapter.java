package com.example.user.ncku_course_search;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by User on 2019/5/18.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackHolder> {
    Context ctx;
    String dep_code[],no[],remain_seats[],course_name[];
    public TrackAdapter(Context ct,String s1[],String s2[],String s3[],String s4[]){
        ctx = ct;
        dep_code = s1;
        no = s2;
        remain_seats = s3;
        course_name = s4;
    }

    public String[] getDep_code(){return dep_code;}
    public String[] getNo(){return no;}
    public String[] getRemain_seats(){return remain_seats;}
    public String[] getCourse_name(){return course_name;}

    @Override
    public TrackHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View TrackView = myInflater.inflate(R.layout.track_row,parent,false);
        return new TrackHolder(TrackView);
    }

    @Override
    public void onBindViewHolder(TrackHolder holder, int position) {
        holder.dep_code_textview.setText(dep_code[position]);
        holder.no_textview.setText(no[position]);
        holder.remain_seats_textview.setText("餘額: " + remain_seats[position]);
        holder.course_name_textview.setText(course_name[position]);
        String target = remain_seats[position];
        if(target.contains("額滿"))
            holder.remain_seats_textview.setTextColor(Color.GRAY);
        else if(target.contains("洽師培中心") || target.contains("洽系辦") || target.contains("洽不分系學程"))
            holder.remain_seats_textview.setTextColor(Color.DKGRAY);
        else if(Integer.parseInt(target) < 10)
            holder.remain_seats_textview.setTextColor(Color.RED);
        else if(Integer.parseInt(target) < 50)
            holder.remain_seats_textview.setTextColor(Color.MAGENTA);
        else
            holder.remain_seats_textview.setTextColor(Color.GREEN);
    }

    @Override
    public int getItemCount() {
        return dep_code.length;
    }

    public class TrackHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView dep_code_textview,no_textview,remain_seats_textview,course_name_textview;
        public TrackHolder(View itemView) {
            super(itemView);
            dep_code_textview = itemView.findViewById(R.id.track_dep_code);
            no_textview = itemView.findViewById(R.id.track_no);
            remain_seats_textview = itemView.findViewById(R.id.track_remain_seats);
            course_name_textview = itemView.findViewById(R.id.track_course_name);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public void removeItem(int position){
        dep_code[position] = "";
        no[position] = "";
        remain_seats[position] = "";
        course_name[position] = "";

        String t1[] = new String[dep_code.length-1];
        String t2[] = new String[no.length-1];
        String t3[] = new String[remain_seats.length-1];
        String t4[] = new String[course_name.length-1];
        for(int i=0,j=0;i<dep_code.length;++i){
            if(!dep_code[i].isEmpty()) {
                t1[j] = dep_code[i];
                t2[j] = no[i];
                t3[j] = remain_seats[i];
                t4[j++] = course_name[i];
            }
        }
        dep_code = new String[t1.length];
        no = new String[t2.length];
        remain_seats = new String[t3.length];
        course_name = new String[t4.length];
        for(int i=0;i<t1.length;++i){
            dep_code[i] = t1[i];
            no[i] = t2[i];
            remain_seats[i] = t3[i];
            course_name[i] = t4[i];
        }
    }
}
