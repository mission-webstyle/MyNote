package de.rhistel.mynote.logic

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import de.rhistel.mynote.R
import de.rhistel.mynote.gui.MainActivity
import de.rhistel.mynote.gui.SettingsActivity

class MainActivityListener(var mainActivity: MainActivity) : View.OnClickListener,
	MenuItem.OnMenuItemClickListener {

	//region 0. Konstanten
	//endregion

	//region 1. Decl. and Init
	//endregion

	//region 2. Klickhandling FabButton
	/**
	 * Spring an wenn der FloationActionButton geklickt wird
	 *
	 * @param v :View: Geklickte Button
	 */
	override fun onClick(v: View?) {
		TODO("Not yet implemented")
	}
	//endregion

	//region 3. MenuItem Klickhandling
	/**
	 * Called when a menu item has been invoked.  This is the first code
	 * that is executed; if it returns true, no other callbacks will be
	 * executed.
	 *
	 * @param mnuItemClickedItem The menu item that was invoked.
	 *
	 * @return Return true to consume this click and prevent others from
	 * executing.
	 */
	override fun onMenuItemClick(mnuItemClickedItem: MenuItem?): Boolean {
		when(mnuItemClickedItem?.itemId) {
			R.id.mnuItemSettings -> startSettingsActivity()
		}

		return true
	}

	/**
	 * Startet die SettingsActivity und gibt
	 * einen Wert als Extra im Intent gekapslet mit
	 */
	private fun startSettingsActivity() {

		//Implizites Intent MainActivity ruft die SettingsActivity auf
		val intentStartSettingsActivity: Intent = Intent(
			this.mainActivity,
			SettingsActivity::class.java
		)

		//Schluessel fuer den ExtraWert
		val strKeyForValueForIntentExtras =
			this.mainActivity.getString(R.string.strKeyForValueForIntentExtras)

		//Wert der als Extra uergeben wird
		val iIntentExtraIntValue =
			this.mainActivity.resources.getInteger(R.integer.iIntentExtraIntValue)

		//Wert und Schluessel and das extra Bundle uebergeben
		intentStartSettingsActivity.putExtra(strKeyForValueForIntentExtras,
			iIntentExtraIntValue)

		//SettingsActivity mit Extra starten
		this.mainActivity.startActivity(intentStartSettingsActivity)
	}
	//endregion

}