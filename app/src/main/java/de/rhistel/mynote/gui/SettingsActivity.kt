package de.rhistel.mynote.gui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import de.rhistel.mynote.R
import de.rhistel.mynote.logic.SettingsActivityListener

/**
 * Bietet dem User die
 * Moeglichkeite eine Supporthotline
 * anzurufen oder die Internetseite
 * des Herstellers zu besuchen
 */
class SettingsActivity : AppCompatActivity() {

	//region 1. Decl. and Init
	private lateinit var btnCallHotline: Button
	private lateinit var txtvUrl: TextView
	private lateinit var webViewHtmlHelpText: WebView

	private lateinit var settingsActivityListener: SettingsActivityListener;
	//endregion

	//region 2. Lebenszyklus
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		//1. Layout setzen
		this.setContentView(R.layout.settings_activity_layout)

		//2. Widgets generieren
		this.btnCallHotline = this.findViewById(R.id.btnCallHotline)
		this.txtvUrl = this.findViewById(R.id.txtvUrl)

		this.webViewHtmlHelpText = this.findViewById(R.id.webViewHtmlHelpText)

		//3 Aufrufendes Intent
		val strKeyForValueForIntentExtras = this.getString(R.string.strKeyForValueForIntentExtras)

		if (this.intent.hasExtra(strKeyForValueForIntentExtras)) {
			val iExtraValueFromIntent = this.intent.getIntExtra(strKeyForValueForIntentExtras, 1337)

			Log.d(SettingsActivity::class.java.simpleName, "Extra gefunden: $iExtraValueFromIntent")
		}

		//HtmlContent und Typ auslesen
		val strHtmlContent = this.getString(R.string.strHtmlContent)
		val strHtmlContentTyp = this.getString(R.string.strHtmlContentType);

		this.webViewHtmlHelpText.loadData(strHtmlContent, strHtmlContentTyp, null)

		//Die WebView ist nicht geeignet um das Web anzuzeigen.
//		this.webViewHtmlHelpText.loadUrl("https://www.heise.de/")

		//4. Genererien des Listeners
		this.settingsActivityListener = SettingsActivityListener(this)

		//5. Zuweisen des Listeners
		this.btnCallHotline.setOnClickListener(this.settingsActivityListener)

		Log.d(SettingsActivity::class.java.simpleName, "onCreate")

	}

	override fun onStart() {
		super.onStart()
		Log.d(SettingsActivity::class.java.simpleName, "onStart")
	}

	override fun onResume() {
		super.onResume()
		Log.d(SettingsActivity::class.java.simpleName, "onResume")
	}

	override fun onRestart() {
		super.onRestart()
		Log.d(SettingsActivity::class.java.simpleName, "onRestart")
	}

	override fun onPause() {
		super.onPause()
		Log.d(SettingsActivity::class.java.simpleName, "onPause")
	}

	override fun onStop() {
		super.onStop()
		Log.d(SettingsActivity::class.java.simpleName, "onStop")
	}

	override fun onDestroy() {
		super.onDestroy()
		Log.d(SettingsActivity::class.java.simpleName, "onDestroy")
	}
	//endregion


	/**
	 * Berechtigungsdialog auswerten
	 * @param requestCode : Int : Selbstdefinierter Code der die konkrete
	 * @param permissions : Array<out String>: Gecheckten Berechtigungen
	 * @param grantResults : IntArray : Bestaetitungsergebnis der einzelnen Checks
	 */
	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		if ((requestCode == 1) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
			this.settingsActivityListener.callHotline();
		}
	}
}
