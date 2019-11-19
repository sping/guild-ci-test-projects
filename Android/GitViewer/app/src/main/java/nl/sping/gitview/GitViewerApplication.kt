package nl.sping.gitview

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import io.fabric.sdk.android.Fabric
import nl.sping.gitview.data.TokenWarehouse

/**
 * Created by sebastiaan on 2019-05-15
 */

class GitViewerApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        TokenWarehouse.init(this)
        Fabric.with(this, Crashlytics(), Answers())
    }
}