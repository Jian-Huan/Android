package hong.jianhuan.activities;

import java.util.List;

import hong.jianhuan.beans.RowItem;
import hong.jianhuan.imagetextlistview.R;
import hong.jianhuan.util.WebCrawlerTask;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;
import android.widget.ListView;

public class ListViewActivity extends Activity {
	ListView listView;
	List<RowItem> rowItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_main);
		setProgressBarIndeterminateVisibility(true);
		
		WebCrawlerTask crawler = new WebCrawlerTask(this);
		crawler.execute("http://sfbay.craigslist.org/ata");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
