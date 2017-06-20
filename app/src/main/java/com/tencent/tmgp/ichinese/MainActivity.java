package com.tencent.tmgp.ichinese;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView tx1,tx2;
    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.activity_main, null);
        setContentView(view);
        this.hideActionBar();
        this.setFullScreen();
        btn=(Button)findViewById(R.id.button);
        tx1=(TextView)findViewById(R.id.textView2) ;
        tx2=(TextView)findViewById(R.id.textView3) ;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                redirectTo();
            }
        });
        //背景渐变
        AlphaAnimation aa=new AlphaAnimation(0.3f,0.8f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        //跳过倒计时
        timer=this.startCountDownTime(6);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        //文字渐变
        final AlphaAnimation textaa=new AlphaAnimation(0.0f,1.0f);
        textaa.setDuration(3000);
        CountDownTimer timer1=new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
               tx1.setVisibility(View.VISIBLE);
                tx2.setVisibility(View.VISIBLE);
                tx1.startAnimation(textaa);
                tx2.startAnimation(textaa);
            }
        };
        timer1.start();

    }
    //跳过倒计时

    private CountDownTimer startCountDownTime(long time) {

        CountDownTimer timer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //每隔countDownInterval秒会回调一次onTick()方法
                btn.setText("跳过"+(millisUntilFinished / 1000)+"s");
            }

            @Override
            public void onFinish() {
               redirectTo();
            }
        };
        timer.start();// 开始计时
        return timer;
    }

    /**
     * 跳转到登录界面
     */
    public void redirectTo(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    /**
     * 隐藏ActionBar
     */
    private void hideActionBar() {
        // Hide UI
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    /**
      * 当前界面满屏
      */
    private void setFullScreen() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
