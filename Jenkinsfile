pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'which mvn'
                sh 'mvn clean install -B'
            }
        }
    }
}
