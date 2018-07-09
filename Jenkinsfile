pipeline {
  agent any
  stages {
    stage('Compile') {
      steps {
        tool 'M3'
        sh 'mvn compile'
      }
    }
  }
}