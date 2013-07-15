package corso.samples.concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class Download {
	private final static String SERVICE = "https://script.google.com/macros/s/AKfycbxlVZVB9Qud36N0lPdt-cJcYLMVWTyTgJ2w5ng9nhRjKaw3EIo0/exec";
	
	public static JSONObject download(String param) throws IOException, JSONException{
		URL url = new URL(String.format("%s?name=%s", SERVICE,param));
		char[] buffer = new char[1024];
		BufferedReader reader = null;
		StringWriter sw = null;
		try{
			reader = new BufferedReader(new InputStreamReader(url.openStream(),Charset.defaultCharset()));
			sw = new StringWriter();
			int read = 0;
			while((read =reader.read(buffer))!=-1){
				sw.write(buffer, 0,read);
			}
			JSONObject obj = new JSONObject(sw.toString());
			return obj;
		}finally{
			if(reader!=null)reader.close();
			if(sw!=null)sw.close();
		}
		
	}
}
