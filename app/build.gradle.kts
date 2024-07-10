plugins {
    alias(notation = libs.plugins.android.application)
    alias(notation = libs.plugins.jetbrains.kotlin.android)
    alias(notation = libs.plugins.kotlin.serialization)
    alias(notation = libs.plugins.ksp)
    alias(notation = libs.plugins.safe.args)
}

android {
    namespace = "cifor.icraf.flashcardsxml"
    compileSdk = 34

    defaultConfig {
        applicationId = "cifor.icraf.flashcardsxml"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                files = arrayOf(
                    getDefaultProguardFile(name = "proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(dependencyNotation = libs.androidx.core.ktx)
    implementation(dependencyNotation = libs.androidx.appcompat)
    implementation(dependencyNotation = libs.material)
    implementation(dependencyNotation = libs.androidx.activity)
    implementation(dependencyNotation = libs.androidx.constraintlayout)
    implementation(dependencyNotation = libs.livedata)

    implementation(dependencyNotation = libs.bundles.koin)

    implementation(dependencyNotation = libs.bundles.async)

    implementation(dependencyNotation = libs.room.runtime)
    implementation(dependencyNotation = libs.room.ktx)
    ksp(dependencyNotation = libs.room.compiler)

    implementation(dependencyNotation = libs.bundles.navigation)

    testImplementation(dependencyNotation = libs.junit)

    testImplementation(dependencyNotation = libs.test.coroutines)
    testImplementation(dependencyNotation = libs.test.mockK)

    debugImplementation(dependencyNotation = libs.leakcanary.android)

    androidTestImplementation(dependencyNotation = libs.androidx.junit)
    androidTestImplementation(dependencyNotation = libs.androidx.espresso.core)
    androidTestImplementation(dependencyNotation = libs.androidx.test.runner)
    androidTestImplementation(dependencyNotation = libs.androidx.test.rules)
    implementation(kotlin("script-runtime"))

}