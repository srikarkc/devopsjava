pipeline {
  agent any
  tool{
      maven 'm3'
      jdk 'jdk8'
  }
  stages {
    stage('Checkout') {
      steps {
        chckoutscm
      }
    }

    stage('Compile') {
      steps {
        sh 'mvn compile'
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

     stage('Publish Test Result') {
      steps {
         junit '**/*.xml'
      }
    }

  }
  post {
    always {
       slackSend channel: 'devops_sept_2020', color: 'red', message: "BUILD FAILD - $JOB_NAME - $BUILD_ID", teamDomain: 'pragraconsulting2020', tokenCredentialId: 'slack'
    }
  }
}