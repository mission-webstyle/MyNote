package de.rhistel.mynote.gui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import de.rhistel.mynote.R
import de.rhistel.mynote.logic.listener.fragments.LoginFragmentListener

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LogInFragment : Fragment() {

	//region 1. Decl. and Init
	private lateinit var txtUserName: TextInputEditText
	private lateinit var txtUserPw: TextInputEditText

	private lateinit var btnLogin: Button

	private lateinit var loginFragmentListener: LoginFragmentListener

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

		//3. Listener
		this.loginFragmentListener =
			LoginFragmentListener(this,
				txtUserName,
				txtUserPw)

		//4. Listener zuweisenea
		this.btnLogin.setOnClickListener(this.loginFragmentListener)


	}

	override fun onResume() {
		super.onResume()
		this.loginFragmentListener.configureWidgetsForLoggedInUser(this.btnLogin)
	}
	//endregion
}
