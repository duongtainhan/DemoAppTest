package com.dienty.feature.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dienty.core.extension.launchActivity
import com.dienty.core.structure.base.BaseBindingActivity
import com.dienty.feature.home.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : BaseBindingActivity<ActivityHomeBinding>(), HomeAdapter.Listener {

    override val viewModel by viewModels<HomeViewModel>()

    private val adapter by lazy { HomeAdapter(this) }

    override fun getContentView() = R.layout.activity_home

    override fun configBinding(binding: ActivityHomeBinding) {
        binding.adapter = adapter
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        binding.btnBack.setOnClickListener { finish() }
    }

    override fun observeEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.pagingFlow.collectLatest {
                        adapter.submitData(it)
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                launch {
                    viewModel.showLoadingFlow.collectLatest(::showLoading)
                }
            }
        }
        registerErrorFlow(viewModel.errorFlow)
    }

    override fun onClickedItem(username: String) {
        launchActivity(
            packageName = packageName,
            className = "com.dienty.feature.detail.DetailActivity",
            bundle = bundleOf("USERNAME" to username)
        )
    }
}