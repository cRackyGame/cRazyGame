package com.inception.otaku;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.inception.otaku.google.R;
import com.inception.payment.UmengDelegate;
import com.inception.utils.BitmapUtils;
import com.inception.utils.LogUtils;
import com.inception.utils.StringUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class CoverActivity extends Activity
{
  public static CoverActivity activity;
  private Bitmap coverBitmap;
  private ImageView coverImage = null;
  private long exitTime = 0L;
  private ArrayList<ActivityObserver> observers;
  private Bitmap titleBitmap;

  private void addShortcutToDesktop()
  {
    Intent localIntent1 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    localIntent1.putExtra("duplicate", false);
    localIntent1.putExtra("android.intent.extra.shortcut.NAME", "宅男福利");
    localIntent1.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(this, 2130837555));
    Intent localIntent2 = new Intent(this, getClass());
    localIntent2.setAction("android.intent.action.MAIN");
    localIntent2.addCategory("android.intent.category.LAUNCHER");
    localIntent1.putExtra("android.intent.extra.shortcut.INTENT", localIntent2);
    sendBroadcast(localIntent1);
  }

  private int getSystemVersion()
  {
    return Build.VERSION.SDK_INT;
  }

  public boolean isShortcutInstalled()
  {
    ContentResolver localContentResolver = getContentResolver();
    if (getSystemVersion() >= 8);
    for (String str = "com.android.launcher2.settings"; ; str = "com.android.launcher.settings")
    {
      Uri localUri = Uri.parse("content://" + str + "/favorites?notify=true");
      String[] arrayOfString1 = { "title", "iconResource" };
      String[] arrayOfString2 = new String[1];
      arrayOfString2[0] = getString(2131165285);
      Cursor localCursor = localContentResolver.query(localUri, arrayOfString1, "title=?", arrayOfString2, null);
      boolean bool = false;
      if (localCursor != null)
      {
        int i = localCursor.getCount();
        bool = false;
        if (i > 0)
        {
          bool = true;
          System.out.println("已创建");
        }
      }
      return bool;
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.cover_layout);
    activity = this;
    this.coverImage = ((ImageView)findViewById(R.id.imageView1));
    this.coverImage.setOnClickListener(new ImageViewClickListener());
    int i = (int)(1.0D + 3.0D * Math.random());
    this.coverBitmap = BitmapUtils.getBitmapFromAssets(this, "cover" + i + ".jpg");
    if (this.coverBitmap != null)
      this.coverImage.setImageBitmap(this.coverBitmap);
    ImageView localImageView = (ImageView)findViewById(R.id.imageView3);
    localImageView.setBackgroundColor(Color.parseColor("#7b7b7b"));
    localImageView.getBackground().setAlpha(153);
    this.titleBitmap = BitmapUtils.getBitmapFromAssets(this, "cover_title.png");
    if (this.titleBitmap != null)
      localImageView.setImageBitmap(this.titleBitmap);
    this.observers = new ArrayList();
    Iterator localIterator = this.observers.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (!"google".equalsIgnoreCase("google"))
          MainApp.getInstance().mUmengDelegate.checkUpdate(this);
        if (!isShortcutInstalled())
          addShortcutToDesktop();
        return;
      }
      ((ActivityObserver)localIterator.next()).onCreate(paramBundle);
    }
  }

  protected void onDestroy()
  {
    Iterator localIterator;
    if (this.observers != null)
      localIterator = this.observers.iterator();
    else
    	return;
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (this.observers != null)
        {
          this.observers.clear();
          this.observers = null;
        }
        if ((this.titleBitmap != null) && (!this.titleBitmap.isRecycled()))
        {
          this.titleBitmap.recycle();
          this.titleBitmap = null;
        }
        if ((this.coverBitmap != null) && (!this.coverBitmap.isRecycled()))
        {
          this.coverBitmap.recycle();
          this.coverBitmap = null;
        }
        super.onDestroy();
        return;
      }
      ((ActivityObserver)localIterator.next()).onDestroy();
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 0))
    {
      if (System.currentTimeMillis() - this.exitTime > 2000L)
      {
        LogUtils.toastShort(this, StringUtils.getString(this, R.string.tips_press_again_to_exit));
        this.exitTime = System.currentTimeMillis();
      }
      while (true)
      {
        finish();
        System.exit(0);
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
    Iterator localIterator = this.observers.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((ActivityObserver)localIterator.next()).onResume();
    }
  }

  protected void onStop()
  {
    super.onStop();
  }

  class ImageViewClickListener
    implements View.OnClickListener
  {
    ImageViewClickListener()
    {
    }

    public void onClick(View paramView)
    {
      if (paramView == CoverActivity.this.coverImage)
      {
        Intent localIntent = new Intent(CoverActivity.activity, LoadActivity.class);
        localIntent.putExtra("LoadActivityStatus", "LoadActivityFromCover");
        CoverActivity.activity.startActivity(localIntent);
        CoverActivity.activity.finish();
      }
    }
  }
}