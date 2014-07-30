package anil.myntratinder;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.FileNotFoundException;

import anil.myntratinder.adapters.ProductListAdapter;
import anil.myntratinder.utils.Downloader;
import anil.myntratinder.utils.ProductsJSONPullParser;

public class ProductListViewActivity extends Activity {

    private ProductListAdapter productListAdapter;
    private ListView productListView;

    public String url = "http://www.myntra.com/searchws/search/styleids2";
    public String postData = "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Casual Shoes\\\") AND global_attr_master_category:(\\\"Footwear\\\" OR \\\"Free Items\\\"))\",\"start\":0,\"rows\":96,\"facet\":true,\"facetField\":[\"Casual_Shoe_Type_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Ankle_Height_article_attr\",\"Width_article_attr\"],\"fq\":[\"discounted_price:[499 TO 8199]\",\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store3_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true}]";
    public String fileName = "products.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_view);

        productListView = (ListView) findViewById(R.id.productList);

        /* if network is available, download the file from internet, other wise get products
         * from file system.
         */
        if (isNetworkAvailable()){
            ProductsDownloadTask downloadTask = new ProductsDownloadTask();
            downloadTask.execute();
        } else {
            productListAdapter = new ProductListAdapter(getApplicationContext(), -1,
                    ProductsJSONPullParser.getProductsFromFile(ProductListViewActivity.this, "products.json"));
            productListView.setAdapter(productListAdapter);
        }
    }

    private boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    private class ProductsDownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Downloader.downloadFromUrl(url, postData, openFileOutput(fileName, Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // super.onPostExecute(aVoid);
            productListAdapter = new ProductListAdapter(ProductListViewActivity.this, -1,
                    ProductsJSONPullParser.getProductsFromFile(ProductListViewActivity.this, "products.json"));
            productListView.setAdapter(productListAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_list_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
