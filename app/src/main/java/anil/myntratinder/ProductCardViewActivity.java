package anil.myntratinder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import anil.myntratinder.adapters.ProductCardAdapter_;
import anil.myntratinder.adapters.ProductCardAdapter;
import anil.myntratinder.utils.DatabaseHelper;
import anil.myntratinder.views.ProductStackView;
import anil.myntratinder.views.SingleProductView;

@EActivity(R.layout.activity_product_card_view)
public class ProductCardViewActivity extends Activity {

    @ViewById
    ProductStackView mProductStack;

    private Handler handler;

    public String url = "http://www.myntra.com/searchws/search/styleids2";
    public String postData = "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Casual Shoes\\\") AND global_attr_master_category:(\\\"Footwear\\\" OR \\\"Free Items\\\"))\",\"start\":0,\"rows\":96,\"facet\":true,\"facetField\":[\"Casual_Shoe_Type_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Ankle_Height_article_attr\",\"Width_article_attr\"],\"fq\":[\"discounted_price:[499 TO 8199]\",\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store3_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true}]";
    public String postDataHead = "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Casual Shoes\\\") AND global_attr_master_category:(\\\"Footwear\\\" OR \\\"Free Items\\\"))\",\"start\":";
    public String postDataTail = ",\"rows\":96,\"facet\":true,\"facetField\":[\"Casual_Shoe_Type_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Ankle_Height_article_attr\",\"Width_article_attr\"],\"fq\":[\"discounted_price:[499 TO 8199]\",\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store3_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true}]";
    public String fileName = "productscard.json";

    DatabaseHelper db;

    @AfterViews
    public void initialize(){
//        handler = new Handler();
//
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                final View splash = findViewById(R.id.splash);
//                splash.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        splash.setVisibility(View.GONE);
//                        doInitialize();
//                    }
//                }).setDuration(2000).start();
//            }
//        }, 0);
        final View splash = findViewById(R.id.splash);
        final TextView logText = (TextView) findViewById(R.id.logText);
        splash.setVisibility(View.GONE);
        db = new DatabaseHelper(getApplicationContext());
        //db.deleteTable(db.TABLE_NAME);
        doInitialize();

        mProductStack.setmProductStackListener(new ProductStackView.ProductStackListener() {
            @Override
            public void onUpdateProgress(boolean positif, float percent, View view) {
                SingleProductView item = (SingleProductView)view;
                item.onUpdateProgress(positif, percent, view);
            }

            @Override
            public void onCancelled(View beingDragged) {
                SingleProductView item = (SingleProductView)beingDragged;
                item.onCancelled(beingDragged);
            }

            @Override
            public void onChoiceMade(boolean choice, View beingDragged) {
                SingleProductView item = (SingleProductView)beingDragged;
                item.onChoiceMade(choice, beingDragged);
                //todo: here is where you have to handle what happens after you select yes or no to a particular card
                if (choice) {
                    db.updateLikeStatus(item.product, db.VALUE_LIKED);
                } else {
                    db.updateLikeStatus(item.product, db.VALUE_DISLIKED);
                }
                Log.d("product card view activity", "updated the choice made " + String.valueOf(choice) + " " + item.product.getStyleName());
                logText.setText(logText.getText() + String.valueOf(item.product));
            }
        });

        return;
    }

    private void doInitialize() {
        ProductCardAdapter mAdapter = ProductCardAdapter_.getInstance_(this);
        SharedPreferences sharedPreferences = getSharedPreferences("anil.myntratinder.sharedPrefFile", MODE_PRIVATE);
        int startFrom = sharedPreferences.getInt("start_from_shared_preference_key", 0);
        String maxProducts = sharedPreferences.getString("max_products_shared_preference_key", "3000");
        String updatedPostData = getUpdatedPostData(startFrom, maxProducts);
        mAdapter.initFromDatabase(url, updatedPostData, db, fileName, 0);
        boolean isEmpty = mAdapter.isEmpty();
        if (!isEmpty){
            mProductStack.setAdapter(mAdapter);
        }
    }

    public String getUpdatedPostData(int startFrom, String maxProducts) {
        if (startFrom > Integer.parseInt(maxProducts)) {
            startFrom = 0;
        }
        return postDataHead + String.valueOf(startFrom) + postDataTail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_card_view, menu);
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
