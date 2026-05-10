package com.example.makan_log.core.util

import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import org.json.JSONObject

suspend fun GetMessageFromHttpResponse(response: HttpResponse): String? {
  return try {
    val rawJsonString = response.bodyAsText()

    val jsonObject = JSONObject(rawJsonString)
    jsonObject.getString("message")
  } catch (e: Exception) {
    println("Gagal mengekstrak pesan: ${e.localizedMessage}")
    null
  }
}
