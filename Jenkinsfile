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

        stage('Docker Build') {
            steps {
                echo 'Building Docker image...'

                bat '''
                    C:\\Users\\Home\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe build -t pranay1602/hospital-management:latest .
                '''
            }
        }

        stage('Docker Push') {
            steps {
                echo 'Pushing Docker image to Docker Hub...'

                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_PASSWORD'
                    )
                ]) {
                    bat '''
                        echo %DOCKER_PASSWORD% | C:\\Users\\Home\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe login -u %DOCKER_USERNAME% --password-stdin

                        C:\\Users\\Home\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe push %DOCKER_USERNAME%/hospital-management:latest

                        C:\\Users\\Home\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe logout
                    '''
                }
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