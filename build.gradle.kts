@file:Suppress("UnstableApiUsage")

import java.io.ByteArrayOutputStream
import java.nio.charset.Charset

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

abstract class GitVersionValueSource : ValueSource<String, ValueSourceParameters.None> {
    @get:Inject
    abstract val execOperations: ExecOperations

    override fun obtain(): String {
        val output = ByteArrayOutputStream()
        val error = ByteArrayOutputStream()
        execOperations.exec {
            commandLine("git rev-parse --short HEAD".split(" "))
            standardOutput = output
            errorOutput = error
        }

        return if (error.toByteArray().isNotEmpty()) {
            ""
        } else {
            "-" + String(output.toByteArray(), Charset.defaultCharset()).trim()
        }
    }
}


