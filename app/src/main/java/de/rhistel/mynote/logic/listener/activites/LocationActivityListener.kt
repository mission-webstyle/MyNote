package de.rhistel.mynote.logic.listener.activites

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationProvider
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import de.rhistel.mynote.R
import de.rhistel.mynote.gui.activites.LocationActivity

public class LocationActivityListener :
	MenuItem.OnMenuItemClickListener,
	LocationListener,
	SensorEventListener {

	//region 0.Konstanten
	companion object {
		//Millisekunden
		const val MIN_TIME_TO_REFRESH: Long = 250L

		//Meter
		const val MIN_DISTANCE_TO_REFRESH: Float = 1F

		//Abfragecode fuer Berechtigungsdialog
		const val REQUEST_CODE_FINE_LOCATION = 1337

	}
	//endregion


	//region 1. Decl and Init
	private var locationActivity: LocationActivity

	private lateinit var txtvAirPressureValue: TextView
	private lateinit var txtvLongitudeValue: TextView
	private lateinit var txtvLatitudeValue: TextView
	private lateinit var txtvAltitudeValue: TextView
	private lateinit var txtvUtcTimeValue: TextView

	/**
	 * Location der die Standortuberwachung an und abschlatet
	 * den bekommen wir vom Os
	 */
	private lateinit var locationManager: LocationManager
	private lateinit var sensorManger: SensorManager;
	//endregion

	//region 2. Konstruktor
	/**
	 * Standardkonstruktor zum dirketne sezten
	 * der Attribute und geneireren der Widgetreferenz
	 */
	constructor(locationActivity: LocationActivity) {
		this.locationActivity = locationActivity;

		this.generateWidgetReferences()
	}
	//endregion

	//region 3. Klicklhandling Trigger GPS
	/**
	 * Called when a menu item has been invoked.  This is the first code
	 * that is executed; if it returns true, no other callbacks will be
	 * executed.
	 *
	 * @param mnuItenClickedItem The menu item that was invoked.
	 *
	 * @return Return true to consume this click and prevent others from
	 * executing.
	 */
	override fun onMenuItemClick(mnuItenClickedItem: MenuItem?): Boolean {
		when (mnuItenClickedItem?.itemId) {
			R.id.mnutItemAirPressureValue -> triggerAirPressureSensor(true)
			R.id.mnutItemTriggerGps -> triggerGps(true)
		}

		return true
	}

	//region AirPressure-Trigger

	/**
	 * Barometer ansteuern
	 */
	private fun triggerAirPressureSensor(activate: Boolean) {
		if (activate) {

			//1. Sensor Manger generieren
			this.sensorManger =
				this.locationActivity.
				getSystemService(Context.SENSOR_SERVICE) as SensorManager
		} else {

		}
	}
	//endregion

	//region GPS-Trigger
	/**
	 * Schaltet die Standortueberwachung an und
	 * aus. Zeigt den passenden Berechtigungsdialog
	 * an.
	 * @param activate : true : Standortueberwachung anschlaten - false : abschalten
	 */
	public fun triggerGps(activate: Boolean) {

		if (ActivityCompat.checkSelfPermission(this.locationActivity,
				Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
		) {
			//Berechtigung festlege
			val strWantedPermissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

			//Berechtigungsdialog anzeig
			this.locationActivity.requestPermissions(strWantedPermissions,
				REQUEST_CODE_FINE_LOCATION)
		} else {
			if (activate) {
				//1. LocationManager geneireren

				this.locationManager = this.locationActivity
					.getSystemService(Context.LOCATION_SERVICE) as LocationManager


				//2.Location Updaten starten
				this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
					MIN_TIME_TO_REFRESH,
					MIN_DISTANCE_TO_REFRESH,
					this)
			} else {
				//Standortupdates entfernen
				this.locationManager.removeUpdates(this)
			}
		}
	}
	//endregion

	//endregion

	//region 4. Location Listener
	/**
	 * Called when the location has changed.
	 *
	 *
	 *  There are no restrictions on the use of the supplied Location object.
	 *
	 * @param currentLocation The new location, as a Location object.
	 */
	override fun onLocationChanged(currentLocation: Location?) {

		//Aktueller GPS-Standort ausgeben
		txtvLongitudeValue.setText(currentLocation?.longitude.toString())
		txtvLatitudeValue.setText(currentLocation?.latitude.toString())
		txtvAltitudeValue.setText(currentLocation?.altitude.toString())
		txtvUtcTimeValue.setText(currentLocation?.time.toString())
	}

	/**
	 * This callback will never be invoked and providers can be considers as always in the
	 * [LocationProvider.AVAILABLE] state.
	 *
	 */
	override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
		Log.d("LAL", "LocationProvider.AVAILABLE");
	}

	/**
	 * Called when the provider is enabled by the user.
	 *
	 * @param provider the name of the location provider associated with this
	 * update.
	 */
	override fun onProviderEnabled(provider: String?) {
		Toast.makeText(this.locationActivity,
			R.string.strUserMsgGpsOn,
			Toast.LENGTH_LONG).show()
	}

	/**
	 * Called when the provider is disabled by the user. If requestLocationUpdates
	 * is called on an already disabled provider, this method is called
	 * immediately.
	 *
	 * @param provider the name of the location provider associated with this
	 * update.
	 */
	override fun onProviderDisabled(provider: String?) {
		Toast.makeText(this.locationActivity,
			R.string.strUserMsgGpsOff,
			Toast.LENGTH_LONG).show()
	}
	//endregion

	//region 5. SensorListener
	/**
	 * Called when the accuracy of the registered sensor has changed.  Unlike
	 * onSensorChanged(), this is only called when this accuracy value changes.
	 *
	 *
	 * See the SENSOR_STATUS_* constants in
	 * [SensorManager][android.hardware.SensorManager] for details.
	 *
	 * @param accuracy The new accuracy of this sensor, one of
	 * `SensorManager.SENSOR_STATUS_*`
	 */
	override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
		Log.d("LAL", "Accuracy: $accuracy")
	}

	/**
	 * Called when there is a new sensor event.  Note that "on changed"
	 * is somewhat of a misnomer, as this will also be called if we have a
	 * new reading from a sensor with the exact same sensor values (but a
	 * newer timestamp).
	 *
	 *
	 * See [SensorManager][android.hardware.SensorManager]
	 * for details on possible sensor types.
	 *
	 * See also [SensorEvent][android.hardware.SensorEvent].
	 *
	 *
	 * **NOTE:** The application doesn't own the
	 * [event][android.hardware.SensorEvent]
	 * object passed as a parameter and therefore cannot hold on to it.
	 * The object may be part of an internal pool and may be reused by
	 * the framework.
	 *
	 * @param event the [SensorEvent][android.hardware.SensorEvent].
	 */
	override fun onSensorChanged(event: SensorEvent?) {

		//Lufdruck auslesen
		val fltAirPressure = event?.values?.get(0)

		//Luftduck ausgebe
		txtvAirPressureValue.setText(fltAirPressure.toString())

	}
	//endregion

	//region Hilfsmethoden und Funktionen
	private fun generateWidgetReferences() {
		this.txtvAirPressureValue = this.locationActivity.findViewById(R.id.txtvAirPressureValue)
		this.txtvLongitudeValue = this.locationActivity.findViewById(R.id.txtvLongitudeValue)
		this.txtvLatitudeValue = this.locationActivity.findViewById(R.id.txtvLatitudeValue)
		this.txtvAltitudeValue = this.locationActivity.findViewById(R.id.txtvAltitudeValue)
		this.txtvUtcTimeValue = this.locationActivity.findViewById(R.id.txtvUtcTimeValue)
	}
	//endregion

}
