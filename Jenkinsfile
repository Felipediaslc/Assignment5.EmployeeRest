pipeline {
    agent any
    stages {
        stage('Git clone') {
            steps {
                git "https://github.com/himanshudabas/Assignment5.EmployeeRest"
            }
        }
        stage('Maven clean') {
            steps {
                bat "mvn clean"
            }
        }
        stage('Maven install') {
            steps {
                bat "mvn install"
            }
        }
        stage('Maven test') {
            steps {
                bat "mvn test"
            }
        }
        stage('Maven package') {
            steps {
                bat "mvn package"
            }
        }
    }
}
