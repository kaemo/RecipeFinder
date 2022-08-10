package pl.kaemo.recipefinder.data.DI

import SpoonacularAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.kaemo.recipefinder.common.Constants
import pl.kaemo.recipefinder.data.Repository.RecipeByIngredientsImpl
import pl.kaemo.recipefinder.domain.Repository.RecipeByIngredientsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
    @Singleton
    fun provideSpoonacularAPI(): SpoonacularAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpoonacularAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeByIngridientsRepository(api: SpoonacularAPI): RecipeByIngredientsRepository {
        return RecipeByIngredientsImpl(api)
    }
}