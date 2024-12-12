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
                    print("Total Tests : " + ${currentBuild.testResultObject.totalCount})
                    print("Total Tests : " + ${currentBuild.testResultObject.passCount})
                    print("Total Tests : " + ${currentBuild.testResultObject.failCount})
                    print("Total Tests : " + ${currentBuild.testResultObject.skipCount})
                    junit '**/target/generated-test-sources/TEST-*.xml'
                }
            }
        }
    }
}
