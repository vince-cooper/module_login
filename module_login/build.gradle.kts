import com.vanniktech.maven.publish.AndroidSingleVariantLibrary

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.kotlin.kapt)
}

kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}

android {
    namespace = "com.wangwh.modules.login"
    compileSdk = libs.versions.compileSdk.get().toInt()
    resourcePrefix = "module_login_"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = libs.versions.jvmTarget.get()
    }
    buildFeatures {
        viewBinding = true
    }
}

apply(from = "../../../config/config.gradle.kts")
val nexusUsername: String by extra
val nexusPassword: String by extra
val nexusSnapshotUrl: String by extra
val nexusReleaseUrl: String by extra
val releasesName: String by extra
val snapshotName: String by extra

publishing {
    repositories {
        maven {
            name = snapshotName
            url = uri(nexusSnapshotUrl)
            credentials {
                username = nexusUsername
                password = nexusPassword
            }
        }
        maven {
            name = releasesName
            url = uri(nexusReleaseUrl)
            credentials {
                username = nexusUsername
                password = nexusPassword
            }
        }
    }
}

mavenPublishing {
    val group = libs.module.login.get().group
    val name = libs.module.login.get().name
    val version = libs.module.login.get().version
    println("\n> Task publish info: $group:$name:$version")

    coordinates(group, name, version)
    configure(
        AndroidSingleVariantLibrary(
            variant = "release",
            sourcesJar = true,
            publishJavadocJar = true,
        )
    )
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    api(libs.common.module)
    kapt(libs.arouter.compiler)
}