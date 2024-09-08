package fr.rage.lafie.table.games.points.sheet.data.exception

import kotlin.reflect.KClass

open class EntityNotFoundByField(
    entityName: KClass<out Any>,
    searchField: String,
    value: Any,
) : DatabaseException(
    message = "No entity `${entityName.simpleName}` found with $searchField `$value`"
)