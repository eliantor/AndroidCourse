package corso.samples.fragments.dynamic;

import corso.samples.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;

public class DynamicFragmentsActivity extends FragmentActivity implements OnCheckedChangeListener, OnClickListener{
	private final static String FTAG = "fragment_tag";
	FrameLayout mHost;
	FragmentManager mFragManager;
	private int mCounter;
	MemoryFragment mMemory;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("HIDE", "BIRTH");
		super.onCreate(savedInstanceState);
		mFragManager = getSupportFragmentManager();
		if(savedInstanceState==null){
			mFragManager.beginTransaction().add(new MemoryFragment(), "MEM").commit();
			mFragManager.executePendingTransactions();
		}
		mMemory = (MemoryFragment) mFragManager.findFragmentByTag("MEM");
		
		setContentView(R.layout.dynamic_activity);
		CheckBox cb = (CheckBox)findViewById(R.id.ck_choose_fragment);
		cb.setOnCheckedChangeListener(this);
		findViewById(R.id.btn_add_hidden).setOnClickListener(this);
		
		mHost = (FrameLayout)findViewById(R.id.host);
	}
	
	private void getMyFragment(){
		mFragManager.findFragmentByTag("first");
	}
	@Override
	public void onCheckedChanged(CompoundButton checkbox, boolean isChecked) {
		Fragment frag;
		String tag;
		if(isChecked){
			tag = "first";
			frag = FirstFragment.create(mCounter++);
		}else{
			tag = "second";
			frag = new SecondFragment();
		}
		FragmentTransaction tx = mFragManager.beginTransaction();
		tx.replace(R.id.host, frag, tag);
		tx.addToBackStack(null);
		tx.commit();
	}

	@Override
	public void onClick(View v) {
		Fragment opaque = mFragManager.findFragmentByTag(OpaqueFragment.OPAQUE_TAG);
		if(opaque == null){
		
			mFragManager.beginTransaction()
						.add(new OpaqueFragment(), OpaqueFragment.OPAQUE_TAG)
						.commit();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("HIDE", "DEATH");
	}

}
