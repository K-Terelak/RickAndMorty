package data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class NetworkCharactersDataSource(private val client: HttpClient) {
    suspend fun getCharacters(): HttpResponse = client.get("/api/character/?page=1")
    suspend fun getCharacterDetails(id: Int): HttpResponse = client.get("/api/character/$id")

}
