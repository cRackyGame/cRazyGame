package com.inception.request;

import com.inception.model.ImageModel;
import com.inception.model.ImageModel.Image;

public class GetImageRequest extends RequestBase
{
  public String action;
  public ImageModel.Image image;
}
