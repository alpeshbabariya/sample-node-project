pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'docker build -t node-app .'
            }
        }

        stage('Push to ECR') {
            steps {
                sh 'docker tag node-app:latest 282484137350.dkr.ecr.us-east-1.amazonaws.com/nodejs-app-repo:latest'
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'your-aws-credentials', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                    sh 'docker push 282484137350.dkr.ecr.us-east-1.amazonaws.com/nodejs-app-repo:latest'
                }
            }
        }

        stage('Deploy to ECS') {
            steps {
                sh 'aws ecs update-service --cluster nodejs-cluster --service nodejs-service --force-new-deployment --no-cli-pager'
            }
        }
    }
}
