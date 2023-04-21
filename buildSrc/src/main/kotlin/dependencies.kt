object Ext {
    const val applicationId = "com.ohyooo.jpsyllabary"
    const val minSdk = 21
    const val compileSdk = 33
    const val buildToolsVersion = "33.0.2"
    const val targetSdk = 33
    const val versionCode = 20
    const val versionName = "3.1"
}

object Libs {
    val updateList = arrayListOf<String>()
    val implementList = arrayListOf<String>()
    val debugImplementList = arrayListOf<String>()

    object Plugin {
        val AGP = "com.android.tools.build:gradle:8.0.0".regUpdate()
        val KGP = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20".regUpdate()

        val list = arrayOf(AGP, KGP)
    }

    object Kotlin {
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4".regLib()
    }

    object Compose {
        const val composeVersion = "1.4.2"
        const val compilerVersion = "1.4.6"
        val compiler = "androidx.compose.compiler:compiler:$compilerVersion".regLib()
        val animation = "androidx.compose.animation:animation:$composeVersion".regLib()
        val foundation = "androidx.compose.foundation:foundation:$composeVersion".regLib()
        val material = "androidx.compose.material3:material3:1.0.1".regLib()
        val materialIconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion".regLib()
        val runtime = "androidx.compose.runtime:runtime:$composeVersion".regLib()
        val tooling = "androidx.compose.ui:ui-tooling:$composeVersion".regUpdate().regDebug()
        val preview = "androidx.compose.ui:ui-tooling-preview:$composeVersion".regLib()
        val ui = "androidx.compose.ui:ui:$composeVersion".regLib()
        val navigation = "androidx.navigation:navigation-compose:2.5.3".regLib()
    }

    object AndroidX {
        val coreKtx = "androidx.core:core-ktx:1.10.0".regLib()
        val fragmentKtx = "androidx.fragment:fragment-ktx:1.5.7".regLib()
        val compose = "androidx.activity:activity-compose:1.7.1".regLib()
        val profileinstaller = "androidx.profileinstaller:profileinstaller:1.3.0".regLib()
    }

    object Google {
        val accompanistVersion = "0.30.1"
        val pager = "com.google.accompanist:accompanist-pager:$accompanistVersion".regLib()
        val indicators = "com.google.accompanist:accompanist-pager-indicators:$accompanistVersion".regLib()
    }

    object Test {
        val junit = "androidx.test.ext:junit:1.1.5".regUpdate()
        val espresso = "androidx.test.espresso:espresso-core:3.5.1".regUpdate()
        val uiautomator = "androidx.test.uiautomator:uiautomator:2.2.0".regUpdate()
        val macro = "androidx.benchmark:benchmark-macro-junit4:1.2.0-alpha06".regUpdate()

        val list = arrayOf(junit, espresso, uiautomator, macro)
    }

    init {
        Plugin
        Kotlin
        Compose
        AndroidX
        Google
        Test
    }

    private fun String.regLib() = this.also {
        implementList.add(it)
        updateList.add(it)
    }
    fun String.regDebug() = this.also {
        debugImplementList.add(it)
        updateList.add(it)
    }
    fun String.regUpdate() = this.also(updateList::add)
}
