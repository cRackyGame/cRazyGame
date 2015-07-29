package com.inception.net;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.inception.notification.NotificationCenter;
import com.inception.request.RequestBase;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

public class GetImageRunnable
  implements Runnable
{

  @SuppressLint({"HandlerLeak"})
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      Bundle localBundle = paramAnonymousMessage.getData();
      Hashtable localHashtable = new Hashtable();
      switch (paramAnonymousMessage.what)
      {
	      case 1:
	      case 2:
	      default:
      }
      
      while (true)
      {
        localHashtable.put("request", GetImageRunnable.this.request);
        localHashtable.put("result", localBundle.getByteArray("result"));
        NotificationCenter.getInstance().postNotification(GetImageRunnable.this.request.msg, localHashtable);
        return;
//        localHashtable.put("handleResult", "1");
//        continue;
//        localHashtable.put("handleResult", "-1");
      }
    }
  };
  private RequestBase request;

  public GetImageRunnable(RequestBase paramRequestBase)
  {
    this.request = paramRequestBase;
  }

  private void sendMessage(int paramInt, byte[] paramArrayOfByte)
  {
    Message localMessage = Message.obtain(this.handler);
    localMessage.what = paramInt;
    Bundle localBundle = new Bundle();
    localBundle.putByteArray("result", paramArrayOfByte);
    localMessage.setData(localBundle);
    this.handler.sendMessage(localMessage);
  }

  public void run()
  {
    String str = "http://zhainan.kukeku.com:8080/otakuweb3/" + this.request.url;
    try
    {
      URL localURL1 = new URL(str);
//      localURL2 = localURL1;
//      if (localURL2 == null);
    }
    catch (MalformedURLException localMalformedURLException)
    {
      try
      {
//        URL localURL2;
        URL localURL2 = new URL(str);
        HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL2.openConnection();
        localHttpURLConnection.connect();
        InputStream localInputStream = localHttpURLConnection.getInputStream();
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        byte[] arrayOfByte = new byte[1024];
        while (true)
        {
          int i = localInputStream.read(arrayOfByte);
          if (i == -1)
          {
            localInputStream.close();
            sendMessage(1, localByteArrayOutputStream.toByteArray());
            ConnectionMgr.getInstance().remove(this);
            localByteArrayOutputStream.close();
            localInputStream.close();
            localHttpURLConnection.disconnect();
//            return;
//            localMalformedURLException = localMalformedURLException;
//            localMalformedURLException.printStackTrace();
//            localURL2 = null;
            break;
          }
          localByteArrayOutputStream.write(arrayOfByte, 0, i);
        }
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        sendMessage(2, "failed".getBytes());
        ConnectionMgr.getInstance().remove(this);
      }
    }
  }
}
