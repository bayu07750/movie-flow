import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val prop = Properties().apply {
            load(FileInputStream(File(rootProject.rootDir, "local.properties")))
        }

        buildConfigField("String", "TMDB_API_KEY", prop.getProperty("TMDB_API_KEY"))
        buildConfigField("String", "TMDB_BASE_URL", "\"https://api.themoviedb.org/3/\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // retrofit
    implementation(Deps.Network.retrofit)
    implementation(Deps.Network.gsonConverter)
    implementation(Deps.Network.okhttp)

    // lifecycle
    api(Deps.Lifecycle.lifecycleViewModel)
    api(Deps.Lifecycle.lifecycleLiveData)

    // room
    implementation(Deps.Room.room)
    api(Deps.Room.roomRuntime)
    kapt(Deps.Room.roomCompiler)

    implementation(Deps.Ui.coreKtx)
    implementation(Deps.Ui.appCompat)
    implementation(Deps.Ui.materialDesign)
    implementation(Deps.Ui.constraintLayout)
    implementation(Deps.Ui.supportLegeacy)
    testImplementation(Deps.UnitTest.junit)
    androidTestImplementation(Deps.InstrumentationTest.extJunit)
    androidTestImplementation(Deps.InstrumentationTest.espresso)

    // dagger-hilt
    implementation(Deps.Injection.hilt)
    kapt(Deps.Injection.hiltCompiler)

    // coroutines
    implementation(Deps.Kotlin.coroutines)

    // navigation
    implementation(Deps.Navigation.fragment)
    implementation(Deps.Navigation.ui)

    // coil
    implementation(Deps.Ui.coil)

    // datastore
    implementation(Deps.Preference.datastore)
}
