package de.rhistel.mynote.gui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.rhistel.mynote.R
import de.rhistel.mynote.logic.MainActivityListener

import kotlinx.android.synthetic.main.main_activity_layout.*

/**
 * Eintiegspunkt in die App
 */
class MainActivity : AppCompatActivity() {

	//region 1. Decl. and Init
	private lateinit var fabInfo: FloatingActionButton
	private lateinit var mainActivityListener: MainActivityListener
	//endregion

	//region 2. Lebenszykus
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		//1. Setzen des Layouts
		setContentView(R.layout.main_activity_layout)

		//2. Setzen der Toolbal
		setSupportActionBar(toolbar)

		//3. Generieren der Widgets
		this.fabInfo = this.findViewById(R.id.fabInfo)

		//4. Generieren des Listeners
		this.mainActivityListener = MainActivityListener(this)

		//5. Listener setzen
		fabInfo.setOnClickListener(this.mainActivityListener)

		Log.d(MainActivity::class.java.simpleName, "onCreate")
	}

	override fun onStart() {
		super.onStart()
		Log.d(MainActivity::class.java.simpleName, "onStart")
	}

	override fun onResume() {
		super.onResume()
		Log.d(MainActivity::class.java.simpleName, "onResume")
	}

	override fun onRestart() {
		super.onRestart()
		Log.d(MainActivity::class.java.simpleName, "onRestart")
	}

	override fun onPause() {
		super.onPause()
		Log.d(MainActivity::class.java.simpleName, "onPause")
	}

	override fun onStop() {
		super.onStop()
		Log.d(MainActivity::class.java.simpleName, "onStop")
	}

	override fun onDestroy() {
		super.onDestroy()
		Log.d(MainActivity::class.java.simpleName, "onDestroy")
	}
	//endregion

	//region 3. Menu Handling
	/**
	 * @return true : Menu anzeigen - False: Menu wird nicht angezeigt
	 */
	override fun onCreateOptionsMenu(mainActivityMenu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.menuInflater.inflate(R.menu.main_menu_layout, mainActivityMenu)
		return true
	}

	override fun onOptionsItemSelected(mnuItemClickedItem: MenuItem): Boolean {
		return this.mainActivityListener.onMenuItemClick(mnuItemClickedItem);
	}
	//endregion
}
