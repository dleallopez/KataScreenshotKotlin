package com.karumi.ui.view

import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.karumi.data.repository.SuperHeroRepository
import com.karumi.domain.model.SuperHero
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.mockito.Mock

class MainActivityTest : AcceptanceTest<MainActivity>(MainActivity::class.java) {

    @Mock
    private lateinit var repository: SuperHeroRepository

    @Test
    fun showsEmptyCaseIfThereAreNoSuperHeroes() {
        givenThereAreNoSuperHeroes()

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsHeroesListIfThereAreSomeSuperHeroes() {
        givenThereAreSomeSuperHeroes(5)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsHeroesListIfThereAreSomeAvengers() {
        givenThereAreSomeSuperHeroes(5, avengers = true)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsHeroesListGivenThereAreMixedRegularSuperHeroesAndAvengers() {
        givenThereAreMixedRegularSuperHeroesAndAvengers(10)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsOnlyGivenSuperHero() {
        givenThereAreSomeSuperHeroes()

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsOnlyGivenAvenger() {
        givenThereAreSomeSuperHeroes(numberOfSuperHeroes = 1, avengers = true)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsSuperHeroesWithShortNames() {
        givenThereAreSuperHeroesWithNameLength(numberOfSuperHeroes = 5, textLength = TextLength.SHORT)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsSuperHeroesWithNormalLengthNames() {
        givenThereAreSuperHeroesWithNameLength(numberOfSuperHeroes = 5, textLength = TextLength.NORMAL)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsSuperHeroesWithLongNames() {
        givenThereAreSuperHeroesWithNameLength(numberOfSuperHeroes = 5, textLength = TextLength.LONG)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsSuperHeroesWithVeryLongNames() {
        givenThereAreSuperHeroesWithNameLength(numberOfSuperHeroes = 5, textLength = TextLength.VERY_LONG)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test
    fun showsSuperHeroesWithEmptyNames() {
        givenThereAreSuperHeroesWithNameLength(numberOfSuperHeroes = 5, textLength = TextLength.EMPTY)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    private fun givenThereAreSomeSuperHeroes(
            numberOfSuperHeroes: Int = 1,
            avengers: Boolean = false
    ): List<SuperHero> {
        val superHeroes = IntRange(0, numberOfSuperHeroes - 1).map { id ->
            val superHeroName = "SuperHero - $id"
            val superHeroDescription = "Description Super Hero - $id"
            SuperHero(
                    superHeroName, null, avengers,
                    superHeroDescription
            )
        }

        whenever(repository.getAllSuperHeroes()).thenReturn(superHeroes)
        return superHeroes
    }

    private fun givenThereAreMixedRegularSuperHeroesAndAvengers(totalNumberOfSuperHeroes: Int = 1): List<SuperHero> {
        val superHeroes = IntRange(0, totalNumberOfSuperHeroes - 1).map { id ->
            val superHeroName = "SuperHero - $id"
            val superHeroDescription = "Description Super Hero - $id"
            SuperHero(
                    name = superHeroName,
                    photo = null,
                    isAvenger = id % 2 == 0,
                    description = superHeroDescription
            )
        }

        whenever(repository.getAllSuperHeroes()).thenReturn(superHeroes)
        return superHeroes
    }

    private fun givenThereAreSuperHeroesWithNameLength(numberOfSuperHeroes: Int = 1,
                                                       textLength: TextLength = TextLength.NORMAL): List<SuperHero> {
        val superHeroes = IntRange(0, numberOfSuperHeroes - 1).map { id ->
            val superHeroName = getTextByLength(textLength, id)
            val superHeroDescription = "Description Super Hero - $id"
            SuperHero(
                    name = superHeroName,
                    photo = null,
                    isAvenger = id % 2 == 0,
                    description = superHeroDescription
            )
        }

        whenever(repository.getAllSuperHeroes()).thenReturn(superHeroes)
        return superHeroes
    }

    private fun givenThereAreNoSuperHeroes() {
        whenever(repository.getAllSuperHeroes()).thenReturn(emptyList())
    }

    override val testDependencies = Module(allowSilentOverride = true) {
        bind<SuperHeroRepository>() with instance(repository)
    }

    private fun getTextByLength(textLength: TextLength, id: Int): String =
            when (textLength) {
                TextLength.EMPTY -> ""
                TextLength.SHORT -> SHORT_TEXT
                TextLength.NORMAL -> "$NORMAL_TEXT - $id"
                TextLength.LONG -> "$LONG_TEXT - $id"
                TextLength.VERY_LONG -> "$VERY_LONG_TEXT - $id"
            }
}

enum class TextLength { EMPTY, SHORT, NORMAL, LONG, VERY_LONG }

const val SHORT_TEXT = "A"
const val NORMAL_TEXT = "Lorem ipsum dolor sit amet."
const val LONG_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vestibulum dui magna."
const val VERY_LONG_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at vestibulum sapien, eu tempus dolor. Quisque pretium orci at dolor maximus mattis. Suspendisse auctor, urna ut vestibulum euismod, enim nisi consectetur dui, at dictum risus neque ultrices mi. Praesent tincidunt tellus quam. Lorem ipsum dolor sit amet, consectetur adipiscing elit."