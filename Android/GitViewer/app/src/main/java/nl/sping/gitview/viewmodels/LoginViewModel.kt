package nl.sping.gitview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import nl.sping.gitview.data.Git
import nl.sping.gitview.data.GitRepository

class LoginViewModel(private val gitRepository: GitRepository): ViewModel() {

    fun login(email: String, password: String): LiveData<List<Git>>? {
        if (!validateText(email, password)) return null
        return gitRepository.getAll()
    }

    private fun validateText(email: String, password: String) = !(email.isEmpty() || password.isEmpty())
}