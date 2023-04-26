# Projet_DevOps_2023

[![Java CI with Maven](https://github.com/DrankRock/Projet_DevOps_2023/actions/workflows/maven.yml/badge.svg)](https://github.com/DrankRock/Projet_DevOps_2023/actions/workflows/maven.yml)

Un site web decrivant les taches accomplis et la javadoc est disponible ici [https://drankrock.github.io/Projet_DevOps_2023/](https://drankrock.github.io/Projet_DevOps_2023/).

## Auteur
* Deschamps Clarisse ([deschcla](https://github.com/deschcla))
* Deschamps Thibaud ([ZeloXThib](https://github.com/ZeloXThib))
* Pavlov Matvei ([DrankRock](https://github.com/DrankRock))
* Pereira Evan ([Evan1700](https://github.com/Evan1700))

## Fonctionnalités

## Outils
### IDE
* Eclipse

## Workflow
* Gitflow

## Docker
* Peut etre

## Infrastructure as code
* Terraform 
  Nous avons réussi à créer une configuration avec Terraform qui instancie une machine virtuelle avec une installation de Docker, dans le but qu'elle affiche une démonstration de la bibliothèque.
  Nous n'avons cependant pas réussi à terminer la configuration pour que les commandes ce lancent automatiquement au démarage. 
  Ci-dessous, les commandes à suivre :
$ cd Projet_DevOps_2023/terraform-demo/
$ terraform init
$ terraform validate
$ terraform apply
			          yes
Puis se connecter à la machine virtuelle créer.
$ sudo docker pull drankrock/devops_project_2023:v0.3
$ sudo docker run drankrock/devops_project_2023:v0.3
  
* Ansible

## Feedback
### Eclipse
Pratique, sympa

## Notes

