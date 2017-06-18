package com.tencent.tmgp.ichinese;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

public class VideoPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        setTitle("Video");
        android.support.v7.app.ActionBar actionBar =getSupportActionBar();
        try {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            System.out.println(e);
        }

        Intent intent=getIntent();
        String url=intent.getStringExtra("videoUrl");
        System.out.println(url);

        //mPlayerView即step1中添加的界面view
        TXCloudVideoView mPlayerView = (TXCloudVideoView)findViewById(R.id.video_view);
//创建player对象
        TXLivePlayer mLivePlayer = new TXLivePlayer(this);
        mPlayerView.setRenderMode(1);
//关键player对象与界面view

        mLivePlayer.setPlayerView(mPlayerView);
        mLivePlayer.startPlay(url, TXLivePlayer.PLAY_TYPE_VOD_MP4); //推荐FLV
    }

    public static void actionStart(FragmentActivity mContext, String url){
        Intent intent=new Intent(mContext,VideoPlayActivity.class);
        intent.putExtra("videoUrl",url);
        mContext.startActivity(intent);
        mContext.finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent=new Intent(VideoPlayActivity.this,HomeActivity.class);
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
