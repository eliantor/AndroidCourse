package corso.samples.feed;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import corso.samples.R;
import corso.samples.feed.RssFragment.OnRefreshListener;

public class RssListActivity extends FragmentActivity implements OnRefreshListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rss_activity);
	}

	@Override
	public void onRefresh() {
		RssService.start(this);
	}
}
