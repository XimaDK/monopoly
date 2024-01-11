package com.example.monopolia.connection

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class ClientService : Service() {

    private val binder: IBinder = LocalBinder()
    private lateinit var client: Socket
    private lateinit var input : BufferedReader
    private lateinit var output : PrintWriter
    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    inner class LocalBinder : Binder() {
        fun getService(): ClientService {
            return this@ClientService
        }
    }

    fun connectClient(ip: String, PORT: Int) {
        client = Socket(ip, PORT)
        input = BufferedReader(InputStreamReader(client.inputStream))
        output = PrintWriter(client.getOutputStream(), true)
        startListening()
    }


    private fun startListening() {
        Thread {
            try {
                while (true) {
                    val message = input.readLine()
                    if (message != null) {
                        Log.d("CON", "Клиенту пришло сообщение: $message")
                        catchMessage(message)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    private fun catchMessage(message : String){

    }


}