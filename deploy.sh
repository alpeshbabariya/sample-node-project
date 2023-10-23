#!/bin/bash

# Set configurable parameters
SERVICE_NAME="nodejs-service"
CLUSTER_NAME="arn:aws:ecs:us-east-1:282484137350:cluster/nodejs-cluster"
REGION="us-east-1"

# Pull the latest Docker image from the registry
#docker pull "aws_account_id.dkr.ecr..amazonaws.com/atest"

# Deploy the application to ECS
aws ecs update-service --cluster $CLUSTER_NAME --service $SERVICE_NAME --force-new-deployment --no-cli-pager
