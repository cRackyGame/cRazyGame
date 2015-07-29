package com.inception.otaku;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class MiniAdView
{
  private static final long DELETE_ICON_LIMIT_TIME = 604800000L;
  static long e = 0L;
  static int f = 5;
  private static int r = 0;
  LinearLayout a;
  boolean b = false;
  View c;
  Context d;
  TextView g;
  private int[] h;
  private int i;
  private AnimationType j;
  private int k = -1;
  private String l = "";
  private int m ;//= AppConnect.h;
  final Handler mHandler = new Handler();
//  final Runnable mUpdateResults = new aw(this);
  private boolean n = true;
  private int o = 0;
  private String p;
  private int q;
  private String s = "/Android/data/cache/iconCache";
  private String t = "CacheTime";
  private SharedPreferences u = null;
  private SharedPreferences.Editor v = null;
  private AdInfo w = null;

  public MiniAdView()
  {
  }

  public MiniAdView(Context paramContext)
  {
    this.d = paramContext;
  }

  public MiniAdView(Context paramContext, LinearLayout paramLinearLayout)
  {
    a(this.s);
    this.d = paramContext;
    this.a = paramLinearLayout;
//    this.a.setBackgroundColor(AppConnect.g);
    this.h = new int[] { -2 };
    this.i = -2;
    this.k = 0;
    this.u = paramContext.getSharedPreferences("AppSettings", 0);
    this.v = this.u.edit();
    ShapeDrawable localShapeDrawable = new ShapeDrawable(new RoundRectShape(new float[] { 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F }, null, null));
//    localShapeDrawable.getPaint().setColor(AppConnect.g);
    this.a.setBackgroundDrawable(localShapeDrawable);
  }

  private LinearLayout a(Context paramContext, Bitmap paramBitmap, String paramString, int paramInt)
  {
    LinearLayout localLinearLayout = new LinearLayout(paramContext);
    localLinearLayout.setOrientation(0);
    localLinearLayout.setGravity(17);
    localLinearLayout.setBackgroundColor(Color.argb(0, 0, 0, 0));
    localLinearLayout.setPadding(5, 0, 0, 0);
    int i1 = (int)(paramInt / 16.0F);
    int i2 = (int)(paramInt / 16.0F);
    ImageView localImageView = new ImageView(paramContext);
//    localImageView.setLayoutParams(new ViewGroup.LayoutParams(i1, i2));
    if (paramBitmap != null)
      if (paramBitmap.getWidth() > i2)
      {
        localImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        localImageView.setImageBitmap(paramBitmap);
      }
    while (true)
    {
      this.g = new TextView(paramContext);
      this.g.setText(paramString);
      this.g.setTextSize(15.0F);
      this.g.setTextColor(this.m);
      this.g.setPadding(5, 0, 0, 0);
      localLinearLayout.addView(localImageView);
      localLinearLayout.addView(this.g);
      return localLinearLayout;
//      localImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//      break;
//      localImageView.setImageResource(17301516);
    }
  }

  // ERROR //
  private void a(String paramString)
  {
    // Byte code:
    //   0: new 250	com/inception/otaku/SDKUtils
    //   3: dup
    //   4: aload_0
    //   5: getfield 105	com/inception/otaku/MiniAdView:d	Landroid/content/Context;
    //   8: invokespecial 251	com/inception/otaku/SDKUtils:<init>	(Landroid/content/Context;)V
    //   11: aload_0
    //   12: getfield 89	com/inception/otaku/MiniAdView:t	Ljava/lang/String;
    //   15: aload_1
    //   16: invokevirtual 255	com/inception/otaku/SDKUtils:loadStringFromLocal	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   19: astore_3
    //   20: aload_3
    //   21: invokestatic 260	com/inception/otaku/bg:a	(Ljava/lang/String;)Z
    //   24: ifeq +30 -> 54
    //   27: new 250	com/inception/otaku/SDKUtils
    //   30: dup
    //   31: aload_0
    //   32: getfield 105	com/inception/otaku/MiniAdView:d	Landroid/content/Context;
    //   35: invokespecial 251	com/inception/otaku/SDKUtils:<init>	(Landroid/content/Context;)V
    //   38: invokestatic 266	java/lang/System:currentTimeMillis	()J
    //   41: invokestatic 272	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   44: aload_0
    //   45: getfield 89	com/inception/otaku/MiniAdView:t	Ljava/lang/String;
    //   48: aload_1
    //   49: iconst_1
    //   50: invokevirtual 276	com/inception/otaku/SDKUtils:saveDataToLocal	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    //   53: return
    //   54: aload_3
    //   55: invokestatic 260	com/inception/otaku/bg:a	(Ljava/lang/String;)Z
    //   58: ifne +152 -> 210
    //   61: aload_3
    //   62: ldc_w 278
    //   65: ldc 69
    //   67: invokevirtual 281	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   70: invokestatic 287	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   73: lstore 4
    //   75: invokestatic 266	java/lang/System:currentTimeMillis	()J
    //   78: lstore 6
    //   80: lload 6
    //   82: lload 4
    //   84: lsub
    //   85: ldc2_w 7
    //   88: lcmp
    //   89: iflt +121 -> 210
    //   92: invokestatic 293	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   95: ldc_w 295
    //   98: invokevirtual 299	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   101: ifeq +51 -> 152
    //   104: new 301	java/io/File
    //   107: dup
    //   108: new 303	java/lang/StringBuilder
    //   111: dup
    //   112: invokespecial 304	java/lang/StringBuilder:<init>	()V
    //   115: invokestatic 308	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   118: invokevirtual 311	java/io/File:toString	()Ljava/lang/String;
    //   121: invokevirtual 315	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: aload_1
    //   125: invokevirtual 315	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: invokevirtual 316	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   131: invokespecial 318	java/io/File:<init>	(Ljava/lang/String;)V
    //   134: astore 9
    //   136: new 250	com/inception/otaku/SDKUtils
    //   139: dup
    //   140: aload_0
    //   141: getfield 105	com/inception/otaku/MiniAdView:d	Landroid/content/Context;
    //   144: invokespecial 251	com/inception/otaku/SDKUtils:<init>	(Landroid/content/Context;)V
    //   147: aload 9
    //   149: invokevirtual 322	com/inception/otaku/SDKUtils:deleteLocalFiles	(Ljava/io/File;)V
    //   152: new 250	com/inception/otaku/SDKUtils
    //   155: dup
    //   156: aload_0
    //   157: getfield 105	com/inception/otaku/MiniAdView:d	Landroid/content/Context;
    //   160: invokespecial 251	com/inception/otaku/SDKUtils:<init>	(Landroid/content/Context;)V
    //   163: new 303	java/lang/StringBuilder
    //   166: dup
    //   167: invokespecial 304	java/lang/StringBuilder:<init>	()V
    //   170: invokestatic 266	java/lang/System:currentTimeMillis	()J
    //   173: invokevirtual 325	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   176: ldc 69
    //   178: invokevirtual 315	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: invokevirtual 316	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   184: aload_0
    //   185: getfield 89	com/inception/otaku/MiniAdView:t	Ljava/lang/String;
    //   188: aload_1
    //   189: iconst_1
    //   190: invokevirtual 276	com/inception/otaku/SDKUtils:saveDataToLocal	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
    //   193: return
    //   194: astore_2
    //   195: aload_2
    //   196: invokevirtual 328	java/lang/Exception:printStackTrace	()V
    //   199: return
    //   200: astore 8
    //   202: aload 8
    //   204: invokevirtual 328	java/lang/Exception:printStackTrace	()V
    //   207: goto -55 -> 152
    //   210: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	53	194	java/lang/Exception
    //   54	80	194	java/lang/Exception
    //   152	193	194	java/lang/Exception
    //   202	207	194	java/lang/Exception
    //   92	152	200	java/lang/Exception
  }

  private void b()
  {
    while (true)
    {
      try
      {
        this.a.removeView(this.a.getChildAt(0));
        this.a.removeAllViews();
        if ((this.c == null) || (!this.b))
          break;
        if (r == 0)
        {
//          ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(0, 0);
//          this.c.setLayoutParams(localLayoutParams);
          return;
        }
        this.a.refreshDrawableState();
        this.a.setAlwaysDrawnWithCacheEnabled(true);
        this.a.clearFocus();
        this.a.clearDisappearingChildren();
        this.a.addView(this.c);
//        this.l = Build.VERSION.SDK;
        this.a.clearAnimation();
        this.c.clearAnimation();
        if (this.k == 0)
        {
          this.j = new AnimationType(this.h);
          this.j.startAnimation(this.c);
          this.b = false;
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      if (this.k == 1)
        this.j = new AnimationType(this.i);
      else if (this.k == 2)
        this.j = new AnimationType(this.h);
    }
  }

  private void b(int paramInt)
  {
    try
    {
      this.q = paramInt;
      if (r == 0)
      {
        this.b = true;
//        this.mHandler.post(this.mUpdateResults);
        return;
      }
//      this.w = ((AdInfo)AppConnect.i.get(this.q));
      if (this.w != null)
      {
        this.p = this.w.getAdText();
        Bitmap localBitmap = this.w.getAdIcon();
        int i1 = new SDKUtils(this.d).initAdWidth();
        int i2 = (int)(i1 / 13.333333F);
        this.c = a(this.d, localBitmap, this.p, i1);
//        ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(i1, i2);
//        this.c.setLayoutParams(localLayoutParams);
//        this.mHandler.post(new ay(this));
        this.c.setOnClickListener(getMiniAdClickListener(this.d, this.q));
        if (this.c != null)
        {
          this.b = true;
//          this.mHandler.post(this.mUpdateResults);
        }
        this.v.putInt("MiniAd_ShowTag", this.q);
        this.v.commit();
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void c()
  {
    e = System.currentTimeMillis();
    this.u = this.d.getSharedPreferences("AppSettings", 0);
    int i1 = this.u.getInt("MiniAd_ShowTag", -1);
    if (i1 != -1)
      this.o = (i1 + 1);
    if (this.o >= r)
      this.o = 0;
    b(this.o);
//    new ax(this).start();
  }

  public void DisplayAd()
  {
    DisplayAd(f);
  }

  public void DisplayAd(int paramInt)
  {
    f = paramInt;
    if (paramInt < 5)
      f = 5;
    if (this.d.getSharedPreferences("ShowAdFlag", 3).getBoolean("show_mini_flag", true))
      c();
  }

  public View.OnClickListener getMiniAdClickListener(Context paramContext, int paramInt)
  {
//    return new ba(this, paramContext, paramInt);
	  return null;
  }

  public MiniAdView setAnimationType(int paramInt)
  {
    this.i = paramInt;
    this.k = 1;
    return this;
  }

  public MiniAdView setAnimationType(int[] paramArrayOfInt)
  {
    this.h = paramArrayOfInt;
    this.k = 2;
    return this;
  }
}
