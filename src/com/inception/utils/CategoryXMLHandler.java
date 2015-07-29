package com.inception.utils;

import com.inception.model.CategoryModel;
import com.inception.model.CategoryModel.Category;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class CategoryXMLHandler extends DefaultHandler
{
  private List<CategoryModel.Category> categorys = null;
  private CategoryModel.Category currentCategory = null;
  private String tagName = null;

  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SAXException
  {
    String str;
    if (this.tagName != null)
    {
      str = new String(paramArrayOfChar, paramInt1, paramInt2);
//      if (!this.tagName.equalsIgnoreCase("name"))
//        break label41;
      this.currentCategory.name = str;
    }
    else
    {
    	return;
    }
    label41: 
    do
    {
//      return;
      if (this.tagName.equalsIgnoreCase("font_color"))
      {
        this.currentCategory.fontColor = str;
        return;
      }
      if (this.tagName.equalsIgnoreCase("count"))
      {
        this.currentCategory.count = str;
        return;
      }
      if (this.tagName.equalsIgnoreCase("default_category"))
      {
        this.currentCategory.isDefault = str;
        return;
      }
      if (this.tagName.equalsIgnoreCase("charge_type"))
      {
        this.currentCategory.chargeType = str;
        return;
      }
    }
    while (!this.tagName.equalsIgnoreCase("charge_value"));
    this.currentCategory.chargeValue = str;
  }

  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {
    if (paramString2.equalsIgnoreCase("category"))
    {
      this.categorys.add(this.currentCategory);
      this.currentCategory = null;
    }
    this.tagName = null;
  }

  List<CategoryModel.Category> getCategorys()
  {
    return this.categorys;
  }

  public void startDocument()
    throws SAXException
  {
    this.categorys = new ArrayList();
  }

  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    if (paramString2.equalsIgnoreCase("category"))
    {
      this.currentCategory = new CategoryModel.Category();
      this.currentCategory.id = paramAttributes.getValue("id");
    }
    this.tagName = paramString2;
  }
}