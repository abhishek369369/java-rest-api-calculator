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
                    ./mvnw clean test -Dmaven.test.failure.ignore=true surefire-report:report jacoco:report
                """
            }
            post {
                always {
                    junit '**/target/generated-test-sources/TEST-*.xml'
                }
            }
        }
    }
}
