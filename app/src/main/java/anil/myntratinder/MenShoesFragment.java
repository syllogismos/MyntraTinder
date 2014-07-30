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

    private static final String ARG_URL = "url";
    private static final String ARG_POSTDATA = "postData";
    private static final String ARG_FILENAME = "fileName";

    private String mUrl;
    private String mPostData;
    private String mFileName;

    ProductStackView menShoesStackView;
    //private Handler handler;
    public MenShoesFragment() {

    }

    public static MenShoesFragment newInstance(String url, String postData, String fileName){
        MenShoesFragment fragment = new MenShoesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        args.putString(ARG_POSTDATA, postData);
        args.putString(ARG_FILENAME, fileName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mUrl = getArguments().getString(ARG_URL);
            mPostData = getArguments().getString(ARG_POSTDATA);
            mFileName = getArguments().getString(ARG_FILENAME);
        }
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
        ProductCardAdapter mCardAdapter = ProductCardAdapter_.getInstance_(getActivity());
        mCardAdapter.init(mUrl, mPostData, mFileName);
        menShoesStackView.setAdapter(mCardAdapter);
    }
}
