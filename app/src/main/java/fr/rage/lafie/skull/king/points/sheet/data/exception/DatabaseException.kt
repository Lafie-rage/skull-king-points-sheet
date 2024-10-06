package fr.rage.lafie.skull.king.points.sheet.data.exception

abstract class DatabaseException(
    override val message: String?
) : RuntimeException()