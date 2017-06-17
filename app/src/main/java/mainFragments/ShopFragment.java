package mainFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.tencent.tmgp.ichinese.R;

/**
 * Created by VULCNAVSeries on 2017/6/13.
 */

public class ShopFragment extends android.support.v4.app.Fragment {
    SearchView m_urlInput;
    WebView webView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.shopfragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        m_urlInput=(SearchView)getActivity().findViewById(R.id.urlInput);
        webView=(WebView)getActivity().findViewById(R.id.weixinview);
        m_urlInput.setIconifiedByDefault(false);
        m_urlInput.setQueryHint("输入网址");
        m_urlInput.setIconified(false);
       m_urlInput.setOnCloseListener(new SearchView.OnCloseListener() {

                                         @Override
                                         public boolean onClose() {
                                             // to avoid click x button and the edittext hidden
                                             return true;
                                         }
                                     });
      m_urlInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String query) {
              webView.loadUrl(query);
              return false;
          }

          @Override
          public boolean onQueryTextChange(String newText) {
              return false;
          }
      });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) webView.getLayoutParams();
                DisplayMetrics displayMetrics=new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                lp.height = displayMetrics.heightPixels;
                webView.setLayoutParams(lp);

            }
        });







    }
}
