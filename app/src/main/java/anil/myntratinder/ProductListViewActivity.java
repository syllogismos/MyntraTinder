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

public class ProductListViewActivity extends Activity {

    private ProductListAdapter productListAdapter;
    private ListView productListView;

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
                    ProductsJSONPullParser.getProductsFromFile(ProductListViewActivity.this));
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
                Downloader.downloadFromUrl("http://", "postdata", openFileOutput("products.json", Context.MODE_PRIVATE));
                // todo: url, postdata and filename
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // super.onPostExecute(aVoid);
            productListAdapter = new ProductListAdapter(ProductListViewActivity.this, -1,
                    ProductsJSONPullParser.getProductsFromFile(ProductListViewActivity.this));
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
