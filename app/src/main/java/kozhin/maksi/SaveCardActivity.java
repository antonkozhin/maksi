package kozhin.maksi;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import ru.tinkoff.decoro.MaskImpl;
import ru.tinkoff.decoro.parser.UnderscoreDigitSlotsParser;
import ru.tinkoff.decoro.slots.Slot;
import ru.tinkoff.decoro.watchers.FormatWatcher;
import ru.tinkoff.decoro.watchers.MaskFormatWatcher;

public class SaveCardActivity extends BaseActivity {

	private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_card);
        setupDrawerLayout(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener((View view) -> {
            saveCardNumber();
        });
        input = (EditText) findViewById(R.id.input);
        Slot[] slots = new UnderscoreDigitSlotsParser().parseSlots("__ __ __ __");
        MaskImpl mask = MaskImpl.createTerminated(slots);
        FormatWatcher watcher = new MaskFormatWatcher(mask);
        watcher.installOn(input);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_card, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
			saveCardNumber();
            return true;
        }
        return super.onOptionsItemSelected(item);
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

	private void saveCardNumber() {
		String cardNumber = input.getText().toString().replace(" ", "");
		if (cardNumber.length() < 8) {
			Toast.makeText(this, R.string.card_error, Toast.LENGTH_SHORT).show();
		} else{
			PreferencesManager.setCardNumber(cardNumber);
			Intent intent = new Intent(this, CardActivity.class);
			startActivity(intent);
		}
	}
}
