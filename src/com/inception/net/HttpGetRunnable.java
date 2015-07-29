package com.inception.net;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.inception.request.RequestBase;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressLint({"HandlerLeak"})
public class HttpGetRunnable
  implements Runnable
{
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      Bundle localBundle = paramAnonymousMessage.getData();
      switch (paramAnonymousMessage.what)
      {
	      
	      case 1:
	        HttpGetRunnable.this.listener.httpDidSuccess(HttpGetRunnable.this.request, localBundle.getString("result"));
	        return;
	      case 2:
	    	  break;
	      default:
		        return;
      }
      HttpGetRunnable.this.listener.httpDidFailed(HttpGetRunnable.this.request, localBundle.getString("result"));
    }
  };
  private HttpListener listener;
  private RequestBase request;

  public HttpGetRunnable(RequestBase paramRequestBase, HttpListener paramHttpListener)
  {
    this.request = paramRequestBase;
    this.listener = paramHttpListener;
  }

  private void sendMessage(int paramInt, String paramString)
  {
    Message localMessage = Message.obtain(this.handler);
    localMessage.what = paramInt;
    Bundle localBundle = new Bundle();
    localBundle.putString("result", paramString);
    localMessage.setData(localBundle);
    this.handler.sendMessage(localMessage);
  }

  public void run()
  {
    String str1 = "http://zhainan.kukeku.com:8080/otakuweb3/" + this.request.url;
    try
    {
      URL localURL1 = new URL(str1);
//      localURL2 = localURL1;
//      if (localURL2 == null);
    }
    catch (MalformedURLException localMalformedURLException)
    {
      try
      {
        URL localURL2 = new URL(str1);        
        HttpURLConnection localHttpURLConnection = (HttpURLConnection)localURL2.openConnection();
        localHttpURLConnection.connect();
        InputStream localInputStream = localHttpURLConnection.getInputStream();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localInputStream, "gbk"));
        StringBuilder localStringBuilder = new StringBuilder();
        while (true)
        {
          String str2 = localBufferedReader.readLine();
          if (str2 == null)
          {
            sendMessage(1, localStringBuilder.toString());
            ConnectionMgr.getInstance().remove(this);
            localInputStream.close();
            localHttpURLConnection.disconnect();
//            return;
//            localMalformedURLException = localMalformedURLException;
//            localMalformedURLException.printStackTrace();
            localURL2 = null;
            break;
          }
          localStringBuilder.append(str2);
        }
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        sendMessage(2, "failed");
        ConnectionMgr.getInstance().remove(this);
      }
    }
  }
}
