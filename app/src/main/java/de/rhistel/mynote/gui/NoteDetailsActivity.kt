package de.rhistel.mynote.gui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import de.rhistel.mynote.R

/**
 * Leitet die CRUD Operationen fuer die Notiz ein
 */
class NoteDetailsActivity : AppCompatActivity() {
	//region 0.Konstanten
	//endregion

	//region 1. Decl and Init Attribute
	//endregion

	/**
	 * Alle direkt benoetigten Widgets
	 * zu generieren und das Layout setzen
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		//1.Layout setzen
		this.setContentView(R.layout.note_details_activity_layout)

	}
	//endregion

	//region 3. Menuhandling
	//endregion

}