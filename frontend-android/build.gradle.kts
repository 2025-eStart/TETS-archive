// Top-level build file where you can add configuration options common to all sub-projects/modules.
// build.gradle.kts (Project level)

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.application) apply false
}
