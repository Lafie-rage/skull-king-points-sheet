package fr.rage.lafie.skull.king.points.sheet.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.rage.lafie.skull.king.points.sheet.data.repository.GameRepository
import fr.rage.lafie.skull.king.points.sheet.data.repository.MatchRepository
import fr.rage.lafie.skull.king.points.sheet.data.repository.PlayerPointsRepository
import fr.rage.lafie.skull.king.points.sheet.data.repository.PlayerRepository
import fr.rage.lafie.skull.king.points.sheet.data.repository.impl.GameRepositoryImpl
import fr.rage.lafie.skull.king.points.sheet.data.repository.impl.MatchRepositoryImpl
import fr.rage.lafie.skull.king.points.sheet.data.repository.impl.PlayerPointsRepositoryImpl
import fr.rage.lafie.skull.king.points.sheet.data.repository.impl.PlayerRepositoryImpl

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGameRepository(gameRepositoryImpl: GameRepositoryImpl): GameRepository

    @Binds
    abstract fun bindMatchRepository(matchRepositoryImpl: MatchRepositoryImpl): MatchRepository

    @Binds
    abstract fun bindPlayerRepository(playerRepositoryImpl: PlayerRepositoryImpl): PlayerRepository

    @Binds
    abstract fun bindPlayerPointsRepository(playerPointsRepositoryImpl: PlayerPointsRepositoryImpl): PlayerPointsRepository


}