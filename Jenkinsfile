pipeline{

    agent any
    tools{
        maven "maven"
    }

    environment{
        APP_NAME = "spring-docker-cicd"
        RELEASE_NO="1.0.0"
        DOCKER_USER="praneethrsp"
        IMAGE_NAME="${DOCKER_USER}"+"/"+"APP_NAME"
        IMAGE_TAG="${RELEASE_NO}.${BUILD_NUMBER}"
    }

    stages{

        stage("SCM Checkout"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/rspraneeth/jenkinscicd']])
            }
        }

        stage("Maven Build Process"){
            steps{
                script{
                    bat 'mvn clean install'
                }
            }
        }

        stage("Docker build image"){
            steps{
                script{
                    bat 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
//                     bat 'docker build -t praneethrsp/docker-cicd:2.0 .'
                }
            }
        }

        stage("Push image to hub"){
            steps{
                script{
                    withCredentials([string(credentialsId: 'hubpwd', variable: 'dockerpwd')]){
                        bat "docker login -u ${DOCKER_USER} -p ${dockerpwd}"
                        bat "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
//                         bat "docker push praneethrsp/docker-cicd:4.0"
                    }
                }
            }
        }

        // stage("Deploy .war to Container"){
        //     steps{
        //         deploy adapters: [tomcat9(credentialsId: '759a7f31-47d6-4137-923d-2126cfc1b824', path: '', url: 'http://localhost:9090/')], contextPath: 'jenkinscicd', war: '**/*.war'
        //     }
        // }

    }

    post{
        always{
            emailText attachLog: true, body: '''<html>
<body>
  <p>Build Status: ${BUILD_STATUS}</p>
  <p>Build Number: ${BUILD_NUMBER}</p>
  <p>Check the <a href="${BUILD_URL}">console output</a></p>
</body>
</html>


''', mimeType: 'text/html', replyTo: 'hashtagrsp@gmail.com', subject: 'Build Status : ${BUILD_NUMBER}', to: 'hashtagrsp@gmail.com'
        }
    }
}

//SCM checkout
//build
//deploy war
//email