package com.dlwngud.app

import android.content.Context

class AssetLoader {

    fun getJsonString(context: Context, fileName: String): String? {
        return kotlin.runCatching {
            loadAsset(context, fileName)
        }.getOrNull()
    }

    private fun loadAsset(context: Context, fileName: String): String {
        return context.assets.open(fileName).use { inputSteam ->
            val size = inputSteam.available()
            val bytes = ByteArray(size)
            inputSteam.read(bytes)
            String(bytes)
        }
    }

}