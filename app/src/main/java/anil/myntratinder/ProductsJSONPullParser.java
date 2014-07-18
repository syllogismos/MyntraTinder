package anil.myntratinder;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        List<Product> products = new ArrayList<Product>();
        String json = null;
        Product product = null;
        try {
            FileInputStream fis = context.openFileInput("products.json");
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            json = new String(buffer, "UTF-8");
            if (json != null){
                JSONObject jsonObject = new JSONObject(json);
                JSONObject response1 = jsonObject.getJSONObject("response1");
                JSONArray productObjects = response1.getJSONArray("products");
                for (int i = 0; i < productObjects.length(); i++) {
                    JSONObject p = productObjects.getJSONObject(i);
                    product = new Product(i);
                    product.setImageUrl(p.getString("search_image"));
                    product.setPrice(p.getString("discounted_price"));
                    product.setName(p.getString("product"));
                    products.add(product);
                    /*
                     * todo: get image url from the json object imageEntry_default, rather than search_image
                     * todo: add more getter and setters to the Product class to extract even more data from json object
                     */
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return products;
    }
}
