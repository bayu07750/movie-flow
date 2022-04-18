object Deps {

    object Network {
        val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gson}"
        val okhttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
        val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.moshi}"
    }

    object Lifecycle {
        val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object Room {
        val room = "androidx.room:room-ktx:${Versions.room}"
        val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Ui {
        val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        val materialDesign = "com.google.android.material:material:${Versions.material}"
        val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        val supportLegeacy = "androidx.legacy:legacy-support-v4:${Versions.legacySupport}"
        val coil = "io.coil-kt:coil:${Versions.coil}"
    }

    object UnitTest {
        val junit = "junit:junit:${Versions.junit}"
    }

    object InstrumentationTest {
        val extJunit = "androidx.test.ext:junit:${Versions.junitExt}"
        val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    }

    object Injection {
        val hilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
        val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
    }

    object Kotlin {
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object Navigation {
        val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Preference {
        val datastore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    }
}