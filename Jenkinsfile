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
                    ./mvnw clean test -Dtest=**/*Test.java -Dmaven.test.failure.ignore=true
                """
            }
        }
    }
}
