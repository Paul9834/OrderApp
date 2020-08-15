package com.paul9834.orderapp_mvvm.data.model

import android.app.Application
import android.content.res.AssetManager
import com.google.gson.GsonBuilder

class Test constructor(private val application: Application) {

    companion object {
        const val KEY_FIRST_START = "FIRST_START"
    }

    fun generateMockList(): List<InvoiceItems> {
        val response = application.assets.readAssetsFile("response.json")
        return GsonBuilder().create().fromJson(response, Array<InvoiceItems>::class.java).asList()
    }

    private fun AssetManager.readAssetsFile(fileName: String): String =
            open(fileName).bufferedReader().use { it.readText() }

}