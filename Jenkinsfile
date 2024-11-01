pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'Java'
    }
    stages {
        stage('env') {
            steps {
                bat 'mvn --version'
            }
        }
        stage('build') {
            steps {
                bat 'mvn clean install'
            }
        }
    }
}
