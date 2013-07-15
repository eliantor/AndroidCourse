package corso.samples.concurrency;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import corso.samples.R;
import corso.samples.concurrency.BufferFragment.OnDataReceivedListener;
import corso.samples.concurrency.RemoteListener.ResultListener;

public class DownloadActivity extends FragmentActivity implements OnDataReceivedListener{
	private final static String FTAG = "BUFFER_FRAGMENT";
	private EditText mEdit;
	private TextView mOut;
	private BufferFragment mFragment;
	private RemoteListener mListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download_activity);
		mEdit = (EditText)findViewById(R.id.edt_input_name);
		mOut = (TextView)findViewById(R.id.tv_response);
		findViewById(R.id.btn_submit_to_network).setOnClickListener(fListener);
		findViewById(R.id.btn_start_service).setOnClickListener(fListener);
		FragmentManager fm = getSupportFragmentManager();
		if(savedInstanceState == null){
			BufferFragment f = new BufferFragment();
			fm.beginTransaction()
					.add(f, FTAG)
					.commit();
			fm.executePendingTransactions();
		}
		mFragment = (BufferFragment)fm.findFragmentByTag(FTAG);
		mListener = new RemoteListener(new Handler());
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mFragment.setListener(this);
		mListener.setListener(new ResultListener() {
			@Override
			public void onResult(String value) {
				onDataReceived(value);
			}
		});
	}
	
	@Override
	public void onDataReceived(String message) {
		mOut.setText(message);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mFragment.setListener(null);
		mListener.setListener(null);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mListener = null;
	}
	private final OnClickListener fListener  = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String text = mEdit.getText().toString();
			final int id = v.getId();
			switch (id) {
			case R.id.btn_start_service:
				DownloadIntentService.start(DownloadActivity.this,text,mListener);
				break;
			case R.id.btn_submit_to_network:
				mFragment.startTask(text);
				break;
			default:
				break;
			}
			
//			new DownloadTask().execute(mEdit.getText().toString());
//			try {
//				JSONObject obj =Download.download(mEdit.getText().toString());
//				String result = obj.getString("response");
//				mOut.setText(result);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	};
	
}
