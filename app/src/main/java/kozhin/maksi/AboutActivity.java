package kozhin.maksi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

public class AboutActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		PreferencesManager.init(this);
		setupDrawerLayout(false);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.nav_card) {
			if (PreferencesManager.getCardNumber().isEmpty()) {
				Intent intent = new Intent(this, SaveCardActivity.class);
				startActivity(intent);
			} else {
				Intent intent = new Intent(this, CardActivity.class);
				startActivity(intent);
			}
		} else {
			Toast.makeText(this, R.string.in_developing, Toast.LENGTH_SHORT).show();
		}
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
}
