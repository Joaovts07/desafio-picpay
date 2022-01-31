
object BuildPlugins {
    val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
}

object Libraries {

    //core
    private const val kotlin =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    private const val coreKtx =
        "androidx.core:core-ktx:${Versions.core_ktx_version}"

    private const val appCompat =  "androidx.appcompat:appcompat:${Versions.appCompat}"

    //lifecycle
    private const val lifeCycleViewModel =
         "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    private const val lifeCycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    private const val lifeCycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"


    //coroutines

    private const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"

    private const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"


    private const val materialDesign = "com.google.android.material:material:${Versions.material}"
    private const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    private const val cicleImage = "de.hdodenhof:circleimageview:${Versions.circleImage}"

    private const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    val glideKapt = "com.github.bumptech.glide:compiler:${Versions.glide}"


    private const val junit = "junit:junit:${Versions.jUnit}"

    val androidCore = listOf(
        kotlin,
        coreKtx,
        coroutinesCore,
        coroutinesAndroid,
        lifeCycleLiveData,
        lifeCycleRuntime,
        lifeCycleViewModel
    )
    val ui = listOf(
        appCompat,
        materialDesign,
        constraintLayout,
        cicleImage,
        glide
    )

    //retrofit
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    private const val gsonConverter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    private const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    private const val okhttp3Interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    private const val mockWebServer =
        "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"

    val http = listOf(
        retrofit,
        gsonConverter,
        okhttp3,
        okhttp3Interceptor,
        mockWebServer)

    //navigation
    private const val navigationKtx =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    private const val navigationUi =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val navigation = listOf(navigationKtx, navigationUi)

    const val androidxSupport ="androidx.legacy:legacy-support-v4:${Versions.support}"

    //room
    private const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    private const val roomKTX = "androidx.room:room-ktx:${Versions.room}"
    private const val roomAnnotation = "androidx.room:room-compiler:${Versions.room}"
    val room = listOf(roomRuntime, roomKTX)


    //test
    private const val mockk = "io.mockk:mockk:${Versions.mockk}"
    private const val coroutineTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
    val test = listOf(mockk, coroutineTest)


}