package com.tencent.tmgp.ichinese;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class NewsDetailActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        setTitle("News");
        webView=(WebView)findViewById(R.id.openWeb);
        android.support.v7.app.ActionBar actionBar =getSupportActionBar();
        try {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            System.out.println(e);
        }

        Intent intent=getIntent();
        String url=intent.getStringExtra("wechatUrl");
        webView.loadUrl(url);
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);


    }
   public static void actionStart(FragmentActivity mContext,String url){
        Intent intent=new Intent(mContext,NewsDetailActivity.class);
       intent.putExtra("wechatUrl",url);
        mContext.startActivity(intent);
        mContext.finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(NewsDetailActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
        }
        return true;
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
}
