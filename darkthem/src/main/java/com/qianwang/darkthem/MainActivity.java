package com.qianwang.darkthem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.qianwang.nightmodel.NightModelManager;

public class MainActivity extends AppCompatActivity {

    private Button btn_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NightModelManager.getInstance().attach(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_change = (Button) findViewById(R.id.btn_change);
    }

    @Override
    protected void onDestroy() {
        NightModelManager.getInstance().detach(this);
        super.onDestroy();
    }

    public void onChangeThem(View view) {

        if (NightModelManager.getInstance().isCurrentNightModel(this)) {
            btn_change.setText("夜间模式");
            NightModelManager.getInstance().applyDayModel(this);
        } else {
            btn_change.setText("白天模式");
            NightModelManager.getInstance().applyNightModel(this);
        }
    }
}
