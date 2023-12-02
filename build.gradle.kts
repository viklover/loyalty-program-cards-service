import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.openapi.generator") version "7.1.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

group = "ru.viklover"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.flywaydb:flyway-core")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework:spring-jdbc")
    implementation("org.openapitools:openapi-generator-gradle-plugin:7.1.0") {
        exclude(group = "org.slf4j", module = "slf4j-simple")
    }
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("org.postgresql:r2dbc-postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}

val projectPackage = "$group.${rootProject.name}"

val generatedSourcesPath = "${layout.buildDirectory.get()}/generated"
val contractsDirectoryPath = "$rootDir/src/main/resources/contracts"
val contractTasks = apiContracts()

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
    dependsOn(contractTasks)
}


kotlin.sourceSets["main"].kotlin.srcDir("$generatedSourcesPath/src/main/kotlin")

// tasks.withType<Test> {
//     useJUnitPlatform()
// }

tasks.bootBuildImage {
    builder.set("paketobuildpacks/builder-jammy-base:latest")
}

/* GENERATE API CONTRACT TASKS */

fun apiContracts(): List<String> {

    val directory = file(contractsDirectoryPath)
    val files = directory.listFiles() ?: throw StopExecutionException("Where's contracts?")

    val pattern = Regex("api-v(\\d+\\.\\d+)\\.yaml")

    return files.map { file ->

        val matches = pattern.find(file.name) ?: throw StopExecutionException("Matches were not found")

        val apiSuffix = matches.let {
            val (major, minor) = it.groupValues[1].split('.')

            if (minor == "0") "v$major" else "v${major}_${minor}"
        }

        val taskName = "generate${apiSuffix.uppercase()}"

        tasks.create(taskName, GenerateTask::class) {
            generatorName.set("kotlin-spring")
            inputSpec.set("$rootDir/src/main/resources/contracts/${file.name}")
            outputDir.set(generatedSourcesPath)
            apiPackage.set("$projectPackage.contracts.$apiSuffix.controller")
            modelPackage.set("$projectPackage.contracts.$apiSuffix.models")
            packageName.set("$projectPackage.contracts.$apiSuffix")
            configOptions.set(
                mapOf(
                    "reactive" to "true",
                    "useSpringBoot3" to "true",
                    "useSwaggerUI" to "false",
                    "interfaceOnly" to "true",
                    "apiSuffix" to "Controller${apiSuffix.uppercase()}",
                    "annotationLibrary" to "none",
                    "packageName" to "$projectPackage.contracts",
                    "documentationProvider" to "none"
                )
            )
        }

        return@map taskName;
    }
}
