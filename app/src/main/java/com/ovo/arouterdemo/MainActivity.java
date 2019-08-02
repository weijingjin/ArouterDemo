package com.ovo.arouterdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class MainActivity extends Activity {
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

    @OnClick({R.id.bt_activity, R.id.bt_module, R.id.tv_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_activity:
                ARouter.getInstance().build("/test/activity")
                        //activity动画
                        .withTransition(R.anim.enter_anim, R.anim.cunrent_anim)
                        .withInt("ints", 12)
                        .navigation(this, 12);
                break;
            case R.id.bt_module:
                //两种方法
//                ARouter.getInstance().navigation(IProviderServer.class).setData();
                ((IProviderServer)ARouter.getInstance()
                        .build("/test/TestServer").navigation()).setData();
                break;
            case R.id.tv_text:
                Intent intent = new Intent(
                        MainActivity.this, FragmentTestActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_anim, R.anim.cunrent_anim);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            String main = data.getStringExtra("main");
            Log.e(TAG, requestCode + "=" + resultCode + "=" + main);
        }
    }
}
