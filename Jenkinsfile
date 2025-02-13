pipeline {
    agent any

    parameters {
      choice(name: 'run_tests',choices: ['no', 'yes'], description: 'Do you want to run the test cases')
    }

    environment{
        CLIENT_DEV_CREDENTIALS = "lumberfi-clients-dev-credentials"
        CLIENT_QA_CREDENTIALS = "lumberfi-clients-qa-credentials"
        CLIENT_STAGE_CREDENTIALS = "lumberfi-clients-stage-credentials"
        CLIENT_PROD_CREDENTIALS = "lumberfi-clients-prod-credentials"
    }
    
    stages {
        stage("Compile") {
            steps {
                script{
                    sh """
                        ./mvnw clean install
                    """
                }
            }
        }
        stage("Tests") {
            when {
              expression {
                  params.run_tests == 'yes'
              }
            }
            
            steps {
                script{
                    echo "hello 1"
                    def envClientCredentials = env.CLIENT_DEV_CREDENTIALS;
                    if(env.BRANCH_NAME == 'master'){
                        envClientCredentials = env.CLIENT_PROD_CREDENTIALS
                    } else if(env.BRANCH_NAME == 'qa'){
                        envClientCredentials = env.CLIENT_QA_CREDENTIALS
                    } else if(env.BRANCH_NAME == 'stage'){
                        envClientCredentials = env.CLIENT_STAGE_CREDENTIALS
                    } else if(env.BRANCH_NAME == 'main'){
                        envClientCredentials = env.CLIENT_PROD_CREDENTIALS
                    }
                
                withCredentials([
                    string(credentialsId: "${CLIENT_DEV_CREDENTIALS}", variable: 'lumberfi-clients-dev-credentials'),
                    string(credentialsId: "${CLIENT_QA_CREDENTIALS}", variable: 'lumberfi-clients-qa-credentials'),
                    string(credentialsId: "${CLIENT_STAGE_CREDENTIALS}", variable: 'lumberfi-clients-stage-credentials'),
                    string(credentialsId: "${CLIENT_PROD_CREDENTIALS}", variable: 'lumberfi-clients-prod-credentials')
                ]){
                        echo "hello 2"

                        if(env.BRANCH_NAME == 'develop'){
                            echo "Credentials of DEV"
                        } else if(env.BRANCH_NAME == 'qa'){
                            echo "Credentials of QA"
                        } else if(env.BRANCH_NAME == 'stage'){
                            echo "Credentials of STAGE"
                        } else if(env.BRANCH_NAME == 'master'){
                            echo "Credentials of PROD"
                        } else if(env.BRANCH_NAME == 'main'){
                            echo "Credentials of Prod"
                        }
                    
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
