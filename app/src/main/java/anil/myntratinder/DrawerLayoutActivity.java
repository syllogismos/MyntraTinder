package anil.myntratinder;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import anil.myntratinder.adapters.NavDrawerListAdapter;
import anil.myntratinder.models.NavDrawerItem;

public class DrawerLayoutActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        mTitle = mDrawerTitle = getTitle();

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0,-1)));
        // Male
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1,-1)));
        // Female
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2,-1)));
        // Kids with counter for shits and giggles
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3,-1), "22", true));
        // Settings
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));

        navMenuIcons.recycle();

        adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);

        mDrawerList.setAdapter(adapter);

        //todo: fix action bar, we need it..
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name){

            /**
             * {@link android.support.v4.widget.DrawerLayout.DrawerListener} callback method. If you do not use your
             * ActionBarDrawerToggle instance directly as your DrawerLayout's listener, you should call
             * through to this method from your own listener object.
             *
             * @param drawerView Drawer view that is now open
             */
            @Override
            public void onDrawerOpened(View drawerView) {
                //getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }

            /**
             * {@link android.support.v4.widget.DrawerLayout.DrawerListener} callback method. If you do not use your
             * ActionBarDrawerToggle instance directly as your DrawerLayout's listener, you should call
             * through to this method from your own listener object.
             *
             * @param drawerView Drawer view that is now closed
             */
            @Override
            public void onDrawerClosed(View drawerView) {
                //getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if (savedInstanceState == null){
            displayView(0);
        }

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

    }

    private class SlideMenuClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            displayView(i);
        }
    }

    private void displayView(int position) {
        // todo: replace each fragment with the respective one
        // todo: and figure out how to initialize different fragments with the same fragment class.
        // say menshoes = Fragment.init(url, data, filename); and etc
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new sampleFragment();
                break;
            case 2:
                fragment = new HomeFragment();
                break;
            case 3:
                fragment = new HomeFragment();
                break;
            case 4:
                fragment = new HomeFragment();
                break;
            default:
                break;
        }

        if (fragment != null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            Log.e("drawer layout activity", "error in creating fragment");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
