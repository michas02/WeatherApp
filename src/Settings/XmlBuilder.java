package Settings;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

/**
 *
 * @author Michał Sypniewski
 */
public class XmlBuilder {
	private Settings settings;
    private DocumentBuilderFactory docFactory;
    private DocumentBuilder docBuilder;
    private Document doc;
    private Element settingsModule;
    private Element websiteModule;
    private Element mainModule;
    private static int quizTriggerId=0;
    public XmlBuilder(Settings settings)
    {
    	this.settings=settings;
        try {
        docFactory=DocumentBuilderFactory.newInstance();
        docBuilder=docFactory.newDocumentBuilder();
        doc = docBuilder.newDocument();    
        settingsModule = doc.createElement("settings");
        websiteModule = doc.createElement("website");
        mainModule = doc.createElement("system");
        doc.appendChild(settingsModule);
        settingsModule.appendChild(websiteModule);
        settingsModule.appendChild(mainModule);
        
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XmlBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       }
    
    public void saveXml() throws TransformerException
    {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Config.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XmlBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setSettings(Settings settings)
    {
    	this.settings=settings;
    }
    public Settings getSettings()
    {
    	return this.settings;
    }
    
    public void createXml()
    {
    	addSetting("cityName",settings.getCityName(),mainModule);
    	addSetting("cityId", Integer.toString(settings.getCityId()), mainModule);
    	addSetting("latitude", Double.toString(settings.getLatitude()), mainModule);
    	addSetting("longitude", Double.toString(settings.getLongitude()), mainModule);
    	addSetting("updateMode", Integer.toString(settings.getUpdateMode()),mainModule);
    	
    	addSetting("dbUser",settings.getDbUser(),mainModule);
    	addSetting("dbPassword",settings.getDbPassword(),mainModule);
    	addSetting("dbUrl",settings.getDbUrl(),mainModule);
    	
    	
    	addSetting("website", settings.getWebsite(),websiteModule);
    	addSetting("line", settings.getLine(),websiteModule);
    	addSetting("ownWebsite", Boolean.toString(settings.isOwnWebsite()),websiteModule);
    	
    	addSetting("updateTime", Integer.toString(settings.getUpdateTime()),mainModule);
    }
    
    private void addSetting(String name, String value, Element element)
    {
    	   Attr attr = doc.createAttribute(name);
           attr.setValue(value);
           element.setAttributeNode(attr);
    }
 }
