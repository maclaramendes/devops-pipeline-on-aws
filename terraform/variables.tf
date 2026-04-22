variable "aws_region" {
  type = string
  default = "us-east-1"
}

variable "instance_type" {
  type = string
  default = "t3.micro"
}

variable "ami_id" {
  type = string
}

variable "key_name" {
  type = string
}

variable "my_ip_cidr" {
  type = string
}
