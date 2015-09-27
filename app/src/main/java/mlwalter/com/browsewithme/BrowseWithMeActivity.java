package mlwalter.com.browsewithme;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.widget.TextView;

import java.util.ArrayList;

public class BrowseWithMeActivity extends AppCompatActivity {

	public static final Uri BOOKMARKS_URI = Uri.parse("content://browser/bookmarks");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_browse_with_me);
		history();
	}

	private void history() {
		String[] proj = new String[]{BookmarkColumns.TITLE, BookmarkColumns.URL};
		String sel = BookmarkColumns.BOOKMARK + " = 0"; // 0 = history, 1 = bookmark
		Cursor cursor = getContentResolver().query(BOOKMARKS_URI, proj, sel, null, null);
		if (cursor.moveToFirst() && cursor.getCount() > 0) {
			int count = 0;
			Pair[] pairs = new Pair[3];
			while (cursor.isAfterLast() == false && count < 3) {
				String title = cursor.getString(cursor.getColumnIndex(BookmarkColumns.TITLE));
				String url = cursor.getString(cursor.getColumnIndex(BookmarkColumns.URL));
				Pair<String, String> p = new Pair<String, String>(title, url);
				pairs[count] = p;
				cursor.moveToNext();
				count++;
			}
			TextView tv = (TextView) findViewById(R.id.textView8);
			tv.setText(pairs[0].first + ": " + pairs[0].second);
			tv = (TextView) findViewById(R.id.textView9);
			tv.setText(pairs[1].first + ": " + pairs[1].second);
			tv = (TextView) findViewById(R.id.textView10);
			tv.setText(pairs[2].first + ": " + pairs[2].second);
//			startService(getIntent().<Intent>getParcelableExtra("SEND_MESSAGE").setAction(title + ": " + url));
//			finish();
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
