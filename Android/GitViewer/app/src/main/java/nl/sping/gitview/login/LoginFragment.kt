package nl.sping.gitview.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nl.sping.gitview.R
import nl.sping.gitview.network.GithubApi
import nl.sping.gitview.network.GithubViewerService
import nl.sping.gitview.network.NetworkUnavailableException
import nl.sping.gitview.persistence.TokenWarehouse
import okhttp3.Credentials
import retrofit2.HttpException


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
            val email = input_email.editText?.text.toString()
            val password = input_password.editText?.text.toString()
            val token = Credentials.basic(email, password)
            TokenWarehouse.setAuthToken(token)
            val service = GithubViewerService.createRetrofitService(requireContext())

            CoroutineScope(Dispatchers.IO).launch {
                val request = service.getRepos()
                try {
                    val response = request.await()
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            Log.d("LoginFragment", "Done")
                        } else {
                            Log.d("LoginFragment", "Error")
                        }
                    }
                } catch (e: NetworkUnavailableException) {
                    TokenWarehouse.clearTokens()
                    Log.e("LoginFragment", "Error no interwebs")
                } catch (e: HttpException) {
                    TokenWarehouse.clearTokens()
                    Log.e("LoginFragment", "Exception ${e.message()}")
                } catch (e: Throwable) {
                    TokenWarehouse.clearTokens()
                    Log.e("LoginFragment", "Exception ${e.message}")
                }
            }
        }
    }

    private fun validateText(): Boolean {
        val email = input_email.editText?.text.toString()
        val password = input_password.editText?.text.toString()

        return !(email.isEmpty() || password.isEmpty())
    }

}
