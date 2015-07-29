package com.inception.request;

import com.inception.model.CategoryModel;
import com.inception.model.ImageModel;
import com.inception.net.HttpListener;
import com.inception.notification.NotificationCenter;
import com.inception.utils.LogUtils;
import com.inception.utils.XmlUtils;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RequestHandler
  implements HttpListener
{
  private static RequestHandler instance = null;

  public static RequestHandler getInstance()
  {
    if (instance == null)
      instance = new RequestHandler();
    return instance;
  }

  private int handleGetCategoryConfigRequest(RequestBase paramRequestBase, String paramString)
  {
//    List localList = XmlUtils.categoryXMLParser(paramString);
//    if (localList != null)
//    {
//      CategoryModel.getInstance().setCategorys((ArrayList)localList);
//      return 1;
//    }
    return -1;
  }

  private int handleGetImageConfigRequest(RequestBase paramRequestBase, String paramString)
  {
//    List localList = XmlUtils.imageXMLParser(paramString);
//    if (localList != null)
//    {
//      ImageModel.getInstance().initWithImages((ArrayList)localList);
//      return 1;
//    }
    return -1;
  }

  private int handleGetImageRequest(RequestBase paramRequestBase, String paramString)
  {
    if (paramString != null)
      return 1;
    return -1;
  }

  private int handleGetThumbnailRequest(RequestBase paramRequestBase, String paramString)
  {
    if (paramRequestBase != null)
      return 1;
    return -1;
  }

  public void httpDidFailed(RequestBase paramRequestBase, String paramString)
  {
    LogUtils.log(paramString.toString());
    Hashtable localHashtable = new Hashtable();
    localHashtable.put("handleResult", "-1");
    NotificationCenter.getInstance().postNotification(paramRequestBase.msg, localHashtable);
  }

  public void httpDidSuccess(RequestBase paramRequestBase, String paramString)
  {
    LogUtils.log(paramString);
    int i = -1;
    if (paramRequestBase.msg.equalsIgnoreCase("kRequestMessageGetCategoryConfig"))
      i = handleGetCategoryConfigRequest(paramRequestBase, paramString);
    while (true)
    {
      Hashtable localHashtable = new Hashtable();
      localHashtable.put("handleResult", Integer.toString(i));
      localHashtable.put("request", paramRequestBase);
      localHashtable.put("result", paramString);
      NotificationCenter.getInstance().postNotification(paramRequestBase.msg, localHashtable);
//      return;
      if (paramRequestBase.msg.equalsIgnoreCase("kRequestMessageGetImageConfig"))
        i = handleGetImageConfigRequest(paramRequestBase, paramString);
      else if (paramRequestBase.msg.equalsIgnoreCase("kRequestMessageGetImage"))
        i = handleGetImageRequest(paramRequestBase, paramString);
      else if (paramRequestBase.msg.equalsIgnoreCase("kRequestMessageGetThumbnail"))
        i = handleGetThumbnailRequest(paramRequestBase, paramString);
    }
  }
}
