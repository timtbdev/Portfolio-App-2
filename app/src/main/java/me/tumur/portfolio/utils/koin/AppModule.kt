package me.tumur.portfolio.utils.koin

import android.content.Context
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import me.tumur.portfolio.repository.database.AppDatabase
import me.tumur.portfolio.repository.network.RestApi
import me.tumur.portfolio.repository.repo.Repository
import me.tumur.portfolio.repository.repo.RepositoryImp
import me.tumur.portfolio.utils.constants.DbConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/** 1.CONSTANTS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/** HTTP CONSTANTS ---------------------------------------------------------------------------------------------- */
object Http {
    const val URL = "URL"
    const val CONNECT = "CONNECT"
    const val READ = "READ"
    const val WRITE = "WRITE"
}

/** 2.MODULES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/** DATABASE MODULES ------------------------------------------------------------------------------------------------ */
val databaseModule = module {

    /** Database */
    single { createAppDatabase(androidApplication()) }

    /** Welcome DAO */
    factory { get<AppDatabase>().welcomeDao }

    /** Profile DAO */
    factory { get<AppDatabase>().profileDao }

    /** Social DAO */
    factory { get<AppDatabase>().socialDao }

    /** About DAO */
    factory { get<AppDatabase>().aboutDao }

    /** App DAO */
    factory { get<AppDatabase>().appDao }

    /** Portfolio DAO */
    factory { get<AppDatabase>().portfolioDao }

    /** Experience DAO */
    factory { get<AppDatabase>().experienceDao }

    /** Task DAO */
    factory { get<AppDatabase>().taskDao }

    /** Button DAO */
    factory { get<AppDatabase>().buttonDao }

    /** Category DAO */
    factory { get<AppDatabase>().categoryDao }

    /** ScreenShot DAO */
    factory { get<AppDatabase>().screenShotDao }

    /** Favorite DAO */
    factory { get<AppDatabase>().favoriteDao }

    /** Location DAO */
    factory { get<AppDatabase>().locationDao }

    /** Resource DAO */
    factory { get<AppDatabase>().resourceDao }
}

/** NETWORK MODULES ------------------------------------------------------------------------------------------------- */
val networkModule = module {

    /** WEB SERVICE - REST API */
    single {
        createWebService<RestApi>(
            getProperty(Http.URL),
            getProperty(Http.CONNECT),
            getProperty(Http.READ),
            getProperty(Http.WRITE)
        )
    }

    /**
     * RepositoryImp
     * Single source of truth
     * */
    single<Repository> { RepositoryImp() }
}

/** 3.KOIN APP MODULES * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

val appModule = listOf(databaseModule, networkModule)


/** 4.FUNCTIONS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/** WEB SERVICE & REST API ------------------------------------------------------------------------------------------ */
inline fun <reified T> createWebService(serverUrl: String, connect: Long, read: Long, write: Long): T {
    val retrofit = provideRetrofit(serverUrl, connect, read, write)
    return retrofit.create(T::class.java)
}

/** RETROFIT -------------------------------------------------------------------------------------------------------- */
fun provideRetrofit(serverUrl: String, connect: Long, read: Long, write: Long): Retrofit {
    return Retrofit.Builder()
        .baseUrl(serverUrl)
        .client(provideOkHttpClient(provideLoggingInterceptor(), connect, read, write))
        .addConverterFactory(GsonConverterFactory.create())
        //.addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

/** OKHTTP ---------------------------------------------------------------------------------------------------------- */
fun provideOkHttpClient(interceptor: HttpLoggingInterceptor, connect: Long, read: Long, write: Long): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.connectTimeout(connect, TimeUnit.SECONDS)
    client.readTimeout(read, TimeUnit.SECONDS)
    client.writeTimeout(write, TimeUnit.SECONDS)
    client.addInterceptor(interceptor)
    client.addNetworkInterceptor(StethoInterceptor())
    return client.build()
}

/** OKHTTP LOGGING -------------------------------------------------------------------------------------------------- */
fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

///** MOSHI ----------------------------------------------------------------------------------------------------------- */
//fun provideMoshiBuilder(): Moshi {
//    return Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
//        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
//        .build()
//}

/** DATABASE -------------------------------------------------------------------------------------------------------- */
internal fun createAppDatabase(context: Context): AppDatabase {

    return Room.databaseBuilder(context, AppDatabase::class.java, DbConstants.DATABASE_NAME)
        //Migration is not provided. So database will be cleared on upgrade
        .createFromAsset(DbConstants.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .enableMultiInstanceInvalidation()
        .build()
}