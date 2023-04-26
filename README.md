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
* Terraform \
  Nous n'avons pas réussi à créer une configuration avec Terraform qui instancie une machine virtuelle avec une installation de Docker, dans le but qu'elle affiche une démonstration de la bibliothèque. \
  Ci-dessous, les commandes à suivre : \
$ cd Projet_DevOps_2023/terraform-demo/ \
$ terraform init \
$ terraform validate \
$ terraform apply \
			          yes \
Puis se connecter à la machine virtuelle créer et installer docker. \
$ sudo docker pull drankrock/devops_project_2023:v0.3 \
$ sudo docker run drankrock/devops_project_2023:v0.3 
  
* Ansible 
Nous avons réussi à créer une configuration qui puisse télécharger par exemple docker et même réussi à faire copier un fichier depuis le local jusq'à la VM mais nous n'arrivons pas à généraliser cela pour d'autres VM.

## Feedback
### Eclipse
Pratique, sympa

## Notes

