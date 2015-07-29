package com.inception.otaku.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.inception.utils.LogUtils;

public class AlarmReceiver extends BroadcastReceiver
{
  public static final String NOTIFICATION_CONTENT = "notificationContent";
  public static final String NOTIFICATION_TITLE = "notificationTitle";

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    LogUtils.log("alarm received!!!!");
    Notifier localNotifier = new Notifier(paramContext);
    LogUtils.log("title: " + paramIntent.getExtras().getString("notificationTitle") + " content: " + paramIntent.getExtras().getString("notificationContent"));
    localNotifier.notify("", "", paramIntent.getExtras().getString("notificationTitle"), paramIntent.getExtras().getString("notificationContent"), "");
  }
}
