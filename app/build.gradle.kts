plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.profile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.profile"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.compose.material:material-icons-extended:1.2.0")
    implementation("androidx.compose.ui:ui:1.2.0")
    implementation("androidx.compose.material:material:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.0")
    implementation("androidx.navigation:navigation-compose:2.4.0-alpha10")
    implementation("com.google.accompanist:accompanist-flowlayout:0.26.3-beta")

    //Maps
    implementation("com.google.maps.android:maps-compose:2.5.3")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.android.libraries.places:places:3.5.0")
    implementation ("com.google.android.gms:play-services-location:21.0.1")


    /*   implementation 'com.google.android.gms:play-services-maps:18.0.2'
    implementation 'com.google.android.libraries.places:places:2.5.0'
    */

    implementation(libs.androidx.benchmark.common)
    val retrofit_version = "2.11.0"

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.play.services.location)
    implementation(libs.play.services.places)
    implementation(libs.play.services.tasks)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    ksp("androidx.room:room-compiler:2.6.1")
}

configurations.all {
    resolutionStrategy {
        force ("com.google.android.gms:play-services-maps:18.1.0")
        force ("com.google.android.gms:play-services-location:21.0.1")
        force ("'com.google.android.libraries.places:places:2.6.0';")
    }
}
