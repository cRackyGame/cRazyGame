package com.inception.mediator;

import com.inception.notification.NotificationCenter;
import com.inception.otaku.CategoryActivity;

public class CategoryMediator extends MediatorBase
{
  private CategoryActivity activity;

  public void handleNotification(String paramString, Object paramObject)
  {
  }

  public void onRegister()
  {
    NotificationCenter.getInstance();
  }

  public void onUnregister()
  {
    NotificationCenter.getInstance().removeObservers(this);
  }

  public void setActivity(CategoryActivity paramCategoryActivity)
  {
    this.activity = paramCategoryActivity;
  }
}