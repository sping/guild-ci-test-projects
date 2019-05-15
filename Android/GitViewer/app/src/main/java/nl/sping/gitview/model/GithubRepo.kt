package nl.sping.gitview.model

import com.google.gson.annotations.SerializedName

/**
 * Created by sebastiaan on 2019-05-15
 */

data class GithubRepo(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)