package pl.kaemo.recipefinder.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.kaemo.recipefinder.data.service.RecipeServiceImpl
import pl.kaemo.recipefinder.data.spoonacularApi.QuotaLeftInterceptor
import pl.kaemo.recipefinder.data.spoonacularApi.RetrofitHelper
import pl.kaemo.recipefinder.domain.RecipeService

@Module
@InstallIn(SingletonComponent::class) //singletoncomponent - unikalne instancje dla całej aplikacji
object AppModule {

    @Provides
    fun provideQuotaInterceptor(@ApplicationContext context: Context): QuotaLeftInterceptor {
        return QuotaLeftInterceptor(context)
    }

    @Provides
    fun provideretrofitHelper(quotaLeftInterceptor: QuotaLeftInterceptor): RetrofitHelper {
        return RetrofitHelper(quotaLeftInterceptor)
    } //zadziała również bez tego - RetrofitHelper ma construktor oznaczony @Inject

    @Provides
    fun provideRecipeService(retrofitHelper: RetrofitHelper): RecipeService {
        return RecipeServiceImpl(retrofitHelper.getInstance())
//        return FakeRecipeService() //FakeDataBase
    }
}