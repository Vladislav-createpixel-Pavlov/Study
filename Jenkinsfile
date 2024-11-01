pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'Java'
    }
    stages {
        stage('env') {
            steps {
                sh 'mvn --version'
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean install -B --no-transfer-progress'
            }
        }
    }
}
