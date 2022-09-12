package pl.kaemo.recipefinder.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.kaemo.recipefinder.data.service.FakeRecipeService
import pl.kaemo.recipefinder.data.service.RecipeServiceImpl
import pl.kaemo.recipefinder.data.spoonacularApi.QuotaLeftInterceptor
import pl.kaemo.recipefinder.data.spoonacularApi.RetrofitHelper
import pl.kaemo.recipefinder.domain.RecipeService
import pl.kaemo.recipefinder.domain.utils.AppContextStringRepository
import pl.kaemo.recipefinder.domain.utils.StringRepository

@Module
@InstallIn(SingletonComponent::class) //singletoncomponent - unikalne instancje dla całej aplikacji
object AppModule {

    /* ------------ Chapter 1 ------------ */

    /* ------ Provider 1 ------ */
//    @Provides
//    @Singleton // doczytać
//    fun provideQuotaInterceptor(@ApplicationContext context: Context): QuotaLeftInterceptor {
//        return QuotaLeftInterceptor(context)
//    }

    /* ------ Provider 2 ------ */
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    /* ------ Provider 3 ------ */
//    @Provides
//    fun provideretrofitHelper(quotaLeftInterceptor: QuotaLeftInterceptor): RetrofitHelper {
//        return RetrofitHelper(quotaLeftInterceptor)
//    }
    /*
    Provider 3 jest zbędny - RetrofitHelper ma konstruktor oznaczony poprzez @Inject
    więc i tak i tak znajduje się w środowisku zarządzanym przez Hilta
    i nie potrzeba go jeszcze raz definiować w tym miejscu
    */

    /* ------ Provider 4 ------ */
    @Provides
    fun provideRecipeService(retrofitHelper: RetrofitHelper): RecipeService {
        return RecipeServiceImpl(retrofitHelper.getInstance())
//        return FakeRecipeService() /* FakeDataBase */
    }

    /* INFO - Damian, popraw jak coś się nie zgadza pls :)

    # Do poprawnego działania wystarczy:
        1) Provider 1 oraz Provider 4
        2) Provider 2 oraz Provider 4

    # Dlaczego?:
        W obu przypadkach:
            Provider 3 jest zbędny - wytłumaczone w komentarzu pod providerem
            Provider 4 jest niezbędny - odwołujemy się do metod nadpisanych (getInstance()) - która nie znajduje się w środowisku Hilta. Poza tym określamy tutaj z jakiej bazy danych korzystamy.
            (Dodatkowe info: Provider 4 wymaga RetrofitHelper'a - i to trzeba mu zapewnić)
        W 1) przypadku:
            Provider 2 jest zbędny - context został już dostarczony w Providerze 1 wraz z instancją QuotaLeftInterceptor
            Provider 1 tworzy/zwraca/wstrzykuje gotowy QuotaLeftInterceptor (który jest potrzebny do stworzenia RetrofitHelper'a)
            RetrofitHelper jest już w środowisku Hilta za sprawą "@Inject" przed kontruktorem - więc zostanie automatycznie stworzony i zapewniony Providerowi nr 4
        W 2) przypadku:
            Provider 1 jest zbędny - bo w środowisku Hilta znajdują się już wszystkie niezbędne elementy (oznaczone poprzez @Inject)
            Do QuotaLeftInterceptor'a trafia context (zapewniony przez Provider 2) - więc gotowy QuotaLeftInterceptor istnieje już w środowisku Hilta
            Do RetrofitHelper'a trafia QuotaLeftInterceptor (stworzony wyżej) - gotowy Retrofithelper trafia potem do Prvidera 4

    # Podsumowanie:
        Niezbędne:
            - Provider 1 lub Provider 2
            - Provider 4
        Zdbędne:
            - Provider 3

     */

    /* ------------ Chapter 2 ------------ */

    @Provides
    fun provideStringRepository(stringRepository: AppContextStringRepository): StringRepository {
        return stringRepository
    }

}