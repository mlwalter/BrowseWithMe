package mlwalter.com.browsewithme;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;

public class BrowseWithMeActivity extends AppCompatActivity {

	public static final Uri BOOKMARKS_URI = Uri.parse("content://browser/bookmarks");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		doStuff();
	}

	private void doStuff() {
		String[] proj = new String[]{BookmarkColumns.TITLE, BookmarkColumns.URL};
		String sel = BookmarkColumns.BOOKMARK + " = 0"; // 0 = history, 1 = bookmark
		Cursor cursor = getContentResolver().query(BOOKMARKS_URI, proj, sel, null, null);

		if (cursor.moveToFirst() && cursor.getCount() > 0) {
			String title = cursor.getString(cursor.getColumnIndex(BookmarkColumns.TITLE));
			String url = cursor.getString(cursor.getColumnIndex(BookmarkColumns.URL));
			startService(getIntent().<Intent>getParcelableExtra("SEND_MESSAGE").setAction(title + ": " + url));
			finish();
		}
	}

	public static class BookmarkColumns implements BaseColumns {
		public static final String URL = "url";
		public static final String VISITS = "visits";
		public static final String DATE = "date";
		public static final String BOOKMARK = "bookmark";
		public static final String TITLE = "title";
		public static final String CREATED = "created";
		public static final String FAVICON = "favicon";
		public static final String THUMBNAIL = "thumbnail";
		public static final String TOUCH_ICON = "touch_icon";
		public static final String USER_ENTERED = "user_entered";
	}

}
