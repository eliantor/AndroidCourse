package corso.samples.fragments.dynamic;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MemoryFragment extends Fragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

}
