package nl.sping.gitview.data

import com.google.gson.annotations.SerializedName

/**
 * Created by sebastiaan on 2019-05-15
 */

data class Git(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)