package com.inception.payment;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import com.inception.otaku.UpdatePointsNotifier;

public class WapsDelegate
  implements UpdatePointsNotifier
{
  public int currentCoin = 0;
  public Application mAppContext = null;

  public WapsDelegate(Application paramApplication)
  {
    this.mAppContext = paramApplication;
  }

  public void getUpdatePoints(String paramString, int paramInt)
  {
    this.currentCoin = paramInt;
  }

  public void getUpdatePointsFailed(String paramString)
  {
  }

  public boolean hasPay(Activity paramActivity, String paramString)
  {
    int i = paramActivity.getSharedPreferences("pay_preference", 0).getString(paramString, "0").compareToIgnoreCase("1");
    boolean bool = false;
    if (i == 0)
      bool = true;
    return bool;
  }

  public void releaseWaps()
  {
   
  }

  public void showWapsOffer()
  {
    
  }

  public void spendPoints(int paramInt)
  {
    
  }

  public void updatePoints()
  {
   
  }
}
