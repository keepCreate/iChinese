package com.luojiahanyu.products.ichinese;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button btn_register;
    EditText getCode;
    ImageView sendMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_register=(Button)findViewById(R.id.button4);
        getCode=(EditText)findViewById(R.id.editText8);
        sendMsg=(ImageView)findViewById(R.id.imageView7) ;
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
                       getCode.setText("Click to get Code");
               }

           }
       });
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this,"验证码已发送，请注意查收",Toast.LENGTH_LONG).show();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "注册成功",Toast.LENGTH_SHORT ).show();
                Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
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
}
