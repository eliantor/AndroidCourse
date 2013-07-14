package corso.samples.fragments.basics;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import corso.samples.R;
import corso.samples.lists.BasicAdapter;

public class OutputFragment extends Fragment{
	
	private ListView mList;
	private BasicAdapter mAdapter;
	private List<String> mStrings;
	private DataProvider mProvider;
	
	public interface DataProvider{
		public List<String> provideData();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
//		if(activity instanceof DataProvider){
//			mProvider = (DataProvider)activity;
//		}else{
//			throw new IllegalStateException("Activity must implement "+DataProvider.class.getName());
//		}
	}
	
	public void setProvider(DataProvider provider){
		mProvider = provider;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View  v = inflater.inflate(R.layout.output_layout, container);
		mList = (ListView)v;
		return v;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mStrings = mProvider.provideData();
		mAdapter = new BasicAdapter(getActivity(), mStrings);
		mList.setAdapter(mAdapter);
		
	}
	public void updateList(String text){


		mAdapter.notifyDataSetChanged();
	}
	
	

}
