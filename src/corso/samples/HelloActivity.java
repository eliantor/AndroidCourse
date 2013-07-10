package corso.samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class HelloActivity extends Activity implements OnClickListener{
	private final static int MY_REQUEST = 1;
	private final static String LAST_INPUT = "LAST_INPUT";
	
	private final static boolean USE_OTHER_ACTIVITY = false;
	
	private EditText mInput;
	private TextView mOutput;
	private String mLastInput;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hello_activity);
		findViewById(R.id.btn_set_name).setOnClickListener(this);
		mInput = (EditText)findViewById(R.id.edt_name);
		mOutput= (TextView)findViewById(R.id.tv_output);
		
		if(savedInstanceState != null){
			mLastInput = savedInstanceState.getString(LAST_INPUT);
		}
		
		if(mLastInput!=null){
			mOutput.setText(mLastInput);
		}
	}
	
	
	
	private void handleWithOtherActivity(){
		Intent callOtherActivity = new Intent(this,SubActivity.class);
		startActivityForResult(callOtherActivity, MY_REQUEST);
	}
	

	@Override
	public void onClick(View v) {
		if(USE_OTHER_ACTIVITY) {
			handleWithOtherActivity();
		} else {
			handleByMyself();
		}
	}

	private void handleByMyself() {
		Editable text = mInput.getText();
		mLastInput = text.toString();
		mOutput.setText(mLastInput);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(LAST_INPUT, mLastInput);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == MY_REQUEST && resultCode == RESULT_OK && data != null){
			mLastInput = data.getStringExtra("RESULT");
			mOutput.setText(mLastInput);
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	
	}
}
