package anil.myntratinder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import anil.myntratinder.adapters.ProductCardAdapter;
import anil.myntratinder.adapters.ProductCardAdapter_;
import anil.myntratinder.models.Product;
import anil.myntratinder.utils.DatabaseHelper;
import anil.myntratinder.views.ProductStackView;
import anil.myntratinder.views.SingleProductView;


public class TinderUIFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_GROUP_LABEL = "groupLabel";
    private static final String ARG_GROUP_FILE_NAME = "fileName";
    private static final String ARG_GROUP_START_FROM_KEY = "startFromKey";
    private static final String ARG_GROUP_MAX_PRODUCTS_KEY = "maxProductsKey";
    private static final String ARG_GROUP_POST_DATA_HEAD = "postDataHead";
    private static final String ARG_GROUP_POST_DATA_TAIL = "postDataTail";

    // TODO: Rename and change types of parameters
    private String mGroupLabel;
    private String mFileName;
    private String mStartFromKey;
    private String mMaxProductsKey;
    private String mPostDataHead;
    private String mPostDataTail;

    ProductStackView mProductStackView;
    DatabaseHelper db;
    int startFrom;
    String maxProducts;
    SharedPreferences sharedPreferences;

    public String url = "http://www.myntra.com/searchws/search/styleids2";

    public static TinderUIFragment newInstance(String groupLabel, String fileName, String startFromKey, String maxProductsKey, String postDataHead, String postDataTail) {
        TinderUIFragment fragment = new TinderUIFragment();
        Bundle args = new Bundle();
        args.putString(ARG_GROUP_LABEL, groupLabel);
        args.putString(ARG_GROUP_FILE_NAME, fileName);
        args.putString(ARG_GROUP_START_FROM_KEY, startFromKey);
        args.putString(ARG_GROUP_MAX_PRODUCTS_KEY, maxProductsKey);
        args.putString(ARG_GROUP_POST_DATA_HEAD, postDataHead);
        args.putString(ARG_GROUP_POST_DATA_TAIL, postDataTail);
        fragment.setArguments(args);
        return fragment;
    }
    public TinderUIFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGroupLabel = getArguments().getString(ARG_GROUP_LABEL);
            mFileName = getArguments().getString(ARG_GROUP_FILE_NAME);
            mStartFromKey = getArguments().getString(ARG_GROUP_START_FROM_KEY);
            mMaxProductsKey = getArguments().getString(ARG_GROUP_MAX_PRODUCTS_KEY);
            mPostDataHead = getArguments().getString(ARG_GROUP_POST_DATA_HEAD);
            mPostDataTail = getArguments().getString(ARG_GROUP_POST_DATA_TAIL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tinder_ui, container, false);
        mProductStackView = (ProductStackView) view.findViewById(R.id.tinder_mProductStack);
        db = new DatabaseHelper(getActivity().getApplicationContext());
        // List<Product> products = db.getUnseenProductsFromGroup(getString(R.string.men_shoes_group_label), 5);
        // Log.e("from tinder fragment, check if its the same database", products.toString());
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        resetStoredValues();
        startFrom = sharedPreferences.getInt(mStartFromKey, 0);
        maxProducts = sharedPreferences.getString(mMaxProductsKey, "1000");
        doInitialize();

        mProductStackView.setmProductStackListener(new ProductStackView.ProductStackListener() {
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
                //todo: handle what to do after the choice is made.
                if (choice) {
                    db.updateLikeStatus(item.product, db.VALUE_LIKED);
                } else {
                    db.updateLikeStatus(item.product, db.VALUE_DISLIKED);
                }
                Log.d("tinder fragment", "updated the choice made " + String.valueOf(choice) + " " + item.product.getStyleName());
            }
        });

        return view;
    }

    private void resetStoredValues() {
        db.deleteTable(db.TABLE_NAME);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(mStartFromKey, 0);
        editor.putString(mMaxProductsKey, "1000");
        editor.commit();
    }
    //fixme: figure out how to eliminate the ui lag when the database gets new products from the internet..
    // already tried initializing the product stack inside the overridden method onViewCreated, and it didn't improve anything..

    private void doInitialize() {
        Log.e("Tinder Fragment: start from", String.valueOf(startFrom));
        Log.e("Tinder Fragment: max products", maxProducts);
        ProductCardAdapter mAdapter = ProductCardAdapter_.getInstance_(getActivity());
        String postData = getUpdatedPostData(startFrom);
        mAdapter.initForTinderFragment(url,postData,mFileName,mGroupLabel, db, sharedPreferences, mMaxProductsKey, mStartFromKey);
        if (!mAdapter.isEmpty()){
            mProductStackView.setAdapter(mAdapter);
        }
    }

    private String getUpdatedPostData(int startFrom) {
        if (startFrom > Integer.parseInt(maxProducts)){
            startFrom = 0;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(getString(R.string.men_shoes_start_from_key), 0);
            editor.commit();
        }
        return mPostDataHead + String.valueOf(startFrom) + mPostDataTail;
    }

}
