package com.inception.otaku;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.inception.mediator.LoadMediator;
import com.inception.mediator.MainMediator;
import com.inception.mediator.MediatorMgr;
import com.inception.model.CategoryModel;
import com.inception.model.CategoryModel.Category;
import com.inception.model.ImageModel;
import com.inception.model.ImageModel.Image;
import com.inception.otaku.google.R;
import com.inception.payment.DianJoyDelegate;
import com.inception.payment.GfanDelegate;
import com.inception.payment.TapjoyDelegate;
import com.inception.payment.WapsDelegate;
import com.inception.utils.FileUtils;
import com.inception.utils.LogUtils;
import com.inception.utils.StringUtils;
import com.inception.utils.SystemUtils;
import java.util.HashMap;

public class MainActivity extends Activity
{
  private AlertDialog exitDialog = null;
  private AlertDialog gfanPayDialog = null;
  private ImageView imageView = null;
  private ImageView loadingBackgroundImageView = null;
  private TextView loadingTextView = null;
  private MainMediator mediator = null;
  private TextView pageNumberView = null;
  private ProgressBar progressBar = null;
  private SeekBar seekBar = null;
  private AlertDialog tapjoyDialog = null;
  private AlertDialog wapsPayDialog = null;

  public void addImageView()
  {
    if (this.imageView == null)
    {
      this.imageView = ((ImageView)findViewById(R.id.imageView1));
      if (this.imageView != null)
        this.imageView.setOnTouchListener(new ImageViewTouchListener());
    }
  }

  public void addLoadingView()
  {
    if (this.loadingBackgroundImageView == null)
      this.loadingBackgroundImageView = ((ImageView)findViewById(R.id.imageView2));
    if (this.progressBar == null)
      this.progressBar = ((ProgressBar)findViewById(R.id.progressBar1));
    if (this.loadingTextView == null)
    {
      this.loadingTextView = ((TextView)findViewById(R.id.textView2));
      this.loadingTextView.setText(R.string.tips_loading_image2);
    }
    hideLoadingView();
  }

  public void addPageNumberView()
  {
    this.pageNumberView = ((TextView)findViewById(R.id.textView1));
    this.pageNumberView.setTextColor(-1);
    this.pageNumberView.setVisibility(4);
  }

  public void addSeekBar()
  {
    this.seekBar = ((SeekBar)findViewById(R.id.seekBar1));
    if (this.seekBar != null)
    {
      this.seekBar.setOnSeekBarChangeListener(new SeekBarChangeListener());
      this.seekBar.setVisibility(4);
    }
  }

  public int getSeekBarProgress()
  {
    return this.seekBar.getProgress();
  }

  public void hideLoadingView()
  {
    if ((this.loadingBackgroundImageView != null) && (this.progressBar != null) && (this.loadingTextView != null))
    {
      this.loadingBackgroundImageView.setVisibility(4);
      this.progressBar.setVisibility(4);
      this.loadingTextView.setVisibility(4);
    }
  }

  public void hidePageNumberView()
  {
    this.pageNumberView.setVisibility(4);
  }

  public void hideSeekBar()
  {
    this.seekBar.setVisibility(4);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_main);
    MediatorMgr.getInstance().unregisterMediator(LoadMediator.class.getName());
    this.mediator = ((MainMediator)MediatorMgr.getInstance().registerMediator(MainMediator.class.getName()));
    this.mediator.setMainActivity(this);
    addImageView();
    addSeekBar();
    addPageNumberView();
    addLoadingView();
    ImageModel.Image localImage = ImageModel.getInstance().getCurrentImage();
    if (localImage != null)
    {
      Bitmap localBitmap = localImage.bitmap;
      if (localBitmap != null)
      {
        this.imageView.setImageBitmap(localBitmap);
        this.mediator.onActivityCreate();
      }
      return;
    }
    startActivity(new Intent(this, CoverActivity.class));
    finish();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(R.menu.activity_main, paramMenu);
    return true;
  }

  protected void onDestroy()
  {
    MediatorMgr.getInstance().unregisterMediator(MainMediator.class.getName());
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (this.progressBar.getVisibility() == 0)
        return true;
      this.mediator.switchToThumbnailActivity();
    }
    return false;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    boolean bool = true;
    switch (paramMenuItem.getItemId())
    {
   
	    case R.id.menu_save:
	      Bitmap localBitmap;
	      do
	      {
	        ImageModel.Image localImage;
	        do
	        {
	//          return bool;
	//          MobclickAgent.onEvent(this, "save_image_times");
	          localImage = ImageModel.getInstance().getCurrentImage();
	        }
	        while (localImage == null);
	        localBitmap = localImage.bitmap;
	      }
	      while ((localBitmap == null) || (!FileUtils.writeBitmap(this, "", System.currentTimeMillis() + ".jpg", localBitmap)));
	      LogUtils.toastShort(this, FileUtils.getAppRootDir(this) + System.currentTimeMillis() + ".jpg");
	      return bool;
	    case R.id.menu_auto_play:
	//      MobclickAgent.onEvent(this, "auto_play_times");
	      if (MainApp.getInstance().isAutoPlay)
	      {
	        stopAutoPlay(paramMenuItem);
	        return bool;
	      }
	      startAutoPlay(paramMenuItem);
	      return bool;
	    case R.id.menu_feedback:
	    	
	    default:
	        LogUtils.log("onOptionsItemSelected error");
	        bool = false;
    }
