pipeline {
    agent {
        docker {
            image 'markhobson/maven-chrome'
        }
    }
    tools {
        maven 'Maven'
    }
    stages {
        stage ("Checkout") {
            steps {
                git url: "https://github.com/kumarabhinv/krabhinav.git",
                    branch: "main"
                echo "Checkout complete."
            }
        }
        stage ("Test") {
            steps {
                echo "Starting test.."
                script {
                    sh "mvn clean"
                    sh "mvn test"
                }
            }
        }
    }
    post {
        success {
            echo "Pipeline execution successful."
        }
        failure {
            echo "Pipeline execution failed."
        }
        always {
            echo "Pipeline execution completed."
        }
    }
}
