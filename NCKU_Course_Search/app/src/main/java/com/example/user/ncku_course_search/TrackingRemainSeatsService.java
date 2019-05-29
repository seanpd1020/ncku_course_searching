package com.example.user.ncku_course_search;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by User on 2019/5/18.
 */

public class TrackingRemainSeatsService extends IntentService {
    public TrackingRemainSeatsService(){
        super("TrackingRemainSeatsService");
    }
    @Override
    public void onCreate(){
        super.onCreate();
        Log.v("timer1","Timer service has started.");
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        super.onStartCommand(intent,flags,startId);

        return START_STICKY;
    }
    private String search = "http://course-query.acad.ncku.edu.tw/qry/qry001.php?dept_no=";
    public static final int NOTIFICATION_ID = 101;
    private static final String NOTIFICATION_CHANNEL_ID = "example_notification_channel";
    private NotificationCompat.Builder mBuilder;
    @Override
    protected void onHandleIntent(Intent intent){
        boolean check;
        SharedPreferences getsh = getSharedPreferences("toggleSharedPreferences", Context.MODE_APPEND);
        check = getsh.getBoolean("toggle",false);
        while(check){
            final ArrayList<String> dep_code = new ArrayList<String>();
            final ArrayList<String> no = new ArrayList<String>();
            final ArrayList<String> remain_seats = new ArrayList<String>();
            final ArrayList<String> course_name = new ArrayList<String>();
            //parse URL
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        //update SharedPreferences
                        SharedPreferences getsh = getSharedPreferences("sharedprefs", Context.MODE_APPEND);
                        String dc = getsh.getString("dep_code","");
                        String n = getsh.getString("no","");
                        String cn = getsh.getString("course_name","");
                        if(!dc.isEmpty()) {
                            String dep_code_array[] = dc.split(",");
                            String no_array[] = n.split(",");
                            String course_name_array[] = cn.split(",");
                            Collections.addAll(course_name,course_name_array);
                            Collections.addAll(dep_code,dep_code_array);
                            Collections.addAll(no,no_array);

                            for (int i=0;i<dep_code.size();++i) {
                                Document doc2 = Jsoup.connect(search + dep_code.get(i)).execute().parse();
                                Elements links2 = doc2.select("TR[class='course_y1']");
                                for (Element link3 : links2) {
                                    Elements links3 = link3.select("TD");
                                    String item_info[] = new String[25];
                                    int k = 0;
                                    for (Element link4 : links3) {
                                        item_info[k++] = link4.text();
                                    }
                                    if(item_info[2].contains(no.get(i))){
                                        remain_seats.add(item_info[15]);
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
                                    if(item_info[2].contains(no.get(i))){
                                        remain_seats.add(item_info[15]);
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
                                    if(item_info[2].contains(no.get(i))){
                                        remain_seats.add(item_info[15]);
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
                                    if(item_info[2].contains(no.get(i))){
                                        remain_seats.add(item_info[15]);
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
                                    if(item_info[2].contains(no.get(i))){
                                        remain_seats.add(item_info[15]);
                                    }
                                }
                            }
                        }
                    }catch(IOException e) {
                        Log.v("timer1", "onParse: "+e.getMessage());
                    }
                }
            });
            th.start();
            try{
                Log.v("timer1", "before join");
                th.join();
                Log.v("timer1", "after join");
            }catch(InterruptedException e){
                Log.v("timer1", "onJoin: "+e.getMessage());
            }
            Log.v("timer1", "size:"+String.valueOf(remain_seats.size()));
            String message = "";
            for(int i=0;i<remain_seats.size();++i){
                Log.v("timer1", remain_seats.get(i));
                if(remain_seats.get(i).contains("額滿") || remain_seats.get(i).contains("洽師培中心") || remain_seats.get(i).contains("洽系辦") || remain_seats.get(i).contains("洽不分系學程"))
                    continue;
                else{
                    message = message + dep_code.get(i)+" "+no.get(i)+" "+course_name.get(i)+" 餘額: "+remain_seats.get(i) + "\n";
                }
            }
            //send notification
            if(!message.isEmpty()) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                NotificationChannel notificationChannel = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications",
                            NotificationManager.IMPORTANCE_LOW);
                    notificationChannel.enableLights(true);
                    notificationChannel.enableVibration(true);
                    //notificationChannel.setImportance(NotificationManager.IMPORTANCE_HIGH);
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                Intent main_intent = new Intent(this,CourseSystemActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, main_intent, 0);
                mBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                        .setVisibility(NotificationManager.IMPORTANCE_DEFAULT)
                        .setContentTitle("有新ㄉ課程餘額!!!")
                        .setContentText(message)
                        .setSmallIcon(R.mipmap.ncku)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ncku))
                        .setFullScreenIntent(pendingIntent, true)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                        .setWhen(System.currentTimeMillis())
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
                Log.v("timer1", message);
                //return;
            }
            for(int i=0;i<60;++i){
                Log.v("timer1","i = "+i);
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                }
            }
            check = getsh.getBoolean("toggle",false);
        }
    }
}
