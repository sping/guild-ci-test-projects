package nl.sping.gitview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nl.sping.gitview.data.GitRepository
import nl.sping.gitview.network.GithubApi

class LoginViewModelFactory(private val gitRepository: GitRepository): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(gitRepository) as T
    }
}