package com.inception.payment;

import android.app.Activity;
import com.inception.utils.LogUtils;

public class DianJoyDelegate
{
  public int currentCoin = 0;
  private Activity mActivity;

  public void destroy()
  {
  }

  public void getADAcountFailed(String paramString)
  {
    LogUtils.log("getADAcountFailed:" + paramString);
  }

  public void getADAcountSuccessed(String paramString, long paramLong)
  {
    LogUtils.log("getADAcountSuccessed:" + paramString + paramLong);
  }

  public void getAdAmount()
  {
  }

  public void getGetUpdateMessageFailed(String paramString)
  {
    LogUtils.log("getGetUpdateMessageFailed:" + paramString);
  }

  public void getGetUpdateMessageSuccessed(String paramString1, String paramString2)
  {
    LogUtils.log("getGetUpdateMessageSuccessed:" + paramString1 + paramString2);
  }

  public void getTotalMoneyFailed(String paramString)
  {
    LogUtils.log("getTotalMoneyFailed:" + paramString);
  }

  public void getTotalMoneySuccessed(String paramString, long paramLong)
  {
    LogUtils.log("getTotalMoneySuccessed:" + paramString + paramLong);
    this.currentCoin = ((int)paramLong);
  }

  public void init(Activity paramActivity)
  {
    this.mActivity = paramActivity;
  }

  public void sendMoney(int paramInt)
  {
    
  }

  public void showOffers()
  {

  }

  public void spendMoneyFailed(String paramString)
  {
    LogUtils.log("spendMoneyFailed:" + paramString);
  }

  public void spendMoneySuccess(long paramLong)
  {
    LogUtils.log("spendMoneySuccess:" + paramLong);
    this.currentCoin = ((int)paramLong);
  }
}