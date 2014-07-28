package anil.myntratinder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import anil.myntratinder.adapters.ProductCardAdapter_;
import anil.myntratinder.adapters.ProductCardAdapter;
import anil.myntratinder.views.ProductStackView;
import anil.myntratinder.views.SingleProductView;

@EActivity(R.layout.activity_product_card_view)
public class ProductCardViewActivity extends Activity {

    @ViewById
    ProductStackView mProductStack;

    private Handler handler;

    @AfterViews
    public void initialize(){
        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final View splash = findViewById(R.id.splash);
                splash.animate().alpha(0.0f).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        splash.setVisibility(View.GONE);
                        doInitialize();
                    }
                }).setDuration(2000).start();
            }
        }, 0);

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
            }
        });

        return;
    }

    private void doInitialize() {
        ProductCardAdapter mAdapter = ProductCardAdapter_.getInstance_(this);
        // fixme: add proper url, postdata, filename for this to work
        mAdapter.init("url", "postdata", "filename");
        mProductStack.setAdapter(ProductCardAdapter_.getInstance_(this));
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
