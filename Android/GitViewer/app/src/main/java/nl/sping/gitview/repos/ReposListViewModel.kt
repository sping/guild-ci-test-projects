package nl.sping.gitview.repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nl.sping.gitview.model.GithubRepo

/**
 * Created by sebastiaan on 2019-05-15
 */

class ReposListViewModel: ViewModel() {
    val repos = MutableLiveData<MutableList<GithubRepo>>()
}