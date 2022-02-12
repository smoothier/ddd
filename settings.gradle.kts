rootProject.name = "ddd"

enableFeaturePreview("VERSION_CATALOGS")
// == Define locations for components ==
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}
