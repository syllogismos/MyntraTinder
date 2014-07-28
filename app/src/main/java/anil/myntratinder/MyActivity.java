package anil.myntratinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MyActivity extends Activity {

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
