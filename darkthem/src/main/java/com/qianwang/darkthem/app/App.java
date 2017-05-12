package com.qianwang.darkthem.app;

import android.app.Application;
import com.qianwang.nightmodel.NightModelManager;
/**
 * Created by luo on 2017/5/10.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NightModelManager.getInstance().init(this);
    }
}
