pipeline {
  agent any
  stages {
    stage('Preparation') {
      steps {
        tool(name: 'm3', type: 'maven')
        sh 'echo Preparing ENV'
      }
    }

    stage('Compile') {
      steps {
        sh 'echo helloworld'
      }
    }

  }
  environment {
    ABC = 'DEF'
  }
}