package de.rhistel.mynote.gui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import de.rhistel.mynote.R
import de.rhistel.mynote.logic.NoteDetailsActivityListener

/**
 * Leitet die CRUD Operationen fuer die Notiz ein
 */
class NoteDetailsActivity : AppCompatActivity() {
	//region 0.Konstanten
	//endregion

	//region 1. Decl and Init Attribute
	private lateinit var txtMyNoteContent: EditText
	private lateinit var noteDetailsActivityListener: NoteDetailsActivityListener
	//endregion

	/**
	 * Alle direkt benoetigten Widgets
	 * zu generieren und das Layout setzen
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		//1.Layout setzen
		this.setContentView(R.layout.note_details_activity_layout)

		//2. Widget generieren
		this.txtMyNoteContent = this.findViewById(R.id.txtMyNoteContent)

		//3. Listener generieren
		this.noteDetailsActivityListener = NoteDetailsActivityListener(this, txtMyNoteContent)


	}
	//endregion

	//region 3. Menuhandling
	/**
	 * Generiert das Menu
	 * @return true Menu wird angezeigt false : Menu wird nicht angzeigt
	 */
	override fun onCreateOptionsMenu(noteDetailsMenu: Menu?): Boolean {
		this.menuInflater.inflate(R.menu.note_details_menu_layout, noteDetailsMenu)
		return true
	}

	override fun onOptionsItemSelected(mnuItemClickedItem: MenuItem): Boolean {
		return noteDetailsActivityListener.onMenuItemClick(mnuItemClickedItem)
	}
	//endregion

}