package com.inception.otaku;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SDKUtils
{
  private Context a;
  private PackageManager b;
  private ApplicationInfo c;
  private String d = "";
  private PackageInfo e;
  private Handler f;
  private RelativeLayout g;
  private LinearLayout h;
  private AdViewCloseListener i;

  public SDKUtils(Context paramContext)
  {
    this.a = paramContext;
  }

  public SDKUtils(Context paramContext, Handler paramHandler, RelativeLayout paramRelativeLayout, LinearLayout paramLinearLayout, AdViewCloseListener paramAdViewCloseListener)
  {
    this.a = paramContext;
    this.f = paramHandler;
    this.g = paramRelativeLayout;
    this.h = paramLinearLayout;
    this.i = paramAdViewCloseListener;
  }

  public static int getDisplaySize(Context paramContext)
  {
    int j = ((Activity)paramContext).getWindowManager().getDefaultDisplay().getWidth();
    int k = ((Activity)paramContext).getWindowManager().getDefaultDisplay().getHeight();
    if (j < k)
    {
      if (j == 320)
        return 320;
      if (j < 320)
        return 240;
    }
    else
    {
      if (k == 320)
        return 320;
      if (k < 320)
        return 240;
    }
    return 480;
  }

  public void callTel(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.DIAL");
    localIntent.setData(Uri.parse("tel:" + paramString));
    this.a.startActivity(localIntent);
  }

  public void close()
  {
    ((Activity)this.a).finish();
  }

  public void closeAd()
  {
    try
    {
//      this.f.post(new bn(this));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void closeOfDialog(String paramString)
  {
//    submit((String)AppConnect.e(this.a).get("message_title"), paramString);
  }

  public void closeSubmit(String paramString)
  {
    Toast.makeText(this.a, paramString, 1).show();
    ((Activity)this.a).finish();
  }

  public void deleteLocalFiles(File paramFile)
  {
    try
    {
      if (paramFile.exists())
      {
        if (paramFile.isFile())
        {
          paramFile.delete();
          return;
        }
        if (paramFile.isDirectory())
        {
          File[] arrayOfFile = paramFile.listFiles();
          int j = arrayOfFile.length;
          for (int k = 0; k < j; k++)
            deleteLocalFiles(arrayOfFile[k]);
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void full_screen()
  {
//    this.f.post(new bq(this));
  }

  public String[] getAllPermissions()
  {
    PackageManager localPackageManager = this.a.getPackageManager();
    try
    {
      String[] arrayOfString = localPackageManager.getPackageInfo(this.a.getPackageName(), 4096).requestedPermissions;
      return arrayOfString;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public Map getAppInfoMap(String paramString)
  {
    try
    {
      HashMap localHashMap = new HashMap();
      PackageManager localPackageManager = this.a.getPackageManager();
      Intent localIntent = new Intent("android.intent.action.MAIN", null);
      localIntent.addCategory("android.intent.category.LAUNCHER");
      List localList = localPackageManager.queryIntentActivities(localIntent, 1);
      for (int j = 0; j < localList.size(); j++)
      {
        ResolveInfo localResolveInfo = (ResolveInfo)localList.get(j);
        if (localResolveInfo.activityInfo.packageName.equals(paramString))
        {
          String str1 = localResolveInfo.loadLabel(localPackageManager).toString();
          int k = localResolveInfo.activityInfo.applicationInfo.icon;
          String str2 = localResolveInfo.activityInfo.name;
          if ((str2 != null) && (!"".equals(str2.trim())))
          {
            localHashMap.put("appName", str1);
            localHashMap.put("appIcon", Integer.valueOf(k));
            localHashMap.put("activityName", str2);
            return localHashMap;
          }
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public String getAppName()
  {
    try
    {
      String str = (String)this.a.getApplicationInfo().loadLabel(this.a.getPackageManager());
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }

  public String getAppVersion(String paramString)
  {
    try
    {
      Context localContext = this.a.createPackageContext(paramString, 3);
      this.b = localContext.getPackageManager();
      this.e = this.b.getPackageInfo(localContext.getPackageName(), 0);
      if (this.e != null)
      {
        String str = this.e.versionName;
        if ((str != null) && (!"".equals(str.trim())))
          return str;
        Log.i("APP_SDK", "The app is not exist.");
      }
      return "";
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public String getBrowserPackageName(String paramString)
  {
    try
    {
      String str1 = getInstalled();
      if ((paramString != null) && (!"".equals(paramString.trim())))
      {
        if (paramString.indexOf(";") < 0)
        {
//          if (str1.contains(paramString))
//            break label104;
//          break label106;
        }
        String[] arrayOfString = paramString.split(";");
        for (int j = 0; j < arrayOfString.length; j++)
          if (str1.contains(arrayOfString[j]))
          {
            String str2 = arrayOfString[j];
            return str2;
          }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return "";
    }
    paramString = "";
    label104: return paramString;
//    label106: return "";
  }

  public String getCountryCode()
  {
    return Locale.getDefault().getCountry();
  }

  public String getDeviceName()
  {
    return Build.MODEL;
  }

  public int getDeviceOSVersion()
  {
    return Integer.parseInt(Build.VERSION.SDK);
  }

  public String getImsi()
  {
    try
    {
      TelephonyManager localTelephonyManager = (TelephonyManager)this.a.getSystemService("phone");
      if (localTelephonyManager != null)
      {
        String str = localTelephonyManager.getSubscriberId();
        return str;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }

  public String getInstalled()
  {
    Object localObject1 = "";
    while (true)
    {
      try
      {
        this.b = this.a.getPackageManager();
        List localList = this.b.getInstalledPackages(0);
        int j = 0;
        if (j < localList.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)localList.get(j);
          int k = localPackageInfo.applicationInfo.flags;
//          if ((k & 0x1) > 0)
//            break label123;
          String str = (String)localObject1 + localPackageInfo.packageName + ";";
//          localObject2 = str;
          j++;
//          localObject1 = localObject2;
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
//      return localObject1;
//      label123: Object localObject2 = localObject1;
    }
  }

  public String getLanguageCode()
  {
    return Locale.getDefault().getLanguage();
  }

  public List getList(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString;
    int j;
    if ((paramString != null) && (!"".equals(paramString)) && (paramString.indexOf("[;]") >= 0))
    {
      arrayOfString = paramString.split("\\[;\\]");
      j = 0;
    }
//    while (j < arrayOfString.length)
//    {
//      localArrayList.add(arrayOfString[j]);
//      j++;
//      continue;
//      localArrayList.add(paramString);
//    }
    return localArrayList;
  }

  public String getMac_Address()
  {
    try
    {
      if (hasThePermission("ACCESS_WIFI_STATE"))
      {
        WifiInfo localWifiInfo = ((WifiManager)this.a.getSystemService("wifi")).getConnectionInfo();
        if (localWifiInfo != null)
        {
          String str = localWifiInfo.getMacAddress();
          if ((str != null) && (!"".equals(str.trim())))
            return str;
          Log.i("APP_SDK", "The mac address is not found!");
        }
        while (true)
        {
          return "";
//          Log.i("APP_SDK", "Pleass check the Network connection!");
        }
      }
      return null;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        continue;
//        Log.i("APP_SDK", "Permission.ACCESS_WIFI_STATE is not found or the device is Emulator, Please check it!");
      }
    }
  }

  public InputStream getNetDataToStream(String paramString)
  {
    try
    {
//      InputStream localInputStream = ((HttpResponse)new bg(this.a).a(paramString, null, null)[0]).getEntity().getContent();
//      return localInputStream;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public String getNodeTrimValues(NodeList paramNodeList)
  {
    Object localObject1 = "";
    int j = 0;
    if (j < paramNodeList.getLength())
    {
      Element localElement = (Element)paramNodeList.item(j);
//      if (localElement == null)
//        break label186;
      NodeList localNodeList = localElement.getChildNodes();
      if (localNodeList.getLength() > 0)
      {
//        localObject2 = localObject1;
//        for (int k = 0; k < localNodeList.getLength(); k++)
//        {
//          Node localNode = localNodeList.item(k);
//          if (localNode != null)
//            localObject2 = (String)localObject2 + localNode.getNodeValue() + "[;]";
//        }
      }
      return null;
    }
    label186: for (Object localObject2 = (String)localObject1 + "a[;]"; ; localObject2 = localObject1)
    {
      j++;
      localObject1 = localObject2;
      break;
//      if ((localObject1 != null) && (!((String)localObject1).equals("")))
//        return ((String)localObject1).substring(0, -3 + ((String)localObject1).length()).trim();
//      return null;
    }
    return null;
  }

  public String getParams()
  {
//    return AppConnect.getInstance(this.a).c(this.a);
	  return null;
  }

  public String getResponseResult(HttpResponse paramHttpResponse)
  {
    try
    {
      String str = EntityUtils.toString(paramHttpResponse.getEntity());
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }

  public String getRunningAppPackageNames()
  {
    Object localObject2;
    for (Object localObject1 = ""; ; localObject1 = localObject2)
    {
      try
      {
        Iterator localIterator = ((ActivityManager)this.a.getSystemService("activity")).getRunningAppProcesses().iterator();
        if (localIterator.hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
//          if (!getInstalled().contains(localRunningAppProcessInfo.processName))
//            break label143;
          localObject2 = (String)localObject1 + localRunningAppProcessInfo.processName + ";";
          continue;
        }
        if ((localObject1 != null) && (!"".equals(((String)localObject1).trim())) && (((String)localObject1).endsWith(";")))
        {
          String str = ((String)localObject1).substring(0, -1 + ((String)localObject1).length());
          return str;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return "";
//      label143: localObject2 = localObject1;
    }
  }

  public String getSDKVersion()
  {
    return "1.8.5";
  }

  public String getScreenStatus()
  {
    try
    {
      if (this.a.getResources().getConfiguration().orientation == 1)
        return "true";
      if (this.a.getResources().getConfiguration().orientation == 2)
        return "false";
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }

  // ERROR //
  public String getUdid()
  {
	  return null;
  }

  public String getWAPS_ID()
  {
//    return AppConnect.d(this.a);
	  return null;
  }

  public String getWAPS_PID()
  {
    this.b = this.a.getPackageManager();
    String str = "";
    try
    {
      this.c = this.b.getApplicationInfo(this.a.getPackageName(), 128);
      if ((this.c != null) && (this.c.metaData != null))
      {
        Object localObject = this.c.metaData.get("WAPS_PID");
        if (localObject != null)
        {
          this.d = localObject.toString();
          if ((this.d != null) && (!this.d.equals("")))
            str = this.d;
        }
      }
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return str;
  }

  public void goToTargetBrowser(String paramString1, String paramString2, PackageManager paramPackageManager)
  {
    try
    {
      Intent localIntent = goToTargetBrowser_Intent(paramString1, paramString2, paramPackageManager);
      this.a.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public Intent goToTargetBrowser_Intent(String paramString1, String paramString2, PackageManager paramPackageManager)
  {
    try
    {
      Intent localIntent = goToTargetBrowser_Intent(paramString1, "", paramString2, paramPackageManager);
      if (localIntent != null)
        return localIntent;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public Intent goToTargetBrowser_Intent(String paramString1, String paramString2, String paramString3, PackageManager paramPackageManager)
  {
    try
    {
      if (getInstalled().contains(paramString1))
      {
        Intent localIntent = paramPackageManager.getLaunchIntentForPackage(paramString1);
        if ((paramString2 != null) && (!"".equals(paramString2.trim())))
        {
          localIntent = new Intent();
          localIntent.setClassName(paramString1, paramString2);
        }
        localIntent.setAction("android.intent.action.VIEW");
        localIntent.addCategory("android.intent.category.DEFAULT");
        localIntent.setData(Uri.parse(paramString3));
        return localIntent;
      }
      new Intent("android.intent.action.VIEW", Uri.parse(paramString3)).setFlags(268435456);
      return null;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public boolean hasThePermission(String paramString)
  {
    try
    {
      String[] arrayOfString = getAllPermissions();
      boolean bool1 = false;
      int k;
      if (arrayOfString != null)
      {
        int j = arrayOfString.length;
        bool1 = false;
        if (j > 0)
          k = arrayOfString.length;
      }
      for (int m = 0; ; m++)
      {
        bool1 = false;
//        if (m < k)
//        {
//          String str = arrayOfString[m];
//          if (!bg.a(paramString))
//          {
//            boolean bool2 = str.contains(paramString);
//            if (bool2)
//              bool1 = true;
//          }
//        }
//        else
//        {
//          return bool1;
//        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public void hideAd()
  {
    try
    {
//      this.f.post(new bo(this));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public int initAdWidth()
  {
    try
    {
      if (this.a.getResources().getConfiguration().orientation == 1)
        return ((Activity)this.a).getWindowManager().getDefaultDisplay().getWidth();
      if (this.a.getResources().getConfiguration().orientation == 2)
      {
        int j = ((Activity)this.a).getWindowManager().getDefaultDisplay().getHeight();
        return j;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

  public boolean isCmwap()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)this.a.getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.getExtraInfo() != null) && (localNetworkInfo.getExtraInfo().toLowerCase().contains("wap"));
  }

  public boolean isConnect()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)this.a.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
        return true;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public String isInstalled(String paramString)
  {
    try
    {
      if (this.a.getPackageManager().getLaunchIntentForPackage(paramString) != null)
        return "true";
      return "false";
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }

  // ERROR //
  public boolean isTimeLimited(String paramString1, String paramString2)
  {
	  return true;
  }

  public String isVisible()
  {
    if (((Activity)this.a).hasWindowFocus())
      return "true";
    return "false";
  }

  public boolean isWifi()
  {
    while (true)
    {
      try
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)this.a.getSystemService("connectivity")).getActiveNetworkInfo();
        if (localNetworkInfo != null)
        {
          if (!localNetworkInfo.getTypeName().toLowerCase().equals("mobile"))
          {
//            localObject = localNetworkInfo.getTypeName().toLowerCase();
//            if ("wifi".equals(localObject))
//              return true;
          }
          else
          {
//            String str = localNetworkInfo.getExtraInfo().toLowerCase();
//            localObject = str;
            continue;
          }
          return false;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return false;
      }
      Object localObject = "";
    }
  }

  public void load(String paramString)
  {
    if (paramString != null)
      try
      {
        if (!"".equals(paramString))
        {
          this.b = this.a.getPackageManager();
          Intent localIntent = this.b.getLaunchIntentForPackage(paramString);
          if (localIntent != null)
          {
            this.a.startActivity(localIntent);
            return;
          }
          Log.i("APP_SDK", "The app is not exist.");
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
  }

  public InputStream loadStreamFromLocal(String paramString1, String paramString2)
  {
    try
    {
      if ("mounted".equals(Environment.getExternalStorageState()))
      {
        String str = Environment.getExternalStorageDirectory().toString() + paramString2;
        File localFile2 = new File(str + "/" + paramString1);
        if ((localFile2.exists()) && (localFile2.length() > 0L))
          return new FileInputStream(localFile2);
      }
      File localFile1 = this.a.getFileStreamPath(paramString1);
      if ((localFile1.exists()) && (localFile1.length() > 0L))
      {
        FileInputStream localFileInputStream = new FileInputStream(localFile1);
        return localFileInputStream;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  // ERROR //
  public String loadStringFromLocal(String paramString1, String paramString2)
  {
	  return null;
  }

  public void openAd()
  {
    openAd("");
  }

  public void openAd(String paramString)
  {
    try
    {
//      this.f.post(new bp(this, paramString));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void openUrlByBrowser(String paramString1, String paramString2)
  {
    try
    {
      String str = getBrowserPackageName(paramString1);
      if ((str != null) && (!"".equals(str.trim())))
      {
        goToTargetBrowser(str, paramString2, this.a.getPackageManager());
        return;
      }
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
      localIntent.setFlags(268435456);
      this.a.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public Intent openUrlByBrowser_Intent(String paramString1, String paramString2)
  {
    try
    {
      String str = getBrowserPackageName(paramString1);
      if ((str != null) && (!"".equals(str.trim())))
        return goToTargetBrowser_Intent(str, paramString2, this.a.getPackageManager());
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramString2));
      localIntent.setFlags(268435456);
      return localIntent;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public String replaceData(String paramString)
  {
    if ((!paramString.equals("")) && (paramString.equals("a")))
      paramString = paramString.replace("a", "");
    return paramString;
  }

  // ERROR //
  public void saveDataToLocal(InputStream paramInputStream, String paramString1, String paramString2, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: sipush 10240
    //   6: newarray byte
    //   8: astore 10
    //   10: ldc_w 690
    //   13: invokestatic 695	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   16: invokevirtual 254	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   19: istore 11
    //   21: aconst_null
    //   22: astore 5
    //   24: iload 11
    //   26: ifeq +210 -> 236
    //   29: new 86	java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial 87	java/lang/StringBuilder:<init>	()V
    //   36: invokestatic 699	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   39: invokevirtual 700	java/io/File:toString	()Ljava/lang/String;
    //   42: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: aload_3
    //   46: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   52: astore 12
    //   54: new 168	java/io/File
    //   57: dup
    //   58: aload 12
    //   60: invokespecial 703	java/io/File:<init>	(Ljava/lang/String;)V
    //   63: astore 13
    //   65: new 168	java/io/File
    //   68: dup
    //   69: new 86	java/lang/StringBuilder
    //   72: dup
    //   73: invokespecial 87	java/lang/StringBuilder:<init>	()V
    //   76: aload 12
    //   78: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: ldc_w 702
    //   84: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   90: aload_2
    //   91: invokespecial 759	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   94: astore 14
    //   96: aload 13
    //   98: invokevirtual 172	java/io/File:exists	()Z
    //   101: istore 15
    //   103: aconst_null
    //   104: astore 5
    //   106: iload 15
    //   108: ifne +9 -> 117
    //   111: aload 13
    //   113: invokevirtual 762	java/io/File:mkdirs	()Z
    //   116: pop
    //   117: aload 14
    //   119: invokevirtual 172	java/io/File:exists	()Z
    //   122: istore 17
    //   124: aconst_null
    //   125: astore 5
    //   127: iload 17
    //   129: ifne +9 -> 138
    //   132: aload 14
    //   134: invokevirtual 765	java/io/File:createNewFile	()Z
    //   137: pop
    //   138: aconst_null
    //   139: astore 5
    //   141: aload 14
    //   143: ifnull +67 -> 210
    //   146: new 767	java/io/FileOutputStream
    //   149: dup
    //   150: aload 14
    //   152: invokespecial 768	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   155: astore 19
    //   157: aload_1
    //   158: aload 10
    //   160: invokevirtual 774	java/io/InputStream:read	([B)I
    //   163: istore 22
    //   165: iload 22
    //   167: iconst_m1
    //   168: if_icmpeq +45 -> 213
    //   171: aload 19
    //   173: aload 10
    //   175: iconst_0
    //   176: iload 22
    //   178: invokevirtual 778	java/io/FileOutputStream:write	([BII)V
    //   181: goto -24 -> 157
    //   184: astore 21
    //   186: aload 19
    //   188: astore 5
    //   190: aload 21
    //   192: astore 8
    //   194: aload 8
    //   196: invokevirtual 134	java/lang/Exception:printStackTrace	()V
    //   199: aload 5
    //   201: ifnull +8 -> 209
    //   204: aload 5
    //   206: invokevirtual 779	java/io/FileOutputStream:close	()V
    //   209: return
    //   210: aconst_null
    //   211: astore 19
    //   213: aload 19
    //   215: astore 5
    //   217: aload 5
    //   219: ifnull -10 -> 209
    //   222: aload 5
    //   224: invokevirtual 779	java/io/FileOutputStream:close	()V
    //   227: return
    //   228: astore 23
    //   230: aload 23
    //   232: invokevirtual 735	java/io/IOException:printStackTrace	()V
    //   235: return
    //   236: aconst_null
    //   237: astore 5
    //   239: iload 4
    //   241: ifeq -24 -> 217
    //   244: aload_0
    //   245: getfield 33	com/inception/otaku/SDKUtils:a	Landroid/content/Context;
    //   248: aload_2
    //   249: iconst_3
    //   250: invokevirtual 783	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   253: astore 5
    //   255: sipush 10240
    //   258: newarray byte
    //   260: astore 24
    //   262: aload_1
    //   263: aload 24
    //   265: invokevirtual 774	java/io/InputStream:read	([B)I
    //   268: istore 25
    //   270: iload 25
    //   272: iconst_m1
    //   273: if_icmpeq -56 -> 217
    //   276: aload 5
    //   278: aload 24
    //   280: iconst_0
    //   281: iload 25
    //   283: invokevirtual 778	java/io/FileOutputStream:write	([BII)V
    //   286: goto -24 -> 262
    //   289: astore 8
    //   291: goto -97 -> 194
    //   294: astore 9
    //   296: aload 9
    //   298: invokevirtual 735	java/io/IOException:printStackTrace	()V
    //   301: return
    //   302: astore 6
    //   304: aload 5
    //   306: ifnull +8 -> 314
    //   309: aload 5
    //   311: invokevirtual 779	java/io/FileOutputStream:close	()V
    //   314: aload 6
    //   316: athrow
    //   317: astore 7
    //   319: aload 7
    //   321: invokevirtual 735	java/io/IOException:printStackTrace	()V
    //   324: goto -10 -> 314
    //   327: astore 20
    //   329: aload 19
    //   331: astore 5
    //   333: aload 20
    //   335: astore 6
    //   337: goto -33 -> 304
    //
    // Exception table:
    //   from	to	target	type
    //   157	165	184	java/lang/Exception
    //   171	181	184	java/lang/Exception
    //   222	227	228	java/io/IOException
    //   3	21	289	java/lang/Exception
    //   29	103	289	java/lang/Exception
    //   111	117	289	java/lang/Exception
    //   117	124	289	java/lang/Exception
    //   132	138	289	java/lang/Exception
    //   146	157	289	java/lang/Exception
    //   244	262	289	java/lang/Exception
    //   262	270	289	java/lang/Exception
    //   276	286	289	java/lang/Exception
    //   204	209	294	java/io/IOException
    //   3	21	302	finally
    //   29	103	302	finally
    //   111	117	302	finally
    //   117	124	302	finally
    //   132	138	302	finally
    //   146	157	302	finally
    //   194	199	302	finally
    //   244	262	302	finally
    //   262	270	302	finally
    //   276	286	302	finally
    //   309	314	317	java/io/IOException
    //   157	165	327	finally
    //   171	181	327	finally
  }

  // ERROR //
  public void saveDataToLocal(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aload_1
    //   4: ldc_w 786
    //   7: invokevirtual 790	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   10: astore 10
    //   12: ldc_w 690
    //   15: invokestatic 695	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   18: invokevirtual 254	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   21: istore 11
    //   23: aconst_null
    //   24: astore 5
    //   26: iload 11
    //   28: ifeq +145 -> 173
    //   31: new 86	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 87	java/lang/StringBuilder:<init>	()V
    //   38: invokestatic 699	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   41: invokevirtual 700	java/io/File:toString	()Ljava/lang/String;
    //   44: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: aload_3
    //   48: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: astore 12
    //   56: new 168	java/io/File
    //   59: dup
    //   60: aload 12
    //   62: invokespecial 703	java/io/File:<init>	(Ljava/lang/String;)V
    //   65: astore 13
    //   67: new 168	java/io/File
    //   70: dup
    //   71: new 86	java/lang/StringBuilder
    //   74: dup
    //   75: invokespecial 87	java/lang/StringBuilder:<init>	()V
    //   78: aload 12
    //   80: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: ldc_w 702
    //   86: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: aload_2
    //   90: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   96: invokespecial 703	java/io/File:<init>	(Ljava/lang/String;)V
    //   99: astore 14
    //   101: aload 13
    //   103: invokevirtual 172	java/io/File:exists	()Z
    //   106: istore 15
    //   108: aconst_null
    //   109: astore 5
    //   111: iload 15
    //   113: ifne +9 -> 122
    //   116: aload 13
    //   118: invokevirtual 762	java/io/File:mkdirs	()Z
    //   121: pop
    //   122: aload 14
    //   124: invokevirtual 172	java/io/File:exists	()Z
    //   127: istore 17
    //   129: aconst_null
    //   130: astore 5
    //   132: iload 17
    //   134: ifne +9 -> 143
    //   137: aload 14
    //   139: invokevirtual 765	java/io/File:createNewFile	()Z
    //   142: pop
    //   143: aconst_null
    //   144: astore 5
    //   146: aload 14
    //   148: ifnull +25 -> 173
    //   151: new 767	java/io/FileOutputStream
    //   154: dup
    //   155: aload 14
    //   157: invokespecial 768	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   160: astore 19
    //   162: aload 19
    //   164: aload 10
    //   166: invokevirtual 793	java/io/FileOutputStream:write	([B)V
    //   169: aload 19
    //   171: astore 5
    //   173: iload 4
    //   175: ifeq +21 -> 196
    //   178: aload_0
    //   179: getfield 33	com/inception/otaku/SDKUtils:a	Landroid/content/Context;
    //   182: aload_2
    //   183: iconst_3
    //   184: invokevirtual 783	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   187: astore 5
    //   189: aload 5
    //   191: aload 10
    //   193: invokevirtual 793	java/io/FileOutputStream:write	([B)V
    //   196: aload 5
    //   198: ifnull +8 -> 206
    //   201: aload 5
    //   203: invokevirtual 779	java/io/FileOutputStream:close	()V
    //   206: return
    //   207: astore 20
    //   209: aload 20
    //   211: invokevirtual 735	java/io/IOException:printStackTrace	()V
    //   214: return
    //   215: astore 8
    //   217: aload 8
    //   219: invokevirtual 134	java/lang/Exception:printStackTrace	()V
    //   222: aload 5
    //   224: ifnull -18 -> 206
    //   227: aload 5
    //   229: invokevirtual 779	java/io/FileOutputStream:close	()V
    //   232: return
    //   233: astore 9
    //   235: aload 9
    //   237: invokevirtual 735	java/io/IOException:printStackTrace	()V
    //   240: return
    //   241: astore 6
    //   243: aload 5
    //   245: ifnull +8 -> 253
    //   248: aload 5
    //   250: invokevirtual 779	java/io/FileOutputStream:close	()V
    //   253: aload 6
    //   255: athrow
    //   256: astore 7
    //   258: aload 7
    //   260: invokevirtual 735	java/io/IOException:printStackTrace	()V
    //   263: goto -10 -> 253
    //   266: astore 6
    //   268: aload 19
    //   270: astore 5
    //   272: goto -29 -> 243
    //   275: astore 8
    //   277: aload 19
    //   279: astore 5
    //   281: goto -64 -> 217
    //
    // Exception table:
    //   from	to	target	type
    //   201	206	207	java/io/IOException
    //   3	23	215	java/lang/Exception
    //   31	108	215	java/lang/Exception
    //   116	122	215	java/lang/Exception
    //   122	129	215	java/lang/Exception
    //   137	143	215	java/lang/Exception
    //   151	162	215	java/lang/Exception
    //   178	196	215	java/lang/Exception
    //   227	232	233	java/io/IOException
    //   3	23	241	finally
    //   31	108	241	finally
    //   116	122	241	finally
    //   122	129	241	finally
    //   137	143	241	finally
    //   151	162	241	finally
    //   178	196	241	finally
    //   217	222	241	finally
    //   248	253	256	java/io/IOException
    //   162	169	266	finally
    //   162	169	275	java/lang/Exception
  }

  public void sendSMS(String paramString1, String paramString2)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.SENDTO");
    localIntent.setData(Uri.parse("smsto:" + paramString1));
    localIntent.putExtra("sms_body", paramString2);
    this.a.startActivity(localIntent);
  }

  public void showToast(String paramString)
  {
    Toast.makeText(this.a, paramString, 1).show();
  }

  public String[] splitString(String paramString1, String paramString2, String paramString3)
  {
    if (paramString2 != null);
    while (true)
    {
      try
      {
        if ("".equals(paramString2.trim()))
          return new String[] { paramString1 };
//        if ((paramString3 == null) || (paramString3.equals("")))
//          break label138;
        if ((paramString1 != null) && (!"".equals(paramString1.trim())))
        {
          if (paramString1.endsWith(paramString2))
            paramString1 = paramString1.substring(0, paramString1.lastIndexOf(paramString2));
          if (paramString1.indexOf(paramString2) > 0)
            return paramString1.split(paramString3);
//          if (paramString1.indexOf(paramString2) != 0)
//            break label125;
          String[] arrayOfString2 = new String[1];
          arrayOfString2[0] = paramString1.substring(1);
          return arrayOfString2;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return null;
//      label125: String[] arrayOfString1 = { paramString1 };
//      return arrayOfString1;
//      label138: paramString3 = paramString2;
    }
  }

  public void submit(String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (!"".equals(paramString2)))
    {
//      new AlertDialog.Builder(this.a).setTitle(paramString1).setMessage(paramString2).setPositiveButton("确定", new bm(this)).create().show();
      return;
    }
    ((Activity)this.a).finish();
  }
}
