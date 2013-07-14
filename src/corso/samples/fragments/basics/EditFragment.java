package corso.samples.fragments.basics;

import corso.samples.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditFragment extends Fragment{
	private final OnClickListener fListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			String element = mInput.getText().toString();
			boolean valid = mValidator==null?true:mValidator.isValid(element);
			if(valid){
				if(mListener!=null)mListener.onNewText(element);
				if(mEraseOnPress)mInput.setText(null);
			}
		}
	};
	
	public interface Validator{
		public boolean isValid(String text);
	}
	public interface OnNewTextListener{
		public void onNewText(String text);
	}
	
	private Validator mValidator;
	private EditText mInput;
	private boolean mEraseOnPress;
	private OnNewTextListener mListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.edit_fragment,container,false);
		mInput  = (EditText)v.findViewById(R.id.edt_input);
		v.findViewById(R.id.btn_submit).setOnClickListener(fListener);
		return v;
	}
	
	
	public void setOnNewTextListener(OnNewTextListener listener){
		mListener = listener;
	}
}
