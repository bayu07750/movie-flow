dependencies {

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