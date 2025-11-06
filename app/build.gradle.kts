plugins {
    id("application")
    id("se.patrikerdes.use-latest-versions") version "0.2.19"
    id("com.github.ben-manes.versions") version "0.53.0"
    id("org.sonarqube") version "6.3.1.5724"
    id("checkstyle")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "hexlet.code.App"
}

sonar {
    properties {
        property("sonar.projectKey", "mtvru_java-project-71")
        property("sonar.organization", "mtvru")
    }
}

checkstyle {
    toolVersion = "10.12.4"
}

dependencies {
    implementation("info.picocli:picocli:4.7.7")
    implementation("tools.jackson.core:jackson-databind:3.0.1")
    annotationProcessor("info.picocli:picocli-codegen:4.7.7")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.compileJava {
    options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
}
