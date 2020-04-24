package de.rhistel.mynote.logic.listener.activites

import android.view.MenuItem
import de.rhistel.mynote.R
import de.rhistel.mynote.gui.activites.LocationActivity

public class LocationActivityListener(var locationActivity: LocationActivity) :
	MenuItem.OnMenuItemClickListener {

	//region 2. Klicklhandling Trigger GPS
	/**
	 * Called when a menu item has been invoked.  This is the first code
	 * that is executed; if it returns true, no other callbacks will be
	 * executed.
	 *
	 * @param mnuItenClickedItem The menu item that was invoked.
	 *
	 * @return Return true to consume this click and prevent others from
	 * executing.
	 */
	override fun onMenuItemClick(mnuItenClickedItem: MenuItem?): Boolean {
		when(mnuItenClickedItem?.itemId){
			R.id.mnutItemTriggerGps -> triggerGps(true)
		}

		return true
	}

	private fun triggerGps(activate: Boolean) {
		TODO("Not yet implemented")
	}
	//endregion

}
