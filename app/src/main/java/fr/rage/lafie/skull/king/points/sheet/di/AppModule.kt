package fr.rage.lafie.skull.king.points.sheet.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module(
    includes = [
        DatabaseModule::class,
    ]
)
@ComponentScan("fr.rage.lafie.skull.king.points.sheet")
class AppModule