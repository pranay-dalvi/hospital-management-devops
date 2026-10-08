pipeline {

    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    stages {

        stage('Build') {
            steps {
                echo 'Building Hospital Management Application...'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Start Application') {
            steps {
                echo 'Starting Hospital Management Application...'

                bat '''
                    start "HospitalApp" /B cmd /c "mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081 > app.log 2>&1"
                '''

                sleep 20
            }
        }

        stage('Selenium Tests') {
            steps {
                echo 'Running Selenium Tests...'
                bat 'mvn test'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }

        success {
            echo 'Hospital Management Pipeline SUCCESS!'
        }

        failure {
            echo 'Pipeline FAILED. Check the console output.'
        }
    }
}