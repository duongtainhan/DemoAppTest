package com.dienty.common.remote.di

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AppAuthenticator : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // handle refresh token in here !
        return null
    }
}