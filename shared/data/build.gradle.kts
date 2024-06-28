plugins {
    id("android.kotlin.convention")
}

dependencies {
    implementation(projects.shared.domain)
    implementation(projects.shared.util.kotlin)
    implementation(libs.java.inject)
}