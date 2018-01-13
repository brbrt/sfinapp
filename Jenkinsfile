pipeline {
    agent {
        docker {
            image 'vegansk/ubuntu-java-nodejs' 
        }
    }

    stages {
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
