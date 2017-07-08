package widget;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by VULCNAVSeries on 2017/7/8.
 */

public class SMSContentObserver extends ContentObserver {
    private ContentResolver mResolver;
    private Context mContext;
    private Handler mHandler;
    //收件箱短信
    public static final String SMS_URI_INBOX = "content://sms/inbox";
    public SMSContentObserver(Context context, Handler handler) {
        super(handler);
        this.mContext=context;
       this.mHandler=handler;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        super.onChange(selfChange, uri);
        if (uri.toString().equals("content://sms/raw")){
            return;
        }
        String code = "";
        Uri inboxUri = Uri.parse("content://sms/inbox");
        // 按短信ID倒序排序，避免修改手机时间数据不正确
        Cursor c = mContext.getContentResolver().query(inboxUri, null, null,
                null, "_id desc");
        if (c != null) {
            if (c.moveToFirst()) {
                String body = c.getString(c.getColumnIndex("body")); // 短信内容
                if (body.substring(0,6).equals("【珞珈汉语】")){
                    code=body.substring(6,12);
                }
                    mHandler.obtainMessage(1, code).sendToTarget();
                }

        }
    }
}