pipeline {
    agent any
    stages {
        stage("Compile") {
            steps {
                sh """
                    ./mvnw clean install
                """
            }
        }
        stage("Tests") {
            steps {
                sh """
                    ./mvnw clean test -Dtest=**/*Test.java -Dmaven.test.failure.ignore=true surefire-report:report-only
                """
            }
            post {
                always {
                    script {
                        def testResult = junit skipPublishingChecks: true , '**/target/surefire-reports/TEST-*.xml'

                        echo "Total Tests: ${testResult.totalCount}"
                        echo "Passed Tests: ${testResult.passCount}"
                        echo "Failed Tests: ${testResult.failCount}"
                        echo "Skipped Tests: ${testResult.skipCount}"
                    }
                }
            }
        }
    }
}
