package com.ejsong.apptemplate.base

import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    abstract val screenTag: String?

    @Inject
    protected lateinit var flags: com.ejsong.apptemplate.base.config.Flags

    override fun onStart() {
        super.onStart()

        val tag = screenTag
        if (tag != null) com.ejsong.apptemplate.base.analytics.Data.log(
            com.ejsong.apptemplate.base.analytics.ViewScreenEvent(
                tag
            )
        )
    }
}