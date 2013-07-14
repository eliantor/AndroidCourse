package corso.samples.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Streams {


	public static String readStream(InputStream fileName)  throws IOException
	{
		BufferedReader reader = null;
		try{
			reader =  getInputStream(fileName);
			String line;
			StringBuilder sb = new StringBuilder();
			while((line = reader.readLine())!=null){
				sb.append(line).append('\n');
			}
			return sb.toString();
		
		}finally{
			if(reader!=null)reader.close();
		}
		
	}
	private static BufferedReader getInputStream(InputStream fileName) {
		return new BufferedReader(new InputStreamReader(fileName,Charset.defaultCharset()));
	}
	public static String readFile(String fileName)  throws IOException
	{
		BufferedReader reader = null;
		try{
			reader =  getInputStream(fileName);
			String line;
			StringBuilder sb = new StringBuilder();
			while((line = reader.readLine())!=null){
				sb.append(line).append('\n');
			}
			return sb.toString();
		
		}finally{
			if(reader!=null)reader.close();
		}
		
	}

	private static BufferedReader getInputStream(String fileName)
			throws FileNotFoundException {
		return new BufferedReader(new InputStreamReader(new FileInputStream(fileName),Charset.defaultCharset()));
	}
}

