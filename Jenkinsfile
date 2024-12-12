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
                    ./mvnw test
                """
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
    }
}
