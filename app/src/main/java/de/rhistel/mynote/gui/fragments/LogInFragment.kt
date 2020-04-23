package de.rhistel.mynote.gui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import de.rhistel.mynote.R
import de.rhistel.mynote.logic.UserController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LogInFragment : Fragment() {

	//region 1. Decl. and Init
	private lateinit var txtUserName: TextInputEditText
	private lateinit var txtUserPw: TextInputEditText
	private lateinit var btnLogin: Button

	//endregion
	//region 2. Lebenszyklus
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.login_fragment_layout, container, false)
	}

	override fun onViewCreated(loginFragmentView: View, savedInstanceState: Bundle?) {
		super.onViewCreated(loginFragmentView, savedInstanceState)

		//2. Widgets generieren
		this.txtUserName = loginFragmentView.findViewById<TextInputEditText>(R.id.txtUserName)
		this.txtUserPw = loginFragmentView.findViewById<TextInputEditText>(R.id.txtUserPw)
		this.btnLogin = loginFragmentView.findViewById<Button>(R.id.btnLogin)

		this.btnLogin.setOnClickListener {

			if (!UserController.getInstance().isUserIsLoggedIn) {
				val strUserName = "Hans";
				val strUserPw = "123";

				val strInputtedUserName = txtUserName.text.toString();
				val strInputtedUserPw = txtUserPw.text.toString();


				//1. Nicht leere Eingaben
				if ((strInputtedUserName.isNotEmpty()) && (strUserPw.isNotEmpty())) {

					//2. Checken ob die Eingabedaten korrekt sind
					if ((strInputtedUserName.equals(strUserName)) && (strInputtedUserPw.equals(
							strUserPw))) {

						UserController.getInstance().isUserIsLoggedIn = true;

						//DashboardFragement aufrufen
						this.findNavController()
							.navigate(R.id.actionLoginFragmentToDashboardFragment)
					}
				}
			}else{
				this.findNavController()
					.navigate(R.id.actionLoginFragmentToDashboardFragment)
			}


		}
	}

	override fun onResume() {
		super.onResume()

		if (UserController.getInstance().isUserIsLoggedIn) {
			this.txtUserName.visibility = View.GONE
			this.txtUserPw.visibility = View.GONE

			this.btnLogin.setText(R.string.strToDashboardText)

			Toast.makeText(this.context,
				R.string.strUserMsgYourAreAlreadyLoggedIn, Toast.LENGTH_LONG).show()
//			this.findNavController().
//			navigate(R.id.actionLoginFragmentToDashboardFragment)
		}
	}
	//endregion
}
