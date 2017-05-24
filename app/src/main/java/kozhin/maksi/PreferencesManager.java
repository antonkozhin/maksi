package kozhin.maksi;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

	private static final String APP_PREFERENCES 			= "maksi_settings";      	// Имя файла настроек
	private static final String APP_USER_ID 				= "user_id";           		// Идентификатор пользователя
	private static final String APP_CARD_NUMBER				= "card_number";       		// Номер карточки

	private static SharedPreferences settings;

	public static void init(Context context) {
		if (settings == null) settings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
	}

	public static void setUserId(int userId) {
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt(APP_USER_ID, userId);
		editor.apply();
	}

	public static int getUserId() {
		return settings.getInt(APP_USER_ID, 0);
	}

	public static void setCardNumber(String number) {
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(APP_CARD_NUMBER, number);
		editor.apply();
	}

	public static String getCardNumber() {
		return settings.getString(APP_CARD_NUMBER, "");
	}

}
