package corso.samples.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import android.util.Xml;

public class XParse {
	
	/*
	 * <items>
	 * 	<item>
	 * 		<title>dasdas</title>
	 * 		<description>
	 * 	</item>
	 * <item>
	 * 		<title>
	 * 		<description>
	 * 	</item>
	 * <item>
	 * 		<title>
	 * 		<description>
	 * 	</item>
	 * 	...
	 * 	...
	 * </items>
	 */
	public static List<Bundle> fromXml(InputStream input) throws XmlPullParserException, IOException{
		XmlPullParser xpp = Xml.newPullParser();
		xpp.setInput(input, Charset.defaultCharset().name());
		int event = xpp.getEventType();
		Bundle current = null;
		ArrayList<Bundle> bundles = new ArrayList<Bundle>();
		while(event != XmlPullParser.END_DOCUMENT){
			switch (event) {
			case XmlPullParser.START_TAG:{
				String tagName = xpp.getName();
				if(current != null){
					if("title".equals(tagName)){
						String content = xpp.nextText();
						current.putString("title", content);
					}else if ("description".equals(tagName)){
						String content = xpp.nextText();
					}
					
				}
				
				if("item".equals(tagName)){
					current = new Bundle();
				}
			}
				break;
			case XmlPullParser.END_TAG:{
				String tagName = xpp.getName();
				if("item".equals(tagName)){
					bundles.add(current);
					current = null;
				}
				
			}break;
			default:
				break;
			}
			event = xpp.next();
		}
		
		return bundles;
	}
}
