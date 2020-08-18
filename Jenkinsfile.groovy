pipeline {
  environment {
    registry = "afroz147/newimage1"
    registryCredential = 'dockerhub'
    dockerImage=''
  }
  agent any
  stages {
    stage('Cloning from Git') {
      steps {
        git 'https://github.com/afroz147/Project.git'
      }
    }
    stage('Building image') {
      steps{
        script {
          dockerImage=docker.build registry + ":$BUILD_NUMBER"
        }
      }
    }
    stage('Pushing Image to Docker hub') {
      steps{
        script {
           docker.withRegistry( '', registryCredential ) {
           dockerImage.push()
           }
        }
      }
    }
  }
}