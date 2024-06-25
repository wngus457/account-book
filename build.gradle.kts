buildscript {
    dependencies {
        classpath(libs.hilt.plugin)
        classpath(libs.agp)
        classpath(libs.ksp)
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.serialization)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.serialization) apply false
}