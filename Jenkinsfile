pipeline {
    agent any

    parameters {
      choice(name: 'run_tests',choices: ['no', 'yes'], description: 'Do you want to run the test cases')
    }
    
    stages {
        stage("Compile") {
            steps {
                sh """
                    ./mvnw clean install
                """
            }
        }
        stage("Tests") {
            when {
              expression {
                  params.run_tests == 'yes'
              }
            }
            
            steps {
                withCredentials([
                    file(credentialsId: 'lumberfi-clients-dev-credentials', variable: '')
                ]){
                        script{
                            catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                                if(params.run_tests == 'yes'){
                                    sh """
                                        ./mvnw clean test -Dtest=**/*Test.java -Dmaven.test.failure.ignore=true -Djacoco.skip=false -DfailIfNoTests=false surefire-report:report jacoco:report
                                    """
                                }
                            }
                        }
                }
            }
            post {
                unstable {
                    script {
                        currentBuild.rawBuild.@result = hudson.model.Result.SUCCESS
                    }
                }
                always {
                    script {
                        def testResult = junit '**/target/surefire-reports/TEST-*.xml'


                        echo "Total Tests: ${testResult.totalCount}"
                        echo "Passed Tests: ${testResult.passCount}"
                        echo "Failed Tests: ${testResult.failCount}"
                        echo "Skipped Tests: ${testResult.skipCount}"
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
