package com.inception.mediator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import android.util.Log;

public class MediatorMgr
{
  private static MediatorMgr instance = null;
  private HashMap<String, MediatorBase> mediators = new HashMap<String,MediatorBase>();

  public static MediatorMgr getInstance()
  {
    if (instance == null)
      instance = new MediatorMgr();
    return instance;
  }

    public MediatorBase getMediatorByName(String paramString)
    {
    	Log.d("wuhongbo", paramString);
    	Iterator localIterator = this.mediators.entrySet().iterator();
	    Map.Entry localEntry;
	    do
	    {
	      if (!localIterator.hasNext())
	      {
	        return null;
	      }
	      localEntry = (Map.Entry)localIterator.next();
	    }
	    while (!((String)localEntry.getKey()).equalsIgnoreCase(paramString));
	    return (MediatorBase)localEntry.getValue();
  }

  public MediatorBase registerMediator(String paramString)
  {
    MediatorBase localMediatorBase1 = getMediatorByName(paramString);
    if (localMediatorBase1 != null)
      return localMediatorBase1;
    try
    {
      MediatorBase localMediatorBase2 = (MediatorBase)Class.forName(paramString).newInstance();
      this.mediators.put(paramString, localMediatorBase2);
      localMediatorBase2.onRegister();
      return localMediatorBase2;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return null;
    }
    catch (InstantiationException localInstantiationException)
    {
      while (true)
        localInstantiationException.printStackTrace();
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      while (true)
        localClassNotFoundException.printStackTrace();
    }
  }

  public void unregisterAllMediator()
  {
    Iterator localIterator = this.mediators.entrySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        this.mediators.clear();
        return;
      }
      ((MediatorBase)((Map.Entry)localIterator.next()).getValue()).onUnregister();
    }
  }

  public void unregisterMediator(String paramString)
  {
    MediatorBase localMediatorBase = getMediatorByName(paramString);
    if (localMediatorBase != null)
    {
      localMediatorBase.onUnregister();
      this.mediators.remove(paramString);
    }
  }
}
