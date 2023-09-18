package com.ejsong.apptemplate.base

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    protected val flags: com.ejsong.apptemplate.base.config.Flags,
) : ViewModel()