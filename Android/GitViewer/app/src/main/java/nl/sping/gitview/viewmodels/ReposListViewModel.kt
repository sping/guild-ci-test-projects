package nl.sping.gitview.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nl.sping.gitview.data.Git

/**
 * Created by sebastiaan on 2019-05-15
 */

class ReposListViewModel: ViewModel() {
    val repos = MutableLiveData<MutableList<Git>>()
}