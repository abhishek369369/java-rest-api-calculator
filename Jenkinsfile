pipeline {
    agent any
    stages {
        stage("Compile") {
            steps {
                sh """
                    ./mvnw clean compile
                """
            }
        }
        stage("Tests") {
            steps {
                sh """
                    ./mvnw test -Dmaven.test.failure.ignore=true
                """
            }
        }
    }
}
