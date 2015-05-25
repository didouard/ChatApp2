package com.meuuh.dev.chatapp2.views;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.meuuh.dev.chatapp2.R;
import com.meuuh.dev.chatapp2.navigation.DaggerNavigatorComponent;
import com.meuuh.dev.chatapp2.navigation.Navigator;
import com.meuuh.dev.chatapp2.navigation.NavigatorComponent;
import com.meuuh.dev.chatapp2.navigation.NavigatorModule;

import javax.inject.Inject;


public class ChatActivity extends ActionBarActivity {
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        NavigatorComponent navigatorComponent = DaggerNavigatorComponent.builder()
                .navigatorModule(new NavigatorModule())
                .build();

        navigator = navigatorComponent.provideNavigator();
        navigator.setChatActivity(this);
        navigator.navigateToRoom(null);
    }

    public void onSectionAttached(String roomName) {
        mTitle = roomName;
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
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
            getMenuInflater().inflate(R.menu.room, menu);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
