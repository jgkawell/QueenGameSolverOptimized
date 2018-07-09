pipeline {
  agent any
  stages {
    stage('Compile') {
      agent {
        docker {
          image 'maven:3.3.9-jdk-8'
        }
        
      }
      steps {
        sh '''echo PATH = ${PATH}
echo M2_HOME = ${M2_HOME}
mvn clean
mvn compile'''
      }
    }
    stage('Sonar') {
      agent any
      steps {
        sh '''mvn sonar:sonar \\
  -Dsonar.organization=jgkawell-github \\
  -Dsonar.host.url=https://sonarcloud.io \\
  -Dsonar.login=8a28a685e6d345eff310a35e60f654ccde0f9abd'''
      }
    }
  }
}