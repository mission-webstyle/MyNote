package de.rhistel.mynote.logic

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import de.rhistel.mynote.R
import de.rhistel.mynote.gui.NoteDetailsActivity
import java.io.File

/**
 * Nimmt die Klicks der NoteDetailsActivity entegen
 * und leietet die weitere Logik ein.
 */
class NoteDetailsActivityListener(
	var noteDetailsActivity: NoteDetailsActivity,
	var txtMyNoteContent: EditText
) : MenuItem.OnMenuItemClickListener, DialogInterface.OnClickListener {

	//region 0. Konstantne
	//endregion

	//region 1. Decl. and Init
	//endregion

	//region 2. MenutItem Klickauswertung
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
			R.id.mnuItemSaveNoteContent -> saveNoteContent(false)
			R.id.mnuItemDeleteNoteContent -> deleteNoteContent()
			R.id.mnuItemShareNoteContent -> shareNoteContent()
		}
		return true
	}
	//endregion

	//region 3. Speichern der Datei
	private fun saveNoteContent(delete: Boolean) {
		val strMyNoteContent = txtMyNoteContent.text.toString()

		//1. Dateinamen beschaffen
		val strFileName = this.noteDetailsActivity.getString(R.string.strFileName);

		this.noteDetailsActivity.openFileOutput(strFileName, Context.MODE_PRIVATE).use {
			it?.write(strMyNoteContent.toByteArray(Charsets.UTF_8))
		}


		if (!delete) {
			Toast.makeText(this.noteDetailsActivity,
				R.string.strUserMsgSavedSuccessfully,
				Toast.LENGTH_LONG).show()
		}
	}
	//endregion

	//region 4. Datei auslesen
	/**
	 * Liest die Daten aus der Datei aus.
	 * data/data/packageStrukturDerApp/files
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
	//endregion

	//region 5. Noitz loeschen
	/**
	 * 1. Loeschen Dialog anzeigen
	 */
	private fun deleteNoteContent() {

		//1. Generieren des Dialog Builders mit Hilfe des Contexts
		val alertDialogBuilder = AlertDialog.Builder(this.noteDetailsActivity)

		//Titel setzen anzeige in der Leiste
		alertDialogBuilder.setTitle(R.string.strDeleteText)

		//UserMsg festlegen
		alertDialogBuilder.setMessage(R.string.strUserMsgDoYouWantToDeleteTheNote)

		//Text und Listener fuer den Besaetatigungsbutton festlegen
		alertDialogBuilder.setPositiveButton(android.R.string.yes, this)

		//Text und Listener fuer den Abbrechen Button festlegen
		alertDialogBuilder.setNegativeButton(android.R.string.no, this)

		//Anzeigen des Dialogs
		alertDialogBuilder.show()

	}

	/**
	 * This method will be invoked when a button in the dialog is clicked.
	 *
	 * @param dialog the dialog that received the click
	 * @param iClickedButton the button that was clicked (ex.
	 * [DialogInterface.BUTTON_POSITIVE]) or the position
	 * of the item clicked
	 */
	override fun onClick(dialog: DialogInterface?, iClickedButton: Int) {
		if (iClickedButton == DialogInterface.BUTTON_POSITIVE) {

			//Textfeld resetten
			txtMyNoteContent.setText("")

			//UserMsg Erfolgreich geloescht
			Toast.makeText(this.noteDetailsActivity,
				R.string.strUserMsgDeletedSuccessfully,
				Toast.LENGTH_SHORT).show()

			//Leerstring in die Datei schreiben lassen
			this.saveNoteContent(true)
		}
	}

	//endregion

	//region 6. Notizinhalt Teilen

	/**
	 * Zeigt den Teilendialog an
	 */
	private fun shareNoteContent() {
		//Decl and Init
		val strMyNoteContent = txtMyNoteContent.text.toString()
		val strContentTypeTextPlain =
			this.noteDetailsActivity.getString(R.string.strContentTypeTextPlain)

		/*
		 * Kapselt die Inofrmationen was wie geteilet wird
		 * - An andere Apps soll etwas gesendent
		 * - Das Was ist dabei ein Text aus der GUI
		 * - Inhaltstyp wird festgelegt
		 */
		val intentWithContentAndSendInfos: Intent = Intent().apply {
			action = Intent.ACTION_SEND
			putExtra(Intent.EXTRA_TEXT, strMyNoteContent)
			type = strContentTypeTextPlain
		}

		//Vom Os auswaehalen lassen was getan werden soll
		val intentShareDialog = Intent.createChooser(intentWithContentAndSendInfos, null)

		//Teilen Dialog start
		this.noteDetailsActivity.startActivity(intentShareDialog)
	}
	//endregion

}