package com.karumi.ui.view

import android.os.Bundle
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance
import com.karumi.data.repository.SuperHeroRepository
import com.karumi.domain.model.SuperHero
import com.karumi.utils.TextLength
import com.karumi.utils.givenSuperHero
import org.junit.Test
import org.mockito.Mock

class SuperHeroDetailViewTest : AcceptanceTest<SuperHeroDetailActivity>(SuperHeroDetailActivity::class.java) {

    @Mock
    private lateinit var repository: SuperHeroRepository

    override val testDependencies = Kodein.Module(allowSilentOverride = true) {
        bind<SuperHeroRepository>() with instance(repository)
    }

    @Test
    fun showsProperlyNormalSuperHeroGivenOne() {
        val superHero = givenSuperHero(repository)

        val activity = startActivityWithSuperHero(superHero)

        compareScreenshot(activity)
    }

    @Test
    fun showsProperlyAvengerGivenOne() {
        val superHero = givenSuperHero(repository, isAvenger = true)

        val activity = startActivityWithSuperHero(superHero)

        compareScreenshot(activity)
    }

    @Test
    fun showsSuperHeroWithVeryLongName() {
        val superHero = givenSuperHero(repository, nameLength = TextLength.VERY_LONG)

        val activity = startActivityWithSuperHero(superHero)

        compareScreenshot(activity)
    }

    @Test
    fun showsSuperHeroWithVeryLongDescription() {
        val superHero = givenSuperHero(repository, descriptionLength = TextLength.VERY_LONG)

        val activity = startActivityWithSuperHero(superHero)

        compareScreenshot(activity)
    }

    @Test
    fun showsSuperHeroWithVeryLongNameAndDescription() {
        val superHero = givenSuperHero(repository, nameLength = TextLength.VERY_LONG,
                descriptionLength = TextLength.VERY_LONG)

        val activity = startActivityWithSuperHero(superHero)

        compareScreenshot(activity)
    }

    @Test
    fun showsSuperHeroWithEmptyName() {
        val superHero = givenSuperHero(repository, nameLength = TextLength.EMPTY)

        val activity = startActivityWithSuperHero(superHero)

        compareScreenshot(activity)
    }

    @Test
    fun showsSuperHeroWithEmptyDescription() {
        val superHero = givenSuperHero(repository, descriptionLength = TextLength.EMPTY)

        val activity = startActivityWithSuperHero(superHero)

        compareScreenshot(activity)
    }

    @Test
    fun showsSuperHeroWithShortNameAndDescription() {
        val superHero = givenSuperHero(repository, nameLength = TextLength.SHORT,
                descriptionLength = TextLength.SHORT)

        val activity = startActivityWithSuperHero(superHero)

        compareScreenshot(activity)
    }

    private fun startActivityWithSuperHero(superHero: SuperHero): SuperHeroDetailActivity {
        val bundle = Bundle().apply {
            putString("super_hero_name_key", superHero.name)
        }
        return startActivity(bundle)
    }
}