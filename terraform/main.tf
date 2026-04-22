terraform {
  required_version = ">= 1.6.0"

  required_providers {
    aws ={
        source = "hashicorp/aws"
        version = ">=5.0"
    }
  }
}

provider "aws" {
  region = "us-east-1"
}

resource "aws_security_group" "k3s_sg" {
  name = "k3s-devops-sg"
  description = "New Security group for k3s single-node cluster"
  ingress {
    description = "SSH"
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["186.229.168.47/32"]
}

ingress {
    description = "HTTP"
    from_port = 80
    to_port = 80
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
}
ingress {
    description = "HTTPS"
    from_port = 443
    to_port = 443
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
}

ingress {
    description = "Kubernetes API"
    from_port = 6443
    to_port = 6443
    protocol = "tcp"
    cidr_blocks = ["186.229.168.47/32"]
}
egress {
  from_port   = 0
  to_port     = 0
  protocol    = "-1"
  cidr_blocks = ["0.0.0.0/0"]
}
}

resource "aws_instance" "devops_project_server" {
  ami = "ami-098e39bafa7e7303d"                   #amz2023
  instance_type = "t3.micro"
  key_name = "devops_project-2026"
  vpc_security_group_ids = [aws_security_group.k3s_sg.id]
  associate_public_ip_address = true
  subnet_id = "subnet-039342ad0e3521af2"

  tags = {
    Name = "DevOp-project-linux"
    Creation_date = "04.22.2026"
    project = "devops portfolio"
    created_by = "terraform"
}
}