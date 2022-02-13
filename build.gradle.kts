plugins {
    java
    jacoco
    id("org.ajoberstar.reckon") version "0.14.0"
    id("org.jetbrains.qodana") version "0.1.13"
}

group = "com.github.smoothier"
reckon {
    scopeFromProp()
    stageFromProp("dev", "m", "rc", "final")
}

project.extra["reportsDir"] = file("$buildDir/reports")
project.extra["dataDir"] = file("$buildDir/data")
project.extra["javaVersion"] = 17

dependencies {
    compileOnly(libs.org.jetbrains.annotations)
    testCompileOnly(libs.org.jetbrains.annotations)

    testImplementation(libs.org.junit.jupiter.api)
    testRuntimeOnly(libs.org.junit.jupiter.engine)
}

java {
    val javaVersion: Int by project.extra

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

jacoco {
    val reportsDir: File by project.extra

    toolVersion = "0.8.7"
    reportsDirectory.set(reportsDir)
}

qodana {
    val reportsDir: File by project.extra
    val dataDir: File by project.extra

    saveReport.set(true)
    showReport.set(false)
    dockerContainerName.set("qodana")
    reportPath.set("$reportsDir/codequality/qodana")
    resultsPath.set("$dataDir/codequality/qodana")
}

tasks.register<DefaultTask>("codequality") {
    group = "verification"
    description = """
        Analyses the code quality.
    """.trimIndent()
}
val TaskContainer.codequality: TaskProvider<DefaultTask>
    get() = named<DefaultTask>("codequality")

tasks {
    val reportsDir: File by project.extra
    val dataDir: File by project.extra

    runInspections {
        dependsOn += test
        dependsOn += assemble
    }

    test {
        useJUnitPlatform()
        reports.html.outputLocation.set(file("$reportsDir/tests/unit/html"))
        reports.junitXml.outputLocation.set(file("$reportsDir/tests/unit/xml"))
        binaryResultsDirectory.set(file("$dataDir/tests/unit"))
    }

    jacocoTestReport {
        dependsOn += test
    }

    check {
        dependsOn += codequality
        dependsOn += jacocoTestReport
    }

    codequality {
        dependsOn += runInspections
    }
}
