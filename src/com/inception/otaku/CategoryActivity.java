package com.inception.otaku;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.inception.mediator.CategoryMediator;
import com.inception.mediator.LoadMediator;
import com.inception.mediator.MediatorMgr;
import com.inception.model.CategoryModel;
import com.inception.model.CategoryModel.Category;
import com.inception.net.ConnectionMgr;
import com.inception.otaku.google.R;

import java.util.HashMap;
import java.util.List;

public class CategoryActivity extends Activity
{
  MyAdapter mAdapter = null;
  List<String> mData = null;
  private ListView mListView = null;
  private CategoryMediator mediator = null;

  private List<String> getData()
  {
    return CategoryModel.getInstance().getCategoryNameList();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    MediatorMgr.getInstance().unregisterMediator(LoadMediator.class.getName());
    this.mediator = ((CategoryMediator)MediatorMgr.getInstance().registerMediator(CategoryMediator.class.getName()));
    this.mediator.setActivity(this);
    this.mData = getData();
    setContentView(R.layout.category_layout);
    this.mListView = ((ListView)findViewById(R.id.category_list));
    this.mListView.setOnItemClickListener(new ItemClickListener());
    this.mAdapter = new MyAdapter(this);
    this.mListView.setAdapter(this.mAdapter);
//    new AdView(this, (LinearLayout)findViewById(R.id.AdLinearLayout)).DisplayAd();
  }

  protected void onDestroy()
  {
    MediatorMgr.getInstance().unregisterMediator(CategoryMediator.class.getName());
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
      switchToCoverActivity();
    return false;
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
  }

  public void switchToCoverActivity()
  {
    startActivity(new Intent(this, CoverActivity.class));
    finish();
    ConnectionMgr.getInstance().clear();
    MediatorMgr.getInstance().unregisterAllMediator();
  }

  class ItemClickListener
    implements AdapterView.OnItemClickListener
  {
    ItemClickListener()
    {
    }

    public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
    {
      LinearLayout localLinearLayout = (LinearLayout)paramView;
      if ((localLinearLayout != null) && (localLinearLayout.getChildCount() > 0))
      {
        TextView localTextView = (TextView)localLinearLayout.getChildAt(0);
        if (localTextView != null)
        {
          String str = (String)localTextView.getText();
          HashMap localHashMap = new HashMap();
          localHashMap.put("category", str);
          //MobclickAgent.onEvent(MainApp.getInstance(), "each_category_click_times", localHashMap);
          Intent localIntent = new Intent(CategoryActivity.this, LoadActivity.class);
          localIntent.putExtra("LoadActivityStatus", "LoadActivityFromCategory");
          localIntent.putExtra("LoadActivityCategoryName", str);
          CategoryActivity.this.startActivity(localIntent);
          CategoryActivity.this.finish();
        }
      }
    }
  }

  class MyAdapter extends BaseAdapter
  {
    private LayoutInflater mInflater;

    public MyAdapter(Context arg2)
    {
      Context localContext;
//      this.mInflater = LayoutInflater.from(localContext);
    }

    public int getCount()
    {
      return CategoryActivity.this.mData.size();
    }

    public Object getItem(int paramInt)
    {
      return CategoryActivity.this.mData.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
//      CategoryActivity.ViewHolder localViewHolder;
//      if (paramView == null)
//      {
//        localViewHolder = new CategoryActivity.ViewHolder(CategoryActivity.this);
//        paramView = this.mInflater.inflate(2130903046, null);
//        localViewHolder.nameText = ((TextView)paramView.findViewById(2131492887));
//        paramView.setTag(localViewHolder);
//      }
//      while (true)
//      {
//        String str = (String)CategoryActivity.this.mData.get(paramInt);
//        localViewHolder.nameText.setText(str);
//        CategoryModel.Category localCategory = CategoryModel.getInstance().getCategoryByName(str);
//        localViewHolder.nameText.setTextColor(Color.parseColor(localCategory.fontColor));
//        return paramView;
//        localViewHolder = (CategoryActivity.ViewHolder)paramView.getTag();
//      }
    	return null;
    }
  }

  public final class ViewHolder
  {
    public TextView nameText;

    public ViewHolder()
    {
    }
  }
}