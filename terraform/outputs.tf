output "public_ip" {
    value = aws_instance.devops_project_server
}

output "public_dns" {
  value = aws_instance.devops_project_server.public_dns
}