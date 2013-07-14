package corso.samples.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

public class DownloadHttp {
	public String download(String indirizzo) throws IOException{
		URL url = new URL(indirizzo);
		InputStream inputStream = url.openStream();
		String readStream = Streams.readStream(inputStream);
		return readStream;
	}
	
	public JSONObject downloadJson(String indirizzo) throws IOException,JSONException{
		return new JSONObject(download(indirizzo));
	}
	/*
	 * {}
	 * []
	 * 32132
	 * true false null
	 * [true, "ciao", 3]
	 * {"nome" : "Paolo",
	 *  "eta" : 32,
	 *  "maschio" : true,
	 *  "amici" : [, , , , ],
	 *  "dettagli" : {}
	 *  }
	 * 
	 * 
	 * 
	 */
}
