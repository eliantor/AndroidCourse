package corso.samples.lists;

import java.util.List;

import corso.samples.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

class BasicAdapter extends BaseAdapter{
	
	private final List<String> mModel;
	private LayoutInflater mInflater;
	
	BasicAdapter(Context context,List<String> model) {
		mModel = model;
		mInflater = LayoutInflater.from(context);
	}
	@Override
	public int getCount() {
		return mModel == null?0:mModel.size();
	}

	@Override
	public String getItem(int position) {
		return mModel.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View recycledView, ViewGroup listView) {
		// ottnere l'item corrente
		String current = getItem(position);

		//espandere vista == setContentView() nell'activity

		final View view;
		final ViewHolder h;
		if(recycledView == null){
			view  = mInflater.inflate(R.layout.item_row,
					listView, 
					/*attachToRootPermanently*/false);
			
			h = new ViewHolder();
		
			// == a findViewById() nell'activity
			TextView tv = (TextView)view.findViewById(R.id.tv_row_name);
			h.textView = tv;
			
			view.setTag(h);
			
		} else {
			view = recycledView;
			h = (ViewHolder)view.getTag();
		}
		
		// collegare modello a vista
		h.textView.setText(current);
		
		// restituirla
		return view;
	}

	private static class ViewHolder{
		public TextView textView;
		// n altre view che cerco all'interno della radice
	}
}
