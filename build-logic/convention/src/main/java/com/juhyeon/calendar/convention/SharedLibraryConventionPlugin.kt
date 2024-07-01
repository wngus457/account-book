package com.juhyeon.calendar.convention

import com.android.build.api.dsl.LibraryExtension
import com.juhyeon.calendar.convention.config.configureCommonExtension
import com.juhyeon.calendar.convention.config.debug
import com.juhyeon.calendar.convention.config.kotlin
import com.juhyeon.calendar.convention.config.release
import com.juhyeon.calendar.convention.config.sourceSets
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class SharedLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("kotlin-android")
                apply("com.google.devtools.ksp")
                apply("kotlin-parcelize")
            }
            extensions.configure<LibraryExtension> {
                configureCommonExtension(this)
                buildTypes {
                    release {
                        isMinifyEnabled = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                    debug {
//                        isMinifyEnabled = true
//                        proguardFiles(
//                            getDefaultProguardFile("proguard-android-optimize.txt"),
//                            "proguard-rules.pro"
//                        )
                    }
                }

                buildFeatures {
                    compose = true
                }

                kotlin {
                    sourceSets {
                        debug {
                            kotlin.srcDir("build/generated/ksp/debug/kotlin")
                        }
                        release {
                            kotlin.srcDir("build/generated/ksp/release/kotlin")
                        }
                    }
                }

                val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

                composeOptions {
                    kotlinCompilerExtensionVersion = libs.findVersion("compose-compiler").get().requiredVersion
                }

                dependencies {
                    add("implementation", project(":shared:domain"))
                    add("implementation", project(":shared:util:kotlin"))

                    add("implementation", platform(libs.findLibrary("compose-bom").get()))
                    add("implementation", libs.findBundle("compose").get())

                    add("implementation", libs.findLibrary("coil-core").get())
                    add("implementation", libs.findLibrary("coil-compose").get())

                    add("implementation", libs.findLibrary("hilt-android").get())
                    add("ksp", libs.findLibrary("hilt-android-compiler").get())
                }
            }
        }
    }
}