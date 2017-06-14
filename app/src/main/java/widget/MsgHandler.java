package widget;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by VULCNAVSeries on 2017/6/11.
 */

public class MsgHandler extends Handler {
    private Activity activity;
    public MsgHandler(Activity activity){
        this.activity=(Activity) new WeakReference(activity).get();//使用弱引用WeakReferenc，以避免Handler内存泄露
    }
    @Override
    public void handleMessage(Message msg) {

        switch (msg.arg1) {
            case 1:
                showInfo("登录成功！");
                break;
            case 2:
                showInfo("密码错误，请重新输入！");
                break;
            case 3:
                showInfo("该用户不存在");
                break;
            case 4:
                showInfo("注册成功");
                break;
            case 5:
                showInfo("用户名已存在");
                break;
            case 6:
                showInfo("用户口令是必填项！");
                break;
            default:
                break;
        }
        super.handleMessage(msg);
    }

    /**
     * 显示提示信息
     * @param info
     */
    public void showInfo(String info)
    {
        Toast.makeText(activity.getApplicationContext(),info, Toast.LENGTH_SHORT).show();
    }

}