package com.inception.otaku;

public abstract interface UpdatePointsNotifier
{
  public abstract void getUpdatePoints(String paramString, int paramInt);

  public abstract void getUpdatePointsFailed(String paramString);
}
