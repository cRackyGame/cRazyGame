package com.inception.notification;

import java.util.ArrayList;
import java.util.Iterator;

public class NotificationCenter
{
  private static NotificationCenter instance = null;
  private ArrayList<NotificationObserver> observers = new ArrayList<NotificationObserver>();

  public static NotificationCenter getInstance()
  {
    if (instance == null)
      instance = new NotificationCenter();
    return instance;
  }

  private boolean isObserverExist(NotificationHandler paramNotificationHandler, String paramString)
  {
    Iterator localIterator = this.observers.iterator();
    NotificationObserver localNotificationObserver;
    do
    {
      if (!localIterator.hasNext())
        return false;
      localNotificationObserver = (NotificationObserver)localIterator.next();
    }
    while ((localNotificationObserver.getHandler() != paramNotificationHandler) || (!localNotificationObserver.getName().equalsIgnoreCase(paramString)));
    return true;
  }

  public void addObserver(NotificationHandler paramNotificationHandler, String paramString)
  {
    addObserver(paramNotificationHandler, paramString, null);
  }

  public void addObserver(NotificationHandler paramNotificationHandler, String paramString, Object paramObject)
  {
    if (isObserverExist(paramNotificationHandler, paramString));
    NotificationObserver localNotificationObserver;
    do
    {
//      return;
      localNotificationObserver = new NotificationObserver(paramNotificationHandler, paramString, paramObject);
    }
    while (localNotificationObserver == null);
    this.observers.add(localNotificationObserver);
  }

  public void postNotification(String paramString, Object paramObject)
  {
    Iterator localIterator = this.observers.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      NotificationObserver localNotificationObserver = (NotificationObserver)localIterator.next();
      if (localNotificationObserver.getName().equalsIgnoreCase(paramString))
        localNotificationObserver.getHandler().handleNotification(paramString, paramObject);
    }
  }

  public void removeObserver(NotificationHandler paramNotificationHandler, String paramString)
  {
    Iterator localIterator = this.observers.iterator();
    NotificationObserver localNotificationObserver;
    do
    {
      if (!localIterator.hasNext())
        return;
      localNotificationObserver = (NotificationObserver)localIterator.next();
    }
    while ((localNotificationObserver.getHandler() != paramNotificationHandler) || (!localNotificationObserver.getName().equalsIgnoreCase(paramString)));
    localIterator.remove();
  }

  public void removeObservers(NotificationHandler paramNotificationHandler)
  {
    Iterator localIterator = this.observers.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      if (((NotificationObserver)localIterator.next()).getHandler() == paramNotificationHandler)
        localIterator.remove();
    }
  }
}
