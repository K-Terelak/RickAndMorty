package domain.repository

import data.remote.Resource
import domain.model.DomainCharacter

interface RickMortyRepository {
    suspend fun getCharacters(): Resource<List<DomainCharacter>>
    suspend fun getCharacterDetails(id: Int): Resource<DomainCharacter>
}
