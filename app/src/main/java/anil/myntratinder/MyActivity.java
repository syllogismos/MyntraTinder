package anil.myntratinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anil.myntratinder.adapters.MyntraCategoryExpandableListAdapter;
import anil.myntratinder.models.MyntraCategory;


public class MyActivity extends Activity {

    private static final String PARENT_KEY = "pKey";
    private static final String CHILD_KEY = "cKey";
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Button listButton = (Button)findViewById(R.id.list_activity_btn);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listActivityIntent = new Intent(getBaseContext(),ProductListViewActivity.class);
                startActivity(listActivityIntent);
            }
        });

        Button cardButton = (Button)findViewById(R.id.card_activity_btn);
        cardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cardActivityIntent = new Intent(getBaseContext(), ProductCardViewActivity_.class);
                startActivity(cardActivityIntent);
            }
        });

        Button drawerButton = (Button)findViewById(R.id.drawer_layout_btn);
        drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent drawerActivityIntent = new Intent(getBaseContext(), DrawerLayoutActivity.class);
                startActivity(drawerActivityIntent);
            }
        });

        Button myntraTinderButton = (Button)findViewById(R.id.tinder_button);
        myntraTinderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tinderActivityIntent;
                tinderActivityIntent = new Intent(getBaseContext(), MyntraTinderActivity.class);
                startActivity(tinderActivityIntent);
            }
        });

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

//        List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
//        List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
//
//        Map<String, String> curGroupMap = new HashMap<String, String>();
//        groupData.add(curGroupMap);
//        curGroupMap.put(PARENT_KEY, "Hello");
//        curGroupMap.put(CHILD_KEY, "First Order System Response");
//
//        List<Map<String, String>> children = new ArrayList<Map<String, String>>();
//
//        Map<String, String> curChildMap = new HashMap<String, String>();
//        children.add(curChildMap);
//        curChildMap.put(PARENT_KEY, "World");
//        curChildMap.put(CHILD_KEY, "Second Order System");
//
//        childData.add(children);

        // Set up our adapter
//        expandableListAdapter = new SimpleExpandableListAdapter(this, groupData,
//                android.R.layout.simple_expandable_list_item_1, new String[] {
//                PARENT_KEY, CHILD_KEY }, new int[] {
//                android.R.id.text1, android.R.id.text2 }, childData,
//                android.R.layout.simple_expandable_list_item_2, new String[] {
//                PARENT_KEY, CHILD_KEY }, new int[] {
//                android.R.id.text1, android.R.id.text2 });
        //prepareListData();
        List<MyntraCategory.ProductHeadGroup> myntraCategories = MyntraCategory.generateSampleProductHeadGroups(this);
        expandableListAdapter = new MyntraCategoryExpandableListAdapter(this, myntraCategories);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                ExpandableListAdapter adp = expandableListView.getExpandableListAdapter();
                MyntraCategory.ProductGroup pg = (MyntraCategory.ProductGroup) adp.getChild(groupPosition, childPosition);
                Log.e("checking if click event on group is working", pg.getUniqueGroupLabel());
                return true;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if (i == 110) {
                    Log.e("group click listener", "working");
                    return true;
                } else {
                    return false;
                }
                //return true;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                int len = expandableListAdapter.getGroupCount();
                for (int i = 0; i < len; i++) {
                    if (i != groupPosition) {
                        expandableListView.collapseGroup(i);
                    }
                }
            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");


        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
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
}
