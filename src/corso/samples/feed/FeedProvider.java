package corso.samples.feed;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class FeedProvider extends ContentProvider{

	private final static UriMatcher MATCHER;
	private final static int NEWS_CODE = 1;
	private final static int SPECIFIC_NEWS_CODE = 2;
	
	static{
		MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		MATCHER.addURI(Constants.AUTHORITY, Constants.Contract.News.TABLE, NEWS_CODE);
		MATCHER.addURI(Constants.AUTHORITY, Constants.Contract.News.TABLE+"/#", SPECIFIC_NEWS_CODE);
	}
	
	private FeedOpenHelper mDbHelper;
	
	@Override
	public boolean onCreate() {
		mDbHelper = new FeedOpenHelper(getContext());
		return true;
	}

	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
		final int match = MATCHER.match(uri);
		SQLiteQueryBuilder query = new SQLiteQueryBuilder();
		switch (match) {
		case NEWS_CODE:
			query.setTables(Constants.Contract.News.TABLE);
			break;
		case SPECIFIC_NEWS_CODE:
			query.setTables(Constants.Contract.News.TABLE);
			query.appendWhere("_id = "+uri.getLastPathSegment());
			break;
		default:
			throw new UnsupportedOperationException("No matching uri");
		}
		SQLiteDatabase db = mDbHelper.getReadableDatabase();
		Cursor cursor = query.query(db, projection, selection, selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		final int match = MATCHER.match(uri);
		String table;
		switch (match) {
		case NEWS_CODE:
			table = Constants.Contract.News.TABLE;
			break;
		default:
			throw new UnsupportedOperationException("No uri matched");
		}
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		long insertedId = db.insert(table, null, values);
		if(insertedId != -1){
			Uri insertedUri = ContentUris.withAppendedId(uri, insertedId);
			getContext().getContentResolver().notifyChange(insertedUri, null);
			return insertedUri;
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// trovo tabella
		// apro db
		// elimino elementi
		// corrispondono al parametro uri
		// getContext().getContentResolver().notifyChange(insertedUri, null);
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
