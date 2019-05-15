package nl.sping.gitview.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.repo_list_item.view.*
import nl.sping.gitview.R
import nl.sping.gitview.model.GithubRepo

/**
 * Created by sebastiaan on 2019-05-15
 */

class RepoListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var repos: List<GithubRepo> = emptyList()

    override fun getItemCount(): Int = repos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoItemViewHolder {
        return RepoItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.repo_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItemViewHolder = holder as RepoItemViewHolder
        repoItemViewHolder.setData(repos[position])
    }

    fun update(repos: List<GithubRepo>) {
        this.repos = repos
        notifyDataSetChanged()
    }
}

class RepoItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun setData(repo: GithubRepo) {
        itemView.tv_name.text = repo.name
        itemView.tv_link.text = repo.url
    }
}