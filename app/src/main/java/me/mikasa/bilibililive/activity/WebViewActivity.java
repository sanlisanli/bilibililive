package me.mikasa.bilibililive.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import me.mikasa.bilibililive.R;
import me.mikasa.bilibililive.util.CommonUtil;
import woo.mikasa.lib.base.BaseToolbarActivity;

public class WebViewActivity extends BaseToolbarActivity {
    private ProgressBar progressBar;
    @Override
    protected int setLayoutResId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        progressBar=findViewById(R.id.progress_bar);
        //CommonUtil.immersiveScreen(this);
        String link=getIntent().getStringExtra("link");
        WebView webView=findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(chromeClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);
    }

    @Override
    protected void initListener() {
    }

    @Override
    public void onBackPressed() {
        finish();
    }
    WebChromeClient chromeClient=new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress);
            if (newProgress==100){
                progressBar.setVisibility(View.GONE);
            }
        }
    };
}
