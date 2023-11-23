pipeline {
    agent any

    environment {
        AZURE_SUBSCRIPTION_ID='9ae5c229-9413-4920-bd6d-4b8b0ea5d15d'
        AZURE_TENANT_ID='dbd6664d-4eb9-46eb-99d8-5c43ba153c61'
        CONTAINER_REGISTRY='tpgldevopsacr'
        RESOURCE_GROUP='aks_terraform_rg'
        REPO="devops"
        IMAGE_NAME="tp4"
        TAG="latest"
        CLUSTER_NAME = "terraform-aks"
    }
    tools {
        maven "maven"
        terraform "terraform"
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Amal1999/last-question-tp3.git' // Replace with your GitHub repository URL
            }
        }

        stage('Build') {
            steps {
                script {
                    // Assuming your Spring Boot app uses Maven
                    sh 'mvn clean package'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'mvn test'
                }
            }
        }

        stage('Login to Azure with AzureServicePrincipal') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'AzureServicePrincipal', passwordVariable: 'AZURE_CLIENT_SECRET', usernameVariable: 'AZURE_CLIENT_ID')]) {
                        sh 'az login --service-principal -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET -t $AZURE_TENANT_ID'
                        sh 'az account set -s $AZURE_SUBSCRIPTION_ID'
                        sh 'terraform init'

                    }
                }
            }
        }

        stage('Terraform Apply') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'AZURE_USER', passwordVariable: 'AZURE_CLIENT_SECRET', usernameVariable: 'AZURE_CLIENT_ID')]) {
                        sh 'az login -u $AZURE_CLIENT_ID -p $AZURE_CLIENT_SECRET'
                        sh 'terraform plan -out tfplan'
                        sh 'terraform apply --auto-approve tfplan'
                    }
                }
            }

        }

        stage('Build image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'AzureServicePrincipal', passwordVariable: 'AZURE_CLIENT_SECRET', usernameVariable: 'AZURE_CLIENT_ID')]) {
                    sh 'az acr login --name $CONTAINER_REGISTRY --resource-group $RESOURCE_GROUP'
                    sh 'az acr build --image $REPO/$IMAGE_NAME:$TAG --registry $CONTAINER_REGISTRY --file Dockerfile . '
                }
            }
        }

        stage('Deploy Application') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'AzureServicePrincipal', passwordVariable: 'AZURE_CLIENT_SECRET', usernameVariable: 'AZURE_CLIENT_ID')]) {
                        sh 'az aks install-cli'
                        sh 'az aks get-credentials --resource-group $RESOURCE_GROUP --name $CLUSTER_NAME'
                        sh 'kubectl apply -f deployment.yml'
                        sh 'kubectl apply -f service.yml'
                    }
                }
            }
        }
    }}