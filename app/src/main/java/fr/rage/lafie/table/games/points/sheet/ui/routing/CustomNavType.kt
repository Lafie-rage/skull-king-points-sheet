package fr.rage.lafie.table.games.points.sheet.ui.routing

import android.os.Bundle
import androidx.navigation.NavType
import java.util.UUID

val UUIDType = object : NavType<UUID>(
    isNullableAllowed = false,
) {
    override fun get(bundle: Bundle, key: String): UUID? = bundle.getString(key)?.let {
        UUID.fromString(it)
    }

    override fun parseValue(value: String): UUID = UUID.fromString(value)

    override fun put(bundle: Bundle, key: String, value: UUID) {
        bundle.putString(key, value.toString())
    }

}