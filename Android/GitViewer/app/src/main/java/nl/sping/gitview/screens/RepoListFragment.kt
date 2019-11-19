package nl.sping.gitview.screens


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_repo_list.*
import nl.sping.gitview.R
import nl.sping.gitview.data.Git
import nl.sping.gitview.adapters.RepoListAdapter
import nl.sping.gitview.viewmodels.ReposListViewModel

/**
 * A simple [Fragment] subclass.
 * ReposListFragment
 */
class RepoListFragment : Fragment() {

    private val reposViewModel by lazy {
        ViewModelProviders.of(this).get(ReposListViewModel::class.java)
    }

    private lateinit var repoListAdapter: RepoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reposViewModel.repos.observe(this, Observer { repos ->
            repos?.let { renderRepos(repos)}
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        recycler_view.adapter = repoListAdapter

    }

    fun renderRepos(reposList: List<Git>) {
        repoListAdapter.update(reposList)
    }

}
