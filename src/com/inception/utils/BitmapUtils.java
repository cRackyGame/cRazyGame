package com.inception.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtils
{
  public static Bitmap byteToBitmap(byte[] paramArrayOfByte)
  {
    try
    {
      Bitmap localBitmap = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
      return localBitmap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static Bitmap getBitmap(String paramString)
  {
    if (new File(paramString).exists())
      return BitmapFactory.decodeFile(paramString);
    return null;
  }

  public static Bitmap getBitmapFromAssets(Context paramContext, String paramString)
  {
    try
    {
      Bitmap localBitmap = BitmapFactory.decodeStream(paramContext.getAssets().open(paramString));
      return localBitmap;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return null;
  }

  public static Bitmap getBitmapFromDrawable(Context paramContext, int paramInt)
  {
    Bitmap localBitmap = BitmapFactory.decodeResource(paramContext.getResources(), paramInt);
    if (localBitmap != null)
      return localBitmap;
    LogUtils.log("getBitmapFromDrawable error");
    return null;
  }

  public static byte[] readStream(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[1024];
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1)
      {
        localByteArrayOutputStream.close();
        paramInputStream.close();
        return localByteArrayOutputStream.toByteArray();
      }
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
  }
}