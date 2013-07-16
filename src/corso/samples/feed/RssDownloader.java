package corso.samples.feed;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.xmlpull.v1.XmlPullParserException;

import android.os.Bundle;
import corso.samples.utils.XParse;

public class RssDownloader {
	public static List<Bundle> downloadRss() throws IOException{
		InputStream in = null;
		try{
			in = Network.open(Constants.ADDRESS);
			return XParse.fromXml(in);
		}catch(XmlPullParserException ex){
			throw new RuntimeException("Invalid xml ", ex);
		}finally{
			if(in != null){
				in.close();
			}
		}
	}
	
	public static String toString(Bundle bundle){
		if(bundle == null)return "";
		Set<String> keys = bundle.keySet();
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		for(String key:keys){
			Object obj = bundle.get(key);
			String val = "null";
			if(obj instanceof Bundle){
				val = toString((Bundle)obj);
			}else if (obj != null){
				val = obj.toString();
			}
			sb.append(key).append(": ").append(val).append(", ");
		}
		sb.setLength(sb.length()-2);
		sb.append("}");
		return sb.toString();
	}
}
