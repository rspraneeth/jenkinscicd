pipeline{

    agent any
    tools{
        maven "maven"
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
                    bat 'docker build -t praneethrsp/docker-cicd:2.0 .'
                }
            }
        }

        stage("Push image to hub"){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockpwd', variable: 'dockpwd')]) {
                        bat "docker login -u praneethrsp -p ${dockpwd}"
                        bat "docker push praneethrsp/docker-cicd:2.0"
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
            emailext attachLog: true, body: '''<html>
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