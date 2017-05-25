package kozhin.maksi;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	protected void setupDrawerLayout(boolean showHomeIsUp) {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeIsUp);
		}
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.app_name, R.string.app_name);
		toggle.setDrawerIndicatorEnabled(!showHomeIsUp);
		drawer.addDrawerListener(toggle);
		toggle.syncState();
		if (showHomeIsUp) {
			toolbar.setNavigationOnClickListener((View v) -> {
				onBackPressed();
			});
		}
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setItemIconTintList(null);
		navigationView.setNavigationItemSelectedListener(this);
		Menu menu = navigationView.getMenu();
		if (isAuth()) {
			menu.findItem(R.id.nav_auth).setVisible(false);
		} else {
			menu.findItem(R.id.nav_card).setVisible(false);
		}
	}

	protected boolean isAuth() {
		return true;
		//return PreferencesManager.getUserId() != 0;
	}
}
