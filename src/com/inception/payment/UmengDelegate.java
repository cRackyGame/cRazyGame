package com.inception.payment;

import android.app.Activity;
import android.app.Application;
import android.widget.Toast;
import com.inception.utils.LogUtils;
import org.json.JSONObject;

public class UmengDelegate
{
  public Activity mActivity;
  public Application mAppContext;

  public UmengDelegate(Application paramApplication)
  {
    this.mAppContext = paramApplication;
  }

  public void OnDownloadEnd(int paramInt)
  {
    Toast.makeText(this.mAppContext, "涓嬭浇鎴愬姛", 0).show();
  }

  public void checkUpdate(Activity paramActivity)
  {
    if (paramActivity == null)
      return;
    this.mActivity = paramActivity;
  
  }
}
