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
    id("android.app.convention") apply false
    id("android.feature.convention") apply false
    id("android.kotlin.convention") apply false
    id("android.data.convention") apply false
    id("android.library.convention") apply false
    id("android.domain.convention") apply false
}