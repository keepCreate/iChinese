package com.tencent.tmgp.ichinese;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import widget.MyGuideViewPagerAdapter;

/**
 * Created by 宁神i on 2017/6/20.
 */

public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private MyGuideViewPagerAdapter guideViewPagerAdapter;
    private List<View> views;
    private ImageView[] dots; //三个点
    private  int[] ids={R.id.iv1,R.id.iv2,R.id.iv3}; //三个点的ID
    private Button start_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.active_guide);
        initViews(); //初始化ViewPager
        initDots();//初始化导航点
    }
    private  void initViews(){
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.active_guide_one,null));
        views.add(inflater.inflate(R.layout.active_guide_two,null));
        views.add(inflater.inflate(R.layout.active_guide_three,null));
        guideViewPagerAdapter = new MyGuideViewPagerAdapter(views,this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(guideViewPagerAdapter);
        start_btn = (Button) views.get(2).findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(GuideActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        viewPager.setOnPageChangeListener(this);
    }
    private  void initDots(){
        dots=new ImageView[views.size()];
        for(int i=0;i<views.size();i++){
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for(int i = 0;i<ids.length;i++){
            if(position==i){
                dots[i].setImageResource(R.drawable.login_point_selected);
            }else{
                dots[i].setImageResource(R.drawable.login_point);
            }
        }
    }//导航点切换

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
