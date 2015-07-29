package com.inception.model;

import android.graphics.Bitmap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ImageModel
{
  private static ImageModel instance = null;
  public int currentImageIndex = 0;
  public ArrayList<Image> images = null;
  public int progress = 0;

  public static ImageModel getInstance()
  {
    if (instance == null)
      instance = new ImageModel();
    return instance;
  }

  public Image getCurrentImage()
  {
    if (this.images == null);
    while ((this.currentImageIndex < 0) || (this.currentImageIndex >= this.images.size()))
      return null;
    return (Image)this.images.get(this.currentImageIndex);
  }

  public int getCurrentImageIndex()
  {
    return this.currentImageIndex;
  }

  public int getCurrentProgress()
  {
    int i = this.images.size();
    if ((i == 0) || (this.currentImageIndex == 0))
      return 0;
    return 100 * (1 + this.currentImageIndex) / i;
  }

  public Image getImageByProgress(int paramInt)
  {
    this.currentImageIndex = (-1 + paramInt * getTotalImageCount() / 100);
    if (this.currentImageIndex < 0)
      this.currentImageIndex = 0;
    if (this.currentImageIndex < this.images.size())
      return (Image)this.images.get(this.currentImageIndex);
    return null;
  }

  public Image getNextImage()
  {
    int i = this.images.size();
    if (this.currentImageIndex < i - 1)
      return (Image)this.images.get(1 + this.currentImageIndex);
    this.currentImageIndex = (i - 1);
    return null;
  }

  public int getNextProgress()
  {
    int i = this.images.size();
    if (i != 0)
      return 100 * (2 + this.currentImageIndex) / i;
    return 0;
  }

  public Image getPrevBitmap()
  {
    int i = this.images.size();
    if ((this.currentImageIndex > 0) && (this.currentImageIndex < i))
      return (Image)this.images.get(-1 + this.currentImageIndex);
    return null;
  }

  public int getProgress()
  {
    return this.progress;
  }

  public int getTotalImageCount()
  {
    if (this.images != null)
      return this.images.size();
    return 0;
  }

  public void initWithImages(ArrayList<Image> paramArrayList)
  {
    if ((paramArrayList != null) && (paramArrayList.size() > 0))
      resetImages();
    this.images = new ArrayList(paramArrayList.size());
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayList.size())
      {
        ImageComparator localImageComparator = new ImageComparator();
        Collections.sort(this.images, localImageComparator);
        return;
      }
      Image localImage = new Image();
      localImage.id = ((Image)paramArrayList.get(i)).id;
      localImage.name = ((Image)paramArrayList.get(i)).name;
      localImage.category = ((Image)paramArrayList.get(i)).category;
      localImage.isPreview = ((Image)paramArrayList.get(i)).isPreview;
      localImage.bitmap = ((Image)paramArrayList.get(i)).bitmap;
      this.images.add(localImage);
    }
  }

  public void releaseBitmap()
  {
    int i = 0;
    Iterator localIterator = this.images.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Image localImage = (Image)localIterator.next();
      int j = i + 1;
      if (i == this.currentImageIndex)
      {
        i = j;
      }
      else
      {
        if ((localImage != null) && (localImage.bitmap != null) && (!localImage.bitmap.isRecycled()))
        {
          localImage.bitmap.recycle();
          localImage.bitmap = null;
        }
        i = j;
      }
    }
  }

  public void resetImages()
  {
    if ((this.images == null) || (this.images.size() <= 0))
      return;
    for (int i = 0; ; i++)
    {
      if (i >= this.images.size())
      {
        this.images.clear();
        this.currentImageIndex = 0;
        this.progress = 0;
        return;
      }
      Bitmap localBitmap = ((Image)this.images.get(i)).bitmap;
      if ((localBitmap != null) && (!localBitmap.isRecycled()))
        localBitmap.recycle();
    }
  }

  public void setBitmap(Bitmap paramBitmap)
  {
    if ((this.images != null) && (this.images.get(this.currentImageIndex) != null))
      ((Image)this.images.get(this.currentImageIndex)).bitmap = paramBitmap;
  }

  public void setCurrentImageIndex(int paramInt)
  {
    this.currentImageIndex = paramInt;
  }

  public void setProgress(int paramInt)
  {
    this.progress = paramInt;
  }

  public static class Image
  {
    public Bitmap bitmap = null;
    public String category = null;
    public Integer id;
    public String isPreview = "0";
    public String name = null;
  }

  public static class ImageComparator
    implements Comparator<ImageModel.Image>
  {
    public int compare(ImageModel.Image paramImage1, ImageModel.Image paramImage2)
    {
      if ((paramImage2.id != null) && (paramImage1.id != null))
        return paramImage1.id.compareTo(paramImage2.id);
      return 0;
    }
  }
}