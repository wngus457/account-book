pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Calendar"
include(":app")
include(":shared:core-mvi")
include(":shared:util:kotlin")
include(":shared:local")
include(":shared:util:android")
include(":shared:ui:common")
include(":shared:ui:system")
include(":feature:splash")
include(":shared:navigation")
include(":feature:home")
include(":feature:account")
include(":data:local")
include(":data:remote")
include(":domain")
include(":data:repository")
