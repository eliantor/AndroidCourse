package corso.samples.feed;

import corso.samples.feed.Constants.Contract.News;
import android.net.Uri;
import android.provider.BaseColumns;

public class Constants {
	public static final String ADDRESS = "http://ww2.unime.it/ingegneria/new/rss3.php";
	
	public static final String DB = "feeds.db";
	public static final int DB_VERSION = 4;
	
	private final static String SCHEME = "content://";
	public final static String AUTHORITY = "corso.samples.feed.provider";
	
	public final static Uri BASE = Uri.parse(SCHEME+AUTHORITY);
	
	public static class Contract{
		public static class News{
			public static String TABLE = "news";
			
			public static Uri CONTENT_URI = BASE.buildUpon().appendPath(TABLE).build();
			// content://corso.samples.feed.provider/news
			// content://corso.samples.feed.provider/news
			
			public static class Columns{
				public static String ID = BaseColumns._ID; // "_id";
				public static String TITLE = "title";
				public static String DESCRIPTION = "description";
			}
		}
	}
	
	public static String CREATE_NEWS = "CREATE TABLE IF NOT EXISTS "+News.TABLE+ " ("+
			News.Columns.ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
			News.Columns.TITLE+ " TEXT,"+
			News.Columns.DESCRIPTION+ " TEXT"
			+ ")";
	
	public static String DROP_NEWS = "DROP TABLE IF EXISTS "+News.TABLE;
}
