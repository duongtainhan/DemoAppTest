package com.dienty.core.structure.app

import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.dienty.core.structure.BuildConfig
import com.dienty.core.structure.app.ActivityLifecycleCallback.Companion.DEBUG_FRAGMENT
import com.dienty.core.structure.app.ActivityLifecycleCallback.Companion.DEBUG_SCREEN
import timber.log.Timber

class ActivityLifecycleCallback : Application.ActivityLifecycleCallbacks {

    companion object {
        const val DEBUG_SCREEN = "DEBUG_SCREEN"
        const val DEBUG_ACTIVITY = "DEBUG_ACTIVITY"
        const val DEBUG_FRAGMENT = "DEBUG_FRAGMENT"
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Timber.tag(DEBUG_SCREEN).d("${activity::class.simpleName}")
        Timber.tag(DEBUG_ACTIVITY).d("${activity::class.simpleName} --> onCreate")
        activity.allowDebugRotation()
        activity.registerFragmentLifecycleCallbacks()
    }

    override fun onActivityStarted(activity: Activity) {
        Timber.tag(DEBUG_ACTIVITY).d("${activity::class.simpleName} --> onStart")
    }

    override fun onActivityResumed(activity: Activity) {
        Timber.tag(DEBUG_ACTIVITY).d("${activity::class.simpleName} --> onResume")
    }

    override fun onActivityPaused(activity: Activity) {
        Timber.tag(DEBUG_ACTIVITY).d("${activity::class.simpleName} --> onPause")
    }

    override fun onActivityStopped(activity: Activity) {
        Timber.tag(DEBUG_ACTIVITY).d("${activity::class.simpleName} --> onStop")
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Timber.tag(DEBUG_ACTIVITY).d("${activity::class.simpleName} --> onSaveInstanceState")
    }

    override fun onActivityDestroyed(activity: Activity) {
        Timber.tag(DEBUG_ACTIVITY).d("${activity::class.simpleName} --> onDestroy")
    }
}

private fun Activity.allowDebugRotation() {
    requestedOrientation = if (BuildConfig.DEBUG) {
        ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    } else {
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}

fun Activity.registerFragmentLifecycleCallbacks() {
    if (this is FragmentActivity) {
        supportFragmentManager.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(
                    fm: FragmentManager,
                    f: Fragment,
                    savedInstanceState: Bundle?
                ) {
                    super.onFragmentCreated(fm, f, savedInstanceState)
                    Timber.tag(DEBUG_SCREEN).d("${f::class.simpleName}")
                    Timber.tag(DEBUG_FRAGMENT).d("${f::class.simpleName} --> onCreated")
                }

                override fun onFragmentViewCreated(
                    fm: FragmentManager,
                    f: Fragment,
                    v: View,
                    savedInstanceState: Bundle?
                ) {
                    super.onFragmentViewCreated(fm, f, v, savedInstanceState)
                    Timber.tag(DEBUG_FRAGMENT).d("${f::class.simpleName} --> ViewCreated")
                }

                override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                    super.onFragmentResumed(fm, f)
                    Timber.tag(DEBUG_FRAGMENT).d("${f::class.simpleName} --> onResume")
                }

                override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
                    super.onFragmentPaused(fm, f)
                    Timber.tag(DEBUG_FRAGMENT).d("${f::class.simpleName} --> onPause")
                }

                override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                    super.onFragmentViewDestroyed(fm, f)
                    Timber.tag(DEBUG_FRAGMENT).d("${f::class.simpleName} --> onDestroyView")
                }
            }, true
        )
    }
}