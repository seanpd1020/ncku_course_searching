package com.example.user.ncku_course_search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by User on 2019/5/15.
 */

public class MyOwnAdapter extends RecyclerView.Adapter<MyOwnAdapter.MyOwnHolder> {
    String data1[];
    Context ctx;
    public static final String EXTRA_TARGET_DEPARTMENT_CODE = "com.example.user.ncku_course_search.MainActivity.extra.TARGET_DEPARTMENT_CODE";

    public MyOwnAdapter(Context ct, String[] s1){
        ctx = ct;
        data1 = s1;
    }

    @Override
    public MyOwnHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater myInflator = LayoutInflater.from(ctx);
        View myOwnView = myInflator.inflate(R.layout.my_row,parent,false);
        return new MyOwnHolder(myOwnView);
    }

    @Override
    public void onBindViewHolder(MyOwnHolder holder, int position) {
        holder.t1.setText(data1[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyOwnHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView t1;
        public MyOwnHolder(View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.text1);
            //Typeface type = Typeface.createFromAsset(t1.getContext().getAssets(), "newfont.ttf");
            //t1.setTypeface(type);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            int mPosition = getLayoutPosition();
            String element = data1[mPosition];
            Intent intent = new Intent(ctx,CourseDetail.class);
            intent.putExtra(EXTRA_TARGET_DEPARTMENT_CODE,element);
            ctx.startActivity(intent);
        }
    }
}
