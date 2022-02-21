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

//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles = "consumer-rules.pro"

        buildConfigField("String", "TMDB_API_KEY", getApiKey())
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
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // lifecycle
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")

    // room
    implementation("androidx.room:room-ktx:2.4.1")
    api("androidx.room:room-runtime:2.4.1")
    kapt("androidx.room:room-compiler:2.4.1")

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // dagger-hilt
    implementation("com.google.dagger:hilt-android:2.38.1")
    kapt("com.google.dagger:hilt-android-compiler:2.38.1")

    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")

    // navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.0-alpha01")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.0-alpha01")

    // coil
    implementation("io.coil-kt:coil:1.4.0")

    // datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}

fun getApiKey(): String {
    val items = HashMap<String, String>()

    val fl = rootProject.file("app/local.properties")

    (fl.exists())?.let {
        fl.forEachLine {
            items[it.split("=")[0]] = it.split("=")[1]
        }
    }

    return items["TMDB_API_KEY"]!!
}