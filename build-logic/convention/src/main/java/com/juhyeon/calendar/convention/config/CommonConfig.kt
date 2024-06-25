package com.juhyeon.calendar.convention.config

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.plugins.JavaPluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

internal fun Project.configureCommonExtension(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    commonExtension.apply {
        compileSdk = 34

        defaultConfig {
            minSdk = 28
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }
}

fun CommonExtension<*, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

fun Project.java(block: JavaPluginExtension.() -> Unit) {
    (this as ExtensionAware).extensions.configure("java", block)
}

fun Project.kotlin(block: KotlinAndroidProjectExtension.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlin", block)
}

fun KotlinAndroidProjectExtension.sourceSets(block: NamedDomainObjectContainer<KotlinSourceSet>.() -> Unit) {
    block(sourceSets)
}

fun NamedDomainObjectContainer<KotlinSourceSet>.debug(block: KotlinSourceSet.() -> Unit) {
    getByName("debug", block)
}

fun NamedDomainObjectContainer<KotlinSourceSet>.release(block: KotlinSourceSet.() -> Unit) {
    getByName("release", block)
}