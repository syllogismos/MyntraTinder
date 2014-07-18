package anil.myntratinder;

import android.content.Context;

import java.util.List;

/**
 * Created by Anil on 7/18/2014.
 * http json response structure
 * {
 *     response1: {
 *         totalProductsCount: 3012,
 *         filters: {
 *
 *         },
 *         products: [
 *         {
 *             sizes: String,
 *             stylename: String,
 *             search_image: String,
 *             discounted_price: Int?,
 *             discount: Int,
 *             id: Int,
 *             product: String,
 *             imageEntry_default: String,
 *             price: Int,
 *             styleid: Int,
 *             etc..
 *         },
 *         {}
 *         ]
 *     }
 * }
 */
public class ProductsJSONPullParser {

    public static List<Product> getProductsFromFile(Context context){
        /* TODO: parse json file and extract products,
         * http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
         */

        return null;
    }
}
