package com.inception.otaku;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AnimationType
{
  public static final int ALPHA = 5;
  public static final int MINI_RANDOM = -2;
  public static final int NONE = -1;
  public static final int RANDOM = 0;
  public static final int ROTATE = 4;
  public static final int SCALE_CENTER = 1;
  public static final int TRANSLATE_FROM_LEFT = 7;
  public static final int TRANSLATE_FROM_RIGHT = 6;
  private int[] a;
  private int b;

  public AnimationType()
  {
  }

  public AnimationType(int paramInt)
  {
    this.b = paramInt;
  }

  public AnimationType(int[] paramArrayOfInt)
  {
    this.a = paramArrayOfInt;
  }

  private static Map getAnimation(View paramView)
  {
    float f1 = paramView.getLayoutParams().width / 2.0F;
    float f2 = paramView.getLayoutParams().height / 2.0F;
    HashMap localHashMap = new HashMap();
    localHashMap.put("1", new ScaleAnimation(0.0F, 1.0F, 0.0F, 1.0F, f1, f2));
//    localHashMap.put("4", new f(270.0F, 360.0F, f1, f2, 0.0F, true));
    localHashMap.put("5", new AlphaAnimation(0.0F, 1.0F));
    localHashMap.put("6", new TranslateAnimation(paramView.getLayoutParams().width, 0.0F, 0.0F, 0.0F));
    localHashMap.put("7", new TranslateAnimation(-paramView.getLayoutParams().width, 0.0F, 0.0F, 0.0F));
    return localHashMap;
  }

  private List getAnimationList(View paramView)
  {
    Map localMap = getAnimation(paramView);
    ArrayList localArrayList = new ArrayList();
    String str = "";
    for (int i = 0; i < this.a.length; i++)
      str = str + this.a[i];
    if (str.contains("0"))
    {
      localArrayList.add(localMap.get("1"));
      localArrayList.add(localMap.get("4"));
      localArrayList.add(localMap.get("5"));
      localArrayList.add(localMap.get("6"));
      localArrayList.add(localMap.get("7"));
      return localArrayList;
    }
    if (str.contains("-2"))
    {
      localArrayList.add(localMap.get("6"));
      localArrayList.add(localMap.get("7"));
      return localArrayList;
    }
    boolean bool = str.contains("-1");
    int j = 0;
    if (bool)
      return localArrayList;
    do
    {
      localArrayList.add(localMap.get(this.a[j] + ""));
      j++;
      if (j >= this.a.length)
        break;
    }
    while ((this.a[j] < 9) && (this.a.length <= 7));
    localArrayList.add(localMap.get("1"));
    localArrayList.add(localMap.get("4"));
    localArrayList.add(localMap.get("5"));
    localArrayList.add(localMap.get("6"));
    localArrayList.add(localMap.get("7"));
    return localArrayList;
//    return localArrayList;
  }

  private List getAnimationList2(View paramView)
  {
    Map localMap = getAnimation(paramView);
    ArrayList localArrayList = new ArrayList();
    switch (this.b)
    {
    case -1:
    case 0:
    case 2:
    case 3:
    default:
      return localArrayList;
    case -2:
      localArrayList.add(localMap.get("6"));
      localArrayList.add(localMap.get("7"));
      return localArrayList;
    case 1:
      localArrayList.add(localMap.get("1"));
      return localArrayList;
    case 4:
      localArrayList.add(localMap.get("4"));
      return localArrayList;
    case 5:
      localArrayList.add(localMap.get("5"));
      return localArrayList;
    case 6:
      localArrayList.add(localMap.get("6"));
      return localArrayList;
    case 7:
    }
    localArrayList.add(localMap.get("7"));
    return localArrayList;
  }

  private static void startRotation(float paramFloat1, float paramFloat2, View paramView)
  {
//    f localf = new f(paramFloat1, paramFloat2, paramView.getWidth() / 2.0F, paramView.getHeight() / 2.0F, 0.0F, true);
//    localf.setDuration(2000L);
//    localf.setFillAfter(true);
//    localf.setInterpolator(new DecelerateInterpolator());
//    paramView.startAnimation(localf);
  }

  public void startAnimation(View paramView)
  {
    if (this.a == null);
    Random localRandom;
    for (List localList = getAnimationList2(paramView); ; localList = getAnimationList(paramView))
    {
      localRandom = new Random();
      if (localList.size() != 0)
        break;
      return;
    }
//    Animation localAnimation = (Animation)localList.get(localRandom.nextInt(localList.size()));
//    localAnimation.setDuration(2000L);
//    localAnimation.setInterpolator(new DecelerateInterpolator());
//    paramView.startAnimation(localAnimation);
  }

  protected void startMiniAdAnimation(int paramInt, View paramView)
  {
    ArrayList localArrayList;
    Random localRandom;
    if (paramInt == -2)
    {
      Map localMap = getAnimation(paramView);
      localArrayList = new ArrayList();
      localArrayList.add(localMap.get("6"));
      localArrayList.add(localMap.get("7"));
      localRandom = new Random();
      if (localArrayList.size() != 0);
    }
    else
    {
      return;
    }
    Animation localAnimation = (Animation)localArrayList.get(localRandom.nextInt(localArrayList.size()));
    localAnimation.setDuration(2000L);
    localAnimation.setInterpolator(new DecelerateInterpolator());
    paramView.startAnimation(localAnimation);
  }
}
