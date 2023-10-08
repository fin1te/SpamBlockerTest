@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.utkarsha.spamblocker"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.utkarsha.spamblocker"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    
    // Glide dependency
    implementation("com.github.bumptech.glide:glide:4.16.0")

//    // Tensorflow Lite dependencies
//    implementation("org.tensorflow:tensorflow-lite:2.8.0")
//    implementation("org.tensorflow:tensorflow-lite-support:0.2.0")

    // Import the Task Vision Library dependency (NNAPI is included)
    implementation ("org.tensorflow:tensorflow-lite-task-text:0.4.4")
}