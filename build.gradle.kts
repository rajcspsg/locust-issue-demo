plugins {
    id("java")
    id("application")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    //implementation("com.bigsonata.swarm:locust-swarm:1.1.6")
    implementation("com.github.myzhan:locust4j:2.2.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

task("runMain", JavaExec::class) {
    classpath = sourceSets["main"].runtimeClasspath
    mainClass.set( "com.example.Main")
}

tasks.register<Jar>("uberJar") {
    manifest {
        attributes(mapOf("Implementation-Title" to project.name,
                "Implementation-Version" to project.version,
                "Main-Class" to "com.example.Main"))
    }
    archiveClassifier.set("uber")

    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}