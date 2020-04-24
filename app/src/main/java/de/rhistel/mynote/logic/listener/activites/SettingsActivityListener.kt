package de.rhistel.mynote.logic.listener.activites

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import androidx.core.app.ActivityCompat
import de.rhistel.mynote.R
import de.rhistel.mynote.gui.activites.SettingsActivity

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
		when (v?.id) {
			R.id.btnCallHotline -> callHotline()
			R.id.txtvUrl-> showUrlInDefaultBrowser()
		}
	}
	//endregion

	//region 3. Anruf der Hotline

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

	//region 4. URL Aufruf im Standardbrowser
	private fun showUrlInDefaultBrowser(){
		//1. Url aus der res/values/strings.xml auslesen
		val strCompanyUrl = this.settingsActivity.getString(R.string.strCompanyUrl)

		//2. Implizites Intent generieren
		val intentShowUrlInDefaultBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(strCompanyUrl))

		//3. Startent des Intent
		this.settingsActivity.startActivity(intentShowUrlInDefaultBrowser)
	}
	//endregion


}