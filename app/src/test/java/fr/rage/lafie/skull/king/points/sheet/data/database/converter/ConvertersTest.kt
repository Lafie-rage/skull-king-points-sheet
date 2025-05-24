package fr.rage.lafie.skull.king.points.sheet.data.database.converter

import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test
import java.util.UUID

class ConvertersTest {

    @Test
    fun fromUUID_givenCorrectUUID_ok() {
        // Given
        val value = UUID.randomUUID()

        // When
        val result = Converters().fromUUID(value)

        // Then
        assertEquals(value.toString(), result)
    }
    @Test
    fun fromUUID_givenNull_ok() {
        // Given
        val value = null

        // When
        val result = Converters().fromUUID(value)

        // Then
        assertNull(result)
    }

    @Test
    fun toUUID_givenCorrectUUID_ok() {
        // Given
        val value = UUID.randomUUID()

        // When
        val result = Converters().toUUID(value.toString())

        // Then
        assertEquals(value, result)
    }

    @Test
    fun toUUID_givenNull_ok() {
        // Given
        val value = null

        // When
        val result = Converters().toUUID(value)

        // Then
        assertNull(result)
    }

}