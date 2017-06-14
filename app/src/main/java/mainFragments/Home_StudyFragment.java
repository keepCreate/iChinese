package mainFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tmgp.ichinese.R;

import java.util.ArrayList;
import java.util.List;

import widget.CalendarView;

/**
 * Created by VULCNAVSeries on 2017/6/14.
 */

public class Home_StudyFragment extends android.support.v4.app.Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.home_studyfragment,container,false);
        return view;
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
