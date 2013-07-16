package corso.samples.feed;

import java.io.IOException;
import java.util.List;

import corso.samples.concurrency.Threads;
import android.app.IntentService;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class RssService extends IntentService{
	private final static String TAG = RssService.class.getName();
	private final static String LOGTAG = "RSS";
	public RssService() {
		super(TAG);
	}

	public static void start(Context context){
		Intent intent = new Intent(context,RssService.class);
		context.startService(intent);
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		try {
			List<Bundle> vals = RssDownloader.downloadRss();

			ContentResolver cr = getContentResolver();
			for(Bundle b : vals){
				String bundleContent = RssDownloader.toString(b);
				Log.d(LOGTAG, "received: "+bundleContent);
				ContentValues cv = new ContentValues();
				cv.put(Constants.Contract.News.Columns.TITLE, b.getString("title"));
				Threads.sleepMillis(150);
				cr.insert(Constants.Contract.News.CONTENT_URI,cv);	
			}
		} catch (IOException e) {
			
		}
		
	}
	
	

}
