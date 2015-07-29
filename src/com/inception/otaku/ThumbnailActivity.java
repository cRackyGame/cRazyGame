package com.inception.otaku;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.inception.mediator.LoadMediator;
import com.inception.mediator.MediatorMgr;
import com.inception.mediator.ThumbnailMediator;
import com.inception.model.CategoryModel;
import com.inception.model.CategoryModel.Category;
import com.inception.otaku.google.R;
import com.inception.payment.TapjoyDelegate;
import com.inception.request.RequestProxy;
import java.util.ArrayList;

public class ThumbnailActivity extends Activity
  implements LazyScrollView.OnScrollListener
{
  private static int kEachPageThumbnailCount = 12;
  public int Image_width;
  public LinearLayout adLinearLayout;
  public View adView;
  private AssetManager assetManager;
  private int current_page = 0;
  private LinearLayout layout01;
  private LinearLayout layout02;
  private LinearLayout layout03;
  private LazyScrollView lazyScrollView;
  private TextView loadtext;
  public final Handler mHandler = new Handler();
  public final Runnable mUpdateResults = new Runnable()
  {
    public void run()
    {
      ThumbnailActivity.this.updateResultsInUi();
    }
  };
  private ThumbnailMediator mediator = null;
  private LinearLayout progressbar;
  private int x;
  private int y;

  private void addImage(int paramInt1, int paramInt2)
  {
    CategoryModel localCategoryModel = CategoryModel.getInstance();
    int i = localCategoryModel.getCategorys().size();
    for (int j = paramInt1 * paramInt2; ; j++)
    {
      if ((j >= paramInt2 * (paramInt1 + 1)) || (j >= i))
        return;
      addBitMapToImage(((CategoryModel.Category)localCategoryModel.getCategorys().get(j)).id, this.y, j);
      this.y = (1 + this.y);
      if (this.y >= 3)
        this.y = 0;
    }
  }

  public static View scaleDisplayAd(View paramView, int paramInt)
  {
    int i = paramView.getLayoutParams().width;
    int j = paramView.getLayoutParams().height;
    if (i > paramInt)
    {
      int k = Double.valueOf(100.0D * Double.valueOf(Double.valueOf(paramInt).doubleValue() / Double.valueOf(i).doubleValue()).doubleValue()).intValue();
      ((WebView)paramView).getSettings().setSupportZoom(true);
      ((WebView)paramView).setPadding(0, 0, 0, 0);
      ((WebView)paramView).setVerticalScrollBarEnabled(false);
      ((WebView)paramView).setHorizontalScrollBarEnabled(false);
      ((WebView)paramView).setInitialScale(k);
      paramView.setLayoutParams(new ViewGroup.LayoutParams(paramInt, paramInt * j / i));
    }
    return paramView;
  }

  private void updateResultsInUi()
  {
    this.adLinearLayout.removeAllViews();
    this.adLinearLayout.addView(this.adView);
  }

  public void addBitMapToImage(String paramString, int paramInt1, int paramInt2)
  {
    ImageView localImageView = getImageview();
    RequestProxy.getInstance().sendGetThumbnailRequest("getThumbnail", paramString, localImageView);
    localImageView.setTag(paramString);
    setLoadTextViewVisible(true);
    if (paramInt1 == 0)
      this.layout01.addView(localImageView);
    while (true)
    {
      localImageView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ThumbnailActivity.this.mediator.onThumbnailClicked((String)paramAnonymousView.getTag());
        }
      });
//      return;
      if (paramInt1 == 1)
        this.layout02.addView(localImageView);
      else if (paramInt1 == 2)
        this.layout03.addView(localImageView);
    }
  }

  public ImageView getImageview()
  {
    ImageView localImageView = new ImageView(this);
    localImageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    localImageView.setPadding(2, 0, 2, 2);
    return localImageView;
  }

  public void initView()
  {
    setContentView(R.layout.thumbnail_layout);
    this.lazyScrollView = ((LazyScrollView)findViewById(R.id.lazyscrollview));
    this.progressbar = ((LinearLayout)findViewById(R.id.progressbar));
    this.loadtext = ((TextView)findViewById(R.id.loadtext));
    this.lazyScrollView.getView();
    this.lazyScrollView.setOnScrollListener(this);
    this.layout01 = ((LinearLayout)findViewById(R.id.layout01));
    this.layout02 = ((LinearLayout)findViewById(R.id.layout02));
    this.layout03 = ((LinearLayout)findViewById(R.id.layout03));
    this.adLinearLayout = ((LinearLayout)findViewById(R.id.AdLinearLayout));
    MainApp.getInstance().mTapjoyDelegate.getDisplayAd();
  }

  public void onBottom()
  {
    int i = 1 + this.current_page;
    this.current_page = i;
    addImage(i, kEachPageThumbnailCount);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    initView();
    this.assetManager = getAssets();
    this.Image_width = ((-4 + getWindowManager().getDefaultDisplay().getWidth()) / 3);
    MediatorMgr.getInstance().unregisterMediator(LoadMediator.class.getName());
    this.mediator = ((ThumbnailMediator)MediatorMgr.getInstance().registerMediator(ThumbnailMediator.class.getName()));
    this.mediator.setActivity(this);
    addImage(this.current_page, kEachPageThumbnailCount);
  }

  public void onDestroy()
  {
    super.onDestroy();
    MediatorMgr.getInstance().unregisterMediator(ThumbnailMediator.class.getName());
  }

  public void onPause()
  {
    super.onPause();
    MainApp.getInstance().mTapjoyDelegate.enableDisplayAdAutoRefresh(false);
  }

  public void onResume()
  {
    super.onResume();
    TapjoyDelegate localTapjoyDelegate = MainApp.getInstance().mTapjoyDelegate;
    localTapjoyDelegate.activity = this;
    localTapjoyDelegate.enableDisplayAdAutoRefresh(true);
  }

  public void onScroll()
  {
  }

  public void onTop()
  {
  }

  public void setLoadTextViewVisible(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.loadtext.setVisibility(0);
      return;
    }
    this.loadtext.setVisibility(8);
  }

  public void setProgressbarVisible(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.progressbar.setVisibility(0);
      return;
    }
    this.progressbar.setVisibility(8);
  }
}
