package data.remote.repository

import data.remote.NetworkCharactersDataSource
import data.remote.model.CharacterResponse
import data.remote.model.ListResponse
import data.remote.Resource
import domain.repository.RickMortyRepository
import domain.mapper.toDomainCharacter
import domain.model.DomainCharacter
import io.ktor.client.call.body
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class RickMortyRepositoryImpl(
    private val networkCharactersDataSource: NetworkCharactersDataSource
) : RickMortyRepository {

    override suspend fun getCharacters(): Resource<List<DomainCharacter>> =
        withContext(Dispatchers.IO) {
            try {
                val response = networkCharactersDataSource
                    .getCharacters()
                    .body<ListResponse>().characterResponses

                Resource.Success(response?.filterNotNull()?.map { it.toDomainCharacter() }
                    .orEmpty())
            } catch (e: Exception) {
                Resource.Error(e)
            }
        }

    override suspend fun getCharacterDetails(id: Int): Resource<DomainCharacter> =
        withContext(Dispatchers.IO) {
            try {
                val response = networkCharactersDataSource
                    .getCharacterDetails(id)
                    .body<CharacterResponse>()

                Resource.Success(response.toDomainCharacter())
            } catch (e: Exception) {
                Resource.Error(e)
            }
        }
}

