package anil.myntratinder;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anil on 7/18/2014.
 */
public class ProductCardAdapter extends BaseAdapter {

    List<Product> mItems;
    Context mContext;

    public ProductCardAdapter(Context context) {
        // todo: here you need to populate mItems from the json file
        mContext = context;
        mItems = new ArrayList<Product>();
        for (int i = 1; i < 15; i++){
            mItems.add(new Product(i));
        }
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        //todo: update this function properly
        ImageView view = new ImageView(mContext);
        view.setImageResource(R.drawable.ic_launcher);
        Resources r = mContext.getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, r.getDisplayMetrics());

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(px, px);
        return view;
    }
}
