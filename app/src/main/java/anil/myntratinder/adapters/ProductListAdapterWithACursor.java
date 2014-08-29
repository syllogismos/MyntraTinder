package anil.myntratinder.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import anil.myntratinder.R;
import anil.myntratinder.models.Product;
import anil.myntratinder.utils.DatabaseHelper;

/**
 * Created by Anil on 8/29/2014.
 */
public class ProductListAdapterWithACursor extends CursorAdapter {

    ImageLoader imageLoader;
    DisplayImageOptions options;

    public ProductListAdapterWithACursor(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater =(LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout row = (RelativeLayout)inflater.inflate(R.layout.row_product, null);
        return row;

    }

    @Override
    public void bindView(View row, Context context, Cursor cursor) {

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
        Product product = new Product(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        product.setProductGroup(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PRODUCT_GROUP)));
        product.setDiscountedPrice(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DISCOUNTED_PRICE)));
        product.setStyleName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_STYLE_NAME)));
        product.setDiscount(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DISCOUNT)));
        product.setPrice(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PRICE)));
        product.setStyleId(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_STYLE_ID)));
        product.setImageUrl(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_IMAGE_URL)));
        product.setDreLandingPageUrl(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_LANDING_PAGE_URL)));
        product.setLiked(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_LIKED)));

        imageLoader.displayImage(product.getImageUrl(),productImage, options, listener);
        productName.setText(product.getStyleName());
        productPrice.setText(product.getDiscountedPrice());

    }
}
