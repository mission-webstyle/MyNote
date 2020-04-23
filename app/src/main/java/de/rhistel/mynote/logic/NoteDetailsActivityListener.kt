package de.rhistel.mynote.logic

import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import de.rhistel.mynote.R
import de.rhistel.mynote.gui.NoteDetailsActivity

class NoteDetailsActivityListener(
	var noteDetailsActivity: NoteDetailsActivity,
	var txtMyNoteContent: EditText
) : MenuItem.OnMenuItemClickListener {


	//region MenutItem Klickauswertung
	/**
	 * Called when a menu item has been invoked.  This is the first code
	 * that is executed; if it returns true, no other callbacks will be
	 * executed.
	 *
	 * @param item The menu item that was invoked.
	 *
	 * @return Return true to consume this click and prevent others from
	 * executing.
	 */
	override fun onMenuItemClick(item: MenuItem?): Boolean {
		when (item?.itemId) {
			R.id.mnuItemSaveNoteContent -> saveNoteContent()
			R.id.mnuItemDeleteNoteContent -> deleteNoteContent()
		}
		return true
	}
	//endregion

	//region Save and Delete
	private fun saveNoteContent() {
		Toast.makeText(this.noteDetailsActivity, "save", Toast.LENGTH_SHORT).show()
	}

	private fun deleteNoteContent() {
		Toast.makeText(this.noteDetailsActivity, "delete", Toast.LENGTH_SHORT).show()
	}
	//endregion


}