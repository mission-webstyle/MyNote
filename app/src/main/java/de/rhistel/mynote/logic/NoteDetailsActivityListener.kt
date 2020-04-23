package de.rhistel.mynote.logic

import android.content.Context
import android.text.Editable
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import de.rhistel.mynote.R
import de.rhistel.mynote.gui.NoteDetailsActivity
import java.io.BufferedReader
import java.io.File
import java.nio.charset.Charset

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
		val strMyNoteContent = txtMyNoteContent.text.toString()

		if (strMyNoteContent.isNotEmpty()) {

			//1. Dateinamen beschaffen
			val strFileName = this.noteDetailsActivity.getString(R.string.strFileName);

			this.noteDetailsActivity.openFileOutput(strFileName, Context.MODE_PRIVATE).use {
				it?.write(strMyNoteContent.toByteArray(Charsets.UTF_8))
			}
		}

		Toast.makeText(this.noteDetailsActivity,
			R.string.strUserMsgSavedSuccessfully,
			Toast.LENGTH_LONG).show()
	}

	/**
	 * Liest die Daten aus der Datei aus.
	 */
	public fun readNoteContent(): String {

		//1. Inhalt zum zurueckgeben
		var strMyNoteContent = ""

		//2. Dateiname aus res/values/strings.xml auslsen
		val strFileName = this.noteDetailsActivity.getString(R.string.strFileName);

		//3. Verzeichnistruktur von Context beschaffen
		val fileDir = this.noteDetailsActivity.filesDir

		//4. FileObjekt generieren mit der Verzeichnisstruktur
		val noteContentFile = File(fileDir, strFileName)

		//5. Checken ob es den File gibt und wenn ja auslesen
		if (noteContentFile.exists()) {
			//3. Reader Definieren lassen
			val reader =
				this.noteDetailsActivity.openFileInput(strFileName).bufferedReader(Charsets.UTF_8)

			//4. Makiert das Ende der Datei
			var eof = false;

			while (!eof) {

				//Gelesene Zeile
				var strReadLine = reader.readLine()

				//Wenn das Ende Datei erreicht nicht mehr auslesne
				if (strReadLine == null) {
					eof = true;
				} else {
					//Inhalt an Varible packen
					strMyNoteContent += strReadLine + "\n"
				}
			}

			reader.close()
		} else {
			Toast.makeText(this.noteDetailsActivity,
				R.string.strUserMsgNoNoteReadCreateOneFirst,
				Toast.LENGTH_LONG).show()
		}


		return strMyNoteContent
	}

	private fun deleteNoteContent() {
		Toast.makeText(this.noteDetailsActivity, "delete", Toast.LENGTH_SHORT).show()
	}
	//endregion


}