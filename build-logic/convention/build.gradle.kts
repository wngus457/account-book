plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.agp)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp)
}

gradlePlugin {
    plugins {
        register("AndroidApplicationPlugin") {
            id = "android.app.convention"
            implementationClass = "com.juhyeon.calendar.convention.AndroidApplicationConventionPlugin"
        }
        register("FeaturePlugin") {
            id = "android.feature.convention"
            implementationClass = "com.juhyeon.calendar.convention.FeatureConventionPlugin"
        }
        register("KotlinPlugin") {
            id = "android.kotlin.convention"
            implementationClass = "com.juhyeon.calendar.convention.KotlinConventionPlugin"
        }
        register("DataPlugin") {
            id = "android.data.convention"
            implementationClass = "com.juhyeon.calendar.convention.DataConventionPlugin"
        }
        register("SharedLibraryPlugin") {
            id = "android.library.convention"
            implementationClass = "com.juhyeon.calendar.convention.SharedLibraryConventionPlugin"
        }
        register("DomainPlugin") {
            id = "android.domain.convention"
            implementationClass = "com.juhyeon.calendar.convention.DomainConventionPlugin"
        }
    }
}