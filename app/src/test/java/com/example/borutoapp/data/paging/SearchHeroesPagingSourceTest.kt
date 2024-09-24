package com.example.borutoapp.data.paging

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import com.example.borutoapp.data.remote.api.FakeHeroesApi
import com.example.borutoapp.data.remote.api.HeroesApi
import com.example.borutoapp.domain.model.Hero
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class SearchHeroesPagingSourceTest {
    private lateinit var heroesApi: HeroesApi
    private lateinit var heroes: List<Hero>

    @Before
    fun setup() {
        heroesApi = FakeHeroesApi()
        heroes = listOf(
            Hero(
                id = 1,
                name = "Sasuke",
                image = "/images/sasuke.jpg",
                about = "Sasuke Uchiha (うちはサスケ, Uchiha Sasuke) is one of the last surviving members of Konohagakure's Uchiha clan. After his older brother, Itachi, slaughtered their clan, Sasuke made it his mission in life to avenge them by killing Itachi. He is added to Team 7 upon becoming a ninja and, through competition with his rival and best friend, Naruto Uzumaki.",
                rating = 5.0,
                power = 98,
                day = "23rd",
                month = "July",
                family = listOf(
                    "Fugaku",
                    "Mikoto",
                    "Itachi",
                    "Sarada",
                    "Sakura"
                ),
                abilities = listOf(
                    "Sharingan",
                    "Rinnegan",
                    "Sussano",
                    "Amateratsu",
                    "Intelligence"
                ),
                natureTypes = listOf(
                    "Lightning",
                    "Fire",
                    "Wind",
                    "Earth",
                    "Water"
                )
            ),
            Hero(
                id = 2,
                name = "Naruto",
                image = "/images/naruto.jpg",
                about = "Naruto Uzumaki (うずまきナルト, Uzumaki Naruto) is a shinobi of Konohagakure's Uzumaki clan. He became the jinchūriki of the Nine-Tails on the day of his birth — a fate that caused him to be shunned by most of Konoha throughout his childhood. After joining Team Kakashi, Naruto worked hard to gain the village's acknowledgement all the while chasing his dream to become Hokage.",
                rating = 5.0,
                power = 98,
                day = "10th",
                month = "Oct",
                family = listOf(
                    "Minato",
                    "Kushina",
                    "Boruto",
                    "Himawari",
                    "Hinata"
                ),
                abilities = listOf(
                    "Rasengan",
                    "Rasen-Shuriken",
                    "Shadow Clone",
                    "Senin Mode"
                ),
                natureTypes = listOf(
                    "Wind",
                    "Earth",
                    "Lava",
                    "Fire"
                )
            ),
            Hero(
                id = 3,
                name = "Sakura",
                image = "/images/sakura.jpg",
                about = "Sakura Uchiha (うちはサクラ, Uchiha Sakura, née Haruno (春野)) is a kunoichi of Konohagakure. When assigned to Team 7, Sakura quickly finds herself ill-prepared for the duties of a shinobi. However, after training under the Sannin Tsunade, she overcomes this, and becomes recognised as one of the greatest medical-nin in the world.",
                rating = 4.5,
                power = 92,
                day = "28th",
                month = "Mar",
                family = listOf(
                    "Kizashi",
                    "Mebuki",
                    "Sarada",
                    "Sasuke"
                ),
                abilities = listOf(
                    "Chakra Control",
                    "Medical Ninjutsu",
                    "Strength",
                    "Intelligence"
                ),
                natureTypes = listOf(
                    "Earth",
                    "Water",
                    "Fire"
                )
            ),
        )
    }

    @Test
    fun `Search api with existing hero name, expect single hero result, assert LoadResult_Page`() =
        runTest {
            val searchHeroesPagingSource = SearchHeroesPagingSource(
                heroesApi = heroesApi,
                name = "Sasuke"
            )
            assertEquals<LoadResult<Int, Hero>>(
                expected = LoadResult.Page(
                    data = listOf(heroes.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = searchHeroesPagingSource.load(
                    params = LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with existing hero name, expect multiple hero result, assert LoadResult_Page`() =
        runTest {
            val searchHeroesPagingSource = SearchHeroesPagingSource(
                heroesApi = heroesApi,
                name = "Sa"
            )
            assertEquals<LoadResult<Int, Hero>>(
                expected = LoadResult.Page(
                    data = listOf(heroes.first(), heroes[2]),
                    prevKey = null,
                    nextKey = null
                ),
                actual = searchHeroesPagingSource.load(
                    params = LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with empty hero name, expect empty hero result, assert LoadResult_Page`() =
        runTest {
            val searchHeroesPagingSource = SearchHeroesPagingSource(
                heroesApi = heroesApi,
                name = ""
            )
            assertEquals<LoadResult<Int, Hero>>(
                expected = LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                ),
                actual = searchHeroesPagingSource.load(
                    params = LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }

    @Test
    fun `Search api with non-existing hero name, expect empty hero result, assert LoadResult_Page`() =
        runTest {
            val searchHeroesPagingSource = SearchHeroesPagingSource(
                heroesApi = heroesApi,
                name = "Unknown"
            )
            assertEquals<LoadResult<Int, Hero>>(
                expected = LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                ),
                actual = searchHeroesPagingSource.load(
                    params = LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }
}