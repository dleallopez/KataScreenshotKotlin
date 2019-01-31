package com.karumi.utils

import com.karumi.data.repository.SuperHeroRepository
import com.karumi.domain.model.SuperHero
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever

enum class TextLength { EMPTY, SHORT, NORMAL, LONG, VERY_LONG }

const val SHORT_TEXT = "A"
const val NORMAL_TEXT = "Lorem ipsum dolor sit amet."
const val LONG_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vestibulum dui magna."
const val VERY_LONG_TEXT = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas at vestibulum sapien, eu tempus dolor. Quisque pretium orci at dolor maximus mattis. Suspendisse auctor, urna ut vestibulum euismod, enim nisi consectetur dui, at dictum risus neque ultrices mi. Praesent tincidunt tellus quam. Lorem ipsum dolor sit amet, consectetur adipiscing elit."

fun getTestTextByLength(textLength: TextLength, id: Int = 0): String =
        when (textLength) {
            TextLength.EMPTY -> ""
            TextLength.SHORT -> SHORT_TEXT
            TextLength.NORMAL -> "$NORMAL_TEXT - $id"
            TextLength.LONG -> "$LONG_TEXT - $id"
            TextLength.VERY_LONG -> "$VERY_LONG_TEXT - $id"
        }

fun givenSuperHero(
        repository: SuperHeroRepository,
        isAvenger: Boolean = false,
        nameLength: TextLength = TextLength.NORMAL,
        descriptionLength: TextLength = TextLength.NORMAL,
        isAvailable: Boolean = true
): SuperHero {
    val superHero = SuperHero(
            name = getTestTextByLength(textLength = nameLength),
            photo = null,
            isAvenger = isAvenger,
            description = getTestTextByLength(descriptionLength),
            team = null,
            isAvailable = isAvailable
    )

    whenever(repository.getByName(any())).thenReturn(superHero)

    return superHero
}