package com.example.user.ncku_course_search;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.content.Context.MODE_APPEND;


/**
 * Created by User on 2019/5/27.
 */

public class MyAccountFragment extends Fragment {
    View v;
    EditText user,passwd;
    Button save;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.fragment_myaccount, container, false);
        user = v.findViewById(R.id.user);
        passwd = v.findViewById(R.id.passwd);
        save = v.findViewById(R.id.button_save);
        SharedPreferences getsh = getContext().getSharedPreferences("account",MODE_APPEND);
        String user_string = getsh.getString("user","");
        String passwd_string = getsh.getString("passwd","");
        user.setText(user_string);
        passwd.setText(passwd_string);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences setsh = getContext().getSharedPreferences("account", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = setsh.edit();
                edit.putString("user",user.getText().toString());
                edit.putString("passwd",passwd.getText().toString());
                edit.commit();
                if(user.getText().toString().isEmpty() || passwd.getText().toString().isEmpty())
                    Toast.makeText(getActivity(),"請輸入正確帳號密碼QQ",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),"帳號密碼儲存成功!",Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
