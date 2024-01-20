buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")

        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.51")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.gms.google-services") version "4.4.0" apply false
    kotlin("kapt") version "1.9.22"
}