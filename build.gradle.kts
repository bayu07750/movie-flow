// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Versions.androidPlugin apply false
    id("com.android.library") version Versions.androidPlugin apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("androidx.navigation.safeargs.kotlin") version Versions.navigation apply false
    id("dagger.hilt.android.plugin") version Versions.daggerHilt apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
