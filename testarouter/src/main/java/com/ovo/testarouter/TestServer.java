package com.ovo.testarouter;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ovo.commonlib.IProviderServer;

@Route(path = "/test/TestServer")
public class TestServer implements IProviderServer {
    private final String TAG = this.getClass().getSimpleName();
    @Override
    public void setData() {
        Log.e(TAG, "111111");
    }

    @Override
    public void init(Context context) {

    }
}
