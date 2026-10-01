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
        maven {
            url = uri("https://maven.pkg.github.com/wngus457/DesignSystem")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Calendar"
include(":app")
include(":shared:core-mvi")
include(":shared:util:kotlin")
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
