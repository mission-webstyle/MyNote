package de.rhistel.mynote.gui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import de.rhistel.mynote.R
import de.rhistel.mynote.logic.DashboardFragmentListener


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DashboardFragment : Fragment() {

	//region 1. Decl. and Init Attribute
	private lateinit var btnPrevious: Button
	private lateinit var btnTakeAnote: Button

	private lateinit var dashboardFragmentListener: DashboardFragmentListener
	//endregion

	//region Lebenszyklus
	/**
	 * Generiert die eigentliche
	 * View welche nachher das Fragment
	 * ergibt das in main_activity_content_layout genuzt wird.
	 */
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?, savedInstanceState: Bundle?
	): View? {

		//1. Layout setzen bestimmt Das Aussehen und die Kindelemente des Fragements
		return inflater.inflate(R.layout.dashboard_fragment_layout, container, false)
	}
	//endregion

	//region 3. Widgets
	override fun onViewCreated(dashboardFragmentView: View, savedInstanceState: Bundle?) {
		super.onViewCreated(dashboardFragmentView, savedInstanceState)

		//2. Widgets generieren
		this.btnPrevious = dashboardFragmentView.findViewById<Button>(R.id.btnPrevious)
		this.btnTakeAnote = dashboardFragmentView.findViewById<Button>(R.id.btnTakeAnote)

		//3. Listener geneireren
		this.dashboardFragmentListener = DashboardFragmentListener(this);

		this.btnPrevious.setOnClickListener(this.dashboardFragmentListener)
		this.btnTakeAnote.setOnClickListener(this.dashboardFragmentListener)

	}
	//endregion


}
