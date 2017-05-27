package com.qianwang.wechatfriends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.qianwang.wechatfriends.bean.FriendsZone;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll_webview;
    private WebView mWebView;
    private List<FriendsZone> mList = new ArrayList<FriendsZone>();

    private void initView() {
        ll_webview = (LinearLayout) findViewById(R.id.ll_webview);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        mWebView = new WebView(this);
        ll_webview.addView(mWebView);
        //解决点击链接跳转浏览器问题
        mWebView.setWebViewClient(new WebViewClient());
        //js支持
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //允许访问assets目录
        settings.setAllowFileAccess(true);
        //设置WebView排版规则, 实现单列显示, 不允许横向移动
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //assets文件路径
        String path = "file:///android_asset/index.html";
        //添加Json数据
        addJson();
        //加载Html页面
        mWebView.loadUrl(path);
    }

    private void addJson() {

        JsSupport jsSupport = new JsSupport(this);
        for (int i = 0; i < 100; i++) {
            mList.add(new FriendsZone("鹿鹿" + i, "images/icon.png", "这里是Html测试数据, 这里是Html测试数据, 这里是Html测试数据" + i));
        }

        Gson gson = new Gson();
        String json = gson.toJson(mList);
        Log.i("520it", "json" + "***********************************"+json);
        jsSupport.setJson(json);
        mWebView.addJavascriptInterface(jsSupport,"weichat");
    }

    @Override
    public void onBackPressed() {

        if(mWebView.canGoBack()){
            mWebView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
