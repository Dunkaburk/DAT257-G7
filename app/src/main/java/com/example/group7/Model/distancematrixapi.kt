package com.example.group7.Model

import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

data class DistanceMatrixApiResponse(
    val originAddresses: List<String>,
    val destinationAddresses: List<String>,
    val rows: List<Row>
)

data class Row(
    val elements: List<Element>
)

data class Element(
    val distance: Distance
)

data class Distance(
    val text: String,
    val value: Int
)

suspend fun apitest(city1: String, city2: String): DistanceMatrixApiResponse = withContext(Dispatchers.IO) {
    val client = OkHttpClient()
    //val city1 = "Göteborg"
    //val city2 = "Köpenhamn"
    val key = "AIzaSyCF0Eg1Gnq39yvx0OvoMvdzasKsrbcdTLI"
    val url = HttpUrl.Builder()
        .scheme("https")
        .host("maps.googleapis.com")
        .addPathSegment("maps")
        .addPathSegment("api")
        .addPathSegment("distancematrix")
        .addPathSegment("json")
        .addQueryParameter("origins", city1)
        .addQueryParameter("destinations", city2)
        .addQueryParameter("units", "metrics")
        .addQueryParameter("mode", "walking")
        .addQueryParameter("key", key)
        .build()
    val request = Request.Builder()
        .url(url)
        .build()
    val response = client.newCall(request).execute()
    val json = response.body?.string()
    val gson = Gson()
    val apiResponse = gson.fromJson(json, JsonObject::class.java)

    val originAddresses = apiResponse.getAsJsonArray("origin_addresses").let { addresses ->
        List(addresses.size()) { index ->
            addresses[index].asString
        }
    }

    val destinationAddresses = apiResponse.getAsJsonArray("destination_addresses").let { addresses ->
        List(addresses.size()) { index ->
            addresses[index].asString
        }
    }

    val rows = apiResponse.getAsJsonArray("rows").let { rowsArray ->
        List(rowsArray.size()) { rowIndex ->
            val row = rowsArray[rowIndex].asJsonObject
            val elements = row.getAsJsonArray("elements").let { elementsArray ->
                List(elementsArray.size()) { elementIndex ->
                    val element = elementsArray[elementIndex].asJsonObject
                    val distance = Distance(
                        element.getAsJsonObject("distance").get("text").asString,
                        element.getAsJsonObject("distance").get("value").asInt
                    )
                    Element(distance)
                }
            }
            Row(elements)
        }
    }
    return@withContext DistanceMatrixApiResponse(originAddresses, destinationAddresses, rows)
}