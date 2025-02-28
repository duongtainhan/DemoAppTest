package com.dienty.core.structure.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseBindingActivity <B : ViewDataBinding> : AppCompatActivity() {

    private lateinit var _binding: B
    protected val binding: B
        get() = _binding

    abstract val viewModel: BaseViewModel<*, *>

    private val loadingDialog by lazy { LoadingDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, getContentView())

        configBinding(binding)
        setContentView(binding.root)

        viewModel.start()

        initView(savedInstanceState)
        observeEvent()
    }

    @LayoutRes
    abstract fun getContentView(): Int

    protected open fun configBinding(binding: B) {}
    protected open fun initView(savedInstanceState: Bundle?) {}
    protected open fun observeEvent() {}

    protected fun registerErrorFlow(streamMessage: Flow<String>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    streamMessage.collectLatest {
                        Toast.makeText(this@BaseBindingActivity, it, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    open fun showLoading(isLoading: Boolean) {
        if (isLoading) loadingDialog.show() else loadingDialog.dismiss()
    }

    override fun onDestroy() {
        loadingDialog.dismiss()

        super.onDestroy()
    }
}