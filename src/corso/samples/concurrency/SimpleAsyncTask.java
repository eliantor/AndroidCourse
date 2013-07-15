package corso.samples.concurrency;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class SimpleAsyncTask extends AsyncTask<Integer/*params*/, Long/*progress*/, String/*result*/>{
	private final static String TAG = "ASYNC_EXEC";
	private TextView mLog;
	
	SimpleAsyncTask(TextView log) {
		mLog = log;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Log.d(TAG, "Before execute");
	}
	
	@Override
	protected String doInBackground(Integer... params) {
		Integer p = params.length==1? params[0]: 200;
		p = p == null?200:p;
		String endMessage = "the end";
		
		Threads.sleepMillis(p);
		publishProgress((long)p);
		p+=p;
		
		Threads.sleepMillis(p);
		publishProgress((long)p);
		p+=p;
		
		Threads.sleepMillis(p);
		publishProgress((long)p);
		
		return endMessage;
	}
	
	@Override
	protected void onProgressUpdate(Long... values) {
		Long  v  = values[0];
		mLog.append("\nprogress:"+v);
	}
	
	
	@Override
	protected void onPostExecute(String result) {
		mLog.append("\n"+result);
	}
	
	

}
