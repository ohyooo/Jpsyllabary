group = "com.ohyooo"
version = "1.0.0"

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).configureEach {
        kotlinOptions {
            freeCompilerArgs += listOf(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:suppressKotlinVersionCompatibilityCheck=true",
            )
        }
    }
}

buildscript {
    repositories {
        mavenLocal()
    }
}

plugins {
    alias(libs.plugins.kmm) apply false
    alias(libs.plugins.agp) apply false
    alias(libs.plugins.alp) apply false
    alias(libs.plugins.kgp) apply false
    alias(libs.plugins.jc) apply false
    alias(libs.plugins.ks) apply false
}


