package com.inception.otaku;

import android.os.Bundle;

public abstract interface ActivityObserver
{
  public abstract void onCreate(Bundle paramBundle);

  public abstract void onDestroy();

  public abstract void onPause();

  public abstract void onRestart();

  public abstract void onResume();

  public abstract void onStart();

  public abstract void onStop();
}