package com.example.user.ncku_course_search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2019/5/15.
 */

@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment {
    RecyclerView r1;
    String s1[];
    MyOwnAdapter ad;
    Spinner spinner;
    static ProgressBar pb;

    @SuppressLint("ValidFragment")
    public HomeFragment(String[] s){
        s1 = s;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        final View view = inflater.inflate(R.layout.fragment_home,container,false);
        pb = view.findViewById(R.id.pb);
        pb.setVisibility(View.INVISIBLE);
        spinner = view.findViewById(R.id.spinner);
        final ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(
                view.getContext(), R.array.spinner_array, android.R.layout.simple_list_item_1 );
        spinner.setAdapter(nAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(CourseDetail.this,nAdapter.getItem(i),Toast.LENGTH_SHORT).show();
                String newitems[];
                List<String> new_item_array_list = new ArrayList<String>();
                switch (nAdapter.getItem(i).toString()){
                    case "全部":
                        for(int k=0;k<s1.length;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    case "其他":
                        for(int k=0;k<7;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    case "非屬學院":
                        for(int k=7;k<9;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    case "通識課程":
                        for(int k=9;k<13;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    case "文學院":
                        for(int k=13;k<25;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    case "理學院":
                        for(int k=25;k<39;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    case "工學院":
                        for(int k=39;k<71;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    case "管理學院":
                        for(int k=71;k<91;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    case "醫學院":
                        for(int k=91;k<121;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    case "社會科學院":
                        for(int k=121;k<132;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    case "電機資訊學院":
                        for(int k=132;k<158;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    case "規劃與設計學院":
                        for(int k=158;k<168;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    case "生物科學與科技學院":
                        for(int k=168;k<s1.length;++k){
                            new_item_array_list.add(s1[k]);
                        }
                        break;
                    default:
                        break;
                }
                newitems = new String[new_item_array_list.size()];
                new_item_array_list.toArray(newitems);

                ad = new MyOwnAdapter(getContext(),newitems,pb);
                r1.setAdapter(ad);
                r1.setLayoutManager(new LinearLayoutManager(getContext()));
                DividerItemDecoration divider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
                divider.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.my_divider));
                r1.addItemDecoration(divider);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        r1 = view.findViewById(R.id.myRecycler);
        ad = new MyOwnAdapter(getContext(),s1,pb);
        r1.setAdapter(ad);
        r1.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration divider = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.my_divider));
        r1.addItemDecoration(divider);

        return view;
    }
}
