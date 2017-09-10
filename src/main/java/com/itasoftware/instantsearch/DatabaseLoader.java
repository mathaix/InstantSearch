package com.itasoftware.instantsearch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private XMLEventReader reader;

	@Autowired
	private PropertyDatabase db;

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("Loading Database");
		ClassLoader classLoader = getClass().getClassLoader();
		File f = new File(classLoader.getResource("nrhp.xml.gz").getFile());  
		loadDatabase(f);
	}
	
	private 
	
	void loadDatabase(File f) throws FileNotFoundException, IOException, XMLStreamException, FactoryConfigurationError {
        InputStream zipReader = new GZIPInputStream(new FileInputStream(f));
        reader = XMLInputFactory.newInstance().createXMLEventReader(zipReader); 
        
        while (reader.hasNext())
        {
           XMLEvent event  = (XMLEvent) reader.next();
           if (event.isStartElement()) 
           {
              if (event.asStartElement().getName().getLocalPart()=="property")
              {
            	  	NrhpProperty np = new NrhpProperty(event.asStartElement().getAttributeByName(new QName("id")).getValue());
          		initProperty(np);
          		db.add(np);
              }
          }       
        }       
	}
	
	private void initProperty(NrhpProperty np){
		while (reader.hasNext())
		{
			XMLEvent event  = (XMLEvent) reader.next();
			
			if (event.isStartElement()){
				if (event.asStartElement().getName().getLocalPart()=="address")
					np.setAddress(parseText());
		
				if (event.asStartElement().getName().getLocalPart()=="name")
					np.addName(parseText());
				
				if (event.asStartElement().getName().getLocalPart()=="city")
					np.setCity(parseText());
				
				if (event.asStartElement().getName().getLocalPart()=="state")
					np.setState(parseText());
				
			}
			
			if (event.isEndElement())
			{	
				if (event.asEndElement().getName().getLocalPart()=="property")
				{
					break;
				}
			}		
		}	
	}
	
	private String parseText ()
	{
		while (reader.hasNext())
		{
			XMLEvent event = (XMLEvent) reader.next();
			
			if (event.isCharacters() )
			{		
				return event.asCharacters().getData();
			}
			else
			{	if (event.isEndElement())
					break;
				else
					event = (XMLEvent) reader.next();
			}
		}
				
		return null;
	}
}
