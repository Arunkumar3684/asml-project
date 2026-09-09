pipeline {
    agent any

environment {
    JAVA_HOME  = '/usr/lib/jvm/java-21-amazon-corretto.x86_64'
    PATH       = "${JAVA_HOME}/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin"
    AWS_REGION = 'us-east-1'
    ECR_REPO   = '089089715726.dkr.ecr.us-east-1.amazonaws.com/asml-app'
    IMAGE_TAG  = "${BUILD_NUMBER}"
}
    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t asml-app:${IMAGE_TAG} .'
            }
        }

        stage('ECR Login') {
            steps {
                sh '''
                aws ecr get-login-password --region $AWS_REGION | \
                docker login --username AWS --password-stdin \
                089089715726.dkr.ecr.us-east-1.amazonaws.com
                '''
            }
        }

        stage('Push to ECR') {
            steps {
                sh '''
                docker tag asml-app:$IMAGE_TAG $ECR_REPO:$IMAGE_TAG
                docker push $ECR_REPO:$IMAGE_TAG
                '''
            }
        }
    }
}
