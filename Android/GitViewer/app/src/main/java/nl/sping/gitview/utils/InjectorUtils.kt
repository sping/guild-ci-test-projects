package nl.sping.gitview.utils

import android.content.Context
import nl.sping.gitview.data.GitRepository
import nl.sping.gitview.network.GithubViewerService
import nl.sping.gitview.viewmodels.LoginViewModelFactory

object InjectorUtils {

    private fun getGitRepository(context: Context) = GitRepository(GithubViewerService.createRetrofitService(context))

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {
        return LoginViewModelFactory(getGitRepository(context))
    }
}