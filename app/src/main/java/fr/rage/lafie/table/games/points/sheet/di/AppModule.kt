package fr.rage.lafie.table.games.points.sheet.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    includes = [
        DatabaseModule::class,
    ]
)
@ComponentScan("fr.rage.lafie.table.games.points.sheet")
class AppModule