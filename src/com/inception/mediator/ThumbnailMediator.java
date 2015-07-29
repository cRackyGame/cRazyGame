package com.inception.mediator;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.inception.notification.NotificationCenter;
import com.inception.otaku.LoadActivity;
import com.inception.otaku.ThumbnailActivity;
import com.inception.request.GetThumbnailRequest;
import com.inception.utils.BitmapUtils;
import java.util.Hashtable;

public class ThumbnailMediator extends MediatorBase
{
  private ThumbnailActivity activity;

  private void handleGetThumbnail(String paramString, Object paramObject)
  {
    Hashtable localHashtable = (Hashtable)paramObject;
    String str = (String)localHashtable.get("handleResult");
    GetThumbnailRequest localGetThumbnailRequest = (GetThumbnailRequest)localHashtable.get("request");
    byte[] arrayOfByte = (byte[])localHashtable.get("result");
    if (str == null)
      return;
    if (str.equalsIgnoreCase("1"))
    {
      Bitmap localBitmap = BitmapUtils.byteToBitmap(arrayOfByte);
      ViewGroup.LayoutParams localLayoutParams = localGetThumbnailRequest.view.getLayoutParams();
      int i = localBitmap.getHeight();
      int j = localBitmap.getWidth();
      localLayoutParams.height = (i * this.activity.Image_width / j);
      localGetThumbnailRequest.view.setLayoutParams(localLayoutParams);
      localGetThumbnailRequest.view.setImageBitmap(localBitmap);
    }
    while (true)
    {
      this.activity.setProgressbarVisible(false);
      this.activity.setLoadTextViewVisible(false);
//      return;
      str.equalsIgnoreCase("-1");
    }
  }

  public void handleNotification(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("kRequestMessageGetThumbnail"))
      handleGetThumbnail(paramString, paramObject);
  }

  public void onRegister()
  {
    NotificationCenter.getInstance().addObserver(this, "kRequestMessageGetThumbnail");
  }

  public void onThumbnailClicked(String paramString)
  {
    Intent localIntent = new Intent(this.activity, LoadActivity.class);
    localIntent.putExtra("LoadActivityStatus", "LoadActivityFromThumbnail");
    localIntent.putExtra("LoadActivityCategoryId", paramString);
    this.activity.startActivity(localIntent);
    this.activity.finish();
  }

  public void onUnregister()
  {
    NotificationCenter.getInstance().removeObservers(this);
  }

  public void setActivity(ThumbnailActivity paramThumbnailActivity)
  {
    this.activity = paramThumbnailActivity;
  }
}
