package hong.jianhuan.util;

import hong.jianhuan.adapters.CustomListViewAdapter;
import hong.jianhuan.beans.RowItem;
import hong.jianhuan.imagetextlistview.R;

import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ListView;

public class WebCrawlerTask extends AsyncTask<String, Integer, List<RowItem>>  
{	
	private WebCrawler webCrawler;
	private Activity activity;
	
	public WebCrawlerTask(Activity listViewActivity) {
		this.webCrawler = new WebCrawler();
		this.activity = listViewActivity;
	}
	
	@Override  
    protected List<RowItem> doInBackground(String... urls) 
	{
		return webCrawler.crawlPage(urls[0]);
	}
	
	@Override  
    protected void onPostExecute(List<RowItem> rowItems)  
    {
		// Show result on activity.
		ListView listView = (ListView)activity.findViewById(R.id.list);
		CustomListViewAdapter adapter = 
				new CustomListViewAdapter(activity, R.layout.list_item, rowItems);
        listView.setAdapter(adapter);
        
        // Make waiting wheel disappear 
        activity.setProgressBarIndeterminateVisibility(false);
    }
}
