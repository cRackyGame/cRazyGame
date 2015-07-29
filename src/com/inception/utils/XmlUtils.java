package com.inception.utils;

import com.inception.model.CategoryModel;
import com.inception.model.CategoryModel.Category;
import com.inception.model.ImageModel;
import com.inception.model.ImageModel.Image;
import java.io.StringReader;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlUtils
{
  public static List<CategoryModel.Category> categoryXMLParser(String paramString)
  {
    SAXParserFactory localSAXParserFactory = SAXParserFactory.newInstance();
    try
    {
      SAXParser localSAXParser = localSAXParserFactory.newSAXParser();
      CategoryXMLHandler localCategoryXMLHandler = new CategoryXMLHandler();
      localSAXParser.parse(new InputSource(new StringReader(paramString)), localCategoryXMLHandler);
      List localList = localCategoryXMLHandler.getCategorys();
      return localList;
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      localParserConfigurationException.printStackTrace();
      return null;
    }
    catch (SAXException localSAXException)
    {
      while (true)
        localSAXException.printStackTrace();
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public static List<ImageModel.Image> imageXMLParser(String paramString)
  {
    SAXParserFactory localSAXParserFactory = SAXParserFactory.newInstance();
    try
    {
      SAXParser localSAXParser = localSAXParserFactory.newSAXParser();
      ImageXMLHandler localImageXMLHandler = new ImageXMLHandler();
      localSAXParser.parse(new InputSource(new StringReader(paramString)), localImageXMLHandler);
      List localList = localImageXMLHandler.getImages();
      return localList;
    }
    catch (ParserConfigurationException localParserConfigurationException)
    {
      localParserConfigurationException.printStackTrace();
      return null;
    }
    catch (SAXException localSAXException)
    {
      while (true)
        localSAXException.printStackTrace();
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }
}
