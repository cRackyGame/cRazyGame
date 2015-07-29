package com.inception.otaku.notification;

public class LocalNotification
{
  private String content;
  private int second;
  private String title;

  public LocalNotification(String paramString1, String paramString2, int paramInt)
  {
    this.title = paramString1;
    this.content = paramString2;
    this.second = paramInt;
  }

  public String getContent()
  {
    return this.content;
  }

  public int getSecond()
  {
    return this.second;
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }

  public void setSecond(int paramInt)
  {
    this.second = paramInt;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }
}
