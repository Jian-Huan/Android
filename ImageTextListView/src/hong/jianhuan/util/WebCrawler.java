package hong.jianhuan.util;

import hong.jianhuan.beans.RowItem;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class WebCrawler {
	
    public List<RowItem> crawlPage(String url) {
        ArrayList<RowItem> rowItems = new ArrayList<RowItem>();
        InputStream in = null;
        
        try {
        	Document doc = Jsoup.connect(url).get();

        	// Get all item rows
    		for (Element link : doc.select("p > a[href^=/]")) {
				Document itemDoc = Jsoup.connect("http://sfbay.craigslist.org" + link.attr("href")).get();
				Element image = itemDoc.select("div#thumbs a[href]").first();
				
				Bitmap bitmap = null;
				if (image != null) {
					BitmapFactory.Options options = new BitmapFactory.Options();
					options.inJustDecodeBounds = true;
					in = new URL(image.attr("href")).openStream();
					BitmapFactory.decodeStream(in, null, options);
					in.close();

					options.inSampleSize = calculateInSampleSize(options, 80, 80);
					options.inJustDecodeBounds = false;
					in = new URL(image.attr("href")).openStream();
					bitmap = BitmapFactory.decodeStream(in, null, options);
					in.close();
				}

				rowItems.add(new RowItem(bitmap, itemDoc.title()));
    		}
        } catch (IOException e) {
    		e.printStackTrace();
    	} finally {
    		if (in != null) {
    			try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	}
        
        return rowItems;
    } 
    
    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
    	final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
        	final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            
            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }    
    	
    	return inSampleSize;
    }
}    