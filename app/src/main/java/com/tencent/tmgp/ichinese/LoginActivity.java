package com.tencent.tmgp.ichinese;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

public class LoginActivity extends AppCompatActivity {
    Button btn_login,btn_register;
    ImageView btn_qq,btn_wechat,btn_try;
    TextView getPassword,have_try;
    EditText username_Input,pwd_Input;
    CheckBox remember;
    private Handler handler;
    // 消息
    private Message msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.hideActionBar();
        btn_login=(Button)findViewById(R.id.button2);
        btn_register=(Button)findViewById(R.id.button3);
        btn_qq=(ImageView)findViewById(R.id.imageView5);
        btn_wechat=(ImageView)findViewById(R.id.imageView8);
        btn_try=(ImageView)findViewById(R.id.imageView6);
        getPassword=(TextView)findViewById(R.id.textView4);
        have_try=(TextView)findViewById(R.id.textView5);
        username_Input=(EditText) findViewById(R.id.editText);
        pwd_Input=(EditText) findViewById(R.id.editText3);
        remember=(CheckBox)findViewById(R.id.checkBox) ;

        Boolean needRemember=remember.isChecked();
        handler = new MsgHandler(LoginActivity.this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username=username_Input.getText().toString();
                final String pwd=pwd_Input.getText().toString();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(LoginActivity.this, "用户名或者密码不能为空", Toast.LENGTH_LONG).show();
                } else {
                    // 开启子线程
                    new Thread() {
                        public void run() {
                           int result= loginByPost(username, pwd);

                           switch (result){
                               case 1:
                               // 调用loginByPost方法
                               msg = handler.obtainMessage();//其中这句与msg.arg1一起使用，以避免再次运行程序时提示msg.arg1定义的值已使用，如This message is already in use.
                               msg.arg1 = 1;
                               handler.sendMessage(msg);
                               Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                               startActivity(intent);
                               finish();
                                   break;
                               case 2 :
                               msg = handler.obtainMessage();//其中这句与msg.arg1一起使用，以避免再次运行程序时提示msg.arg1定义的值已使用，如This message is already in use.
                               msg.arg1 = result;
                               handler.sendMessage(msg);
                                   pwd_Input.post(new Runnable() {
                                       @Override
                                       public void run() {
                                           pwd_Input.setText("");
                                       }
                                   });
                                break;
                               case 3:
                                   msg = handler.obtainMessage();//其中这句与msg.arg1一起使用，以避免再次运行程序时提示msg.arg1定义的值已使用，如This message is already in use.
                                   msg.arg1 = result;
                                   handler.sendMessage(msg);
                                   pwd_Input.post(new Runnable() {
                                       @Override
                                       public void run() {
                                           pwd_Input.setText("");
                                       }
                                   });
                                   username_Input.post(new Runnable() {
                                       @Override
                                       public void run() {
                                           username_Input.setText("");
                                       }
                                   });
                                   break;
                                default:break;
                           }
                        }
                    }.start();
                }

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        getPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        have_try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    /**
     * POST请求操作
     *
     * @param username
     * @param pwd
     */
    public int loginByPost(String username, String pwd) {
        int result=0;
        try {
            String spec="http://47.94.136.193:3000/signin";
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
            obj.put("name", username);
            obj.put("password", pwd);
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


                reader.close();
                // 断开连接
             if(sb.equals("null")){

                    result = 3;
                }else {
                    JSONObject jsonObject = new JSONObject(sb);

                    if (jsonObject.get("password").equals(pwd)) {
                        result = 1;
                    } else {
                        result = 2;
                    }
                }

            }
            httpURLConnection.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
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
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确认要退出吗");

        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //不做任何动作
            }
        });

        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
