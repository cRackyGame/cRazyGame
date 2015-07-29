package com.inception.otaku;

import android.app.Application;
import com.inception.payment.DianJoyDelegate;
import com.inception.payment.GfanDelegate;
import com.inception.payment.TapjoyDelegate;
import com.inception.payment.UmengDelegate;
import com.inception.payment.WapsDelegate;

public class MainApp extends Application
{
  public static MainApp instance = null;
  public boolean isAutoPlay = false;
  public DianJoyDelegate mDianJoyDelegate = null;
  public GfanDelegate mGfanDelegate = null;
  public TapjoyDelegate mTapjoyDelegate = null;
  public UmengDelegate mUmengDelegate = null;
  public WapsDelegate mWapsDeletage = null;

  public static MainApp getInstance()
  {
    if (instance == null)
      instance = new MainApp();
    return instance;
  }

  public void onCreate()
  {
    super.onCreate();
    instance = this;
    this.mUmengDelegate = new UmengDelegate(this);
    this.mWapsDeletage = new WapsDelegate(this);
    this.mWapsDeletage.updatePoints();
    this.mGfanDelegate = new GfanDelegate(this);
    this.mGfanDelegate.init();
    this.mDianJoyDelegate = new DianJoyDelegate();
    this.mTapjoyDelegate = new TapjoyDelegate(this);
    this.mTapjoyDelegate.init();
  }

  public void onLowMemory()
  {
    super.onLowMemory();
  }

  public void onTerminate()
  {
    this.mTapjoyDelegate.destroy();
    super.onTerminate();
  }
}
