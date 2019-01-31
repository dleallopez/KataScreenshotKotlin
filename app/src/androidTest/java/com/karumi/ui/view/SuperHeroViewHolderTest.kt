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

    @Test
    fun showsAvengerWithVeryLongNameAndShortTeam() {
        val superHero = givenASuperHeroWithNameLength(isAvenger = true, nameLength = TextLength.VERY_LONG)
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsAvailableSuperHero() {
        val superHero = givenASuperHero()
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test
    fun showsUnavailableSuperHero() {
        val superHero = givenASuperHero(isAvailable = false)
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

    private fun givenASuperHeroWithNameLength(isAvenger: Boolean = false,
                                              nameLength: TextLength,
                                              teamLength: TextLength = TextLength.NORMAL): SuperHero {
        val superHeroName = getTestTextByLength(nameLength)
        val superHeroDescription = "Description Super Hero"
        val superHeroTeam = getTestTextByLength(teamLength)
        return givenASuperHero(superHeroName, superHeroDescription, isAvenger, superHeroTeam)
    }

    private fun givenASuperHero(
            superHeroName: String = "Super Hero Name",
            superHeroDescription: String = "Super Hero Description",
            isAvenger: Boolean = false,
            superHeroTeam: String? = null,
            isAvailable : Boolean = true
    ): SuperHero = SuperHero(superHeroName, null, isAvenger, superHeroDescription, superHeroTeam, isAvailable)
}