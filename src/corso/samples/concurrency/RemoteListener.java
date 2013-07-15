package corso.samples.concurrency;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class RemoteListener extends ResultReceiver{

	public RemoteListener(Handler handler) {
		super(handler);
	}
	
	public static interface ResultListener{
		public void onResult(String value);
	}
	
	private ResultListener mListener;
	
	public void setListener(ResultListener listener){
		mListener = listener;
	}
	
	@Override
	protected void onReceiveResult(int resultCode, Bundle resultData) {
		super.onReceiveResult(resultCode, resultData);
		if(mListener!= null){
			mListener.onResult(resultData.getString(DownloadIntentService.RESPONSE));
		}
	}
	

}
