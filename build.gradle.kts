buildscript {
    val agp_version by extra("8.0.1")
    val agp_version1 by extra("8.0.1")
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.1" apply false
}