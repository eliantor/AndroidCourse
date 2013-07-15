package corso.samples.concurrency;

import java.lang.ref.WeakReference;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;
import corso.samples.R;

public class ConcurrentActivity extends FragmentActivity{
	private final static String TAG = "MSG";
	TextView mTv;
	
	private Handler mMyHandler;
	private SimpleAsyncTask mTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.concurrent_activity);
		mTv = (TextView)findViewById(R.id.tv_log_screen);
		mMyHandler = new MyHandler(this);
		Log.d(TAG, "1 This happens ...");
		if(savedInstanceState==null)logMessageWithTask();
		Log.d(TAG, "2 This happens...");	
		
	}
	
	@Override
	protected void onStop() {
		if(mTask!=null){
			mTask.cancel(true);
			mTask = null;
		}
		
		mMyHandler.removeMessages(MyHandler.LOG_MESSAGE);
		super.onStop();
	}
	
	private void logMessageWithTask(){
		mTask = new SimpleAsyncTask(mTv);
		mTask.execute(500);
	}
	
	private void logMessageWithHandlers(){
		Thread t = new Thread(new Worker(mMyHandler));
		t.start();
	}
	
	private void logMessageAsync(){
		Thread t  = new Thread(new Runnable() {
			@Override
			public void run() {
				final String message = " 3 Hello i'm "+Thread.currentThread().getName() 
												+ Thread.currentThread().getId();
				Log.d(TAG, message);
				Threads.sleepMillis(new Random().nextInt(1000));
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						mTv.setText(mTv.getText()+"\n"+message);
						
					}
				});
			}
		}, "ASYNC");
	
		t.start();
	}
	
	private static class Worker implements Runnable{
		private WeakReference<Handler> mMyHandler;

		Worker(Handler h){
			mMyHandler = new WeakReference<Handler>(h);
		}
		
		@Override
		public void run() {
			final String message = " 3 Hello i'm "+Thread.currentThread().getName() 
					+ Thread.currentThread().getId()+ " but i use an handler";
			Log.d(TAG, message);
			Threads.sleepMillis(new Random().nextInt(1000));
			final Handler h = mMyHandler.get();
			if(h !=null){
				Message handlerMsg = h.obtainMessage(MyHandler.LOG_MESSAGE, message);
				h.sendMessage(handlerMsg);
			}
		}
	}
	private static class MyHandler extends Handler{
		private final static int LOG_MESSAGE = 1;
		private TextView mTv;
		private MyHandler(ConcurrentActivity activity){
			mTv = activity.mTv;
		}
		
		@Override
		public void handleMessage(Message msg) {
			if(msg.what == LOG_MESSAGE){
				String message = (String)msg.obj;
				mTv.setText(mTv.getText()+"\n"+message);
			}
		}
	}
}
