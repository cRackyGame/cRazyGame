package com.inception.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class LogUtils
{
  public static void log(String paramString)
  {
    Log.v("otaku", paramString);
  }

  public static void toastLong(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 1).show();
  }

  public static void toastShort(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 0).show();
  }
}