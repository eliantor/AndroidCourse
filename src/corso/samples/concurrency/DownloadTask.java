package corso.samples.concurrency;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadTask extends AsyncTask<String, Void, String>{

	private BufferFragment mFragment;
	
	public DownloadTask(BufferFragment fragment){
		mFragment = fragment;
	}
	
	@Override
	protected String doInBackground(String... params) {
		final String param  = params[0];
		try{
			JSONObject resp= Download.download(param);
			String result = resp.getString("response");
			return result;
		}catch(IOException e){
			return "ERROR NETWORK";
		}catch (JSONException e) {
			return "ERROR JSON FORMAT";
		}
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		mFragment.notifyResult(result);
	}

}
