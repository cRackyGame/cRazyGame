package com.inception.otaku;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import com.inception.mediator.LoadMediator;
import com.inception.mediator.MediatorMgr;
import com.inception.model.CategoryModel;
import com.inception.otaku.google.R;
import com.inception.utils.StringUtils;
import com.inception.utils.SystemUtils;
//import com.umeng.analytics.MobclickAgent;

public class LoadActivity extends Activity
{
	public String categoryId = null;
	public String categoryName = null;
	public LoadMediator mediator = null;
	private ProgressBar progressBar = null;
	private AlertDialog setNetworkDialog = null;
	public String status = null;

	public void hideProgressBar()
	{
		if (this.progressBar != null)
			this.progressBar.setVisibility(4);
	}

    public void onCreate(Bundle paramBundle)
    {
    	super.onCreate(paramBundle);
    	setContentView(R.layout.load_layout);
    	this.progressBar = ((ProgressBar)findViewById(R.id.progressBar1));
	    if (!SystemUtils.isNetworkAvailable(this))
	    {
	      showSetNetworkDialog();
	      return;
	    }
	    
	    Bundle localBundle;
	    if (paramBundle == null)
	    {
	      localBundle = getIntent().getExtras();
	      if (localBundle == null)
	        this.status = null;
	    }
	    else
	    {
	    	return;
	    }
    
	    while (true)
	    {
	    	this.mediator = ((LoadMediator)MediatorMgr.getInstance().registerMediator(LoadMediator.class.getName()));
	    	this.mediator.setLoadActivity(this);
	    	this.status = localBundle.getString("LoadActivityStatus");
	    	this.categoryId = localBundle.getString("LoadActivityCategoryId");
	    	CategoryModel.getInstance().setCurrentCategoryById(this.categoryId);
	    	return;
//      continue;
//	    	this.status = ((String)paramBundle.getSerializable("LoadActivityStatus"));
	    }
  }

  protected void onDestroy()
  {
    MediatorMgr.getInstance().unregisterMediator(LoadMediator.class.getName());
    super.onDestroy();
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
    if (this.mediator == null)
    {
      this.mediator = ((LoadMediator)MediatorMgr.getInstance().registerMediator(LoadMediator.class.getName()));
      this.mediator.setLoadActivity(this);
    }
    this.mediator.onActivityResume();
//    MobclickAgent.onResume(this);
//    MobclickAgent.getConfigParams(this, "isShowPopAd").equalsIgnoreCase("true");
  }

  public void showProgressBar()
  {
    if (this.progressBar != null)
      this.progressBar.setVisibility(0);
  }

  public void showSetNetworkDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(StringUtils.getString(this, 2131165296));
    localBuilder.setPositiveButton(StringUtils.getString(this, 2131165305), new DialogOnClickListener());
    localBuilder.setNegativeButton(StringUtils.getString(this, 2131165306), new DialogOnClickListener());
    this.setNetworkDialog = localBuilder.create();
    this.setNetworkDialog.show();
  }

  class DialogOnClickListener
    implements DialogInterface.OnClickListener
  {
    DialogOnClickListener()
    {
    }

    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      if (paramDialogInterface == LoadActivity.this.setNetworkDialog)
      {
//        if (paramInt != -1)
//          break label70;
//        if (Build.VERSION.SDK_INT <= 10)
//          break label51;
        LoadActivity.this.startActivity(new Intent("android.settings.SETTINGS"));
        LoadActivity.this.setNetworkDialog.dismiss();
      }
      label51: label70: 
      while (paramInt != -2)
        while (true)
        {
//          return;
          LoadActivity.this.startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
        }
      LoadActivity.this.setNetworkDialog.dismiss();
    }
  }
}