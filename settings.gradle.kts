apply(from = "../../config/config.gradle.kts")
val nexusUsername: String by extra
val nexusPassword: String by extra
val nexusSnapshotUrl: String by extra
val nexusReleaseUrl: String by extra

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../../config/libs.versions.toml"))
        }
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri(nexusSnapshotUrl)
            credentials {
                username = nexusUsername
                password = nexusPassword
            }
        }
        maven {
            url = uri(nexusReleaseUrl)
            credentials {
                username = nexusUsername
                password = nexusPassword
            }
        }
    }
}

rootProject.name = "module_login"
include(":app")

if (providers.gradleProperty("enable.local.debug").get().toBoolean()) {
    include(":module_login")
}
