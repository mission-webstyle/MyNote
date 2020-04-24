package de.rhistel.mynote.logic.listener.fragments

import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import de.rhistel.mynote.R
import de.rhistel.mynote.gui.fragments.LogInFragment
import de.rhistel.mynote.logic.controller.UserController


/**
 * Handelt die Klicks des LoginFragments
 */
class LoginFragmentListener(
	var logInFragment: LogInFragment,
	var txtUserName: TextInputEditText,
	var txtUserPw: TextInputEditText
) :
	View.OnClickListener {

	//region 1. Klickhandling
	override fun onClick(v: View?) {
		if (v?.id == R.id.btnLogin) {
			userLogin()
		}
	}
	//endregion


	//region 2. Weitere eingeletiet Logik
	private fun userLogin() {
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
					this.logInFragment.findNavController()
						.navigate(R.id.actionLoginFragmentToDashboardFragment)
				}
			}
		} else {
			this.logInFragment.findNavController()
				.navigate(R.id.actionLoginFragmentToDashboardFragment)
		}

	}
	//endregion

	//region Textfield Handling
	public fun configureWidgetsForLoggedInUser(btnLogin: Button) {
		if (UserController.getInstance().isUserIsLoggedIn) {

			//Unsichtbar schlaten der Textfelder
			this.txtUserName.visibility = View.GONE
			this.txtUserPw.visibility = View.GONE

			//Aendern des Button Textes
			btnLogin.setText(R.string.strToDashboardText)

			//User Mittleiung ueber dein EingeloggtStatus
			Toast.makeText(this.logInFragment.context,
				R.string.strUserMsgYourAreAlreadyLoggedIn, Toast.LENGTH_LONG).show()
//			this.findNavController().
//			navigate(R.id.actionLoginFragmentToDashboardFragment)
		}
	}
	//endreigon
}


