package com.inception.net;

import com.inception.request.RequestBase;

public abstract interface HttpListener
{
  public abstract void httpDidFailed(RequestBase paramRequestBase, String paramString);

  public abstract void httpDidSuccess(RequestBase paramRequestBase, String paramString);
}