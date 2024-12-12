pipeline {
    agent any
    stages {
        stage("Compile") {
            sh """
                ./mvnw clean compile
            """
        }
    }
}
