package second.abdo.com.herag.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import second.abdo.com.herag.R;

/**
 * Created by AhmedDawoud on 09/04/2016.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }



    private Integer[] mThumbIds = {
            R.drawable.ic_audi, R.drawable.ic_aston_martin,
            R.drawable.ic_bentli, R.drawable.ic_bikes,
            R.drawable.ic_bmw, R.drawable.ic_buick,
            R.drawable.ic_cadillac, R.drawable.ic_car_parts,
            R.drawable.ic_chery, R.drawable.ic_chevrolet,

    };
}
