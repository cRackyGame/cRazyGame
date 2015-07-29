package com.inception.net;

import java.util.ArrayList;

public class ConnectionMgr
{
  private static ConnectionMgr instance;
  public static final int kConnectionMax = 5;
  private ArrayList<Runnable> active = new ArrayList();
  private ArrayList<Runnable> queue = new ArrayList();

  public static ConnectionMgr getInstance()
  {
    if (instance == null)
      instance = new ConnectionMgr();
    return instance;
  }

  private void startNext()
  {
    if (!this.queue.isEmpty())
    {
      Runnable localRunnable = (Runnable)this.queue.get(0);
      this.queue.remove(0);
      this.active.add(localRunnable);
      new Thread(localRunnable).start();
    }
  }

  public void add(Runnable paramRunnable)
  {
    this.queue.add(paramRunnable);
    if (this.queue.size() < 5)
      startNext();
  }

  public void clear()
  {
    if (this.active != null)
      this.active.clear();
    if (this.queue != null)
      this.queue.clear();
  }

  public void remove(Runnable paramRunnable)
  {
    this.active.remove(paramRunnable);
    startNext();
  }
}