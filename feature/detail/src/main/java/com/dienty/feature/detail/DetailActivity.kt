package com.dienty.feature.detail

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.dienty.core.structure.base.BaseBindingActivity
import com.dienty.feature.detail.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : BaseBindingActivity<ActivityDetailBinding>() {

    override val viewModel by viewModels<DetailViewModel>()

    private val username by lazy { intent.getStringExtra("USERNAME").orEmpty() }

    override fun getContentView() = R.layout.activity_detail

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        binding.btnBack.setOnClickListener { finish() }
    }

    override fun observeEvent() {
        super.observeEvent()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.detailFlow.collectLatest {
                        binding.apply {
                            username = it.username
                            htmlUrl = it.htmlUrl
                            location = it.location
                            followers = it.followers.toString()
                            followings = it.followings.toString()
                            Glide.with(this@DetailActivity).load(it.avatarUrl).into(imgAvatar)
                        }
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

        viewModel.emitIntent(DetailViewModel.Intent.GetDetail(username))
    }

}