package kozhin.maksi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import net.glxn.qrgen.android.QRCode;

public class CardActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card);
		PreferencesManager.init(this);
		setupDrawerLayout();
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener((View view) -> {
			editCardNumber();
		});
		String cardNumber = PreferencesManager.getCardNumber();
		Bitmap bitmap = QRCode.from(cardNumber).withSize(750, 750).bitmap();
		ImageView image = (ImageView) findViewById(R.id.qr_code);
		image.setImageBitmap(bitmap);
		TextView text = (TextView) findViewById(R.id.card_number);
		text.setText(cardNumber);
	}

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
		int id = item.getItemId();
		if (id != R.id.nav_card) {
			Toast.makeText(this, R.string.in_developing, Toast.LENGTH_SHORT).show();
		}
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	private void editCardNumber() {
		PreferencesManager.setCardNumber("");
		Intent intent = new Intent(this, SaveCardActivity.class);
		startActivity(intent);
	}
}
