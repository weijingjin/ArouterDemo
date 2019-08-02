package com.ovo.commonlib.intercetor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;

/**
 * 使用系统自带的StartActivity()启动后就无法插手其中任何环节了，
 * 只能交给系统管理，这就导致了在跳转失败的情况下无法降级，
 * 而是会直接抛出运营级的异常，甚至导致崩溃，这个给用户的感觉就不是很好。
 * ARouter的降级策略就允许我们在自定义降级服务，在跳转失败的时候可以自行处理
 * 这种错误情况
 *
 */
@Route(path = "/interceptor/DegradeService")
public class DegradeServiceImpl implements DegradeService {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onLost(Context context, Postcard postcard) {
        Log.e(TAG, "在跳转失败的时候可以自行处理");
    }

    @Override
    public void init(Context context) {

    }
}
