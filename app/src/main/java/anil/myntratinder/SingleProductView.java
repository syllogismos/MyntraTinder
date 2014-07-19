package anil.myntratinder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

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

    private Product product;

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
        // todo: you can download the picture here or in the getView function of the ProductCardAdapter, figure out whats best.
    }

    @Override
    public void onUpdateProgress(boolean positif, float percent, View view) {
        if (positif) {
            yes.setAlpha(percent);
        } else {
            no.setAlpha(percent);
        }
    }

    @Override
    public void onCancelled(View beingDragged) {
        yes.setAlpha(0);
        no.setAlpha(0);
    }

    @Override
    public void onChoiceMade(boolean choice, View beingDragged) {
        yes.setAlpha(0);
        no.setAlpha(0);
    }
}
