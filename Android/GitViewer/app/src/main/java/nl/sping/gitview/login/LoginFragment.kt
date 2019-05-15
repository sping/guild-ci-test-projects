package nl.sping.gitview.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.*
import nl.sping.gitview.R


/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_login.setOnClickListener { login() }
    }


    private fun login() {
        if (validateText()) {

        }
    }

    private fun validateText(): Boolean {
        val email = input_email.editText?.text.toString()
        val password = input_password.editText?.text.toString()

        return !(email.isEmpty() || password.isEmpty())
    }

}
