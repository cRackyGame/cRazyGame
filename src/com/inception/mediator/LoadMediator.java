package com.inception.mediator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import com.inception.model.CategoryModel;
import com.inception.model.CategoryModel.Category;
import com.inception.model.ImageModel;
import com.inception.model.ImageModel.Image;
import com.inception.notification.NotificationCenter;
import com.inception.otaku.LoadActivity;
import com.inception.otaku.MainActivity;
import com.inception.otaku.ThumbnailActivity;
import com.inception.otaku.google.R;
import com.inception.request.GetImageRequest;
import com.inception.request.RequestProxy;
import com.inception.utils.BitmapUtils;
import com.inception.utils.FileUtils;
import com.inception.utils.LogUtils;
import com.inception.utils.StringUtils;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class LoadMediator extends MediatorBase
{
  private LoadActivity loadActivity = null;

  private void handlGetImageConfig(String paramString, Object paramObject)
  {
    Hashtable localHashtable = (Hashtable)paramObject;
    String str1 = (String)localHashtable.get("handleResult");
    String str2 = (String)localHashtable.get("result");
    if (str1.equalsIgnoreCase("1"))
    {
        ImageModel localImageModel;
        ImageModel.Image localImage;    	
        localImageModel = ImageModel.getInstance();
        localImageModel.currentImageIndex = CategoryModel.getInstance().getCurrentCategory().currentImageIndex;
        localImage = (ImageModel.Image)localImageModel.images.get(localImageModel.currentImageIndex);
        if ((localImage != null) && (FileUtils.writeFile(this.loadActivity, "", localImage.category + ".xml", str2)))
        	RequestProxy.getInstance().sendGetImageRequest("getImage", localImage);
    }
    while (!str1.equalsIgnoreCase("-1"))
    {
      ImageModel localImageModel;
      ImageModel.Image localImage;
      break;
//      return;
    }
    LogUtils.log("name.equalsIgnoreCase(RequestMessage.kRequestMessageGetImageConfig -1");
    this.loadActivity.hideProgressBar();
  }

  	private void handleGetCategoryConfig(String paramString, Object paramObject)
    {
  		Hashtable localHashtable = (Hashtable)paramObject;
  		String str1 = (String)localHashtable.get("handleResult");
  		String str2 = (String)localHashtable.get("result");
  		if (str1.equalsIgnoreCase("1"))
  		{
  			FileUtils.writeFile(this.loadActivity, "", "category.xml", str2);
  			if (this.loadActivity.status.compareToIgnoreCase("LoadActivityFromCover") == 0)
  			{
  				CategoryModel localCategoryModel;
  				SharedPreferences localSharedPreferences;
  				Iterator localIterator;
  				localCategoryModel = CategoryModel.getInstance();
  				switchToCategoryActivity();
  				localSharedPreferences = this.loadActivity.getSharedPreferences("pay_preference", 0);
  				localIterator = localCategoryModel.categorys.iterator();
//		        if (localIterator.hasNext())
//		          break label108;
  			}
  		}
  		label108: 
  		while (!str1.equalsIgnoreCase("-1"))
	      while (true)
	      {
	        CategoryModel localCategoryModel;
	        SharedPreferences localSharedPreferences;
	        Iterator localIterator;
//	        return;
	        break; 
	//        CategoryModel.Category localCategory = (CategoryModel.Category)localIterator.next();
	//        localCategory.isPay = localSharedPreferences.getString(localCategory.name, "0");
	      }
  		this.loadActivity.hideProgressBar();
  		LogUtils.toastShort(this.loadActivity, StringUtils.getString(this.loadActivity, R.string.tips_server_error));
    }

  private void handleGetImage(String paramString, Object paramObject)
  {
    Hashtable localHashtable = (Hashtable)paramObject;
    String str = (String)localHashtable.get("handleResult");
    GetImageRequest localGetImageRequest = (GetImageRequest)localHashtable.get("request");
    byte[] arrayOfByte = (byte[])localHashtable.get("result");
    if (str == null);
    do
    {
      if (str.equalsIgnoreCase("1"))
      {
        Bitmap localBitmap = BitmapUtils.byteToBitmap(arrayOfByte);
        localGetImageRequest.image.bitmap = localBitmap;
        switchToMainActivity();
        //return;
        break;
      }
    }
    while (!str.equalsIgnoreCase("-1"));
    this.loadActivity.hideProgressBar();
    LogUtils.toastShort(this.loadActivity, StringUtils.getString(this.loadActivity, 2131165312));
  }

  private void switchToCategoryActivity()
  {
    Intent localIntent = new Intent(this.loadActivity, ThumbnailActivity.class);
    this.loadActivity.startActivity(localIntent);
    this.loadActivity.finish();
  }

  private void switchToMainActivity()
  {
    Intent localIntent = new Intent(this.loadActivity, MainActivity.class);
    this.loadActivity.startActivity(localIntent);
    this.loadActivity.finish();
  }

  public LoadActivity getLoadActivity()
  {
    return this.loadActivity;
  }

  public void handleNotification(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("kRequestMessageGetCategoryConfig"))
      handleGetCategoryConfig(paramString, paramObject);
    do
    {
      if (paramString.equalsIgnoreCase("kRequestMessageGetImageConfig"))
      {
        handlGetImageConfig(paramString, paramObject);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("kRequestMessageGetImage"));
    	handleGetImage(paramString, paramObject);
  }

  public void onActivityResume()
  {
    if (this.loadActivity == null);
    do
    {
      if (this.loadActivity.status.compareToIgnoreCase("LoadActivityFromCover") == 0)
      {
        RequestProxy.getInstance().sendGetCotegoryConfigRequest("getCategoryConfig", "category.xml");
        break;
      }
      if (this.loadActivity.status.compareToIgnoreCase("LoadActivityFromCategory") == 0)
      {
        CategoryModel.Category localCategory = CategoryModel.getInstance().getCategoryByName(this.loadActivity.categoryName);
        RequestProxy.getInstance().sendGetImageConfigRequst("getImageConfig", localCategory.id, localCategory.id + ".xml");
        break;
      }
    }
    while (this.loadActivity.status.compareToIgnoreCase("LoadActivityFromThumbnail") != 0);
    RequestProxy.getInstance().sendGetImageConfigRequst("getImageConfig", this.loadActivity.categoryId, this.loadActivity.categoryId + ".xml");
  }

  public void onRegister()
  {
    NotificationCenter localNotificationCenter = NotificationCenter.getInstance();
    localNotificationCenter.addObserver(this, "kRequestMessageGetCategoryConfig");
    localNotificationCenter.addObserver(this, "kRequestMessageGetImageConfig");
    localNotificationCenter.addObserver(this, "kRequestMessageGetImage");
  }

  public void onUnregister()
  {
    NotificationCenter.getInstance().removeObservers(this);
  }

  public void setLoadActivity(LoadActivity paramLoadActivity)
  {
    this.loadActivity = paramLoadActivity;
  }
}