package com.unleashed.android.beeokunleashed.utils;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;

/**
 * Created by sudhanshu on 26/08/15.
 */

public class CoreLib extends Application {
    private static CoreLib me;

    public CoreLib() {
        me = this;
    }

    public static Context Context() {
        return me;
    }

    public static ContentResolver ContentResolver() {
        return me.getContentResolver();
    }
}
