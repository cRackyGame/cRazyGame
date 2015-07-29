package com.inception.utils;

import com.inception.model.ImageModel;
import com.inception.model.ImageModel.Image;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class ImageXMLHandler extends DefaultHandler
{
  private ImageModel.Image currentImage = null;
  private List<ImageModel.Image> images = null;
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
//      this.currentImage.name = str;
    }
    label41: 
    do
    {
//      return;
      if (this.tagName.equalsIgnoreCase("category"))
      {
//        this.currentImage.category = str;
        return;
      }
    }
    while (!this.tagName.equalsIgnoreCase("is_preview"));
//    this.currentImage.isPreview = str;
  }

  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {
    if (paramString2.equalsIgnoreCase("image"))
    {
      this.images.add(this.currentImage);
      this.currentImage = null;
    }
    this.tagName = null;
  }

  List<ImageModel.Image> getImages()
  {
    return this.images;
  }

  public void startDocument()
    throws SAXException
  {
    this.images = new ArrayList();
  }

  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    if (paramString2.equalsIgnoreCase("image"))
    {
      this.currentImage = new ImageModel.Image();
      this.currentImage.id = Integer.valueOf(Integer.parseInt(paramAttributes.getValue("id")));
    }
    this.tagName = paramString2;
  }
}