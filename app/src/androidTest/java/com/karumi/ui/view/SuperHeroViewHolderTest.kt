package com.karumi.ui.view

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.view.LayoutInflater
import com.karumi.R
import com.karumi.domain.model.SuperHero
import com.karumi.ui.presenter.SuperHeroesPresenter
import com.karumi.ui.view.adapter.SuperHeroViewHolder
import com.karumi.utils.TextLength
import com.karumi.utils.getTestTextByLength
import org.junit.Test
import org.mockito.Mockito.mock

class SuperHeroViewHolderTest : ScreenshotTest {

    @Test
    fun showsAnySuperHero() {
        val superHero = givenASuperHero()
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsAvenger() {
        val superHero = givenASuperHero(isAvenger = true)
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsSuperHeroWithNoName() {
        val superHero = givenASuperHeroWithNameLength(nameLength = TextLength.EMPTY)
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsSuperHeroWithShortName() {
        val superHero = givenASuperHeroWithNameLength(nameLength = TextLength.SHORT)
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsSuperHeroWithLongName() {
        val superHero = givenASuperHeroWithNameLength(nameLength = TextLength.LONG)
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsSuperHeroWithVeryLongName() {
        val superHero = givenASuperHeroWithNameLength(nameLength = TextLength.VERY_LONG)
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsAvengerWithVeryLongName() {
        val superHero = givenASuperHeroWithNameLength(isAvenger = true, nameLength = TextLength.VERY_LONG)
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    private fun givenASuperHeroViewHolder(): SuperHeroViewHolder {
        val context = getInstrumentation().targetContext
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.super_hero_row, null, false)
        return SuperHeroViewHolder(
                view,
                mock<SuperHeroesPresenter>(SuperHeroesPresenter::class.java)
        )
    }

    private fun givenASuperHeroWithNameLength(isAvenger: Boolean = false, nameLength: TextLength): SuperHero {
        val superHeroName = getTestTextByLength(nameLength)
        val superHeroDescription = "Description Super Hero"
        val isAvenger = false
        return givenASuperHero(superHeroName, superHeroDescription, isAvenger)
    }

    private fun givenASuperHeroWithALongName(): SuperHero {
        val superHeroName = """
            |Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
            |incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
            |ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in
            |voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
            |proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            |""".trimMargin()
        val superHeroDescription = "Description Super Hero"
        val isAvenger = false
        return givenASuperHero(superHeroName, superHeroDescription, isAvenger)
    }

    private fun givenASuperHero(
            superHeroName: String = "Super Hero Name",
            superHeroDescription: String = "Super Hero Description",
            isAvenger: Boolean = false
    ): SuperHero = SuperHero(superHeroName, null, isAvenger, superHeroDescription, null)
}