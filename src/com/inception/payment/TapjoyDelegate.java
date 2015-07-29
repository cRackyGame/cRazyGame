package com.inception.payment;

import android.app.Application;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import com.inception.otaku.ThumbnailActivity;
import com.inception.utils.LogUtils;
import java.util.Hashtable;

public class TapjoyDelegate
{
  public static final String TAG = "EASY APP";
  public ThumbnailActivity activity = null;
  public int currentCoin = 0;
  public Application mAppContext = null;

  public TapjoyDelegate(Application paramApplication)
  {
    this.mAppContext = paramApplication;
  }

  public void awardTapPoints(int paramInt)
  {
  
  }

  public void destroy()
  {
    sendShutDownEvent();
  }

  public void earnedTapPoints(int paramInt)
  {
  }

  public void enableDisplayAdAutoRefresh(boolean paramBoolean)
  {
    
  }

  public void getAwardPointsResponse(String paramString, int paramInt)
  {
  }

  public void getAwardPointsResponseFailed(String paramString)
  {
  }

  public void getDisplayAd()
  {
 
  }

  public void getDisplayAdResponse(View paramView)
  {
    int i = this.activity.adLinearLayout.getMeasuredWidth();
    this.activity.adView = ThumbnailActivity.scaleDisplayAd(paramView, i);
    this.activity.mHandler.post(this.activity.mUpdateResults);
  }

  public void getDisplayAdResponseFailed(String paramString)
  {
  }

  public void getFullScreenAd()
  {
    
  }

  public void getFullScreenAdResponse()
  {
   
  }

  public void getFullScreenAdResponseFailed(int paramInt)
  {
  }

  public void getSpendPointsResponse(String paramString, int paramInt)
  {
  }

  public void getSpendPointsResponseFailed(String paramString)
  {
  }

  public void getTapPoints()
  {
   
  }

  public void getUpdatePoints(String paramString, int paramInt)
  {
    this.currentCoin = paramInt;
    LogUtils.log("coin:" + paramInt);
  }

  public void getUpdatePointsFailed(String paramString)
  {
  }

  public String getViewName(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "undefined type: " + paramInt;
    case 2:
      return "daily reward ad";
    case 1:
      return "fullscreen ad";
    case 0:
      return "offer wall ad";
    case 3:
    }
    return "video ad";
  }

  public void init()
  {
	  
  }

  public void sendShutDownEvent()
  {
   
  }

  public void showOffers()
  {
    
  }

  public void spendTapPoints(int paramInt)
  {
    
  }
}
