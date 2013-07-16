package corso.samples.feed;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Network {

	public static InputStream open(String address) throws IOException{
		try{
			URL url = new URL(address);
			InputStream str =url.openStream();
			if(str instanceof BufferedInputStream){
				return str;
			}else{
				return new BufferedInputStream(str);
			}
		}catch(MalformedURLException ex){
			throw new RuntimeException("Invali url",ex);
		}
	}
}
