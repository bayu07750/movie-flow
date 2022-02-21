pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            // Use `resolutionStrategy` to define the Hilt plugin and its coordinate
            if (requested.id.id == "dagger.hilt.android.plugin") {
                useModule("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "MovieFlow"
include(":app")
include(":core")
