package com.juhyeon.calendar.convention

import com.android.build.api.dsl.ApplicationExtension
import com.juhyeon.calendar.convention.config.AppConfig
import com.juhyeon.calendar.convention.config.configureCommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("dagger.hilt.android.plugin")
                apply("com.google.devtools.ksp")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<ApplicationExtension> {
                configureCommonExtension(this)

                defaultConfig {
                    applicationId = "com.juhyeon.calendar"
                    versionCode = AppConfig.versionCode
                    versionName = AppConfig.versionName
                    targetSdk = 34
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables.useSupportLibrary = true
                }

                buildTypes {
                    release {
                        isMinifyEnabled = true
                        isShrinkResources = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                    debug {
//                        isMinifyEnabled = true
//                        isShrinkResources = true
//                        proguardFiles(
//                            getDefaultProguardFile("proguard-android-optimize.txt"),
//                            "proguard-rules.pro"
//                        )
                        enableAndroidTestCoverage = true
                        enableUnitTestCoverage = true
                    }
                }

                buildFeatures {
                    compose = true
                    buildConfig = true
                }

                val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

                composeOptions {
                    kotlinCompilerExtensionVersion = libs.findVersion("compose-compiler").get().requiredVersion
                }

                dependencies {
                    add("implementation", project(":feature:splash"))

                    add("implementation", project(":shared:core-mvi"))
                    add("implementation", project(":shared:domain"))
                    add("implementation", project(":shared:data"))
                    add("implementation", project(":shared:local"))
                    add("implementation", project(":shared:navigation"))
                    add("implementation", project(":shared:ui:common"))
                    add("implementation", project(":shared:ui:system"))
                    add("implementation", project(":shared:util:android"))
                    add("implementation", project(":shared:util:kotlin"))

                    add("implementation", platform(libs.findLibrary("compose-bom").get()))
                    add("implementation", libs.findBundle("compose").get())
                    add("implementation", libs.findLibrary("compose-navigation").get())

                    add("implementation", libs.findBundle("retrofit").get())

                    add("implementation", libs.findLibrary("hilt-android").get())
                    add("ksp", libs.findLibrary("hilt-android-compiler").get())

                    add("implementation", libs.findLibrary("room-runtime").get())
                    add("implementation", libs.findLibrary("room-ktx").get())
                    add("ksp", libs.findLibrary("room-compiler").get())

                    add("implementation", libs.findLibrary("datastore-preferences").get())

                    add("implementation", libs.findLibrary("serialization").get())
                }
            }
        }
    }
}