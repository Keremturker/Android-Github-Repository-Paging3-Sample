package com.turker.github_repository_paging3_sample.ui

import android.app.Application
import com.turker.github_repository_paging3_sample.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(myApp: Application) : BaseViewModel(app = myApp)
