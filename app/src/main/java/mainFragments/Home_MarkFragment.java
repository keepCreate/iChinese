package mainFragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tmgp.ichinese.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.zip.Inflater;

import widget.CalendarView;

/**
 * Created by VULCNAVSeries on 2017/6/14.
 */

public class Home_MarkFragment extends android.support.v4.app.Fragment implements View.OnClickListener{

        private TextView mTextSelectMonth;
        private ImageButton mLastMonthView;
        private ImageButton mNextMonthView;
        private CalendarView mCalendarView;
        private  Button button;
        private List<String> mDatas= new ArrayList<>();;
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
                View view= inflater.inflate(R.layout.home_markfragment,container,false);
                return view;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
                super.onActivityCreated(savedInstanceState);
                button=(Button)getActivity().findViewById(R.id.button5) ;
                mTextSelectMonth = (TextView) getActivity().findViewById(R.id.txt_select_month);
                mLastMonthView = (ImageButton) getActivity().findViewById(R.id.img_select_last_month);
                mNextMonthView = (ImageButton) getActivity().findViewById(R.id.img_select_next_month);
                mCalendarView = (CalendarView) getActivity().findViewById(R.id.calendarView);
                mLastMonthView.setOnClickListener(this);
                mNextMonthView.setOnClickListener(this);
                // 初始化打卡日期
                initData();
                // 设置已打卡日期
                mCalendarView.setOptionalDate(mDatas);
                // 设置已选日期
//        mCalendarView.setSelectedDates(mDatas);
                // 设置不可以被点击
                mCalendarView.setClickable(false);
                // 设置点击事件
                mCalendarView.setOnClickDate(new CalendarView.OnClickListener() {
                        @Override
                        public void onClickDateListener(int year, int month, int day) {
//                Toast.makeText(getActivity(), year + "年" + month + "月" + day + "天", Toast.LENGTH_SHORT).show();
//
//                // 获取已选择日期
//                List<String> dates = mCalendarView.getSelectedDates();
//                for (String date : dates) {
//                    Log.e("test", "date: " + date);
//                }
                        }
                });

                mTextSelectMonth.setText(mCalendarView.getDate());
                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Calendar calendar=Calendar.getInstance();
                                int mCurYear    =   calendar.get(Calendar.YEAR);
                                int mCurMonth   =   calendar.get(Calendar.MONTH);
                                int mCurDate    =   calendar.get(Calendar.DATE);

                                if((mCurMonth + 1) < 10){
                                        if(mCurDate<10){
                                                if (mDatas.contains(mCurYear+"0"+(mCurMonth+1)+"0"+mCurDate)){
                                                        Toast.makeText(getActivity(), "已打卡", Toast.LENGTH_SHORT).show();
                                                }else{
                                                        mDatas.add(mCurYear+"0"+(mCurMonth+1)+"0"+mCurDate);
                                                        Toast.makeText(getActivity(), "打卡成功", Toast.LENGTH_SHORT).show();
                                                }
                                        }else{
                                                if (mDatas.contains(mCurYear + "0" + (mCurMonth + 1) + "" + mCurDate)){
                                                        Toast.makeText(getActivity(), "已打卡", Toast.LENGTH_SHORT).show();
                                                }else {
                                                        mDatas.add(mCurYear + "0" + (mCurMonth + 1) + "" + mCurDate);
                                                        Toast.makeText(getActivity(), "打卡成功", Toast.LENGTH_SHORT).show();
                                                }
                                        }

                                }else{
                                        if(mCurDate<10){
                                                if (mDatas.contains(mCurYear+""+(mCurMonth+1)+"0"+mCurDate)){
                                                        Toast.makeText(getActivity(), "已打卡", Toast.LENGTH_SHORT).show();
                                                }else {
                                                        mDatas.add(mCurYear + "" + (mCurMonth + 1) + "0" + mCurDate);
                                                        Toast.makeText(getActivity(), "打卡成功", Toast.LENGTH_SHORT).show();
                                                }
                                        }else{
                                                if (mDatas.contains(mCurYear + "" + (mCurMonth + 1) + "" + mCurDate)){
                                                        Toast.makeText(getActivity(), "已打卡", Toast.LENGTH_SHORT).show();
                                                }else {
                                                        mDatas.add(mCurYear + "" + (mCurMonth + 1) + "" + mCurDate);
                                                        Toast.makeText(getActivity(), "打卡成功", Toast.LENGTH_SHORT).show();
                                                }
                                        }
                                }
                                mCalendarView.setOptionalDate(mDatas);

                        }
                });
        }
        private void initData() {
                mDatas.add("20170601");
                mDatas.add("20170602");
                mDatas.add("20170603");
                mDatas.add("20170608");
                mDatas.add("20170609");
                mDatas.add("20170610");
                mDatas.add("20170611");
                mDatas.add("20170612");
                mDatas.add("20170613");
        }
        @Override
        public void onClick(View view) {
                switch (view.getId()){
                        case R.id.img_select_last_month:
                                mCalendarView.setLastMonth();
                                mTextSelectMonth.setText(mCalendarView.getDate());
                                break;
                        case R.id.img_select_next_month:
                                mCalendarView.setNextMonth();
                                mTextSelectMonth.setText(mCalendarView.getDate());
                                break;
                }
        }
}

