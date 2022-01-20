package com.turker.github_repository_paging3_sample.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<BindingType : ViewBinding, ViewModelType : BaseViewModel> :
    AppCompatActivity() {

    lateinit var binding: BindingType
    abstract fun onActivityCreated()
    abstract fun observe()
    abstract fun getViewBinding(): BindingType
    protected abstract val viewModel: ViewModelType



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        onActivityCreated()
        observe()
    }
}