package com.example.user.ncku_course_search;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.Random;

/**
 * Created by User on 2019/5/15.
 */

public class NotificationsFragment extends Fragment {
    RecyclerView r1;
    Button refresh_button;
    String dep_name[],no[],remain_seats[],course_name[];
    TrackAdapter ad;
    TextView your_track;
    ToggleButton tb;
    private String search = "http://course-query.acad.ncku.edu.tw/qry/qry001.php?dept_no=";
    public View view_fragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        view_fragment = inflater.inflate(R.layout.fragment_notifications,container,false);

        SharedPreferences getsh2 = getContext().getSharedPreferences("toggleSharedPreferences",Context.MODE_APPEND);
        boolean ch = getsh2.getBoolean("toggle",false);
        tb = view_fragment.findViewById(R.id.toggleButton);
        tb.setChecked(ch);
        tb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences setsh = getContext().getSharedPreferences("toggleSharedPreferences",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = setsh.edit();
                if(tb.isChecked()){
                    edit.putBoolean("toggle",true);
                    Intent intent = new Intent(getActivity(),TrackingRemainSeatsService.class);
                    intent.putExtra("time",50);
                    getActivity().startService(intent);
                }
                else{
                    edit.putBoolean("toggle",false);
                }
                edit.commit();
            }
        });
        your_track = view_fragment.findViewById(R.id.texttext);
        your_track.setText("你的追蹤(๑•̀ω•́)ノ");
        SharedPreferences getsh = this.getActivity().getSharedPreferences("sharedprefs", Context.MODE_APPEND);
        String dp = getsh.getString("dep_code","");
        String n = getsh.getString("no","");
        String rs = getsh.getString("remain_seats","");
        String cn = getsh.getString("course_name","");
        dep_name = dp.split(",");
        no = n.split(",");
        remain_seats = rs.split(",");
        course_name = cn.split(",");

        if(dep_name[0].isEmpty())
            return view_fragment;
        refresh_button = view_fragment.findViewById(R.id.refresh_button);
        refresh_button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            //update SharedPreferences
                            SharedPreferences getsh = getContext().getSharedPreferences("sharedprefs", Context.MODE_APPEND);
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
                                        if(item_info[2].contains(no[i])){
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
                                        if(item_info[2].contains(no[i])){
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
                                        if(item_info[2].contains(no[i])){
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
                                        if(item_info[2].contains(no[i])){
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
                                        if(item_info[2].contains(no[i])){
                                            remain_seats[i] = item_info[15];
                                        }
                                    }
                                }
                                SharedPreferences setsh = getContext().getSharedPreferences("sharedprefs",Context.MODE_PRIVATE);
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
                            else{
                                Toast.makeText(getActivity(),"沒東西要更新ㄚo..O",Toast.LENGTH_SHORT).show();
                            }
                        }catch(IOException e) {
                            Log.d("TAG", "onCreateView: "+e.getMessage());
                        }
                    }
                });
                th.start();
                try{
                    th.join();
                }catch(InterruptedException e){
                    Log.d("TAG", "onCreateView: "+e.getMessage());
                }

                SharedPreferences getsh = getContext().getSharedPreferences("sharedprefs", Context.MODE_APPEND);
                String dp = getsh.getString("dep_code","");
                String n = getsh.getString("no","");
                String rs = getsh.getString("remain_seats","");
                String cn = getsh.getString("course_name","");
                dep_name = dp.split(",");
                no = n.split(",");
                remain_seats = rs.split(",");
                course_name = cn.split(",");

                if(dep_name[0].isEmpty())
                    return;

                r1 = view_fragment.findViewById(R.id.trackRecycler);
                ad = new TrackAdapter(getContext(),dep_name,no,remain_seats,course_name);
                r1.setAdapter(ad);
                r1.setLayoutManager(new LinearLayoutManager(getContext()));
                DividerItemDecoration divider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
                divider.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.my_divider));
                r1.addItemDecoration(divider);
                ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder dragged, RecyclerView.ViewHolder target) {
                        int position_dragged = dragged.getAdapterPosition();
                        int position_target = target.getAdapterPosition();
                        String temp;
                        temp = dep_name[position_dragged];
                        dep_name[position_dragged] = dep_name[position_target];
                        dep_name[position_target] = temp;
                        temp = no[position_dragged];
                        no[position_dragged] = no[position_target];
                        no[position_target] = temp;
                        temp = remain_seats[position_dragged];
                        remain_seats[position_dragged] = remain_seats[position_target];
                        remain_seats[position_target] = temp;
                        temp = course_name[position_dragged];
                        course_name[position_dragged] = course_name[position_target];
                        course_name[position_target] = temp;
                        Log.v("timer1","moveItem");
                        /*SharedPreferences setsh = getContext().getSharedPreferences("sharedprefs",Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = setsh.edit();
                        String t = "";
                        for(int k=0;k<dep_name.length;++k){
                            if(dep_name[k].isEmpty())
                                t = dep_name[k];
                            else
                                t = t + "," + dep_name[k];
                        }
                        edit.putString("dep_code",t);
                        t = "";
                        for(int k=0;k<no.length;++k){
                            if(no[k].isEmpty())
                                t = no[k];
                            else
                                t = t + "," + no[k];
                        }
                        edit.putString("no",t);
                        t = "";
                        for(int k=0;k<remain_seats.length;++k){
                            if(remain_seats[k].isEmpty())
                                t = remain_seats[k];
                            else
                                t = t + "," + remain_seats[k];
                        }
                        edit.putString("remain_seats",t);
                        t = "";
                        for(int k=0;k<course_name.length;++k){
                            if(course_name[k].isEmpty())
                                t = course_name[k];
                            else
                                t = t + "," + course_name[k];
                        }
                        edit.putString("course_name",t);
                        edit.commit();
                        Log.v("timer1","moveItem");
                        ad.notifyItemMoved(position_dragged,position_target);
                        */
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        ad.removeItem(viewHolder.getAdapterPosition());
                        SharedPreferences setsh = getContext().getSharedPreferences("sharedprefs",Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = setsh.edit();
                        String dp = "";String dep_code[] = ad.getDep_code();
                        String n = "";String no[] = ad.getNo();
                        String rs = "";String remain_seats[] = ad.getRemain_seats();
                        String cn = "";String course_name[] = ad.getCourse_name();
                        for(int i=0;i<dep_code.length;++i){
                            if(!dep_code[i].isEmpty()){
                                if(dp.isEmpty())
                                    dp = dep_code[i];
                                else
                                    dp = dp + "," + dep_code[i];
                                if(n.isEmpty())
                                    n = no[i];
                                else
                                    n = n + "," + no[i];
                                if(rs.isEmpty())
                                    rs = remain_seats[i];
                                else
                                    rs = rs + "," + remain_seats[i];
                                if(cn.isEmpty())
                                    cn = course_name[i];
                                else
                                    cn = cn + "," + course_name[i];
                            }
                        }
                        edit.putString("dep_code",dp);
                        edit.putString("no",n);
                        edit.putString("remain_seats",rs);
                        edit.putString("course_name",cn);
                        edit.commit();
                        ad.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                });
                helper.attachToRecyclerView(r1);

                Random r = new Random();
                int randnum = r.nextInt(3);
                if(randnum == 0)Toast.makeText(getActivity(),"更新完成(｢･ω･)｢",Toast.LENGTH_SHORT).show();
                    //your_track.setText("更新完成(｢･ω･)｢");
                else if(randnum == 1)Toast.makeText(getActivity(),"更新好了(ﾟд⊙)",Toast.LENGTH_SHORT).show();
                    //your_track.setText("更新好了(ﾟд⊙)");
                else Toast.makeText(getActivity(),"更新完畢(ゝ∀･)b",Toast.LENGTH_SHORT).show();
                    //your_track.setText("更新完畢(ゝ∀･)b");

            }

        });
        r1 = view_fragment.findViewById(R.id.trackRecycler);
        ad = new TrackAdapter(getContext(),dep_name,no,remain_seats,course_name);
        r1.setAdapter(ad);
        r1.setLayoutManager(new LinearLayoutManager(getContext()));
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder dragged, RecyclerView.ViewHolder target) {
                int position_dragged = dragged.getAdapterPosition();
                int position_target = target.getAdapterPosition();
                String temp;
                temp = dep_name[position_dragged];
                dep_name[position_dragged] = dep_name[position_target];
                dep_name[position_target] = temp;
                temp = no[position_dragged];
                no[position_dragged] = no[position_target];
                no[position_target] = temp;
                temp = remain_seats[position_dragged];
                remain_seats[position_dragged] = remain_seats[position_target];
                remain_seats[position_target] = temp;
                temp = course_name[position_dragged];
                course_name[position_dragged] = course_name[position_target];
                course_name[position_target] = temp;
                ad.notifyItemMoved(position_dragged,position_target);
                /*SharedPreferences setsh = getContext().getSharedPreferences("sharedprefs",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = setsh.edit();
                String t = "";
                for(int k=0;k<dep_name.length;++k){
                    if(dep_name[k].isEmpty())
                        t = dep_name[k];
                    else
                        t = t + "," + dep_name[k];
                }
                edit.putString("dep_code",t);
                t = "";
                for(int k=0;k<no.length;++k){
                    if(no[k].isEmpty())
                        t = no[k];
                    else
                        t = t + "," + no[k];
                }
                edit.putString("no",t);
                t = "";
                for(int k=0;k<remain_seats.length;++k){
                    if(remain_seats[k].isEmpty())
                        t = remain_seats[k];
                    else
                        t = t + "," + remain_seats[k];
                }
                edit.putString("remain_seats",t);
                t = "";
                for(int k=0;k<course_name.length;++k){
                    if(course_name[k].isEmpty())
                        t = course_name[k];
                    else
                        t = t + "," + course_name[k];
                }
                edit.putString("course_name",t);
                edit.commit();
                Log.v("timer1","moveItem");
                ad.notifyItemMoved(position_dragged,position_target);*/
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                ad.removeItem(viewHolder.getAdapterPosition());
                SharedPreferences setsh = getContext().getSharedPreferences("sharedprefs",Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = setsh.edit();
                String dp = "";String dep_code[] = ad.getDep_code();
                String n = "";String no[] = ad.getNo();
                String rs = "";String remain_seats[] = ad.getRemain_seats();
                String cn = "";String course_name[] = ad.getCourse_name();
                for(int i=0;i<dep_code.length;++i){
                    if(!dep_code[i].isEmpty()){
                        if(dp.isEmpty())
                            dp = dep_code[i];
                        else
                            dp = dp + "," + dep_code[i];
                        if(n.isEmpty())
                            n = no[i];
                        else
                            n = n + "," + no[i];
                        if(rs.isEmpty())
                            rs = remain_seats[i];
                        else
                            rs = rs + "," + remain_seats[i];
                        if(cn.isEmpty())
                            cn = course_name[i];
                        else
                            cn = cn + "," + course_name[i];
                    }
                }
                edit.putString("dep_code",dp);
                edit.putString("no",n);
                edit.putString("remain_seats",rs);
                edit.putString("course_name",cn);
                edit.commit();
                ad.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        helper.attachToRecyclerView(r1);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.my_divider));
        r1.addItemDecoration(divider);

        return view_fragment;
    }
}
