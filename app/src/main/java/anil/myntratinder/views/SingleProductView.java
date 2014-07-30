package anil.myntratinder.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import anil.myntratinder.R;
import anil.myntratinder.models.Product;

/**
 * Created by Anil on 7/18/2014.
 */
@EViewGroup(R.layout.product_card)
public class SingleProductView extends RelativeLayout implements ProductStackView.ProductStackListener{

    @ViewById
    ImageView picture;

    @ViewById
    TextView yes;

    @ViewById
    TextView no;

    @ViewById
    TextView name;

    @ViewById
    TextView discountedPrice;

    @ViewById
    TextView actualPrice;

    private Product product;
    // todo: can we make Product public to access from outside while overriding onChoiceMade, onCancelled etc

    public SingleProductView(Context context) {
        super(context);
    }

    public void bind(Product mProduct){
        product = mProduct;
        return;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        // todo: you can download the picture here or in the getView function of the ProductCardAdapter, for now things work so, i'm happy
    }

    @Override
    public void onUpdateProgress(boolean positif, float percent, View view) {
        if (positif) {
            yes.setAlpha(percent);
        } else {
            no.setAlpha(percent);
        }
        name.setAlpha(0);
        actualPrice.setAlpha(0);
        discountedPrice.setAlpha(0);
    }

    @Override
    public void onCancelled(View beingDragged) {
        yes.setAlpha(0);
        no.setAlpha(0);
        name.setAlpha(1);
        actualPrice.setAlpha(1);
        discountedPrice.setAlpha(1);
    }

    @Override
    public void onChoiceMade(boolean choice, View beingDragged) {
        yes.setAlpha(0);
        no.setAlpha(0);
        name.setAlpha(1);
        actualPrice.setAlpha(1);
        discountedPrice.setAlpha(1);
        // fixme: here you have to do what happens after the choice is made,
        // todo: can we make Product public in the main class..?
    }
}
