package com.inception.otaku;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ScrollView;

public class LazyScrollView extends ScrollView
{
  private Handler handler;
  private OnScrollListener onScrollListener;
  View.OnTouchListener onTouchListener = new View.OnTouchListener()
  {
    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      switch (paramAnonymousMotionEvent.getAction())
      {
      case 0:
      default:
      case 1:
      }
      while (true)
      {
//        return false;
        if ((LazyScrollView.this.view != null) && (LazyScrollView.this.onScrollListener != null))
          LazyScrollView.this.handler.sendMessageDelayed(LazyScrollView.this.handler.obtainMessage(1), 200L);
      }
    }
  };
  private View view;

  public LazyScrollView(Context paramContext)
  {
    super(paramContext);
  }

  public LazyScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public LazyScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private void init()
  {
    setOnTouchListener(this.onTouchListener);
    this.handler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        super.handleMessage(paramAnonymousMessage);
        switch (paramAnonymousMessage.what)
        {
        default:
        case 1:
        }
        do
        {
          do
          {
            do
            {
//              return;
              if (LazyScrollView.this.view.getMeasuredHeight() > LazyScrollView.this.getScrollY() + LazyScrollView.this.getHeight())
                break;
            }
            while (LazyScrollView.this.onScrollListener == null);
            LazyScrollView.this.onScrollListener.onBottom();
//            return;
            if (LazyScrollView.this.getScrollY() != 0)
              break;
          }
          while (LazyScrollView.this.onScrollListener == null);
          LazyScrollView.this.onScrollListener.onTop();
          return;
        }
        while (LazyScrollView.this.onScrollListener == null);
//        LazyScrollView.this.onScrollListener.onScroll();
      }
    };
  }

  public int computeVerticalScrollOffset()
  {
    return super.computeVerticalScrollOffset();
  }

  public int computeVerticalScrollRange()
  {
    return super.computeHorizontalScrollRange();
  }

  public void getView()
  {
    this.view = getChildAt(0);
    if (this.view != null)
      init();
  }

  public void setOnScrollListener(OnScrollListener paramOnScrollListener)
  {
    this.onScrollListener = paramOnScrollListener;
  }

  public static abstract interface OnScrollListener
  {
    public abstract void onBottom();

    public abstract void onScroll();

    public abstract void onTop();
  }
}