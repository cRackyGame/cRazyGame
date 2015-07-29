package com.inception.request;

import android.util.Log;
import android.widget.ImageView;

import com.inception.model.ImageModel;
import com.inception.model.ImageModel.Image;
import com.inception.net.ConnectionMgr;
import com.inception.net.GetImageRunnable;
import com.inception.net.HttpGetRunnable;

public class RequestProxy
{
  private static RequestProxy instance = null;

  public static RequestProxy getInstance()
  {
    if (instance == null)
      instance = new RequestProxy();
    return instance;
  }

  public void sendGetCotegoryConfigRequest(String paramString1, String paramString2)
  {
	  Log.d("wuhongbo", "jjjjjjjjjjjjjjjjjjjjjjddddddjjjjjjjjjjjjjjjjjjjjjjjjjjj");
    GetCategoryConfigRequest localGetCategoryConfigRequest = new GetCategoryConfigRequest();
    localGetCategoryConfigRequest.msg = "kRequestMessageGetCategoryConfig";
    localGetCategoryConfigRequest.url = ("MainServlet?action=" + paramString1);
    localGetCategoryConfigRequest.action = paramString1;
    localGetCategoryConfigRequest.filename = paramString2;
    HttpGetRunnable localHttpGetRunnable = new HttpGetRunnable(localGetCategoryConfigRequest, RequestHandler.getInstance());
    ConnectionMgr.getInstance().add(localHttpGetRunnable);
  }

  public void sendGetImageConfigRequst(String paramString1, String paramString2, String paramString3)
  {
    GetImageConfigRequest localGetImageConfigRequest = new GetImageConfigRequest();
    String str = paramString2 + "/" + paramString3;
    localGetImageConfigRequest.msg = "kRequestMessageGetImageConfig";
    localGetImageConfigRequest.url = ("MainServlet?action=" + paramString1 + "&filename=" + str);
    localGetImageConfigRequest.action = paramString1;
    localGetImageConfigRequest.dirname = paramString2;
    localGetImageConfigRequest.filename = paramString3;
    HttpGetRunnable localHttpGetRunnable = new HttpGetRunnable(localGetImageConfigRequest, RequestHandler.getInstance());
    ConnectionMgr.getInstance().add(localHttpGetRunnable);
  }

  public void sendGetImageRequest(String paramString, ImageModel.Image paramImage)
  {
    GetImageRequest localGetImageRequest = new GetImageRequest();
    String str = paramImage.category + "/" + paramImage.name;
    localGetImageRequest.msg = "kRequestMessageGetImage";
    localGetImageRequest.url = ("MainServlet?action=" + paramString + "&filename=" + str);
    localGetImageRequest.action = paramString;
    localGetImageRequest.image = paramImage;
    GetImageRunnable localGetImageRunnable = new GetImageRunnable(localGetImageRequest);
    ConnectionMgr.getInstance().add(localGetImageRunnable);
  }

  public void sendGetThumbnailRequest(String paramString1, String paramString2, ImageView paramImageView)
  {
    String str = paramString2 + "/thumbnail_" + paramString2 + ".jpg";
    GetThumbnailRequest localGetThumbnailRequest = new GetThumbnailRequest();
    localGetThumbnailRequest.msg = "kRequestMessageGetThumbnail";
    localGetThumbnailRequest.url = ("MainServlet?action=" + paramString1 + "&filename=" + str);
    localGetThumbnailRequest.action = paramString1;
    localGetThumbnailRequest.categoryId = paramString2;
    localGetThumbnailRequest.view = paramImageView;
    GetImageRunnable localGetImageRunnable = new GetImageRunnable(localGetThumbnailRequest);
    ConnectionMgr.getInstance().add(localGetImageRunnable);
  }
}
