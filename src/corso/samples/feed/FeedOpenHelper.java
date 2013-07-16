package corso.samples.feed;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class FeedOpenHelper extends SQLiteOpenHelper{

	public FeedOpenHelper(Context context) {
		super(context, Constants.DB, null, Constants.DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(Constants.CREATE_NEWS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		if(newVersion>oldVersion){
			db.execSQL(Constants.DROP_NEWS);
			onCreate(db);
		}
	}

}
