package mainFragments;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.tencent.tmgp.ichinese.R;

import java.io.File;

import widget.CircleImageView;
import widget.MyFragmentPagerAdapter;
import widget.MyHomeRecyclerViewAdapter;

/**
 * Created by VULCNAVSeries on 2017/6/13.
 */

public class HomeFragment extends android.support.v4.app.Fragment implements View.OnClickListener {
    private RelativeLayout all=null;
    private CircleImageView circleImageView;
    private ScrollView scrollingView;
    private LinearLayout contentView=null;
    private LinearLayout topBar=null;
    private ViewPager vpager;
    private SearchView searchView;
    private InputMethodManager inputMethodManager;
    private Button toTopBtn;// 返回顶部的按钮
    private int scrollY = 0;// 标记上次滑动位置

    private MyFragmentPagerAdapter mAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.homefragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        all=(RelativeLayout) getActivity().findViewById(R.id.home_all);
        inputMethodManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        topBar=(LinearLayout)getActivity().findViewById(R.id.top_bar);
        topBar.setFocusable(true);
        topBar.setFocusableInTouchMode(true);
        searchView=(SearchView)getActivity().findViewById(R.id.m_search);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search for Chinese");

        circleImageView=(CircleImageView)getActivity().findViewById(R.id.userImage);
        Drawable drawable=getActivity().getDrawable(R.drawable.hugh);
        circleImageView.setImageDrawable(drawable);

        scrollingView=(ScrollView)getActivity().findViewById(R.id.home_scroll);

            contentView=(LinearLayout)getActivity().findViewById(R.id.home_scrollmain);


        toTopBtn=(Button)getActivity().findViewById(R.id.top_btn);
        toTopBtn.setOnClickListener(this);


        mAdapter = new MyFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        vpager = (ViewPager) getActivity().findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        scrollingView.smoothScrollTo(0,0);



        // Create global configuration and initialize ImageLoader with this config
        File cacheDir = StorageUtils.getCacheDirectory(getActivity());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity())
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCache(new UnlimitedDiskCache(cacheDir)) // default
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(getActivity())) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);


        String[] list={"或许，最美的事不是留住时光，而是留住记忆，如最初相识的感觉一样，哪怕一个不经意的笑容，便是我们最怀念的故事。但愿，时光，如初见","fdsfdsg","ewq","rfds"};
        recyclerView=(RecyclerView)getActivity().findViewById(R.id.news_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MyHomeRecyclerViewAdapter(this,list);
        recyclerView.setAdapter(adapter);

        scrollingView.setOnTouchListener(new View.OnTouchListener() {
            private int lastY = 0;
            private int touchEventId = -9983761;

            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    View scroller = (View) msg.obj;
                    if (msg.what == touchEventId) {
                        if (lastY == scroller.getScrollY()) {
                            handleStop(scroller);
                        } else {
                            handler.sendMessageDelayed(handler.obtainMessage(
                                    touchEventId, scroller), 5);
                            lastY = scroller.getScrollY();
                        }
                    }
                }
            };

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    handler.sendMessageDelayed(
                            handler.obtainMessage(touchEventId, v), 5);
                }
                return false;
            }
            /**
             * ScrollView 停止
             *
             * @param view
             */
            private void handleStop(Object view) {


                ScrollView scroller = (ScrollView) view;
                scrollY = scroller.getScrollY();

                doOnBorderListener();
            }

        });



    }

    private void doOnBorderListener() {
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        // 底部判断
        if (contentView != null
                && contentView.getMeasuredHeight() <= scrollingView.getScrollY()
                + scrollingView.getHeight()) {
            toTopBtn.setVisibility(View.VISIBLE);

        }
        // 顶部判断
        else if (scrollingView.getScrollY() == 0) {
            toTopBtn.setVisibility(View.GONE);

        } else if (scrollingView.getScrollY() >  displayMetrics.heightPixels/4) {
            toTopBtn.setVisibility(View.VISIBLE);

        }else{
            toTopBtn.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_btn:
                scrollingView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollingView.fullScroll(ScrollView.FOCUS_UP);
                    }
                });
                toTopBtn.setVisibility(View.GONE);
                break;
        }
    }

    private void hideSoftInput() {
        if (inputMethodManager != null) {
            View v = getActivity().getCurrentFocus();
            if (v == null) {
                return;
            }

            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
            searchView.clearFocus();
        }
    }

}
