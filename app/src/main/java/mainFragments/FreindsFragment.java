package mainFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tencent.tmgp.ichinese.R;

import widget.DrawablePaintView;

/**
 * Created by VULCNAVSeries on 2017/6/13.
 */

public class FreindsFragment extends android.support.v4.app.Fragment {
    DrawablePaintView drawablePaintView;
    Button button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.freindfragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        button=(Button)getActivity().findViewById(R.id.clearAll);
        drawablePaintView=(DrawablePaintView)getActivity().findViewById(R.id.paintBoard);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              drawablePaintView.ClearAllPaint();
            }
        });
    }
}
