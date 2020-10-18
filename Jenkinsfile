node('node1') {
    def mvnHome
    def javaHome
    stage('Preparation') { // for display purposes
        mvnHome = tool 'm3'
        javaHome = tool 'jdk8'
    }
    stage('Checkout') {
        git url:'https://github.com/atinsingh/devopsjava.git', branch:'master'
    }
    stage('Compile') {
        // Run the maven build
        withEnv([ "JAVA_HOME=$javaHome", "MVN_HOME=$mvnHome"]) {
                sh '"$MVN_HOME/bin/mvn" clean compile'
        }
    }
    stage('Test') {
        // Run the maven build
        withEnv(["JAVA_HOME=$javaHome", "MVN_HOME=$mvnHome"]) {
            sh '"$MVN_HOME/bin/mvn" clean test'
        }
    }
    stage('Publish Test Results') {
        junit '**/target/surefire-reports/TEST-*.xml'

    }
    stage('Package') {
        // Run the maven build
        withEnv(["JAVA_HOME=$javaHome", "MVN_HOME=$mvnHome"]) {
            sh '"$MVN_HOME/bin/mvn" clean package'
        }
    }
    stage('Archive Artifact') {
        archiveArtifacts 'target/*.jar'
    }
    stage('Complete') {
        sh 'echo completed'
    }
    stage('Notify') {
        sh 'echo may be sending email'
    }
}
