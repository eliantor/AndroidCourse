package corso.samples;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends Activity implements OnClickListener{
	private EditText mInput;
	private Button mButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_activity);
		mInput = (EditText)findViewById(R.id.edt_name_2);
		mButton = (Button)findViewById(R.id.btn_return_result);
		mButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		setResult(RESULT_OK,new Intent().putExtra("RESULT", mInput.getText().toString()));
		finish();
	}
}
