package corso.samples.fragments.dynamic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

public class OpaqueFragment extends Fragment{
	public final static String OPAQUE_TAG = "OPAQUE";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		Log.d("HIDE", "AND JEKYLL create");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.d("HIDE", "AND JEKYLL activity");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("HIDE", "AND JEKYLL destroy");
	}
}
