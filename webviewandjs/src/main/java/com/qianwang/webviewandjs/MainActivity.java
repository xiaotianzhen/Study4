package com.qianwang.webviewandjs;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout ll_webview;
    private Button btn_test;
    private WebView webView;

    private void initView() {
        ll_webview = (LinearLayout) findViewById(R.id.ll_webview);
        btn_test = (Button) findViewById(R.id.btn_test);

    }

    @JavascriptInterface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        webView = new WebView(this);
        ll_webview.addView(webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("http://192.168.1.101:8080/index2.html");
        webView.addJavascriptInterface(new JsObject(), "demo");
    }


    public void testOnclick(View view) {

 /*       // 加了一个post（new Runble）另外开线程，若不加的话Js代码是不被调用的。
        webView.post(new Runnable() {
            @Override
            public void run() {
                final String strToJs = "hello JS";
                webView.loadUrl("javascript:methodInJs(\""+strToJs+"\")");
            }
        });*/

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final String strToJs = "hello JS";
                webView.loadUrl("javascript:methodInJs(\"" + strToJs + "\")");
            }
        });

    }

    public class JsObject {

        //js可调用andrid中的方法，注意在17以后要添加标注
        @JavascriptInterface
        public void changeBtnText(final String text) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    btn_test.setText(text);
                }
            });
        }
    }
}
