package com.example.monopolia.connection

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log
import java.net.NetworkInterface
import java.net.ServerSocket
import java.net.SocketException


class ServerService : Service() {

    private val binder: IBinder = LocalBinder()
    private var isRun = true
    private lateinit var serverSocket: ServerSocket
    private lateinit var serverThread: Thread



    inner class LocalBinder : Binder() {
        fun getService(): ServerService {
            return this@ServerService
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onCreate() {
        Log.d("CON", "in onCreate")
        super.onCreate()
        serverThread = Thread {
            runServer(isRun)
        }
        serverThread.start()


    }

    override fun onDestroy() {
        super.onDestroy()
        disconnectFromServer()
    }

    private fun disconnectFromServer() {
        if (::serverSocket.isInitialized) {
            isRun = false
            serverSocket.close()
        }
    }

    private fun runServer(isRun: Boolean) {
        serverSocket  = ServerSocket(9999)
        Log.d("CON", "in runserver")
        while (isRun) {
            try {
                val client = serverSocket.accept()
                Log.d("CON", "client connected ${client.inetAddress.hostAddress}")
                val networkController = NetworkController(client, Handler(
                        HandlerThread("clientThread: ${client.inetAddress}:${client.port}")
                            .apply { start() }.looper
                    )
                )
                networkController.onMessage =
                    { message -> catchMessage(networkController, message) }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    private fun catchMessage(networkController: NetworkController, message: String) {

    }
}