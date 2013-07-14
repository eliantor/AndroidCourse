package corso.samples.fragments.basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import corso.samples.R;
import corso.samples.fragments.basics.EditFragment.OnNewTextListener;
import corso.samples.fragments.basics.OutputFragment.DataProvider;

public class Fragments1Activity extends FragmentActivity implements DataProvider{
	OutputFragment mOut;
	
	private final OnNewTextListener fNewText =  new OnNewTextListener() {
		@Override
		public void onNewText(String text) {
			updateAndUpper(text);
		}
	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragments1_activity);
		FragmentManager fm = getSupportFragmentManager();
		EditFragment edit =(EditFragment) fm.findFragmentById(R.id.EditFragment);
		edit.setOnNewTextListener(fNewText);
		mOut = (OutputFragment)fm.findFragmentById(R.id.Output);
		mOut.setProvider(this);
		updateAndUpper("ciao");
	}

	private void updateAndUpper(String text){
		mOut.updateList(text);
		mOut.updateList(text.toUpperCase());
	}

	@Override
	public List<String> provideData() {
		List<String> list = Arrays.asList("ciao","miao","bau");
		return new ArrayList<String>(list);
	}
	
}
