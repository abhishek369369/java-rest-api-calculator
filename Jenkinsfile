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
            post {
                always {
                    script {
                        print("Total Tests : " + currentBuild.testResultObject.totalCount)
                        print("Total Tests : " + currentBuild.testResultObject.passCount)
                        print("Total Tests : " + currentBuild.testResultObject.failCount)
                        print("Total Tests : " + currentBuild.testResultObject.skipCount)
                        junit '**/target/generated-test-sources/TEST-*.xml'
                    }
                }
            }
        }
    }
}
