plugins {
    java
    application
}

group = "org.xendv.java.edumail"
version = "1.0-SNAPSHOT"

allprojects{
    apply(plugin = "java")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        implementation("com.google.inject:guice:5.0.1")
        implementation ("org.jetbrains:annotations:13.0")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }
}

dependencies{
    implementation(project(":application"))
    implementation(project(":modulesSemiCustom"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

application{
    mainClass.set("message.logger.Main");
}

tasks.jar {
    manifest {
        attributes["Main-Class"]="message.logger.Main";
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}

task<JavaExec>("execute") {
    mainClass.set("message.logger.Main");
    classpath = java.sourceSets["main"].runtimeClasspath
    args("-c", "-f", "tag")
    standardInput = System.`in`
}