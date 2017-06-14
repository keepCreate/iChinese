package widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import mainFragments.HomeFragment;
import mainFragments.Home_MarkFragment;
import mainFragments.Home_StudyFragment;

/**
 * Created by VULCNAVSeries on 2017/6/14.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT = 2;
    private Home_StudyFragment myFragment1 = null;
    private Home_MarkFragment myFragment2 = null;



    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        myFragment1 = new Home_StudyFragment();
        myFragment2 = new Home_MarkFragment();

    }


    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = myFragment1;
                break;
            case 1:
                fragment = myFragment2;
                break;

        }
        return fragment;
    }
}
