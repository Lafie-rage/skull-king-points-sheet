package fr.rage.lafie.table.games.points.sheet.data.exception

import java.util.UUID
import kotlin.reflect.KClass

class EntityNotFoundById(
    entityClass: KClass<out Any>,
    id: UUID,
) : EntityNotFoundByField(
    entityName = entityClass,
    searchField = "id",
    value = id,
)