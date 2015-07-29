package com.inception.notification;

public class NotificationObserver
{
  private NotificationHandler handler = null;
  private String name = null;
  private Object param = null;

  public NotificationObserver(NotificationHandler paramNotificationHandler, String paramString, Object paramObject)
  {
    this.handler = paramNotificationHandler;
    this.name = paramString;
    this.param = paramObject;
  }

  public NotificationHandler getHandler()
  {
    return this.handler;
  }

  public String getName()
  {
    return this.name;
  }

  public Object getParam()
  {
    return this.param;
  }

  public void setHandler(NotificationHandler paramNotificationHandler)
  {
    this.handler = paramNotificationHandler;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setParam(Object paramObject)
  {
    this.param = paramObject;
  }
}