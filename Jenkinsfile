pipeline {
    agent {
        docker { 
            image 'markhobson/maven-chrome:jdk-21' 
            args  '--shm-size=2g --user root' // Use root inside the container to avoid permission issues
        }
    }

    tools {
        maven 'Maven'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Cloning repository'
                git branch: 'main',
                    url: 'https://github.com/kumarabhinv/krabhinav.git'
            }
        }

        stage('Test') {
            steps {
                script {
                        sh "mvn clean test"
                    }
                }
            }
        }
}
