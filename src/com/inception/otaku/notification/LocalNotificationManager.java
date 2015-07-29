package com.inception.otaku.notification;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import com.inception.otaku.ActivityObserver;
import com.inception.otaku.CoverActivity;
import com.inception.utils.LogUtils;
import com.inception.utils.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class LocalNotificationManager
  implements ActivityObserver
{
  private static final int MAX_NOTIFICATIONS_SUPPORT = 10;
  static LocalNotificationManager instance = null;

  public static LocalNotificationManager getInstance()
  {
    if (instance == null)
      instance = new LocalNotificationManager();
    return instance;
  }

  public static ArrayList<LocalNotification> getLocalNotifications()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; ; i++)
    {
      if (i >= 1)
        return localArrayList;
      localArrayList.add(new LocalNotification("title", StringUtils.getString(CoverActivity.activity, 2131165316), 172800));
    }
  }

  public static void installAllNotifications()
  {
    LogUtils.log("installAllNotifications()");
    ArrayList localArrayList = getLocalNotifications();
    int i = 0;
    Iterator localIterator = localArrayList.iterator();
    while (true)
    {
      if (!localIterator.hasNext());
      LocalNotification localLocalNotification;
      do
      {
//        return;
        localLocalNotification = (LocalNotification)localIterator.next();
      }
      while (i >= 10);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(i);
      LogUtils.log(String.format("installAllNotifications() request code is %d", arrayOfObject));
      int j = i + 1;
      sendAlarmNotification(localLocalNotification, i);
      i = j;
    }
  }

  private static void sendAlarmNotification(LocalNotification paramLocalNotification, int paramInt)
  {
    CoverActivity localCoverActivity = CoverActivity.activity;
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.add(13, paramLocalNotification.getSecond());
    Intent localIntent = new Intent(localCoverActivity, AlarmReceiver.class);
    localIntent.putExtra("notificationTitle", paramLocalNotification.getTitle());
    localIntent.putExtra("notificationContent", paramLocalNotification.getContent());
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(localCoverActivity, paramInt, localIntent, 268435456);
    ((AlarmManager)localCoverActivity.getSystemService("alarm")).set(0, localCalendar.getTimeInMillis(), localPendingIntent);
  }

  private void uninstallAllNotifications()
  {
    LogUtils.log("uninstallAllNotifications()");
    CoverActivity localCoverActivity = CoverActivity.activity;
    for (int i = 0; ; i++)
    {
      if (i >= 10)
        return;
      PendingIntent localPendingIntent = PendingIntent.getBroadcast(localCoverActivity, i, new Intent(localCoverActivity, AlarmReceiver.class), 536870912);
      if (localPendingIntent != null)
      {
        ((AlarmManager)localCoverActivity.getSystemService("alarm")).cancel(localPendingIntent);
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i);
        LogUtils.log(String.format("uninstallAllNotifications() request code is %d", arrayOfObject));
      }
    }
  }

  public void onCreate(Bundle paramBundle)
  {
  }

  public void onDestroy()
  {
  }

  public void onPause()
  {
  }

  public void onRestart()
  {
  }

  public void onResume()
  {
    uninstallAllNotifications();
  }

  public void onStart()
  {
  }

  public void onStop()
  {
    installAllNotifications();
  }
}
