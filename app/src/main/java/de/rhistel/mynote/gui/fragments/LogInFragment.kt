package de.rhistel.mynote.gui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import de.rhistel.mynote.R
import kotlinx.android.synthetic.main.login_fragment_layout.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LogInFragment : Fragment() {

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.login_fragment_layout, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		view.findViewById<Button>(R.id.btnLogin).setOnClickListener {

			val strUserName = "Hans";
			val strUserPw = "123";

			val strInputtedUserName = txtUserName.text.toString();
			val strInputtedUserPw = txtUserPw.text.toString();



			//1. Nicht leere Eingaben
			if ((strInputtedUserName.isNotEmpty()) && (strUserPw.isNotEmpty())) {

				//2. Checken ob die Eingabedaten korrekt sind
				if ((strInputtedUserName.equals(strUserName)) && (strInputtedUserPw.equals(strUserPw))) {

					//DashboardFragement aufrufen
					this.findNavController().navigate(R.id.actionLoginFragmentToDashboardFragment)
				}
			}


		}
	}
}
