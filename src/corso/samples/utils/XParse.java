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
		boolean parsing = false;
		while(event != XmlPullParser.END_DOCUMENT){
			switch (event) {
			case XmlPullParser.START_TAG:{
				String tagName = xpp.getName();
				if(parsing== false && "items".equals(tagName)){
					parsing = true;
				}
				if(parsing == true){
					if(current != null){
						if("title".equals(tagName)){
							String content = xpp.nextText();
							current.putString("title", content);
						}else if ("description".equals(tagName)){
							String content = extractDescription(xpp);
							current.putString("description", content);
						}
					}
					
					if("item".equals(tagName)){
						current = new Bundle();
					}
				}
			}break;
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
//		input.close();
		return bundles;
	}

	private static String extractDescription(XmlPullParser xpp) throws XmlPullParserException, IOException {
		int event = xpp.getEventType();
		StringBuilder sb = new StringBuilder();
		do{
			event =xpp.next();
			switch (event) {
			case XmlPullParser.START_TAG:
				String st = xpp.getName();
				sb.append("<").append(st).append(">");
				break;
			case XmlPullParser.TEXT:
				String txt = xpp.getText();
				sb.append(txt);
				break;
			case XmlPullParser.END_TAG:
				String e = xpp.getName();
				sb.append("</").append(e).append(">");
				break;
			default:
				break;
			}
		}while(!(event == XmlPullParser.END_TAG && xpp.getName().equals("description")));
		return sb.toString();
	}
}
