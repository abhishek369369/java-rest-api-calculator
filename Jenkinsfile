tpipeline {
    agent any
    stages {
        stage("Compile") {
            steps {
                echo "STAGE 1"
                sh """
                    ./mvnw clean install
                """
            }
        }
        stage("Tests") {
            steps {
                sh """
                    ./mvnw test -Dtest=**/*Test.java -Dmaven.test.failure.ignore=true -Djacoco.skip=false -DfailIfNoTests=false surefire-report:report jacoco:report
                """
            }
            post {
                always {
                    script {
                        def testResult = junit '**/target/surefire-reports/TEST-*.xml'


                        echo "Total Tests: ${testResult.totalCount}"
                        echo "Passed Tests: ${testResult.passCount}"
                        echo "Failed Tests: ${testResult.failCount}"
                        echo "Skipped Tests: ${testResult.skipCount}"


                        echo "????????????????????????  SECOND STAGE END  ?????????????????????????"
                    }
                }
            }
        }
        stage("Step after tests"){
            steps {
                script{
                    echo "--------------------LAST STAGE------------------"
                }
            }
        }
    }
}
