package anil.myntratinder;

import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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
            }
        });

        return view;
    }

    private void doInitialize() {
        //todo: getInstance(context) did some type inference and came up with below statement, don't know if it works
        menShoesStackView.setAdapter(ProductCardAdapter_.getInstance_(this.getActivity().getBaseContext()));
    }
}
