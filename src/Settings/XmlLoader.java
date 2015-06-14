package Settings;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlLoader {

	private Settings settings;
	  public XmlLoader(String file) 
	  {
		  settings = new Settings();
		    try {
		 
			File fXmlFile = new File(file);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
		 
			doc.getDocumentElement().normalize();
		 
			NodeList nList = doc.getElementsByTagName("system");
		 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					settings.setCityId(Integer.parseInt(eElement.getAttribute("cityId")));
					settings.setCityName(eElement.getAttribute("cityName"));
					settings.setLatitude(Double.parseDouble(eElement.getAttribute("latitude")));
					settings.setLongitude(Double.parseDouble(eElement.getAttribute("longitude")));
					settings.setUpdateMode(Integer.parseInt(eElement.getAttribute("updateMode")));
					settings.setUpdateTime(Integer.parseInt(eElement.getAttribute("updateTime")));
				}
			}
			
			nList = doc.getElementsByTagName("website");
			 
			for (int temp = 0; temp < nList.getLength(); temp++) {
		 
				Node nNode = nList.item(temp);
		 
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		 
					Element eElement = (Element) nNode;
					settings.setWebsite(eElement.getAttribute("website"));
					settings.setLine(eElement.getAttribute("line"));
					settings.setOwnWebsite(Boolean.parseBoolean(eElement.getAttribute("ownWebsite")));
				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		  }
	  
	  public Settings getSettings()
	  {
		  return this.settings;
	  }
		 
}