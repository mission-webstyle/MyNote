package de.rhistel.mynote.gui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import de.rhistel.mynote.R

import kotlinx.android.synthetic.main.main_activity_layout.*

/**
 * Eintiegspunkt in die App
 */
class MainActivity : AppCompatActivity() {

    //region 2. Lebenszykus
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_layout)
        setSupportActionBar(toolbar)

        Log.d(MainActivity::class.java.simpleName, "onCreate")
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
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
    //endreigon

    //region 3. Menu Handling
    /**
     * @return true : Menu anzeigen - False: Menu wird nicht angezeigt
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menuInflater.inflate(R.menu.main_menu_layout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuItemSettings) {

            val intentStartSettingsActivity: Intent = Intent(
                this,
                SettingsActivity::class.java
            )

            intentStartSettingsActivity.putExtra("keyValue", 1)

            this.startActivity(intentStartSettingsActivity)
        }

        return false;
    }
    //endregion
}
