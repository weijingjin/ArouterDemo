package com.ovo.arouterdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ovo.commonlib.IProviderServer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/main/avtivity")
public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.bt_activity)
    Button btActivity;
    @BindView(R.id.bt_module)
    Button btModule;
    @BindView(R.id.tv_text)
    TextView tvText;

    @Autowired(name = "str")
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //接收带参数跳转必须
        ARouter.getInstance().inject(this);
        ButterKnife.bind(this);

        Log.e(TAG, "" + str);
    }

    @OnClick({R.id.bt_activity, R.id.bt_module})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_activity:
                ARouter.getInstance().build("/test/activity")
                        .withInt("ints", 12).navigation();
                break;
            case R.id.bt_module:
//                ARouter.getInstance().navigation(IProviderServer.class).setData();
                ((IProviderServer)ARouter.getInstance()
                        .build("/test/TestServer").navigation()).setData();
                break;
        }
    }
}
