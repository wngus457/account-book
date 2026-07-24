plugins {
    id("android.data.convention")
}

android {
    namespace = "com.juhyeon.calendar.data.remote"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.data.repository)
    implementation(projects.shared.util.android)
    implementation(projects.shared.util.kotlin)

    implementation(libs.bundles.retrofit)
}