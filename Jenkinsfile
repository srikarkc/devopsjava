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
//      stage("Quality Gate") {
//          steps {
//              timeout(time: 2, unit: 'MINUTES') {
//                  // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
//                  // true = set pipeline to UNSTABLE, false = don't
//                  waitForQualityGate abortPipeline: true
//              }
//          }
//      }
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
      stage ('Artifactory configuration') {
          steps {
              rtServer (
                      id: "art1",
                      url: "http://3.219.34.82/artifactory",
                      credentialsId: "artifactory"
              )

              rtMavenDeployer (
                      id: "MAVEN_DEPLOYER",
                      serverId: "art1",
                      releaseRepo: "libs-release-local",
                      snapshotRepo: "libs-snapshot-local"
              )

              rtMavenResolver (
                      id: "MAVEN_RESOLVER",
                      serverId: "art1",
                      releaseRepo: "libs-release",
                      snapshotRepo: "libs-snapshot"
              )
          }
      }

      stage ('Exec Maven') {
          steps {
              rtMavenRun (
                      tool: 'm3', // Tool name from Jenkins configuration
                      pom: 'pom.xml',
                      goals: 'install',
                      deployerId: "MAVEN_DEPLOYER",
                      resolverId: "MAVEN_RESOLVER"
              )
          }
      }

      stage ('Publish build info') {
          steps {
              rtPublishBuildInfo (
                      serverId: "art1"
              )
          }
      }



  }
}
