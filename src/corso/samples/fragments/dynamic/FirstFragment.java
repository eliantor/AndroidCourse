package corso.samples.fragments.dynamic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import corso.samples.R;


public class FirstFragment extends Fragment{
	private final static String COUNTER = "counter";
	public final static String TAG = FirstFragment.class.getName();
	
	public static FirstFragment create(int counter){
		FirstFragment f = new FirstFragment();
		Bundle b = new Bundle();
		b.putInt(COUNTER, counter);
		f.setArguments(b);
		return f;
	}
	
	public static FirstFragment create(String counter){
		FirstFragment f = new FirstFragment();
		Bundle b = new Bundle();
		b.putInt(COUNTER, counter.length());
		f.setArguments(b);
		return f;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int c =getArguments().getInt(COUNTER);
		Log.d("ARGS", Integer.toString(c));
		return inflater.inflate(R.layout.first_layout, container,false);
	}
	
	
// a) funziona 
// b) crasha
// c) non crasha ma fa cose strane
}
