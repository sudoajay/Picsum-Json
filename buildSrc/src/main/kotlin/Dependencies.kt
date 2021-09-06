class Dependencies {

    object DeSugar {
        const val DeSugar =
            "com.android.tools:desugar_jdk_libs:1.1.1"
    }

    object Kotlin {
        const val kotlin =
            "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Project.kotlin_version}"
        const val kotlinSerialization =
            "org.jetbrains.kotlin.plugin.serialization:${Versions.Project.kotlin_version}"
    }

    object Coroutine {
        const val coroutineCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Dependencies.Coroutine}"
        const val coroutineAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Dependencies.Coroutine}"
        const val coroutineTest =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Dependencies.Coroutine}"
    }

    object Androidx {
        const val AppCompat = "androidx.appcompat:appcompat:${Versions.MainDependencies.AppCompat}"
        const val Material = "com.google.android.material:material:${Versions.MainDependencies.Material}"
        const val ConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.MainDependencies.ConstraintLayout}"
        const val CoreKtx = "androidx.core:core-ktx:${Versions.MainDependencies.CoreKtx}"
        const val ActivityKtx = "androidx.activity:activity-ktx:1.2.0-alpha06"
        const val FragmentKtx = "androidx.fragment:fragment-ktx:1.3.0-alpha06"
    }

    object Compose {
        const val composeUi = "androidx.compose.ui:ui:${Versions.Dependencies.Compose}"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.Dependencies.Compose}"
        const val composeCompiler = "androidx.compose.compiler:compiler:${Versions.Dependencies.Compose}"
        const val composeMaterial = "androidx.compose.material:material:${Versions.Dependencies.Compose}"
        const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.Dependencies.Compose}"
        const val composeAnimation = "androidx.compose.animation:animation:${Versions.Dependencies.Compose}"
        const val composeLivedata = "androidx.compose.runtime:runtime-livedata:${Versions.Dependencies.Compose}"
    }


    object Navigation {
        const val navCommonKtx =
            "androidx.navigation:navigation-common-ktx:${Versions.Dependencies.Navigation}"
        const val navRuntimeKtx =
            "androidx.navigation:navigation-runtime-ktx:${Versions.Dependencies.Navigation}"
        const val navFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.Dependencies.Navigation}"
        const val navUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Versions.Dependencies.Navigation}"
        const val navDfm =
            "androidx.navigation:navigation-dynamic-features-fragment:${Versions.Dependencies.Navigation}"
    }


    object Lifecycle {
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime:${Versions.Dependencies.Lifecycle}"
        const val lifecycleCompiler =
            "androidx.lifecycle:lifecycle-compiler:${Versions.Dependencies.Lifecycle}"
        const val viewmodelKtx =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Dependencies.Lifecycle}"
        const val livedataKtx =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Dependencies.Lifecycle}"
    }
    

    object DependencyInjection {
        const val dagger = "com.google.dagger:dagger:${Versions.Dependencies.Dagger}"
        const val daggerCompiler =
            "com.google.dagger:dagger-compiler:${Versions.Dependencies.Dagger}"

        const val  daggerHilt = "com.google.dagger:hilt-android:${Versions.Dependencies.Dagger_Hilt}"
        const val daggerHiltCompiler =
            "com.google.dagger:hilt-android-compiler:${Versions.Dependencies.Dagger_Hilt}"

        const val daggerHiltTesting =
            "com.google.dagger:hilt-android-testing:${Versions.Dependencies.Dagger_Hilt}"
        const val androidHilt = "androidx.hilt:hilt-common:${Versions.Dependencies.Android_Hilt}"
        const val androidHiltViewModel =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.Dependencies.Android_Hilt}"
        const val androidHiltCompiler =
            "androidx.hilt:hilt-compiler:${Versions.Dependencies.Android_Hilt}"
    }

    object WebKit{
        const val webKit = "androidx.webkit:webkit:${Versions.Dependencies.WebKit}"
    }

    object SwipeRefreshLayout{
        const val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.Dependencies.SwipeRefreshLayout}"
    }
    object Network {
//        Retrofit
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Dependencies.Retrofit}"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.Dependencies.Retrofit}"
        const val retrofitConverterJackson = "com.squareup.retrofit2:converter-jackson:${Versions.Dependencies.Retrofit}"
        const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.Dependencies.Retrofit}"

//    Ok HTTP
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Dependencies.Okhttp}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.Dependencies.Okhttp}"
        const val mockWebServer =
            "com.squareup.okhttp3:mockwebserver:${Versions.Dependencies.Okhttp}"

// Google gson
        const val googleGson = "com.google.code.gson:gson:${Versions.Dependencies.GoogleGson}"

//  Jackson json
        const val jacksonCore = "com.fasterxml.jackson.core:jackson-core:${Versions.Dependencies.JacksonJson}"
        const val jacksonAnnotation = "com.fasterxml.jackson.core:jackson-annotations:${Versions.Dependencies.JacksonJson}"
        const val jacksonDataBind = "com.fasterxml.jackson.core:jackson-databind:${Versions.Dependencies.JacksonJson}"


        const val retrofitConverter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"


        const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1"


    }

    object Storage {
        const val RoomRuntime = "androidx.room:room-runtime:${Versions.Dependencies.Room}"
        const val RoomCompiler = "androidx.room:room-compiler:${Versions.Dependencies.Room}"
        const val RoomKtx = "androidx.room:room-ktx:${Versions.Dependencies.Room}"
        const val RoomPaging3 = "androidx.room:room-paging:${Versions.Dependencies.RoomPaging3}"
        const val paging ="androidx.paging:paging-runtime:${Versions.Dependencies.Paging}"
        const val firebaseDatabase = "com.google.firebase:firebase-database-ktx:${Versions.Dependencies.FirebaseDatabase}"

        const val preferenceDataStore = "androidx.datastore:datastore-preferences:${Versions.Dependencies.PreferencesDataStore}"
        const val protoDataStore = "androidx.datastore:datastore-core:${Versions.Dependencies.ProtoDataStore}"
        const val protobuf = "com.google.protobuf:protobuf-javalite:${Versions.Dependencies.Protobuf}"

    }



    object Test {
        const val junit = "junit:junit:4.13.2"
        const val mockk = "io.mockk:mockk:1.10.6"
        const val truth = "com.google.truth:truth:1.1.2"
        const val archCore = "androidx.arch.core:core-testing:2.1.0"
    }

    object AndroidTest {

        const val testCore = "androidx.test:core:1.3.0"
        const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
        const val extJunitKtx = "androidx.test.ext:junit-ktx:1.1.2"
        const val extTruth = "androidx.test.ext:truth:1.3.0"
        const val banchmark = "androidx.benchmark:benchmark-junit4:1.0.0"
    }

    object Lint {
        const val ktlint = "com.pinterest:ktlint:0.40.0"
    }
}