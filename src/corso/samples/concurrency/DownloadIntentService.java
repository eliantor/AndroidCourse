package corso.samples.concurrency;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

public class DownloadIntentService extends IntentService{
	public final static int OK = 1;
	public final static int KO = 0;
	public final static String RESPONSE = "response";
	
	private final static String NAME_PARAM = "name_param";
	private final static String TOKEN = "token";
	public static void start(Context context,String param,RemoteListener token){
		Intent intent = new Intent(context,DownloadIntentService.class);
		intent.putExtra(NAME_PARAM, param);
		if(token != null){
			intent.putExtra(TOKEN, token);
		}
		context.startService(intent);
	}
	
	public DownloadIntentService(){
		super(DownloadIntentService.class.getName());
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		String returnValue = null;
		try {
			JSONObject obj =Download.download(intent.getStringExtra(NAME_PARAM));
			returnValue = obj.getString("response");
		} catch (IOException e) {
			returnValue = "ERROR NETWORK";
		} catch (JSONException e) {
			returnValue = "ERROR JSON FORMAT";
		}
		Log.d("SERVICE", returnValue);
		ResultReceiver listener = intent.getParcelableExtra(TOKEN);
		if(listener!=null){
			Bundle data = new Bundle();
			data.putString(RESPONSE, returnValue);
			listener.send(OK, data);
		}
	}

}
