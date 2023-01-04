pipeline {
    agent { label 'Ubuntu18-openjdk1.8'}

    stages {
        stage('Do nothing') {
            steps {
                sh '/bin/true'
            }
        }
    stage('build') {
            steps {
                sh 'ant -version'
                sh 'java -version'
                sh 'mkdir ~/test_jenkins_mkdir'
        }
    }
}
}