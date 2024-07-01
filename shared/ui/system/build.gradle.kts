plugins {
    id("android.library.convention")
}

android {
    namespace = "com.juhyeon.calendar.shared.ui.system"
}

dependencies {
    implementation(projects.shared.coreMvi)
    implementation(projects.shared.ui.common)
    implementation(projects.shared.util.android)

    implementation(libs.compose.navigation)

    implementation(libs.coil.gif)

    implementation(libs.accompanist.permission)

    implementation(libs.bundles.glide)

    implementation(libs.lottie.compose)
}