package anil.myntratinder.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Anil on 8/7/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // version
    private static final int DATABASE_VERSION = 1;
    // database name
    private static final String DATABASE_NAME = "MyntraProducts.db";
    // table names
    private static final String TABLE_MEN_SHOES = "men_shoes";
    private static final String TABLE_WOMEN_SHOES = "women_shoes";

    // column names
    private static final String KEY_ID = "id";

    // men shoes column names
    private static final String KEY_STYLE_NAME = "style_name";
    private static final String KEY_DISCOUNTED_PRICE = "discounted_price";

    // table create statements
    private static final String CREATE_TABLE_MEN_SHOES = "CREATE TABLE "
            + TABLE_MEN_SHOES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_STYLE_NAME + " TEXT,"
            + KEY_DISCOUNTED_PRICE + " TEXT" + ")";

    private static final String CREATE_TABLE_WOMEN_SHOES = "CREATE TABLE "
            + TABLE_WOMEN_SHOES + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_STYLE_NAME + " TEXT,"
            + KEY_DISCOUNTED_PRICE + " TEXT" + ")";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // todo: create tables when database is first created
        // things to do generally when a database is created..
        sqLiteDatabase.execSQL(CREATE_TABLE_MEN_SHOES);
        sqLiteDatabase.execSQL(CREATE_TABLE_WOMEN_SHOES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        // todo: handle what to do when you upgrade the database
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MEN_SHOES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WOMEN_SHOES);

        onCreate(sqLiteDatabase);
    }
}
