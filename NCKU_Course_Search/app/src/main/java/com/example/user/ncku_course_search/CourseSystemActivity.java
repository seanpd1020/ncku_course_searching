package com.example.user.ncku_course_search;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class CourseSystemActivity extends AppCompatActivity {
    private WebView webView;
    private int stage=0;
    boolean ch = false;
    //public String url ="file:///android_asset/javascript.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursesystem);
        //設定隱藏標題
        getSupportActionBar().hide();
        //設定隱藏狀態
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        webView = (WebView)findViewById(R.id.webview);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //Toast.makeText(MainActivity.this, "wodo-7-", Toast.LENGTH_SHORT).show();
                view.loadUrl(url);
                if(url.indexOf("lang=CHT")!=-1){
                    stage = 2;
                }
                if(url.indexOf("https://course.ncku.edu.tw/course/signin.php")!=-1){
                    ch = true;
                }
                //return true;
                return true;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(stage==1) Log.d("123","stage=1");
                else if (stage==0){
                    Log.d("123","stage=0");
                    stage=1;
                }
                SharedPreferences getsh = getSharedPreferences("account",MODE_APPEND);
                String user_name = getsh.getString("user","");
                String user_password = getsh.getString("passwd","");
                String js="";
                //String js="javascript:document.getElementById('login-username').focus();"+
                //       "javascript:document.getElementById('login-username').select();";


                if(user_name.isEmpty() || user_password.isEmpty())
                    js = "";
                //登入成大選課頁面javascript
                else if(stage != 2) {
                    js = "javascript:document.getElementById('login-username').value='" + user_name + "';" +
                            "javascript:document.getElementById('login-password').value = '" + user_password + "';" +
                            "javascript:document.getElementById('btn-login').onclick();";
                }
                else
                    js = "javascript:document.getElementById('login-username').value='" + user_name + "';" +
                            "javascript:document.getElementById('login-password').value = '" + user_password + "';";
                final int version = Build.VERSION.SDK_INT;

                if (version < 18) {
                    webView.loadUrl("javascript:document.getElementById('login-username').value='" + new String("xxx") + "';javascript:document.getElementById('login-password').value = '" + new String("xxx") + "';");
                }else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        //webView.evaluateJavascript("javascript:document.getElementById('login-username').value='" + new String("xxx") + "';javascript:document.getElementById('login-password').value = '" + new String("xxx") + "';",null);
                        webView.evaluateJavascript(js,null);
                    }
                }

            }
        });

        //webView.loadUrl("file:///android_asset/javascript.html");

        webView.loadUrl("http://course.ncku.edu.tw/course/signin.php");
        /*MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("https://old.nckuhub.com");
            }
        });*/
        //webView.loadUrl("https://old.nckuhub.com");
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(ch){
            finish();
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return  super.onKeyDown(keyCode,event);
    }

    class JSObject {
        @JavascriptInterface
// sdk17版本以上加上注解
        public String getData(String txt) {
            return "12345678";
        }


        @JavascriptInterface
// sdk17版本以上加上注解
        public void getClose() {
            Toast.makeText(CourseSystemActivity.this, "dododo", Toast.LENGTH_SHORT)
                    .show();
// finish();


        }
    }
}

