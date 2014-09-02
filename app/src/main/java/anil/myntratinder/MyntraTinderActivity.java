package anil.myntratinder;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import anil.myntratinder.adapters.ProductListAdapterWithACursor;
import anil.myntratinder.models.MyntraCategory;
import anil.myntratinder.utils.DatabaseHelper;

public class MyntraTinderActivity extends Activity
        implements NavigationDrawerFragmentSingleElv.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragmentSingleElv mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myntra_tinder);

        mNavigationDrawerFragment = (NavigationDrawerFragmentSingleElv)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1, "men-footwear-casual-shoes"))
                //.addToBackStack(null)
                .commit();
    }

    @Override
    public void onNavigationDrawerProductGroupSelected(MyntraCategory.ProductGroup productGroup) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = TinderUIFragment.newInstance(
                productGroup.getGroupLabel(),
                productGroup.getUniqueGroupLabel(),
                productGroup.getFileName(),
                productGroup.getStartFromKey(),
                productGroup.getMaxProductsKey(),
                productGroup.getPostDataHead(),
                productGroup.getPostDataTail()
        );
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                //.addToBackStack(null)
                .commit();
    }

    @Override
    public void onMenuItemToGetLikedPictures(MyntraCategory.ProductGroup productGroup) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(1, productGroup.getUniqueGroupLabel()))
                //.addToBackStack(null)
                .commit();
        //todo: change mTitle to show the group label also
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.myntra_tinder, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onTinderFragmentAttached(String groupLabel) {
        mTitle = groupLabel;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        private static final String ARG_PRODUCT_GROUP = "product_group";

        private String productGroup;
        private String sectionNumber;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, String productGroup) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putString(ARG_PRODUCT_GROUP, productGroup);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_product_list_view, container, false);
            ListView listView = (ListView) rootView.findViewById(R.id.productList);
            DatabaseHelper db = new DatabaseHelper(getActivity().getApplicationContext());
            Cursor cursor = db.getLikedProductsFromGroup(productGroup);
            ListAdapter adapter = new ProductListAdapterWithACursor(getActivity(), cursor, false);
            listView.setAdapter(adapter);
            return rootView;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null){
                productGroup = getArguments().getString(ARG_PRODUCT_GROUP);
                sectionNumber = getArguments().getString(ARG_SECTION_NUMBER);
            }
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MyntraTinderActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
