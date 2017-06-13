package com.tencent.tmgp.ichinese;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    // 定义5个Fragment对象
    private HomeFragment homeFragment;
    private ReviewFragment reviewFragment;
    private VideoFragment videoFragment;
    private ShopFragment shopFragment;
    private FreindsFragment freindsFragment;
    // 帧布局对象，用来存放Fragment对象
    private FrameLayout frameLayout;

    // 定义每个选项中的相关控件
    private RelativeLayout firstLayout;
    private RelativeLayout secondLayout;
    private RelativeLayout thirdLayout;
    private RelativeLayout fourthLayout;
    private RelativeLayout fifthLayout;
    private ImageView firstImage;
    private ImageView secondImage;
    private ImageView thirdImage;
    private ImageView fourthImage;
    private ImageView fifthImage;
    private TextView firstText;
    private TextView secondText;
    private TextView thirdText;
    private TextView fourthText;
    private TextView fifthText;
    // 定义几个颜色
    private int white = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int dark = 0xff000000;
    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.hideActionBar();
        fragmentManager=getSupportFragmentManager();
        initView(); // 初始化界面控件
        setChioceItem(0); // 初始化页面加载时显示第一个选项卡

    }


    /**
     * 初始化页面
     */
    private void initView() {


        firstImage = (ImageView) findViewById(R.id.first_image);
        secondImage = (ImageView) findViewById(R.id.second_image);
        thirdImage = (ImageView) findViewById(R.id.third_image);
        fourthImage = (ImageView) findViewById(R.id.fourth_image);
        fifthImage = (ImageView) findViewById(R.id.fifth_image);
        firstText = (TextView) findViewById(R.id.first_text);
        secondText = (TextView) findViewById(R.id.second_text);
        thirdText = (TextView) findViewById(R.id.third_text);
        fourthText = (TextView) findViewById(R.id.fourth_text);
        fifthText = (TextView) findViewById(R.id.fifth_text);
        firstLayout = (RelativeLayout) findViewById(R.id.first_layout);
        secondLayout = (RelativeLayout) findViewById(R.id.second_layout);
        thirdLayout = (RelativeLayout) findViewById(R.id.third_layout);
        fourthLayout = (RelativeLayout) findViewById(R.id.fourth_layout);
        fifthLayout = (RelativeLayout) findViewById(R.id.fifth_layout);
        firstLayout.setOnClickListener(HomeActivity.this);
        secondLayout.setOnClickListener(HomeActivity.this);
        thirdLayout.setOnClickListener(HomeActivity.this);
        fourthLayout.setOnClickListener(HomeActivity.this);
        fifthLayout.setOnClickListener(HomeActivity.this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.first_layout:
                setChioceItem(0);
                break;
            case R.id.second_layout:
                setChioceItem(1);
                break;
            case R.id.third_layout:
                setChioceItem(2);
                break;
            case R.id.fourth_layout:
                setChioceItem(3);
                break;
            case R.id.fifth_layout:
                setChioceItem(4);
                break;
            default:
                break;
        }
    }
    /**
     * 设置点击选项卡的事件处理
     *
     * @param index 选项卡的标号：0, 1, 2, 3,4
     */
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);
        switch (index) {
            case 0:
// firstImage.setImageResource(R.drawable.XXXX); 需要的话自行修改
                firstText.setTextColor(dark);
                firstLayout.setBackgroundColor(gray);
// 如果fg1为空，则创建一个并添加到界面上
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.content, homeFragment);
                } else {
// 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case 1:
// secondImage.setImageResource(R.drawable.XXXX);
                secondText.setTextColor(dark);
                secondLayout.setBackgroundColor(gray);
                if (reviewFragment== null) {
                    reviewFragment = new ReviewFragment();
                    fragmentTransaction.add(R.id.content, reviewFragment);
                } else {
                    fragmentTransaction.show(reviewFragment);
                }
                break;
            case 2:
// thirdImage.setImageResource(R.drawable.XXXX);
                thirdText.setTextColor(dark);
                thirdLayout.setBackgroundColor(gray);
                if (videoFragment == null) {
                    videoFragment = new VideoFragment();
                    fragmentTransaction.add(R.id.content, videoFragment);
                } else {
                    fragmentTransaction.show(videoFragment);
                }
                break;
            case 3:
// fourthImage.setImageResource(R.drawable.XXXX);
                fourthText.setTextColor(dark);
                fourthLayout.setBackgroundColor(gray);
                if (shopFragment == null) {
                    shopFragment= new ShopFragment();
                    fragmentTransaction.add(R.id.content, shopFragment);
                } else {
                    fragmentTransaction.show(shopFragment);
                }
                break;
            case 4:
// fifthImage.setImageResource(R.drawable.XXXX);
                fifthText.setTextColor(dark);
                fifthLayout.setBackgroundColor(gray);
                if (freindsFragment == null) {
                    freindsFragment= new FreindsFragment();
                    fragmentTransaction.add(R.id.content, freindsFragment);
                } else {
                    fragmentTransaction.show(freindsFragment);
                }
                break;
        }
        fragmentTransaction.commit(); // 提交
    }
    /**
     * 当选中其中一个选项卡时，其他选项卡重置为默认
     */
    private void clearChioce() {
        // firstImage.setImageResource(R.drawable.XXX);
        firstText.setTextColor(gray);
        firstLayout.setBackgroundColor(white);
        // secondImage.setImageResource(R.drawable.XXX);
        secondText.setTextColor(gray);
        secondLayout.setBackgroundColor(white);
        // thirdImage.setImageResource(R.drawable.XXX);
        thirdText.setTextColor(gray);
        thirdLayout.setBackgroundColor(white);
        // fourthImage.setImageResource(R.drawable.XXX);
        fourthText.setTextColor(gray);
        fourthLayout.setBackgroundColor(white);
        // fifthImage.setImageResource(R.drawable.XXX);
        fifthText.setTextColor(gray);
        fifthLayout.setBackgroundColor(white);
    }
    /**
     * 隐藏Fragment
     *
     * @param fragmentTransaction
     */
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (reviewFragment != null) {
            fragmentTransaction.hide(reviewFragment);
        }
        if (videoFragment!= null) {
            fragmentTransaction.hide(videoFragment);
        }
        if (shopFragment != null) {
            fragmentTransaction.hide(shopFragment);
        }
        if (freindsFragment != null) {
            fragmentTransaction.hide(freindsFragment);
        }
    }
    /**
     * 隐藏ActionBar
     */
    private void hideActionBar() {
        // Hide UI
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
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
