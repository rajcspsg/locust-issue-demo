plugins {
    id("java")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
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