package com.qianwang.wechatfriends;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by luo on 2017/5/26.
 */

public class JsSupport {

    private String json;
    private Context mContext;



    public void setJson(String json) {
        this.json = json;
    }

    public JsSupport(Context context) {
        mContext = context;
    }

    @JavascriptInterface
    public void showToast(String str){

        Toast.makeText(mContext,str,Toast.LENGTH_SHORT).show();
    }
    @JavascriptInterface
    public String getJson() {
        return json;
    }
}
