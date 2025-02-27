import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
// https://mvnrepository.com/artifact/com.google.guava/guava
    implementation("com.google.guava:guava:33.4.0-jre")
}

dependencies {
    implementation ("com.google.guava:guava:33.4.0-jre")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("gradleHelloWorld")
        archiveVersion.set("0.1")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "ru.otus.App"))
        }
    }

    build {
        dependsOn(shadowJar)
    }
}
