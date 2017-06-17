package widget;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.tmgp.ichinese.R;

import java.util.Calendar;

import mainFragments.VideoFragment;

/**
 * Created by VULCNAVSeries on 2017/6/15.
 */

public class MyVideoRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int NORMAL_ITEM = 0;
    private static final int GROUP_ITEM = 1;
    DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.ic_menu_gallery) // resource or drawable
            .showImageForEmptyUri(R.drawable.ic_menu_slideshow) // resource or drawable
            .build();
    private VideoFragment mFragment;
    private String[] mDataList;
    private LayoutInflater mLayoutInflater;
    public MyVideoRecyclerViewAdapter(VideoFragment mFragment, String[] mDataList) {
        this.mFragment = mFragment;
        this.mDataList = mDataList;
        mLayoutInflater = LayoutInflater.from(mFragment.getActivity());
    }
    /**
     * 渲染具体的ViewHolder
     * @param viewGroup ViewHolder的容器
     * @param i 一个标志，我们根据该标志可以实现渲染不同类型的ViewHolder
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        if (i == NORMAL_ITEM) {
            return new NormalItemHolder(mLayoutInflater.inflate(R.layout.fragment_base_swipe_item, viewGroup, false));
        } else {
            return new GroupItemHolder(mLayoutInflater.inflate(R.layout.fragment_base_swipe_group_item, viewGroup, false));
        }
    }
    /**
     * 绑定ViewHolder的数据。
     * @param viewHolder
     * @param i 数据源list的下标
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
       String str= mDataList[i];

        if (null == str)
            return;

        if (viewHolder instanceof GroupItemHolder) {
            bindGroupItem(str, (GroupItemHolder) viewHolder);
        } else {
            NormalItemHolder holder = (NormalItemHolder) viewHolder;
            bindNormalItem(str, holder.newsTitle, holder.newsIcon);
        }
    }
    @Override
    public int getItemCount() {
        return mDataList.length;
    }
    /**
     * 决定元素的布局使用哪种类型
     * @param position 数据源List的下标
     * @return 一个int型标志，传递给onCreateViewHolder的第二个参数
     */
    @Override
    public int getItemViewType(int position) {
        //第一个要显示时间
        if (position == 0)
            return GROUP_ITEM;

        int prevIndex = position - 1;
        return prevIndex%2==0 ? GROUP_ITEM : NORMAL_ITEM;
    }

//    @Override
//    public long getItemId(int position) {
//        return mDataList.get(position).getNewsID();
//    }

    void bindNormalItem(String str, TextView newsTitle, ImageView newsIcon) {

        ImageLoader.getInstance().displayImage("http://47.94.136.193:3000/source/new.png",newsIcon,options);
//        if (entity.getIconUrl().isEmpty()) {
//
//            if (newsIcon.getVisibility() != View.GONE)
//                newsIcon.setVisibility(View.GONE);
//        } else {
//            ZImage.getInstance().load(entity.getIconUrl(), newsIcon,
//                    ConfigConstant.LIST_ITEM_IMAGE_SIZE_DP, ConfigConstant.LIST_ITEM_IMAGE_SIZE_DP, true, mContext.getMyApplication().canRequestImage());
//
//            if (newsIcon.getVisibility() != View.VISIBLE)
//                newsIcon.setVisibility(View.VISIBLE);
//        }
        newsTitle.setText(Html.fromHtml(str));
    }

    void bindGroupItem(String str, GroupItemHolder holder) {
        bindNormalItem(str, holder.newsTitle, holder.newsIcon);
        Calendar calendar=Calendar.getInstance();
        holder.newsTime.setText(""+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
    }

//    void showNewsDetail(int pos) {
//        NewsListEntity entity = mDataList.get(pos);
//        NewsDetailActivity.actionStart(mContext, entity.getNewsID(), entity.getRecommendAmount(), entity.getCommentAmount());
//    }

    /**
     * 新闻标题
     */
     class NormalItemHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        ImageView newsIcon;

        public NormalItemHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.base_swipe_item_title);
            newsIcon = (ImageView) itemView.findViewById(R.id.base_swipe_item_icon);
            itemView.findViewById(R.id.base_swipe_item_container).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //showNewsDetail(getPosition());
                }
            });
        }
    }

    /**
     * 带日期新闻标题
     */
    class GroupItemHolder extends NormalItemHolder {
        TextView newsTime;

        public GroupItemHolder(View itemView) {
            super(itemView);
            newsTime = (TextView) itemView.findViewById(R.id.base_swipe_group_item_time);
        }
    }

}
