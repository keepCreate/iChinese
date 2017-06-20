package com.tencent.tmgp.ichinese;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BlankActivity extends AppCompatActivity {
    private  boolean isFirstIn = false;
    private  static  final  int TIME = 0;
    private  static  final  int GO_HOME = 1000;
    private  static  final  int GO_GUIDE= 1001;
    private Handler mHandler = new Handler(){
        public  void  handleMessage(android.os.Message msg){
            switch (msg.what){
                case GO_HOME:
                    goHome();
                    break;
                case  GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        init();
    }
    private void goHome(){
        Intent i = new Intent(BlankActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
    private void goGuide(){
        Intent i = new Intent(BlankActivity.this,GuideActivity.class);
        startActivity(i);
        finish();
    }
    private void init(){
        SharedPreferences preferences = getSharedPreferences("ning",MODE_PRIVATE);
        isFirstIn = preferences.getBoolean("isFirstIn",true);
        if (!isFirstIn){
            mHandler.sendEmptyMessageDelayed(GO_HOME,TIME);
        }else {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE,TIME);
            SharedPreferences.Editor editor =preferences.edit();
            editor.putBoolean("isFirstIn",false);
            editor.commit();
        }
    }
}
