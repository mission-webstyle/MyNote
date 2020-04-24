package de.rhistel.mynote.gui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.rhistel.mynote.R

/**
 * Gibt dem User die Moeglichkeit
 * sich auf die Schnelle seine Standort
 * anzeigen zu lassen
 */
class LocationActivity : AppCompatActivity() {

	//region 1. Decl. and Init
	//endregion

	//region 2. Lebenszyklus
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		//1. Layout setzen
		this.setContentView(R.layout.location_activity_layout)
	}
	//endregion
}
