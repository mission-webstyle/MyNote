package de.rhistel.mynote.logic

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import androidx.core.app.ActivityCompat
import de.rhistel.mynote.R
import de.rhistel.mynote.gui.SettingsActivity

/**
 * Dieses Klasse nimmt alle
 * Klicks der SettingsActivity entgegen und
 * leitet damit verbundenen Aktionen ein.
 */
class SettingsActivityListener(var settingsActivity: SettingsActivity) : View.OnClickListener {

	//region 2. Klickauswertung
	/**
	 * Called when a view has been clicked.
	 *
	 * @param v The view that was clicked.
	 */
	override fun onClick(v: View?) {
		if (v?.id == R.id.btnCallHotline) {
			callHotline()
		}
	}
	//endregion

	//region 3. Verberitung der weiteren Logik eingeleitet durch die Klicks

	public fun callHotline() {

		//1. Hotlinstring beschaffen res/values/strings.xml
		val strHotlineNumber = this.settingsActivity.getString(R.string.strHotlineNumber)

		//2. IMPLIZITES Intent generieren
		val intentStartCallDialog = Intent(Intent.ACTION_CALL, Uri.parse(strHotlineNumber))


		if (ActivityCompat.checkSelfPermission(this.settingsActivity,
				Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
		) {
			//Berechtigung festlege
			val strWantedPermissions = arrayOf(Manifest.permission.CALL_PHONE)

			//Berechtigungsdialog anzeig
			this.settingsActivity.requestPermissions(strWantedPermissions, 1)
		} else {
			//3. Starten des Anrufs
			this.settingsActivity.startActivity(intentStartCallDialog)
		}
	}
	//endregion


}