//    UMFeedbackService.openUmengFeedbackSDK(this);
    return bool;
  }

  protected void onPause()
  {
    super.onPause();
    this.mediator.onActivityPause();
    stopAutoPlay(null);
//    MobclickAgent.onPause(this);
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    if (this.progressBar.getVisibility() == 0)
      return false;
    MenuItem localMenuItem = paramMenu.findItem(2131493129);
    if (localMenuItem != null)
    {
      if (!MainApp.getInstance().isAutoPlay)
//        break label51;
      localMenuItem.setTitle(StringUtils.getString(this, 2131165294));
    }
    while (true)
    {
//      return true;
      label51: localMenuItem.setTitle(StringUtils.getString(this, 2131165293));
    }
  }

  protected void onResume()
  {
    super.onResume();
//    MobclickAgent.onResume(this);
    MainApp.getInstance().mTapjoyDelegate.getTapPoints();
  }

  public void setImageView(Bitmap paramBitmap)
  {
    this.imageView.setAlpha(255);
    this.imageView.setImageBitmap(paramBitmap);
  }

  public void setSeekBar(int paramInt)
  {
    this.seekBar.setProgress(paramInt);
  }

  public void showGfanPayDialog(int paramInt)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(StringUtils.getString(this, 2131165299));
    String str = StringUtils.getString(this, 2131165301);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    localBuilder.setMessage(String.format(str, arrayOfObject));
    localBuilder.setPositiveButton(StringUtils.getString(this, 2131165307), new DialogOnClickListener());
    localBuilder.setNegativeButton(StringUtils.getString(this, 2131165308), new DialogOnClickListener());
    this.gfanPayDialog = localBuilder.create();
    this.gfanPayDialog.show();
  }

  public void showLoadingView()
  {
    hideSeekBar();
    hidePageNumberView();
    this.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
    this.imageView.setAlpha(114);
    this.loadingBackgroundImageView.setBackgroundColor(Color.parseColor("#E5434343"));
    this.loadingBackgroundImageView.setVisibility(0);
    this.progressBar.setVisibility(0);
    this.loadingTextView.setVisibility(0);
  }

  public void showPageNumberView(int paramInt1, int paramInt2)
  {
    this.pageNumberView.setText(paramInt1 + "/" + paramInt2);
    this.pageNumberView.setVisibility(0);
  }

  public void showSeekBar()
  {
    if (this.seekBar == null)
      addSeekBar();
    this.seekBar.setVisibility(0);
  }

  public void showTapjoyDialog(int paramInt1, int paramInt2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(StringUtils.getString(this, 2131165302));
    String str = StringUtils.getString(this, 2131165303) + StringUtils.getString(this, 2131165304);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramInt1);
    arrayOfObject[1] = Integer.valueOf(paramInt2);
    localBuilder.setMessage(String.format(str, arrayOfObject));
    localBuilder.setPositiveButton(StringUtils.getString(this, 2131165307), new DialogOnClickListener());
    localBuilder.setNegativeButton(StringUtils.getString(this, 2131165308), new DialogOnClickListener());
    this.tapjoyDialog = localBuilder.create();
    this.tapjoyDialog.show();
  }

  public void showWapsDialog(int paramInt)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(StringUtils.getString(this, 2131165298));
    String str = StringUtils.getString(this, 2131165300);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    localBuilder.setMessage(String.format(str, arrayOfObject));
    localBuilder.setPositiveButton(StringUtils.getString(this, 2131165307), new DialogOnClickListener());
    localBuilder.setNegativeButton(StringUtils.getString(this, 2131165308), new DialogOnClickListener());
    this.wapsPayDialog = localBuilder.create();
    this.wapsPayDialog.show();
  }

  public void startAutoPlay(MenuItem paramMenuItem)
  {
    if (!MainApp.getInstance().isAutoPlay)
    {
      MainApp.getInstance().isAutoPlay = true;
      this.mediator.unscheduleAutoPlay();
      this.mediator.scheduleAutoPlay();
      if (paramMenuItem != null)
        paramMenuItem.setTitle(StringUtils.getString(this, 2131165294));
    }
  }

  public void stopAutoPlay(MenuItem paramMenuItem)
  {
    if (MainApp.getInstance().isAutoPlay)
    {
      MainApp.getInstance().isAutoPlay = false;
      this.mediator.unscheduleAutoPlay();
      if (paramMenuItem != null)
        paramMenuItem.setTitle(StringUtils.getString(this, 2131165293));
    }
  }

  class DialogOnClickListener
    implements DialogInterface.OnClickListener
  {
    DialogOnClickListener()
    {
    }

    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      if (paramDialogInterface == MainActivity.this.exitDialog)
        if (paramInt == -1)
        {
          MainActivity.this.exitDialog.dismiss();
          MainActivity.this.finish();
          Intent localIntent = new Intent("android.intent.action.MAIN");
          localIntent.setFlags(67108864);
          localIntent.addCategory("android.intent.category.HOME");
          MainActivity.this.startActivity(localIntent);
        }
      label375: 
      do
      {
//        do
//          return;
//        while (paramInt != -2);
        MainActivity.this.exitDialog.dismiss();
//        return;
        if (paramDialogInterface == MainActivity.this.wapsPayDialog)
        {
          if (paramInt == -1)
          {
            CategoryModel.Category localCategory2 = CategoryModel.getInstance().getCurrentCategory();
            int k = Integer.parseInt(localCategory2.chargeValue);
            SharedPreferences localSharedPreferences1 = MainActivity.this.getSharedPreferences("pay_preference", 0);
            SharedPreferences.Editor localEditor1 = localSharedPreferences1.edit();
            String str = localSharedPreferences1.getString("key_of_pay_way", "waps");
            int m = MainApp.getInstance().mWapsDeletage.currentCoin;
            int n = MainApp.getInstance().mDianJoyDelegate.currentCoin;
            int i1 = 0;
            int i2 = 0;
            int i3 = 0;
            int i5;
            if (str.compareToIgnoreCase("waps") == 0)
              if (n >= k)
              {
                i5 = 1;
                if (i2 == 0)
                  break label375;
                MainApp.getInstance().mWapsDeletage.showWapsOffer();
              }
            while (true)
            {
              HashMap localHashMap3 = new HashMap();
              localHashMap3.put("category", localCategory2.name);
//              MobclickAgent.onEvent(MainApp.getInstance(), "each_category_click_pay", localHashMap3);
//              return;
              if (m >= k)
              {
                i1 = 1;
                i5 = 0;
                i3 = 0;
                i2 = 0;
                break;
              }
              i3 = 1;
              i5 = 0;
              i1 = 0;
              i2 = 0;
//              break;
//              int i4 = str.compareToIgnoreCase("dianjoy");
              i5 = 0;
              i1 = 0;
              i3 = 0;
              i2 = 0;
//              if (i4 != 0)
//                break;
              if (m >= k)
              {
                i1 = 1;
                i5 = 0;
                i3 = 0;
                i2 = 0;
                break;
              }
              if (n >= k)
              {
                i5 = 1;
                i1 = 0;
                i3 = 0;
                i2 = 0;
                break;
              }
              i2 = 1;
              i5 = 0;
              i1 = 0;
              i3 = 0;
//              break;
              if (i3 != 0)
              {
                MainApp.getInstance().mDianJoyDelegate.showOffers();
              }
              else
              {
                if (i1 != 0)
                {
                  MainApp.getInstance().mWapsDeletage.spendPoints(k);
                  localEditor1.putString("key_of_pay_way", "waps");
                }
                
                SharedPreferences localSharedPreferences2;
                while (true)
                {
                  localEditor1.putString(localCategory2.name, "1");
                  localEditor1.commit();
                  localCategory2.isPay = "1";
                  HashMap localHashMap1 = new HashMap();
                  localHashMap1.put("category", localCategory2.name);
//                  MobclickAgent.onEvent(MainApp.getInstance(), "each_category_click_pay_success", localHashMap1);
                  localSharedPreferences2 = MainActivity.this.getSharedPreferences("old_new_user_preference", 0);
//                  if (localSharedPreferences2.getString("isOldUser", "0").compareToIgnoreCase("1") != 0)
//                    break label579;
                  HashMap localHashMap2 = new HashMap();
                  localHashMap2.put("category", localCategory2.name);
//                  MobclickAgent.onEvent(MainApp.getInstance(), "old_user_pay_success", localHashMap2);
                  break;
//                  if (i5 != 0)
//                  {
//                    MainApp.getInstance().mDianJoyDelegate.sendMoney(k);
//                    localEditor1.putString("key_of_pay_way", "dianjoy");
//                  }
                }
                
                HashMap localHashMap4 = new HashMap();
                localHashMap4.put("category", localCategory2.name);
//                MobclickAgent.onEvent(MainApp.getInstance(), "new_user_pay_success", localHashMap4);
                SharedPreferences.Editor localEditor2 = localSharedPreferences2.edit();
                localEditor2.putString("isOldUser", "1");
                localEditor2.commit();
              }
            }
          }
          MainActivity.this.wapsPayDialog.dismiss();
          return;
        }
        if (paramDialogInterface == MainActivity.this.gfanPayDialog)
        {
          if (paramInt == -1)
          {
            int j = Integer.parseInt(CategoryModel.getInstance().getCurrentCategory().chargeValue);
            MainApp.getInstance().mGfanDelegate.pay(j);
            return;
          }
          MainActivity.this.gfanPayDialog.dismiss();
          return;
        }
      }
      while (paramDialogInterface != MainActivity.this.tapjoyDialog);
      label579: if (paramInt == -1)
      {
        CategoryModel.Category localCategory1 = CategoryModel.getInstance().getCurrentCategory();
        int i = Integer.parseInt(localCategory1.chargeValue);
        if (MainApp.getInstance().mTapjoyDelegate.currentCoin >= i)
        {
          MainApp.getInstance().mTapjoyDelegate.spendTapPoints(i);
          localCategory1.isPay = "1";
          return;
        }
        MainApp.getInstance().mTapjoyDelegate.showOffers();
        return;
      }
      MainActivity.this.tapjoyDialog.dismiss();
    }
  }

  class ImageViewTouchListener
    implements View.OnTouchListener
  {
    ImageViewTouchListener()
    {
    }

    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      if ((paramMotionEvent.getAction() == 0) && (paramView == MainActivity.this.imageView) && (MainActivity.this.progressBar != null) && (MainActivity.this.progressBar.getVisibility() != 0))
      {
//        if (paramMotionEvent.getY() < 4 * SystemUtils.getDisplayHeight(MainActivity.this) / 5)
//          break label99;
        CategoryModel localCategoryModel = CategoryModel.getInstance();
        boolean bool1 = localCategoryModel.isCurrentCategoryFree();
        boolean bool2 = localCategoryModel.isCurrentCategoryPayed();
        if ((bool1) || (bool2))
          MainActivity.this.mediator.scheduleSeekBar();
      }
      while (true)
      {
//        return false;
        label99: if (paramMotionEvent.getX() <= paramView.getWidth() / 2)
          MainActivity.this.mediator.onImageViewLeftTouched();
        else
          MainActivity.this.mediator.onImageViewRightTouched();
      }
    }
  }

  class SeekBarChangeListener
    implements SeekBar.OnSeekBarChangeListener
  {
    SeekBarChangeListener()
    {
    }

    public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
    {
      MainActivity.this.mediator.onProgressChanged(paramInt);
    }

    public void onStartTrackingTouch(SeekBar paramSeekBar)
    {
//      MobclickAgent.onEvent(MainActivity.this, "progressbar_times");
      MainActivity.this.mediator.onStartTrackingTouch();
    }

    public void onStopTrackingTouch(SeekBar paramSeekBar)
    {
      MainActivity.this.mediator.onStopTrackingTouch();
    }
  }
}