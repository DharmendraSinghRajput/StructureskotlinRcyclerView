package com.example.structureskotlin.hilt

import android.content.Context
import com.example.structureskotlin.remote.RemoteService
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val remoteServices: RemoteService, private val context: Context)

