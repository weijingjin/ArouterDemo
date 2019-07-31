package com.ovo.testarouter;

import android.app.Activity;
import android.os.Bundle;
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

@Route(path = "/test/activity")
public class TestActivity extends Activity {
    private final String TAG = this.getClass().getSimpleName();

    @BindView(R2.id.bt_t_activity)
    Button btTActivity;
    @BindView(R2.id.bt_t_module)
    Button btTModule;
    @BindView(R2.id.tv_t_text)
    TextView tvTText;

    @Autowired(name = "ints")
    int ints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        //接收带参数跳转必须
        ARouter.getInstance().inject(this);

        ButterKnife.bind(this);

        Log.e(TAG, "" + ints);
    }

    @OnClick({R2.id.bt_t_activity, R2.id.bt_t_module})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.bt_t_activity) {
            ARouter.getInstance().build("/main/avtivity")
                    .withTransition(R2.anim.cunrent_anim, R2.anim.exit_anim)
                    .withString("str", "来了").navigation();
//            Intent intent = new Intent();
//            intent.putExtra("main", "ontest");
//            setResult(1122, intent);
//            finish();
////            在finish之后使用，activity动画
//            overridePendingTransition(R2.anim.enter_anim
//                    , R2.anim.exit_anim);
    }else if (view.getId() == R.id.bt_t_module){
            ((IProviderServer)ARouter.getInstance()
                    .build("/main/MianServer").navigation()).setData();
        }
    }

}
