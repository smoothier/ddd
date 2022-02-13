rootProject.name = "ddd"

enableFeaturePreview("VERSION_CATALOGS")
// == Define locations for components ==
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    versionCatalogs {
        create("libs") {
            from(files("libs.versions.toml"))
        }
    }
}
