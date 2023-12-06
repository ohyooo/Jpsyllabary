import org.jetbrains.kotlin.gradle.tasks.DummyFrameworkTask

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
}

group = "com.ohyooo"
version = "1.0.0"

kotlin {
    androidTarget()
    jvm("desktop") {
        jvmToolchain(17)
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.ui)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.material3)
                api(compose.materialIconsExtended)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.androidx.core.ktx)
                api(libs.startup.runtime)
            }
        }
        val desktopMain by getting {
            dependencies {
                api(compose.preview)
            }
        }
        val desktopTest by getting
    }
}

android {
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    // https://github.com/icerockdev/moko-resources/issues/353#issuecomment-1179713713
    sourceSets.getByName("main").res.srcDir(File(layout.buildDirectory.get().asFile, "generated/moko/androidMain/res"))
    namespace = "com.ohyooo.jpsyllabary.common"
    compileSdk = libs.versions.compile.sdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.min.sdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    # composeOptions {
    #     kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    # }
    # compose {
    #     kotlinCompilerPlugin.set(libs.compose.compiler.get().toString())
    # }
}

// TODO move to gradle plugin
tasks.withType<DummyFrameworkTask>().configureEach {
    @Suppress("ObjectLiteralToLambda")
    doLast(object : Action<Task> {
        override fun execute(task: Task) {
            task as DummyFrameworkTask

            val frameworkDir = File(task.outputFramework.get().asFile, task.frameworkName.get() + ".framework")

            listOf(
                "compose-resources-gallery:shared.bundle"
            ).forEach { bundleName ->
                val bundleDir = File(frameworkDir, bundleName)
                bundleDir.mkdir()
                File(bundleDir, "dummyFile").writeText("dummy")
            }
        }
    })
}
