package hong.jianhuan.beans;

import android.graphics.Bitmap;

public class RowItem {
	private Bitmap bitmap;
    private String title;
    
    public RowItem(Bitmap bitmap, String title) {
    	this.bitmap = bitmap;
    	this.title = title;
    }
    
    public Bitmap getImage() {
        return bitmap;
    }
    
    public void setImage(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
