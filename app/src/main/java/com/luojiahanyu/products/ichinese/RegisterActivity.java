package com.luojiahanyu.products.ichinese;

import android.app.Dialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

public class RegisterActivity extends AppCompatActivity {
    Button btn_register;
    EditText getCode;
    TextView timer;
    ImageView sendMsg;
    CountDownTimer countDownTimer2;
    EditText username,password,confirm,phone,verifyCode;
    String code="";
    private Handler handler;
    // 消息
    private Message msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_register=(Button)findViewById(R.id.button4);
        getCode=(EditText)findViewById(R.id.editText8);
        timer=(TextView)findViewById(R.id.textView10) ;
        sendMsg=(ImageView)findViewById(R.id.imageView7) ;
        username=(EditText)findViewById(R.id.editText2);
        password=(EditText)findViewById(R.id.editText5);
        confirm=(EditText)findViewById(R.id.editText6);
        phone=(EditText)findViewById(R.id.editText7);
        btn_register.setClickable(false);
        handler = new MsgHandler(RegisterActivity.this);
        setTitle("Register");
        android.support.v7.app.ActionBar actionBar =getSupportActionBar();
        try {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            System.out.println(e);
        }

       getCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if (hasFocus){

                        getCode.setText("");

               }else{
                   if (!(getCode.getText().toString().length()>0)){
                       getCode.setText("Click to get Code");
                   }

               }

           }
       });
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String telnumber  =phone.getText().toString();
               if(telnumber.length()>0) {
                   new Thread() {
                       @Override
                       public void run() {
                           code = getCode(telnumber);
                       }
                   }.start();

                   sendMsg.setClickable(false);
                   Toast.makeText(RegisterActivity.this, "验证码已发送，请注意查收", Toast.LENGTH_LONG).show();
                   countDownTimer2 = new CountDownTimer(60000, 1000) {
                       @Override
                       public void onTick(long millisUntilFinished) {

                           timer.setText((millisUntilFinished / 1000) + " seconds later again");
                       }

                       @Override
                       public void onFinish() {
                           timer.setText("");
                           sendMsg.setClickable(true);
                       }
                   };
                   countDownTimer2.start();
               }else{
                   Toast.makeText(RegisterActivity.this, "请填写手机号", Toast.LENGTH_LONG).show();
               }
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(TextUtils.isEmpty(getCode.getText().toString())||TextUtils.isEmpty(username.getText().toString())||TextUtils.isEmpty(password.getText().toString())||TextUtils.isEmpty(confirm.getText().toString())||TextUtils.isEmpty(phone.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "不能留空",Toast.LENGTH_SHORT ).show();

                }
                else{
                   final String name=username.getText().toString();
                   final String pwd=password.getText().toString();
                    String confir=confirm.getText().toString();
                    final String pho=phone.getText().toString();
                    String verify=getCode.getText().toString();
                    if (!confir.equals(pwd)){
                        Toast.makeText(RegisterActivity.this, "确认密码和密码不一致",Toast.LENGTH_SHORT ).show();
                    }else if (!code.equals(verify)){
                        Toast.makeText(RegisterActivity.this, "验证码错误",Toast.LENGTH_SHORT ).show();
                    } else {
                        new Thread() {
                            @Override
                            public void run() {
                             int result= register(name,pwd,pho);
                                if(result==1){
                                    msg = handler.obtainMessage();//其中这句与msg.arg1一起使用，以避免再次运行程序时提示msg.arg1定义的值已使用，如This message is already in use.
                                    msg.arg1 = 4;
                                    handler.sendMessage(msg);
                                    Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    msg = handler.obtainMessage();//其中这句与msg.arg1一起使用，以避免再次运行程序时提示msg.arg1定义的值已使用，如This message is already in use.
                                    msg.arg1 = 5;
                                    handler.sendMessage(msg);
                                }
                            }
                        }.start();


                    }

                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
        }
        return true;
    }

    /**
     * POST请求操作
     *
     */
    public String getCode(String phone) {
        String result="";
        try {
            String spec="http://47.94.136.193:3000/verify";
            URL url=new URL(spec);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Charset","utf-8");
            // 设置请求的超时时间
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            // 传递的数据
            JSONObject obj = new JSONObject();
            obj.put("phone",phone);
            OutputStream out = httpURLConnection.getOutputStream();
            String content = String.valueOf(obj);
            out.write(content.getBytes());
            out.close();
            int code=httpURLConnection.getResponseCode();
            if (code==200){
                // 读取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String lines;
                String sb = "";
                while ((lines = reader.readLine()) != null) {
                    lines = URLDecoder.decode(lines, "utf-8");
                    sb+=lines;
                }
                JSONObject jsonObject = new JSONObject(sb);
                result=jsonObject.get("code").toString();
                reader.close();
            }
            httpURLConnection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public int register(String username,String password,String phone) {
        int result=0;
        try {
            String spec="http://47.94.136.193:3000/register";
            URL url=new URL(spec);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Charset","utf-8");
            // 设置请求的超时时间
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            // 传递的数据
            JSONObject obj = new JSONObject();
            obj.put("username",username);
            obj.put("password",password);
            obj.put("phone",phone);
            OutputStream out = httpURLConnection.getOutputStream();
            String content = String.valueOf(obj);
            out.write(content.getBytes());
            out.close();
            int code=httpURLConnection.getResponseCode();
            if (code==200){
                // 读取响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String lines;
                String sb = "";
                while ((lines = reader.readLine()) != null) {
                    lines = URLDecoder.decode(lines, "utf-8");
                    sb+=lines;
                }
                JSONObject jsonObject = new JSONObject(sb);
                if (jsonObject.get("username").equals(username)){
                    result=1;
                }
                reader.close();
            }
            httpURLConnection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
