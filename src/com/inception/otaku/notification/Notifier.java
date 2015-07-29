package com.inception.otaku.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.inception.otaku.CoverActivity;
import com.inception.utils.LogUtils;
import java.util.Random;

public class Notifier
{
  private static final Random random = new Random(System.currentTimeMillis());
  private Context context;
  private NotificationManager notificationManager;
  private SharedPreferences sharedPrefs;

  public Notifier(Context paramContext)
  {
    this.context = paramContext;
    this.sharedPrefs = paramContext.getSharedPreferences("client_preferences", 0);
    this.notificationManager = ((NotificationManager)paramContext.getSystemService("notification"));
  }

  private int getNotificationIcon()
  {
    return this.sharedPrefs.getInt("NOTIFICATION_ICON", 0);
  }

  private boolean isNotificationEnabled()
  {
    return this.sharedPrefs.getBoolean("SETTINGS_NOTIFICATION_ENABLED", true);
  }

  private boolean isNotificationSoundEnabled()
  {
    return this.sharedPrefs.getBoolean("SETTINGS_SOUND_ENABLED", true);
  }

  private boolean isNotificationToastEnabled()
  {
    return this.sharedPrefs.getBoolean("SETTINGS_TOAST_ENABLED", false);
  }

  private boolean isNotificationVibrateEnabled()
  {
    return this.sharedPrefs.getBoolean("SETTINGS_VIBRATE_ENABLED", true);
  }

  public void notify(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    LogUtils.log("notify()...");
    LogUtils.log("notificationId=" + paramString1);
    LogUtils.log("notificationApiKey=" + paramString2);
    LogUtils.log("notificationTitle=" + paramString3);
    LogUtils.log("notificationMessage=" + paramString4);
    LogUtils.log("notificationUri=" + paramString5);
    if (isNotificationEnabled())
    {
      if (isNotificationToastEnabled())
        Toast.makeText(this.context, paramString4, 1).show();
      Notification localNotification = new Notification(2130837555, paramString4, System.currentTimeMillis());
      localNotification.defaults = 4;
      if (isNotificationSoundEnabled())
        localNotification.defaults = (0x1 | localNotification.defaults);
      if (isNotificationVibrateEnabled())
        localNotification.defaults = (0x2 | localNotification.defaults);
      localNotification.flags = (0x10 | localNotification.flags);
      localNotification.when = System.currentTimeMillis();
      localNotification.tickerText = paramString4;
      Intent localIntent = new Intent(this.context, CoverActivity.class);
      localIntent.putExtra("NOTIFICATION_ID", paramString1);
      localIntent.putExtra("NOTIFICATION_API_KEY", paramString2);
      localIntent.putExtra("NOTIFICATION_TITLE", paramString3);
      localIntent.putExtra("NOTIFICATION_MESSAGE", paramString4);
      localIntent.putExtra("NOTIFICATION_URI", paramString5);
      localIntent.setFlags(268435456);
      localIntent.setFlags(8388608);
      localIntent.setFlags(1073741824);
      localIntent.setFlags(536870912);
      localIntent.setFlags(67108864);
      PendingIntent localPendingIntent = PendingIntent.getActivity(this.context, 0, localIntent, 134217728);
      RemoteViews localRemoteViews = new RemoteViews(this.context.getPackageName(), 2130903074);
      localRemoteViews.setTextViewText(2131492895, paramString4);
      localRemoteViews.setImageViewResource(2131492997, 2130837555);
      localNotification.contentView = localRemoteViews;
      localNotification.contentIntent = localPendingIntent;
      this.notificationManager.notify(random.nextInt(), localNotification);
      return;
    }
    LogUtils.log("Notificaitons disabled.");
  }
}
