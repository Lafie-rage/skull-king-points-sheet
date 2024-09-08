package fr.rage.lafie.table.games.points.sheet.data.exception

abstract class DatabaseException(
    override val message: String?
) : RuntimeException()