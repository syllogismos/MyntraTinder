package anil.myntratinder;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import anil.myntratinder.adapters.ProductCardAdapter_;
import anil.myntratinder.adapters.ProductCardAdapter;
import anil.myntratinder.views.ProductStackView;
import anil.myntratinder.views.SingleProductView;


public class MenShoesFragment extends Fragment {

    ProductStackView menShoesStackView;
    //private Handler handler;
    public MenShoesFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_men_shoes, container, false);
        menShoesStackView = (ProductStackView) view.findViewById(R.id.men_shoes_mProductStack);
        //handler = new Handler();
        doInitialize();

        menShoesStackView.setmProductStackListener(new ProductStackView.ProductStackListener() {
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

        return view;
    }

    private void doInitialize() {
        // todo: getInstance(context) did some type inference and came up with below statement, don't know if it works
        // todo: getActivity() or this.getActivity().getBaseContext()
        ProductCardAdapter mCardAdapter = ProductCardAdapter_.getInstance_(getActivity());
        mCardAdapter.init("url", "postData", "filename");
        menShoesStackView.setAdapter(mCardAdapter);
    }
}
