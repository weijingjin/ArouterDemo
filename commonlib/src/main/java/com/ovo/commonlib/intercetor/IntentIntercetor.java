package com.ovo.commonlib.intercetor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 跳转页面拦截器
 */
@Interceptor(priority = 1)
public class IntentIntercetor implements IInterceptor {
    private final String TAG = this.getClass().getSimpleName();

    private Context context;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        int extra = postcard.getExtra();
        String path = postcard.getPath();
        Log.e(TAG, "path=" + path + ",extra=" + extra);
        postcard.withString("strs", "在拦截器中附加的参数");
        if (extra != 1) {
            switch (path) {
                case "/testFragment/FragmentTestActivity":
                    callback.onContinue(postcard);
                break;
                default:
                    ARouter.getInstance().build("/testFragment/FragmentTestActivity")
                            .navigation(context);
                    break;
            }

        } else {
            callback.onContinue(postcard);
        }
//        callback.onInterrupt(null);
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }
}
