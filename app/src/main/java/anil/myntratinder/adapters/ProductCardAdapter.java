package anil.myntratinder.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import anil.myntratinder.R;
import anil.myntratinder.utils.DatabaseHelper;
import anil.myntratinder.views.SingleProductView;
import anil.myntratinder.views.SingleProductView_;
import anil.myntratinder.models.Product;
import anil.myntratinder.utils.Downloader;
import anil.myntratinder.utils.ProductsJSONPullParser;

/**
 * Created by Anil on 7/18/2014.
 */
@EBean
public class ProductCardAdapter extends BaseAdapter {

    List<Product> mItems;
    //fixme: should I add @RootContext annotation for mContext below?
    Context mContext;
    ImageLoader imageLoader;
    DisplayImageOptions options;

    public ProductCardAdapter(Context context) {
        // fixme: should we download the json file here?
        mContext = context;


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext).build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

    }

    public void init(String url, String postData, String fileName) {
        if (isNetworkAvailable()){
            downloadJsonToFile(url, postData, fileName);
            mItems = ProductsJSONPullParser.getProductsFromFile(mContext, "products.json");
        } else {
            // fixme: figure out what happens when network is not available
            // fixme: here we are loading the same random product 15 times,
            mItems = new ArrayList<Product>();
            for (int i = 1; i < 15; i++){
                Product p = new Product(i);
                p.setImageUrl("sampleImageurl"); //fixme: add sample image url here
                mItems.add(p);
            }
        }
    }

    public void initFromDatabase(String url, String postData, DatabaseHelper db, String fileName){
        // todo: fill the adapter from the database instead of file system..
        // fixme: here we are downloading data to filesystem and then updating the database.. can we update the database from the network?
        List<Product> productsFromDb = db.getUnseenProductsFromGroup(db.MEN_SHOES_GROUP_LABEL, 20); // fixme: add one more where clause so that we only poll the products that are null liked
        if (productsFromDb.isEmpty()){
            if (isNetworkAvailable()){
                downloadJsonToFile(url, postData, fileName);
                List<Product> productsFromFile = ProductsJSONPullParser.getProductsFromFileAndInsertGroupLabel(mContext, "products.json", db.MEN_SHOES_GROUP_LABEL);
                updateDatabaseAndSetAdapter(db, productsFromFile);
            } else {
                // notify network is not available.
                Log.d("product card adapter", "network is not available");
            }
        } else {
            mItems = productsFromDb;
        }
    }


    public void initFromDatabaseUsingSharedPref(String url, String updatedPostData, DatabaseHelper db, String fileName, SharedPreferences sharedPreferences) {
        List<Product> productsFromDb = db.getUnseenProductsFromGroup(db.MEN_SHOES_GROUP_LABEL, 20);
        if (productsFromDb.isEmpty()){
            if (isNetworkAvailable()){
                // fixme: downloadJsonToFileAndUpdateDb is a background task that downloads new products and updates the db, perhaps it should also query products from the db and return
                downloadJsonToFileAndUpdateDb(url, updatedPostData, fileName, db, sharedPreferences);
                SystemClock.sleep(2000);
                mItems = db.getUnseenProductsFromGroup(db.MEN_SHOES_GROUP_LABEL, 20);
            } else {
                Log.d("productCardAdapter", "network is not available");
                mItems = new ArrayList<Product>();
            }
        } else {
            mItems = productsFromDb;
        }
    }

    public void initForTinderFragment(String url, String postData, String fileName, String groupLabel, DatabaseHelper db, SharedPreferences sharedPreferences, String maxProductsKey, String startFromKey){
        List<Product> productsFromDb = db.getUnseenProductsFromGroup(groupLabel, 20);
        if (productsFromDb.isEmpty()){
            if (isNetworkAvailable()) {
                downloadJsonToFileAndUpdateDbWithGivenKeys(url, postData, fileName, groupLabel, db, sharedPreferences, maxProductsKey, startFromKey);
                SystemClock.sleep(2000);
                mItems = db.getUnseenProductsFromGroup(groupLabel, 20);
            } else {
                Log.d("product card adapter for tinder fragment", "network is not available");
                mItems = new ArrayList<Product>();
            }
        } else {
            mItems = productsFromDb;
        }
    }

    @Background
    public void downloadJsonToFileAndUpdateDbWithGivenKeys(String url, String postData, String fileName, String groupLabel, DatabaseHelper db, SharedPreferences sharedPreferences, String maxProductsKey, String startFromKey) {
        try {
            Downloader.downloadFromUrl(url, postData, mContext.openFileOutput(fileName, Context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Product> productsFromFile = ProductsJSONPullParser.getProdutsFromFileAndUpdateMaxProducts(mContext, fileName, groupLabel, maxProductsKey, sharedPreferences);
        db.insertOrIgnoreProducts(productsFromFile, db.TABLE_NAME);
        int startFrom = sharedPreferences.getInt(startFromKey, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(startFromKey, startFrom + 96);
        editor.commit();
    }


    @Background
    public void downloadJsonToFileAndUpdateDb(String url, String updatedPostData, String fileName, DatabaseHelper db, SharedPreferences sharedPreferences) {
        try {
            Downloader.downloadFromUrl(url, updatedPostData, mContext.openFileOutput(fileName, Context.MODE_PRIVATE));
            // List<Product> productsFromFile = ProductsJSONPullParser.getProductsFromFileAndInsertGroupLabel(mContext, fileName, db.MEN_SHOES_GROUP_LABEL);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<Product> productsFromFile = ProductsJSONPullParser.getProductsFromFileAndInsertGroupLabel(mContext, fileName, db.MEN_SHOES_GROUP_LABEL);
        db.insertOrIgnoreProducts(productsFromFile, db.TABLE_NAME);
        int startFrom = sharedPreferences.getInt(mContext.getString(R.string.men_shoes_start_from_key), 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(mContext.getString(R.string.men_shoes_start_from_key), startFrom + 96);
        editor.commit();
        // SystemClock.sleep(1500);
        // mItems = db.getUnseenProductsFromGroup(db.MEN_SHOES_GROUP_LABEL, 20);
        //return db.getUnseenProductsFromGroup(db.MEN_SHOES_GROUP_LABEL, 20);
    }


    public void updateDatabaseAndSetAdapter(DatabaseHelper db, List<Product> products) {
        db.insertOrIgnoreProducts(products, db.TABLE_NAME);
        mItems = db.getUnseenProductsFromGroup(db.MEN_SHOES_GROUP_LABEL, 20);
    }


    @Background
    public void downloadJsonToFile(String url, String postdata, String filename) {
        try {
            Downloader.downloadFromUrl(url, postdata, mContext.openFileOutput(filename, Context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Product getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        SingleProductView singleProductView;
        if (convertView == null) {
            singleProductView = SingleProductView_.build(mContext);
        } else {
            singleProductView = (SingleProductView) convertView;
        }
        Product product = getItem(position);
        singleProductView.bind(product);

        ImageView productImage = (ImageView)singleProductView.findViewById(R.id.picture);
        // fixme: maybe we need a progressbar when the image is loading?
        TextView styleName = (TextView)singleProductView.findViewById(R.id.styleName);
        TextView discountedPrice = (TextView)singleProductView.findViewById(R.id.discountedPrice);
        TextView actualPrice = (TextView)singleProductView.findViewById(R.id.actualPrice);

        ImageLoadingListener listener = new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        };
        imageLoader.displayImage(product.getImageUrl(), productImage, options, listener);
        styleName.setText(product.getStyleName());
        //todo: update Product class to add additional properties like discounted price, discount, actual price
        //fixme: here we used product.getPrice() for everything, fix this.
        discountedPrice.setText(product.getDiscountedPrice());
        actualPrice.setText(product.getPrice());
        actualPrice.setPaintFlags(actualPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // http://stackoverflow.com/questions/8033316/to-draw-an-underline-below-the-textview-in-android

        return singleProductView;
    }
}
