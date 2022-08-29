import com.android.build.gradle.BaseExtension
import com.nazardunets.news_api_interview.configuration.ConfigData

// full path to Versions object is required
plugins {
    id("com.android.application") version com.nazardunets.news_api_interview.configuration.Versions.General.gradleVersion apply false
    id("com.android.library") version com.nazardunets.news_api_interview.configuration.Versions.General.gradleVersion apply false
    kotlin("android") version com.nazardunets.news_api_interview.configuration.Versions.General.kotlinVersion apply false
    kotlin("plugin.serialization") version com.nazardunets.news_api_interview.configuration.Versions.General.kotlinVersion apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects {
    plugins.applyBaseAndroidConfig(this)
}

fun BaseExtension.baseAndroidConfig() {
    compileSdkVersion(ConfigData.compileSdkVersion)

    defaultConfig {
        minSdk = ConfigData.minSdk
        targetSdk = ConfigData.targetSdk
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

fun PluginContainer.applyBaseAndroidConfig(project: Project) {
    whenPluginAdded {
        project.extensions.run {
            when (this@whenPluginAdded) {
                is com.android.build.gradle.AppPlugin -> {
                    getByType<com.android.build.gradle.AppExtension>()
                            .baseAndroidConfig()
                }
                is com.android.build.gradle.LibraryPlugin -> {
                    getByType<com.android.build.gradle.LibraryExtension>()
                            .baseAndroidConfig()
                }
            }
        }
    }
}
