package de.rhistel.mynote.gui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import de.rhistel.mynote.R

class SettingsActivity : AppCompatActivity() {

	//region 2. Lebenszyklus
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		//1. Layout setzen
		this.setContentView(R.layout.settings_activity_layout)


		//Aufrufendes Intent
		if (this.intent.hasExtra("keyValue")) {
			val iExtraValueFromIntent = this.intent.getIntExtra("keyValue", 1337)

			Log.d(SettingsActivity::class.java.simpleName, "Extra gefunden: $iExtraValueFromIntent")
		}



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

	//region ClickHandling
	fun clickHandling(v: View) {
		if (v.id == R.id.btnCallHotline) {

			callHotline()

		}
	}

	private fun callHotline() {

		//1. Hotlinstring beschaffen res/values/strings.xml
		val strHotlineNumber = this.getString(R.string.strHotlineNumber)

		//2. IMPLIZITES Intent generieren
		val intentStartCallDialog = Intent(Intent.ACTION_CALL, Uri.parse(strHotlineNumber))


		if (ActivityCompat.checkSelfPermission(this,
				Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
		) {
			//Berechtigung festlege
			val strWantedPermissions = arrayOf(Manifest.permission.CALL_PHONE)

			//Berechtigungsdialog anzeig
			this.requestPermissions(strWantedPermissions, 1)
		} else {
			//3. Starten des Anrufs
			this.startActivity(intentStartCallDialog)
		}
	}
	//endregion

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		if ((requestCode == 1) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
			this.callHotline()
		}
	}
}
