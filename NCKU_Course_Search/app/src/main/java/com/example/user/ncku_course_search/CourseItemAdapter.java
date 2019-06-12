package com.example.user.ncku_course_search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.PropertyResourceBundle;

/**
 * Created by User on 2019/5/17.
 */

public class CourseItemAdapter extends RecyclerView.Adapter<CourseItemAdapter.CourseItemHolder>{
    CourseItem data[];
    Context ctx;
    public static final String EXTRA_COURSEITEM = "com.example.user.ncku_course_search.MainActivity.extra.COURSEITEM";

    public CourseItemAdapter(Context ct,CourseItem d[]){
        ctx = ct;
        data = d;
    }


    @Override
    public CourseItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater myInflater = LayoutInflater.from(ctx);
        View courseItemView = myInflater.inflate(R.layout.item_row,parent,false);
        return new CourseItemHolder(courseItemView);
    }

    @Override
    public void onBindViewHolder(CourseItemHolder holder, int position) {
        CourseItem target = data[position];
        holder.t1.setText("序號:"+target.no+" 班別:"+target.class_no+" 年級:"+target.grade);
        holder.t2.setText(target.course_name);
        holder.t3.setText(target.compulsory+"\n學分:"+target.credit);
        holder.t4.setText("已選人數:"+target.selected_seats+" 時間:"+target.times);
        holder.t5.setText("餘額:"+target.remain_seats);
        if(target.dep_code.contains("A9"))
            holder.t6.setText("["+target.category+"]");
        else
            holder.t6.setText("");
        if(target.remain_seats.contains("額滿"))
            holder.t5.setTextColor(Color.GRAY);
        else if(target.remain_seats.contains("洽師培中心") || target.remain_seats.contains("洽系辦") || target.remain_seats.contains("洽不分系學程"))
            holder.t5.setTextColor(Color.DKGRAY);
        else if(Integer.parseInt(target.remain_seats) < 10)
            holder.t5.setTextColor(Color.RED);
        else if(Integer.parseInt(target.remain_seats) < 50)
            holder.t5.setTextColor(Color.MAGENTA);
        else
            holder.t5.setTextColor(Color.GREEN);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class CourseItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView t1,t2,t3,t4,t5,t6;
        public CourseItemHolder(View itemView){
            super(itemView);
            t1 = (TextView)itemView.findViewById(R.id.t1);
            t2 = (TextView)itemView.findViewById(R.id.t2);
            t3 = (TextView)itemView.findViewById(R.id.t3);
            t4 = (TextView)itemView.findViewById(R.id.t4);
            t5 = (TextView)itemView.findViewById(R.id.t5);
            t6 = (TextView)itemView.findViewById(R.id.t6);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            CourseItem element = data[mPosition];
            Intent intent = new Intent(ctx, ItemDetail.class);
            intent.putExtra(EXTRA_COURSEITEM, element);
            ctx.startActivity(intent);
        }
    }
}
