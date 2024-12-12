pipeline {
    agent any
    stages {
        stage("Compile") {
            steps {
                sh """
                    ./mvn clean install -Dmaven.test.skip=true -Dspring.profiles.active=descope -Ddependency-check.skip -Djacoco.skip -Dpmd.skip -Dspotbugs.skip -Dcheckstyle.skip
                """
            }
        }
    }
}
