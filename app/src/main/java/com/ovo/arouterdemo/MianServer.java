package com.ovo.arouterdemo;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ovo.commonlib.IProviderServer;

@Route(path = "/main/MianServer")
public class MianServer implements IProviderServer {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void setData() {
        Log.e(TAG, "22222");
    }

    @Override
    public void init(Context context) {

    }
}
