package corso.samples.concurrency;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class BufferFragment extends Fragment{
	
	public interface OnDataReceivedListener{
		public void onDataReceived(String message);
	}
	
	private OnDataReceivedListener mListener;
	private DownloadTask mTask;
	private String mCachedResult;
	
	public void setListener(OnDataReceivedListener listener){
		mListener = listener;
		if(mListener != null && mCachedResult!=null){
			String result = mCachedResult;
			mCachedResult = null;
			mListener.onDataReceived(result);
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}
	
	public void startTask(String message){
		if(mTask== null){
			mTask = new DownloadTask(this);
			mTask.execute(message);
		}
	}

	public void notifyResult(String result) {
		
		if(mListener !=null){
			mListener.onDataReceived(result);
		}else{
			mCachedResult = null;
		}
		mTask = null;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(mTask!=null){
			mTask.cancel(true);
			mTask = null;
		}
	}
	
	

}
