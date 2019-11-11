void buildApplication() {
    withMaven(maven: MAVEN_INSTALLATION, mavenSettingsConfig: MAVEN_SETTINGS_CONFIG) {
        sh 'mvn -DskipTests clean package'
    }
}

void runAllTests() {
    withMaven(maven: MAVEN_INSTALLATION, mavenSettingsConfig: MAVEN_SETTINGS_CONFIG) {
        sh 'mvn test'
    }
}

void deployApplication() {
    echo "Deploying application..."
}

return this
