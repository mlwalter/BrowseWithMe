package mlwalter.com.browsewithme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BrowseWithMeActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		startService(getIntent().<Intent>getParcelableExtra("SEND_MESSAGE").setAction("Hello"));
		finish();
	}


}
