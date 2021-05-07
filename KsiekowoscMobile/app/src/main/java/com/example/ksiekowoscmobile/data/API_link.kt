package com.example.ksiekowoscmobile.data

import android.util.Log
import org.json.JSONException
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import android.net.Uri;



class API_link {

    fun getUrlBytes(urlSpec: String): ByteArray {
        val url = URL(urlSpec)
        val connection = url.openConnection() as HttpURLConnection

        try {
            val out = ByteArrayOutputStream()
            val input = connection.inputStream

            if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                throw IOException(connection.responseMessage)
            }

            var bytesRead: Int
            val buffer = ByteArray(1024)

            do {
                bytesRead = input.read(buffer)

                out.write(buffer, 0, bytesRead)

            } while (input.read(buffer) > 0)

            return out.toByteArray()

        } catch (e: IOException) {
            e.message?.let { Log.e("ERROR_HTTP", it) }
            return ByteArray(0)
        } finally {
            connection.disconnect()
        }
    }

        fun getUrlString(urlSpec: String):String{
            return String(getUrlBytes(urlSpec))
        }


        fun getJHSONString():String {
            var jsonString ="Error"

            try{
                val url: String = Uri.parse("http://127.0.0.1:8000/api/documentation/")
                    .buildUpon()
                    .appendQueryParameter("method", "@string_api/login")
                    .appendQueryParameter("method", "@string_api/register")
                    .appendQueryParameter("api_key", "")
                    .appendQueryParameter("format", "json")
                    .appendQueryParameter("nojsoncallback", "1")
                    .appendQueryParameter("extras", "urls_s")
                    .build().toString()
                jsonString = getUrlString(url)

            }catch (je: JSONException){
                je.message?.let { Log.e("JSON_ERROR", it) }
            }
            return jsonString
        }

}