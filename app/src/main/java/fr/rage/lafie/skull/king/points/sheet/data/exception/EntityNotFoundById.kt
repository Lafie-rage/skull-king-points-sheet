package fr.rage.lafie.skull.king.points.sheet.data.exception

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