package anil.myntratinder.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import anil.myntratinder.R;
import anil.myntratinder.models.Product;

/**
 * Created by Anil on 7/18/2014.
 */
public class ProductListAdapter extends ArrayAdapter<Product> {

    ImageLoader imageLoader;
    DisplayImageOptions options;

    public ProductListAdapter(Context context, int resource, List<Product> products) {
        super(context, resource, products);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // return super.getView(position, convertView, parent);

        RelativeLayout row = (RelativeLayout)convertView;
        if (row == null) {
            LayoutInflater inflater =(LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = (RelativeLayout)inflater.inflate(R.layout.row_product, null);
        }

        final ImageView productImage = (ImageView)row.findViewById(R.id.productImage);
        TextView productName = (TextView)row.findViewById(R.id.productName);
        TextView productPrice = (TextView)row.findViewById(R.id.productPrice);
        final ProgressBar progressBar = (ProgressBar)row.findViewById(R.id.listRowProgress);

        progressBar.setVisibility(View.VISIBLE);
        productImage.setVisibility(View.INVISIBLE);

        ImageLoadingListener listener = new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                progressBar.setVisibility(View.INVISIBLE);
                productImage.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        };

        imageLoader.displayImage(getItem(position).getImageUrl(),productImage, options, listener);
        productName.setText(getItem(position).getStyleName());
        productPrice.setText(getItem(position).getPrice());

        return row;
    }
}
