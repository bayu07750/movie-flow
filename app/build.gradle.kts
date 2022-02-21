plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.bayu.movieflow"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    implementation(project(":core"))
    implementation(Deps.Lifecycle.lifecycleRuntime)

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