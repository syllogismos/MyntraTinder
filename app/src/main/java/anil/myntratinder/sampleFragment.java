package anil.myntratinder;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import anil.myntratinder.adapters.ProductCardAdapter;
import anil.myntratinder.adapters.ProductCardAdapter_;
import anil.myntratinder.views.ProductStackView;
import anil.myntratinder.views.SingleProductView;


public class sampleFragment extends Fragment {

    ProductStackView mFragmentProductStack;

    public String url = "http://www.myntra.com/searchws/search/styleids2";
    public String postData = "[{\"query\":\"(global_attr_age_group:(\\\"Adults-Men\\\" OR \\\"Adults-Unisex\\\") AND global_attr_article_type_facet:(\\\"Casual Shoes\\\") AND global_attr_master_category:(\\\"Footwear\\\" OR \\\"Free Items\\\"))\",\"start\":0,\"rows\":96,\"facet\":true,\"facetField\":[\"Casual_Shoe_Type_article_attr\",\"Upper_Material_article_attr\",\"Fastening_article_attr\",\"Ankle_Height_article_attr\",\"Width_article_attr\"],\"fq\":[\"discounted_price:[499 TO 8199]\",\"count_options_availbale:[1 TO *]\"],\"sort\":[{\"sort_field\":\"count_options_availbale\",\"order_by\":\"desc\"},{\"sort_field\":\"style_store3_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"potential_revenue_male_sort_field\",\"order_by\":\"desc\"},{\"sort_field\":\"global_attr_catalog_add_date\",\"order_by\":\"desc\"}],\"return_docs\":true,\"colour_grouping\":true}]";
    public String fileName = "productsfragment.json";

    public sampleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sample, container, false);
        mFragmentProductStack = (ProductStackView) view.findViewById(R.id.sampleFragmentProductStack);
        doInitialize();
        mFragmentProductStack.setmProductStackListener(new ProductStackView.ProductStackListener() {
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
        ProductCardAdapter fragmentCradAdapter = ProductCardAdapter_.getInstance_(getActivity());
        fragmentCradAdapter.init(url, postData, fileName);
        mFragmentProductStack.setAdapter(fragmentCradAdapter);
    }

}
