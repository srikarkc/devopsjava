pipeline {
  agent any
  tools{
      maven 'm3'
      jdk 'jdk8'
  }
  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Compile') {
      steps {
        sh 'mvn compile'
      }
    }

    stage('SonarQube analysis') {
      steps {
          withSonarQubeEnv(credentialsId: 'sonarqube',installationName:'sonarqube') { 
            sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
          }
      }
    }
     stage('Test') {
      steps {
        sh 'mvn test'
      }
    }
     stage('Package') {
      steps {
        sh 'mvn package'
      }
    }



  }
  post {
    always {
       junit '**/*.xml'
       slackSend channel: 'devops_sept_2020', color: 'red', message: "BUILD FAILD - $JOB_NAME - $BUILD_ID", teamDomain: 'pragraconsulting2020', tokenCredentialId: 'slack'
    }
  }
}
