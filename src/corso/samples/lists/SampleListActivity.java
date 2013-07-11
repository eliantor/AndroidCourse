package corso.samples.lists;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import corso.samples.R;

public class SampleListActivity extends Activity implements OnClickListener{
	private final static String SAVED_LIST = "saved_list";
	
	private ListView mListView;
	private ArrayList<String> mModel;
	private BasicAdapter mAdapter;
	
	private EditText mInput;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lists_layout);
		
		mInput = (EditText)findViewById(R.id.edt_insert_element);
		findViewById(R.id.btn_add).setOnClickListener(this);
		
		if(savedInstanceState == null){
			initModel();
		} else{
			mModel = savedInstanceState.getStringArrayList(SAVED_LIST);
		}
		
		mListView = (ListView)findViewById(R.id.lv_people);
		
		mAdapter = new BasicAdapter(this, mModel);
		
		mListView.setAdapter(mAdapter);
		
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Toast.makeText(SampleListActivity.this, "You have clicked :"+mModel.get(position), Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putStringArrayList(SAVED_LIST, mModel);
	}
	
	private void initModel() {
		mModel = new ArrayList<String>();
		mModel.add("First item");
		mModel.add("Second item");
		mModel.add("Third item");
	}

	@Override
	public void onClick(View v) {
		String text = mInput.getText().toString();
		if(text != null && text.length()>0){
			mModel.add(text);
			mAdapter.notifyDataSetChanged();
		}
		
	}
	
	
	
	
}
