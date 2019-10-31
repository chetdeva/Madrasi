package com.chetdeva.madrasi

import android.view.ViewGroup
import com.chetdeva.madrasi.domain.IdleUserHandler
import com.chetdeva.madrasi.root.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import kotlin.LazyThreadSafetyMode.NONE

class RootActivity : RibActivity() {

    private val idleUserHandler by lazy(NONE) { IdleUserHandler() }

    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> {
        val rootBuilder = RootBuilder(object : RootBuilder.ParentComponent {
            override fun idleUserHandler(): IdleUserHandler = idleUserHandler
        })
        return rootBuilder.build(parentViewGroup)
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        idleUserHandler.onUserTouch()
    }
}
