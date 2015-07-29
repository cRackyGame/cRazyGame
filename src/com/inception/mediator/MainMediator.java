package com.inception.mediator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import com.inception.model.CategoryModel;
import com.inception.model.CategoryModel.Category;
import com.inception.model.ImageModel;
import com.inception.model.ImageModel.Image;
import com.inception.net.ConnectionMgr;
import com.inception.notification.NotificationCenter;
import com.inception.otaku.CategoryActivity;
import com.inception.otaku.CoverActivity;
import com.inception.otaku.MainActivity;
import com.inception.otaku.MainApp;
import com.inception.otaku.ThumbnailActivity;
import com.inception.payment.TapjoyDelegate;
import com.inception.request.RequestProxy;
import com.inception.utils.BitmapUtils;
import com.inception.utils.FileUtils;
import com.inception.utils.LogUtils;
import com.inception.utils.StringUtils;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint({"HandlerLeak"})
public class MainMediator extends MediatorBase
{
  Handler autoPlayHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
	      default:
	      case 2:
      }
      while (true)
      {
        super.handleMessage(paramAnonymousMessage);
        return;
//        MainMediator.this.onImageViewRightTouched();
      }
    }
  };
  private int autoPlayScheduleTime = 3000;
  private Timer autoPlayTimer = null;
  private TimerTask autoPlayTimerTask = null;
  Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
	      default:
	      case 1:
      }
      while (true)
      {
        super.handleMessage(paramAnonymousMessage);
        return;
//        MainMediator.this.mainActivity.hideSeekBar();
//        MainMediator.this.unscheduleSeekBar();
      }
    }
  };
  private boolean isGettingNextImage = false;
  private boolean isGettingPrevImage = false;
  private boolean isSeekBarInTouching = false;
  private MainActivity mainActivity = null;
  private int scheduleTime = 2000;
  private Timer timer = null;
  private TimerTask timerTask = null;

  private void handlGetImageConfig(String paramString, Object paramObject)
  {
    Hashtable localHashtable = (Hashtable)paramObject;
    String str1 = (String)localHashtable.get("handleResult");
    String str2 = (String)localHashtable.get("result");
    if (str1.equalsIgnoreCase("1"))
    {
		ImageModel.Image localImage;    	
		localImage = ImageModel.getInstance().getCurrentImage();
		if (localImage != null)
		{
			if (FileUtils.writeFile(this.mainActivity, "", localImage.category + ".xml", str2))
			{
				RequestProxy.getInstance().sendGetImageRequest("getImage", localImage);
			}
		}
    }
    while (!str1.equalsIgnoreCase("-1"))
    {
      ImageModel.Image localImage;
      return;
//      LogUtils.log("ImageModel.getInstance().getCurrentImage() failed");
//      return;
    }
    LogUtils.log("name.equalsIgnoreCase(RequestMessage.kRequestMessageGetImageConfig -1");
    this.mainActivity.hideLoadingView();
    LogUtils.toastShort(this.mainActivity, StringUtils.getString(this.mainActivity, 2131165312));
  }

  private void handleGetImage(String paramString, Object paramObject)
  {
    Hashtable localHashtable = (Hashtable)paramObject;
    String str = (String)localHashtable.get("handleResult");
    byte[] arrayOfByte = (byte[])localHashtable.get("result");
    ImageModel localImageModel;
    if (str.equalsIgnoreCase("1"))
    {
      Bitmap localBitmap = BitmapUtils.byteToBitmap(arrayOfByte);
      if (!localBitmap.isRecycled())
      {
        localImageModel = ImageModel.getInstance();
        if (this.isGettingNextImage)
        {
          localImageModel.currentImageIndex = (1 + localImageModel.currentImageIndex);
          CategoryModel.getInstance().getCurrentCategory().currentImageIndex = localImageModel.currentImageIndex;
          localImageModel.setBitmap(localBitmap);
          this.mainActivity.hideLoadingView();
          this.mainActivity.setImageView(localBitmap);
          this.mainActivity.showPageNumberView(1 + localImageModel.getCurrentImageIndex(), localImageModel.getTotalImageCount());
          localImageModel.releaseBitmap();
        }
      }
      else if (MainApp.getInstance().isAutoPlay)
      {
        unscheduleAutoPlay();
        scheduleAutoPlay();
      }
    }
    while (true)
    {
      localHashtable.clear();
      this.isGettingNextImage = false;
      this.isGettingPrevImage = false;
//      return;
      if (!this.isGettingPrevImage)
        break;
//      localImageModel.currentImageIndex = (-1 + localImageModel.currentImageIndex);
//      break;
//      if (str.equalsIgnoreCase("-1"))
//      {
//        this.mainActivity.hideLoadingView();
//        LogUtils.toastShort(this.mainActivity, StringUtils.getString(this.mainActivity, 2131165312));
//      }
    }
  }

  private void unscheduleSeekBar()
  {
    if (this.timer != null)
    {
      this.timer.cancel();
      this.timer = null;
    }
    if (this.timerTask != null)
    {
      this.timerTask.cancel();
      this.timerTask = null;
    }
  }

  public MainActivity getMainActivity()
  {
    return this.mainActivity;
  }

  public void handleNotification(String paramString, Object paramObject)
  {
    LogUtils.log(paramString);
    if (paramString.equalsIgnoreCase("kRequestMessageGetImageConfig"))
      handlGetImageConfig(paramString, paramObject);
    while (!paramString.equalsIgnoreCase("kRequestMessageGetImage"))
      return;
    handleGetImage(paramString, paramObject);
  }

  public void onActivityCreate()
  {
    CategoryModel localCategoryModel = CategoryModel.getInstance();
    boolean bool1 = localCategoryModel.isCurrentCategoryFree();
    boolean bool2 = localCategoryModel.isCurrentCategoryPayed();
    if ((bool1) || (bool2))
      scheduleSeekBar();
    int i = ImageModel.getInstance().getCurrentImageIndex();
    int j = ImageModel.getInstance().getTotalImageCount();
    this.mainActivity.showPageNumberView(i + 1, j);
  }

  public void onActivityPause()
  {
  }

  public void onImageViewLeftTouched()
  {
    ImageModel.Image localImage = ImageModel.getInstance().getPrevBitmap();
    if (localImage != null)
    {
      if (localImage.category != null)
      {
        unscheduleAutoPlay();
        RequestProxy.getInstance().sendGetImageRequest("getImage", localImage);
        this.mainActivity.showLoadingView();
        this.isGettingPrevImage = true;
      }
      return;
    }
    LogUtils.log("onImageViewLeftTouched error");
  }

  public void onImageViewRightTouched()
  {
    ImageModel.Image localImage = ImageModel.getInstance().getNextImage();
    if ((localImage != null) && (localImage.category != null))
    {
      CategoryModel.Category localCategory = CategoryModel.getInstance().getCurrentCategory();
      if (localCategory != null)
      {
        String str1 = localImage.isPreview;
        int i = 0;
        if (str1 != null)
        {
          int j = localImage.isPreview.compareToIgnoreCase("1");
          i = 0;
          if (j != 0)
          {
            String str2 = localCategory.chargeValue;
            i = 0;
            if (str2 != null)
            {
              int k = localCategory.chargeValue.compareToIgnoreCase("0");
              i = 0;
              if (k != 0)
                i = 1;
            }
          }
        }
        if ((localCategory.isPay != null) && (localCategory.isPay.compareToIgnoreCase("1") == 0))
          i = 0;
        if (i != 0)
          this.mainActivity.showTapjoyDialog(MainApp.getInstance().mTapjoyDelegate.currentCoin, Integer.parseInt(localCategory.chargeValue));
      }
      else
      {
        return;
      }
      unscheduleAutoPlay();
      RequestProxy.getInstance().sendGetImageRequest("getImage", localImage);
      this.mainActivity.showLoadingView();
      this.isGettingNextImage = true;
      return;
    }
    LogUtils.log("onImageViewRightTouched error");
  }

  public void onProgressChanged(int paramInt)
  {
    ImageModel.getInstance().setProgress(paramInt);
  }

  public void onRegister()
  {
    NotificationCenter localNotificationCenter = NotificationCenter.getInstance();
    localNotificationCenter.addObserver(this, "kRequestMessageGetImageConfig");
    localNotificationCenter.addObserver(this, "kRequestMessageGetImage");
  }

  public void onStartTrackingTouch()
  {
    this.isSeekBarInTouching = true;
  }

  public void onStopTrackingTouch()
  {
    ImageModel.Image localImage = ImageModel.getInstance().getImageByProgress(ImageModel.getInstance().getProgress());
    if (localImage != null)
    {
      if (localImage.category != null)
      {
//        RequestProxy.getInstance().sendGetImageRequest("getImage", localImage);
        this.mainActivity.showLoadingView();
      }
      scheduleSeekBar();
      int i = ImageModel.getInstance().getCurrentImageIndex();
      int j = ImageModel.getInstance().getTotalImageCount();
      this.mainActivity.showPageNumberView(i + 1, j);
      this.isSeekBarInTouching = false;
    }
  }

  public void onUnregister()
  {
    unscheduleSeekBar();
    unscheduleAutoPlay();
    MainApp.getInstance().isAutoPlay = false;
    NotificationCenter.getInstance().removeObservers(this);
  }

  public void scheduleAutoPlay()
  {
    if (this.autoPlayTimer == null)
      this.autoPlayTimer = new Timer();
    if (this.autoPlayTimerTask == null)
      this.autoPlayTimerTask = new TimerTask()
      {
        public void run()
        {
          Message localMessage = new Message();
          localMessage.what = 2;
          MainMediator.this.autoPlayHandler.sendMessage(localMessage);
        }
      };
    if ((this.autoPlayTimer != null) && (this.autoPlayTimerTask != null))
      this.autoPlayTimer.schedule(this.autoPlayTimerTask, this.autoPlayScheduleTime);
  }

  public void scheduleSeekBar()
  {
    unscheduleSeekBar();
    int i = ImageModel.getInstance().getCurrentProgress();
    int j = ImageModel.getInstance().getNextProgress();
    int k = this.mainActivity.getSeekBarProgress();
    if ((k < i) || (k >= j))
      this.mainActivity.setSeekBar(i);
    this.mainActivity.showSeekBar();
    if (this.timer == null)
      this.timer = new Timer();
    if (this.timerTask == null)
      this.timerTask = new TimerTask()
      {
        public void run()
        {
          if (MainMediator.this.isSeekBarInTouching)
            return;
          Message localMessage = new Message();
          localMessage.what = 1;
          MainMediator.this.handler.sendMessage(localMessage);
        }
      };
    if ((this.timer != null) && (this.timerTask != null))
      this.timer.schedule(this.timerTask, this.scheduleTime);
  }

  public void setMainActivity(MainActivity paramMainActivity)
  {
    this.mainActivity = paramMainActivity;
  }

  public void switchToCategoryActivity()
  {
    Intent localIntent = new Intent(this.mainActivity, CategoryActivity.class);
    this.mainActivity.startActivity(localIntent);
    this.mainActivity.finish();
  }

  public void switchToCoverActivity()
  {
    Intent localIntent = new Intent(this.mainActivity, CoverActivity.class);
    this.mainActivity.startActivity(localIntent);
    this.mainActivity.finish();
    ConnectionMgr.getInstance().clear();
    MediatorMgr.getInstance().unregisterAllMediator();
  }

  public void switchToThumbnailActivity()
  {
    Intent localIntent = new Intent(this.mainActivity, ThumbnailActivity.class);
    this.mainActivity.startActivity(localIntent);
    this.mainActivity.finish();
  }

  public void unscheduleAutoPlay()
  {
    if (this.autoPlayTimer != null)
    {
      this.autoPlayTimer.cancel();
      this.autoPlayTimer = null;
    }
    if (this.autoPlayTimerTask != null)
    {
      this.autoPlayTimerTask.cancel();
      this.autoPlayTimerTask = null;
    }
  }
}