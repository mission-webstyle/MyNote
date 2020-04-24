package de.rhistel.mynote.gui.activites

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import de.rhistel.mynote.R
import de.rhistel.mynote.logic.listener.activites.LocationActivityListener

/**
 * Gibt dem User die Moeglichkeit
 * sich auf die Schnelle seine Standort
 * anzeigen zu lassen
 */
class LocationActivity : AppCompatActivity() {

	//region 1. Decl. and Init
	private lateinit var txtvAirPressureValue: TextView
	private lateinit var txtvLongitudeValue: TextView
	private lateinit var txtvLatitudeValue: TextView
	private lateinit var txtvAltitudeValue: TextView
	private lateinit var txtvUtcTimeValue: TextView

	private lateinit var locationActivityListener: LocationActivityListener
	//endregion

	//region 2. Lebenszyklus
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		//1. Layout setzen
		this.setContentView(R.layout.location_activity_layout)

		//2. Widgets generieren
		this.txtvAirPressureValue = this.findViewById(R.id.txtvAirPressureValue)
		this.txtvLongitudeValue = this.findViewById(R.id.txtvLongitudeValue)
		this.txtvLatitudeValue = this.findViewById(R.id.txtvLatitudeValue)
		this.txtvAltitudeValue = this.findViewById(R.id.txtvAltitudeValue)
		this.txtvUtcTimeValue = this.findViewById(R.id.txtvUtcTimeValue)

		//3. Listener generieren
		this.locationActivityListener = LocationActivityListener(this);
	}

	override fun onStop() {
		super.onStop()
		this.locationActivityListener.triggerAirPressureSensor(false)
		this.locationActivityListener.triggerGps(false)
	}
	//endregion

	//region MenuHandling
	/**
	 * Location Menu mit Return value anzeigen
	 */
	override fun onCreateOptionsMenu(locationMenu: Menu?): Boolean {
		this.menuInflater.inflate(R.menu.location_menu_layout, locationMenu)
		return true
	}

	override fun onOptionsItemSelected(mnuItemClickedItem: MenuItem): Boolean {
		return this.locationActivityListener.onMenuItemClick(mnuItemClickedItem)
	}
	//endregion

	//region FallBackMethoden
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
		if ((requestCode == LocationActivityListener.REQUEST_CODE_FINE_LOCATION)
			&& (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
			this.locationActivityListener.triggerGps(true)
		}
	}
	//endregion
}
