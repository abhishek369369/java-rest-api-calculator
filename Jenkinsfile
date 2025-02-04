tpipeline {
    agent any
    stages {
        stage("Compile") {
            steps {
                sh """
                    ./mvnw clean install
                """

                echo "|||||||||||||||||||||||  FIRST STAGE END ||||||||||||||||||||||||||||"
            }
        }
        stage("Tests") {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                sh """
                    ./mvnw test -Dtest=**/*Test.java -Dmaven.test.failure.ignore=true -Djacoco.skip=false -DfailIfNoTests=false surefire-report:report jacoco:report
                """
                }
            }
            post {
                always {
                    script {
                        def testResult = junit '**/target/surefire-reports/TEST-*.xml'


                        echo "Total Tests: ${testResult.totalCount}"
                        echo "Passed Tests: ${testResult.passCount}"
                        echo "Failed Tests: ${testResult.failCount}"
                        echo "Skipped Tests: ${testResult.skipCount}"

                        currentBuild.result = 'SUCCESS'

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
