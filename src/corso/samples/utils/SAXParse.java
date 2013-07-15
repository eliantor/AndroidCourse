package corso.samples.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.os.Bundle;

public class SAXParse {
	
	public static List<Bundle> parse(InputStream input) throws SAXException, IOException, ParserConfigurationException{
		SAXParser  sxp = SAXParserFactory.newInstance().newSAXParser();
		final List<Bundle> bundles = new ArrayList<Bundle>();
		sxp.parse(input, new DefaultHandler(){
			Bundle current = null;
			int state;
			@Override
			public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
				if(localName.equals("item")){
					current = new Bundle();
				}else if (localName.equals("title")){
					state = 1;
				}else if(localName.equals("description")){
					state = 2;
				}
				
				super.startElement(uri, localName, qName, attributes);
			}
			
			@Override
			public void characters(char[] ch, int start, int length)throws SAXException {
				if(state == 1){
					String title = new String(ch,start,length);
					current.putString("title", title);
				}else if(state == 2){
					
				}
				super.characters(ch, start, length);
			}
		});
		return bundles;
	}

}
