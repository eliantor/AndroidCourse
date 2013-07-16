package corso.samples.feed;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import corso.samples.R;

public class RssFragment extends Fragment implements LoaderCallbacks<Cursor>{
	private static final int LOADER_ID = 1;
	private static final String[] PROJECTION= 
		{Constants.Contract.News.Columns.ID, Constants.Contract.News.Columns.TITLE};
	
	public interface OnRefreshListener{
		public void onRefresh();
	}
	
	private OnRefreshListener mListener;
	private ListView mList;
	private FeedsAdapter mAdapter;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof OnRefreshListener){
			mListener = (OnRefreshListener)activity;
		}else{
			throw new IllegalStateException("Activity must implement "+ OnRefreshListener.class.getName());
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v  = inflater.inflate(R.layout.rss_list_fragment, container);
		v.findViewById(R.id.btn_refresh).setOnClickListener(fRefreshAction);
		mList = (ListView)v.findViewById(R.id.lv_feeds);
		mAdapter = new FeedsAdapter(getActivity());
		mList.setAdapter(mAdapter);
		
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		Cursor cursor = getActivity().getContentResolver().query(Constants.Contract.News.CONTENT_URI, PROJECTION, null, null, null);
//		mAdapter.swapCursor(cursor);
		getLoaderManager().initLoader(LOADER_ID, null, this);
	}
	
	@Override
	public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
		CursorLoader loader = new CursorLoader(getActivity(), Constants.Contract.News.CONTENT_URI, PROJECTION, null, null, null);
		loader.setUpdateThrottle(300);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		mAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		mAdapter.swapCursor(null);
	}
	
	private static class FeedsAdapter extends CursorAdapter{
		private LayoutInflater mInflater;
		public FeedsAdapter(Context context) {
			super(context, null, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView tv = (TextView)view.getTag();
			int titleIndex = cursor.getColumnIndexOrThrow(Constants.Contract.News.Columns.TITLE);
			String title = cursor.getString(titleIndex);
			tv.setText(title);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup list) {
			View v = mInflater.inflate(android.R.layout.simple_list_item_1, list,false);
			TextView tv = (TextView)v.findViewById(android.R.id.text1);
			v.setTag(tv);
			return v;
		}
		
	}
	
	private final OnClickListener fRefreshAction = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			mListener.onRefresh();
		}
	};

	
}
