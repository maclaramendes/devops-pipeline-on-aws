# Cloud Native DevOps Project

## 1. Project Summary

This is a DevOps portfolio project built around a simple Spring Boot API used as the application layer.

The main purpose of the project is to demonstrate a complete DevOps workflow, including containerization, infrastructure provisioning, cloud deployment, Kubernetes orchestration, and pod autoscaling.

The API itself is intentionally simple, since the main focus is on the surrounding infrastructure and deployment process.

This project is being developed with cost awareness in mind, using a lightweight environment suitable for learning and portfolio purposes.

## 2. Architecture

- Spring Boot API
- Docker
- Terraform
- AWS EC2
- k3s
- Kubernetes
- Horizontal Pod Autoscaler (HPA)
- GitHub Actions

## 3. Technical Objective

Demonstrate infrastructure provisioning, containerized deployment, Kubernetes orchestration, and pod autoscaling with a low-cost architecture suitable for learning and portfolio purposes.

## 4. Diagram

```text
Developer
   |
   v
GitHub Repository
   |
   v
GitHub Actions
   |
   v
Docker Image
   |
   v
AWS EC2
   |
   v
k3s Cluster
   |
   v
Spring Boot API
   |
   v
HPA scales pods based on load
```

## 5. Run Locally

### With Maven

```bash
./mvnw spring-boot:run
```

### With Docker

```bash
docker build -t encurtador-de-link-api .
docker run -d -p 8080:8080 --name encurtador-api encurtador-de-link-api
```

## 6. Provision Infrastructure

Example Terraform workflow:

```bash
cd infra
terraform init
terraform plan
terraform apply
```

## 7. Deploy Application

Example Kubernetes deployment workflow:

```bash
kubectl apply -f k8s/namespace.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
kubectl apply -f k8s/hpa.yaml
```

To verify resources:

```bash
kubectl get all -n devops-lab
kubectl get hpa -n devops-lab
```

## 8. Test Autoscaling

After exposing the application locally or in the cluster, generate load against the workload endpoint.

Example:

```bash
for i in {1..200}; do curl http://localhost:8080/work & done
```

To monitor scaling behavior:

```bash
kubectl get hpa -n devops-lab -w
kubectl get pods -n devops-lab -w
```

## Current Status

### Completed

- API implementation
- Docker containerization

### Next Steps

- Provision infrastructure with Terraform
- Deploy the environment on AWS
- Run the application on k3s
- Configure HPA
- Add a CI/CD pipeline with GitHub Actions
