package de.rhistel.mynote.gui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import de.rhistel.mynote.R
import de.rhistel.mynote.logic.listener.activites.LocationActivityListener
import de.rhistel.mynote.logic.listener.activites.MainActivityListener

/**
 * Gibt dem User die Moeglichkeit
 * sich auf die Schnelle seine Standort
 * anzeigen zu lassen
 */
class LocationActivity : AppCompatActivity() {

	//region 1. Decl. and Init
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
		this.txtvLongitudeValue = this.findViewById(R.id.txtvLongitudeValue)
		this.txtvLatitudeValue = this.findViewById(R.id.txtvLatitudeValue)
		this.txtvAltitudeValue = this.findViewById(R.id.txtvAltitudeValue)
		this.txtvUtcTimeValue = this.findViewById(R.id.txtvUtcTimeValue)

		//3. Listener generieren
		this.locationActivityListener = LocationActivityListener(this);
	}
	//endregion

	/**
	 * Location Menu mit Return value anzeigen
	 */
	override fun onCreateOptionsMenu(locationMenu: Menu?): Boolean {
		this.menuInflater.inflate(R.menu.location_menu_layout, locationMenu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		return super.onOptionsItemSelected(item)
	}
	//endregion
}
