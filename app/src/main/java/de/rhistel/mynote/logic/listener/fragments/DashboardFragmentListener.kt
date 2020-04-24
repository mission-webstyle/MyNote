package de.rhistel.mynote.logic.listener.fragments

import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.rhistel.mynote.R
import de.rhistel.mynote.gui.activites.NoteDetailsActivity

/**
 * Diese Klasse wertet die Klicks
 * des DashboardFragmentListener aus.
 */
class DashboardFragmentListener(var currentFragment: Fragment) :
	View.OnClickListener {

	//region 1. Decl. and Init Attribute
	//endregion

	//region 2. Auswertung der Klicks
	override fun onClick(v: View?) {
		when(v?.id){
			R.id.btnPrevious->navigateToPreviousFragment()
			R.id.btnTakeAnote->startNoteDetailsActivity()
		}
	}
	//endregion

	//region 3. Weitere Logik nach der Klickauswertung
	/**
	 * Navigiert zum LoginFragement
	 */
	private fun navigateToPreviousFragment() {
		this.currentFragment.findNavController().navigate(R.id.actionDashboardFragementToLoginFragment)
	}

	/**
	 * Start die NoteDetailsActivity
	 * mit Hilfe einens Expliziten Intents
	 */
	private fun startNoteDetailsActivity() {

		//Explizites Intent (Startactivity/fragement, Zielactivity)
		val intentStartNoteDetailsActivity = Intent(this.currentFragment.context,
			NoteDetailsActivity::class.java)

		//Activity mit explizites Intent starten
		this.currentFragment.startActivity(intentStartNoteDetailsActivity)
	}
	//endregion

}