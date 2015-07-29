package com.inception.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.preference.PreferenceManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils
{
  private static String appSubDir = "/1otaku/";

  public static String getAppRootDir(Context paramContext)
  {
    if (isSDCardExists())
      Config.isUseSDCard = true;
    if (Config.isUseSDCard);
    for (String str1 = Environment.getExternalStorageDirectory().getPath(); ; str1 = paramContext.getFilesDir().getAbsolutePath())
    {
      String str2 = str1 + appSubDir;
      File localFile = new File(str2);
      if ((!localFile.exists()) && (!localFile.mkdirs()))
        LogUtils.log("dirFile.mkdirs() error");
      return str2;
    }
  }

  public static InputStream getAssetsFile(Context paramContext, String paramString)
  {
    try
    {
      InputStream localInputStream = paramContext.getAssets().open(paramString);
      return localInputStream;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return null;
  }

  public static String getSDCardPath()
  {
    return Environment.getExternalStorageDirectory().getPath();
  }

  public static boolean isFileExist(String paramString)
  {
    return new File(paramString).exists();
  }

  public static boolean isSDCardExists()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }

  public static String read(Context paramContext, String paramString)
  {
    try
    {
      FileInputStream localFileInputStream = paramContext.openFileInput(paramString);
      StringBuffer localStringBuffer = new StringBuffer();
      while (true)
      {
        int i = localFileInputStream.read();
        if (i == -1)
        {
          localFileInputStream.close();
          return localStringBuffer.toString();
        }
        localStringBuffer.append((char)i);
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      return "";
    }
    catch (IOException localIOException)
    {
    }
    return "";
  }

  public static boolean readPreference(Context paramContext, String paramString)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean(paramString, false);
  }

  public static void write(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      FileOutputStream localFileOutputStream = paramContext.openFileOutput(paramString1, 0);
      localFileOutputStream.write(paramString2.getBytes());
      localFileOutputStream.flush();
      localFileOutputStream.close();
      return;
    }
    catch (IOException localIOException)
    {
    }
//    catch (FileNotFoundException localFileNotFoundException)
//    {
//    }
  }

  public static boolean writeBitmap(Context paramContext, String paramString1, String paramString2, Bitmap paramBitmap)
  {
    String str = getAppRootDir(paramContext) + paramString1;
    File localFile1 = new File(str);
    if ((!localFile1.exists()) && (!localFile1.mkdirs()))
      LogUtils.log("dirFile.mkdirs() failed");
    File localFile2 = new File(str + paramString2);
    if ((localFile2.exists()) && (!localFile2.delete()))
      LogUtils.log("file.delete() error");
    try
    {
      localFile2.createNewFile();
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, localFileOutputStream);
      localFileOutputStream.flush();
      localFileOutputStream.close();
      return true;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public static boolean writeFile(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    String str = getAppRootDir(paramContext) + paramString1;
    File localFile1 = new File(str);
    if ((!localFile1.exists()) && (!localFile1.mkdirs()))
      LogUtils.log("dirFile.mkdirs() failed");
    File localFile2 = new File(str + paramString2);
    if ((localFile2.exists()) && (!localFile2.delete()))
      LogUtils.log("file.delete() error");
    try
    {
      localFile2.createNewFile();
      FileWriter localFileWriter = new FileWriter(localFile2);
      localFileWriter.write(paramString3);
      localFileWriter.flush();
      localFileWriter.close();
      return true;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return false;
  }

  public static void writePreference(Context paramContext, String paramString, boolean paramBoolean)
  {
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    localSharedPreferences.edit().putBoolean(paramString, paramBoolean);
    localSharedPreferences.edit().commit();
  }
}
