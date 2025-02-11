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
                    file(credentialsId: 'lumberfi-clients-dev-credentials', variable: 'dev-secret-file')
                ]){
                        script{
                            catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                                if(params.run_tests == 'yes'){
                                    sh """
                                        ./mvnw clean test -Dtest=**/*Test.java -Dmaven.test.failure.ignore=true -Djacoco.skip=false -DfailIfNoTests=false surefire-report:report jacoco:report
                                    """
                                }
                            }
                            //here print the acumatica server url
                            //here print the paycor api key

                            // Read the secret file content
                            def secretFileContent = readFile(file: env.dev_secret_file)
                                                        
                            // Parse the content to get individual values
                            def properties = secretFileContent.split('\n').collectEntries { line ->
                                def parts = line.split('=').collect { it.trim() }
                                [(parts[0]): parts[1]]
                            }
                            
                            // Print the values
                            echo "Acumatica Server URL: ${properties['ACUMATICA_SERVER_URL']}"
                            echo "Paycor API Key: ${properties['PAYCOR_API_KEY']}"
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
