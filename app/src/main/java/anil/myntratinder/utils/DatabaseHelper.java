package anil.myntratinder.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import anil.myntratinder.models.Product;

/**
 * Created by Anil on 8/7/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // version
    private static final int DATABASE_VERSION = 1;
    // database name
    private static final String DATABASE_NAME = "anil.MyntraProducts.db";
    // table name
    private static final String TABLE_NAME = "myntra_products";

    private static final String MEN_SHOES_LABEL = "men_shoes";
    private static final String WOMEN_SHOES_LABEL = "women_shoes";

    // column names
    private static final String KEY_ID = "id";
    private static final String KEY_PRODUCT_GROUP = "product_group";
    private static final String KEY_STYLE_NAME = "style_name";
    private static final String KEY_DISCOUNTED_PRICE = "discounted_price";
    private static final String KEY_DISCOUNT = "discount";
    private static final String KEY_PRICE = "price";
    private static final String KEY_STYLE_ID = "style_id";
    private static final String KEY_IMAGE_URL = "image_url";
    private static final String KEY_LANDING_PAGE_URL = "landing_page_url";
    private static final String KEY_LIKED = "is_liked";

    // fixme: can't figure out if liked column must be boolean or integer?

    private static final int VALUE_LIKED = 1;
    private static final int VALUE_DISLIKED = 2;
    private static final int VALUE_NONE = 0;

    // table create statements
    private static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_PRODUCT_GROUP + " TEXT,"
            + KEY_STYLE_NAME + " TEXT,"
            + KEY_DISCOUNTED_PRICE + " TEXT,"
            + KEY_DISCOUNT + " TEXT,"
            + KEY_PRICE + " TEXT,"
            + KEY_STYLE_ID + " TEXT,"
            + KEY_IMAGE_URL + " TEXT,"
            + KEY_LANDING_PAGE_URL + " TEXT,"
            + KEY_LIKED + " BOOLEAN" // fixme: is this integer or boolean?
            + ")";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // todo: create tables when database is first created
        // things to do generally when a database is created..
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // todo: handle what to do when you upgrade the database
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public long insertNewProduct(Product product, String table) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STYLE_NAME, product.getStyleName());
        values.put(KEY_DISCOUNTED_PRICE, product.getDiscountedPrice());
        values.put(KEY_DISCOUNT, product.getDiscount());
        values.put(KEY_PRICE, product.getPrice());
        values.put(KEY_STYLE_ID, product.getStyleId());
        values.put(KEY_IMAGE_URL, product.getImageUrl());
        values.put(KEY_LANDING_PAGE_URL, product.getDreLandingPageUrl());
        values.putNull(KEY_LIKED);

        long product_id = db.insert(table, null, values);
        return product_id;
    }

    public Product getProduct(String tableName, String columnName, String value){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + tableName + " WHERE "
                + columnName + " = " + value;
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null)
            c.moveToFirst();
        Product product = new Product(c.getInt(c.getColumnIndex(KEY_ID))); // fixme: this is wrong, confusion betweet KEY_ID, mId, unique style id from the website
        product.setDiscountedPrice(c.getString(c.getColumnIndex(KEY_DISCOUNTED_PRICE)));
        product.setStyleName(c.getString(c.getColumnIndex(KEY_STYLE_NAME)));
        product.setDiscount(c.getString(c.getColumnIndex(KEY_DISCOUNT)));
        product.setPrice(c.getString(c.getColumnIndex(KEY_PRICE)));
        product.setStyleId(c.getString(c.getColumnIndex(KEY_STYLE_ID)));
        product.setImageUrl(c.getString(c.getColumnIndex(KEY_IMAGE_URL)));
        product.setDreLandingPageUrl(c.getString(c.getColumnIndex(KEY_LANDING_PAGE_URL)));
        c.close();
        return product;
    }

    //todo: add an extra parameter to limit number of products
    //fixme: the columns are a mess, done in a hurry, fix this shit..
    public List<Product> getProducts(String tableName, String columnName, String value){
        List<Product> products = new ArrayList<Product>();
        String selectQuery = "SELECT * FROM " + tableName + " WHERE "
                + columnName + " = " + value;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Product product = new Product(c.getInt(c.getColumnIndex(KEY_ID)));
                product.setDiscountedPrice(c.getString(c.getColumnIndex(KEY_DISCOUNTED_PRICE)));
                product.setStyleName(c.getString(c.getColumnIndex(KEY_STYLE_NAME)));
                product.setDiscount(c.getString(c.getColumnIndex(KEY_DISCOUNT)));
                product.setPrice(c.getString(c.getColumnIndex(KEY_PRICE)));
                product.setStyleId(c.getString(c.getColumnIndex(KEY_STYLE_ID)));
                product.setImageUrl(c.getString(c.getColumnIndex(KEY_IMAGE_URL)));
                product.setDreLandingPageUrl(c.getString(c.getColumnIndex(KEY_LANDING_PAGE_URL)));
                products.add(product);
            } while (c.moveToNext());
        }
        return products;
    }
}
