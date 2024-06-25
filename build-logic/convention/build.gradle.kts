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

    }
}