package com.example.baselibrary;

import android.text.TextUtils;
import android.util.Log;

/**
 * Create by Administrator
 * on 2018/10/29
 */
public class ILog {

    private static final String customTag = "cp_log";
    private static final boolean isDebug = true;

    private static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTag) ? tag : customTag + ":" + tag;
        return tag;
    }

    public static void e(String content) {
        if (!isDebug) {
            return;
        }
        String tag = generateTag();
        Log.e(tag, content);
    }

}
