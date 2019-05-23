package nl.sping.gitview.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_login.*
import nl.sping.gitview.R
import nl.sping.gitview.utils.InjectorUtils
import nl.sping.gitview.viewmodels.LoginViewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment() {

    private val loginViewModel by lazy {
        ViewModelProviders.of(this, InjectorUtils.provideLoginViewModelFactory(requireContext())).get(LoginViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_login.setOnClickListener {
            loginViewModel.login(input_email.editText?.text.toString(), input_password.editText?.text.toString())
                ?.observe(this, Observer {
                    
                })
        }
    }
}
