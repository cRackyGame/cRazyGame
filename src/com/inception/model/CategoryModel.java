package com.inception.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class CategoryModel
{
  private static CategoryModel instance = null;
  public ArrayList<Category> categorys = null;
  public int currentCategoryIndex = 0;

  private CategoryModel()
  {
    setCategorys(new ArrayList());
  }

  public static CategoryModel getInstance()
  {
    if (instance == null)
      instance = new CategoryModel();
    return instance;
  }

  public Category getCategoryById(String paramString)
  {
    Iterator localIterator = this.categorys.iterator();
    Category localCategory;
    do
    {
      if (!localIterator.hasNext())
        return null;
      localCategory = (Category)localIterator.next();
    }
    while (localCategory.id.compareToIgnoreCase(paramString) != 0);
    return localCategory;
  }

  public Category getCategoryByName(String paramString)
  {
    Iterator localIterator = this.categorys.iterator();
    Category localCategory;
    do
    {
      if (!localIterator.hasNext())
        return null;
      localCategory = (Category)localIterator.next();
    }
    while (localCategory.name.compareToIgnoreCase(paramString) != 0);
    return localCategory;
  }

  public ArrayList<String> getCategoryNameList()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.categorys.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      localArrayList.add(((Category)localIterator.next()).name);
    }
  }

  public ArrayList<Category> getCategorys()
  {
    return this.categorys;
  }

  public Category getCurrentCategory()
  {
    if (this.categorys.size() > 0)
      return (Category)this.categorys.get(this.currentCategoryIndex);
    return null;
  }

  public Category getDefaultCategory()
  {
    Iterator localIterator;
    if (this.categorys.size() > 0)
      localIterator = this.categorys.iterator();
    else
    {
    	return null;
    }
    Category localCategory;
    do
    {
      if (!localIterator.hasNext())
        return null;
      localCategory = (Category)localIterator.next();
    }
    while (localCategory.isDefault.compareToIgnoreCase("1") != 0);
    return localCategory;
  }

  public boolean isCurrentCategoryFree()
  {
    Category localCategory = (Category)this.categorys.get(this.currentCategoryIndex);
    boolean bool = false;
    if (localCategory != null)
    {
      String str = localCategory.chargeValue;
      bool = false;
      if (str != null)
      {
        int i = localCategory.chargeValue.compareToIgnoreCase("0");
        bool = false;
        if (i == 0)
          bool = true;
      }
    }
    return bool;
  }

  public boolean isCurrentCategoryPayed()
  {
    Category localCategory = (Category)this.categorys.get(this.currentCategoryIndex);
    boolean bool = false;
    if (localCategory != null)
    {
      String str = localCategory.isPay;
      bool = false;
      if (str != null)
      {
        int i = localCategory.isPay.compareToIgnoreCase("1");
        bool = false;
        if (i == 0)
          bool = true;
      }
    }
    return bool;
  }

  public void setCategorys(ArrayList<Category> paramArrayList)
  {
    this.categorys = paramArrayList;
    CategoryComparator localCategoryComparator = new CategoryComparator();
    Collections.sort(this.categorys, localCategoryComparator);
  }

  public void setCurrentCategoryById(String paramString)
  {
    int i;
    Iterator localIterator;
    if ((paramString != null) && (paramString.length() > 0))
    {
      i = 0;
      localIterator = this.categorys.iterator();
    }
    i = 0;
    localIterator = this.categorys.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      if (((Category)localIterator.next()).id.compareToIgnoreCase(paramString) == 0)
      {
        this.currentCategoryIndex = i;
        return;
      }
      i++;
    }
  }

  public void setCurrentCategoryByName(String paramString)
  {
    int i;
    Iterator localIterator;
    if ((paramString != null) && (paramString.length() > 0))
    {
      i = 0;
      localIterator = this.categorys.iterator();
    }
    i = 0;
    localIterator = this.categorys.iterator();    
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      if (((Category)localIterator.next()).name.compareToIgnoreCase(paramString) == 0)
      {
        this.currentCategoryIndex = i;
        return;
      }
      i++;
    }
  }

  public static class Category
  {
    public String chargeType = null;
    public String chargeValue = null;
    public String count = null;
    public int currentImageIndex = 0;
    public String fontColor = null;
    public String id = null;
    public String isDefault = null;
    public String isPay = "0";
    public String name = null;
  }

  public static class CategoryComparator
    implements Comparator<CategoryModel.Category>
  {
    public int compare(CategoryModel.Category paramCategory1, CategoryModel.Category paramCategory2)
    {
      return paramCategory2.id.compareTo(paramCategory1.id);
    }
  }
}
