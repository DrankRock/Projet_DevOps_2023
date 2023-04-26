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

## DataFrame
Aucune bibliothèque n’existe en Java pour manipuler des DataFrame à partir de fichiers CSV.
Nous avons donc implémenté une bibliothèque semblable à celle déjà existante en Python nommée Pandas.

La classe DataFrame permet de manipuler des données sous forme de tableaux en deux dimensions directement traduits d’un fichier CSV.

* Attributs de la classe :

List<String[]> data : une liste de tableaux de chaînes de caractères contenant les données du DataFrame.

String[] headers : un tableau de chaînes de caractères représentant les noms des colonnes.

Nous avons donc implémenté les méthodes suivantes pour notre bibliothèque :

* Constructeur :

DataFrame(String filename) : construit un DataFrame à partir d'un fichier CSV dont le nom de fichier est passé en argument.

DataFrame(String[] headers, List<String[]> data) : construit un DataFrame à partir d'un tableau de noms de colonnes et d'une liste de tableaux de chaînes de caractères représentant les données.

* Méthodes obligatoires : 

toString() : String : retourne une chaîne de caractères représentant le contenu du DataFrame.

equals(DataFrame obj) : boolean : compare le contenu de ce DataFrame avec un autre DataFrame passé en argument. Retourne vrai si les deux DataFrames sont égaux.

getColumnIndex(String columnLabel) : int : retourne l'indice de la colonne ayant pour nom columnLabel. Si la colonne n'est pas trouvée, retourne -1.

head(int n) : String : retourne les n premières lignes du DataFrame.

tail(int n) : String : retourne les n dernières lignes du DataFrame.

selectRows(int[] rowIndices) : DataFrame : retourne un nouveau DataFrame contenant les lignes sélectionnées par les indices passés en argument.

selectColumns(String[] columnLabels) : DataFrame : retourne un nouveau DataFrame contenant les colonnes sélectionnées par les noms passés en argument.

min(String columnLabel) : double : Cette méthode renvoie la valeur minimale de la colonne spécifiée.

max(String columnLabel) : double : Cette méthode renvoie la valeur maximale de la colonne spécifiée.

mean(String columnLabel) : double  : Cette méthode renvoie la moyenne des valeurs de la colonne spécifiée.

loc(String[] ColumnsLabels, int[] rowIndices) : DataFrame : Cette méthode renvoie un sous-DataFrame en sélectionnant seulement les colonnes et les lignes spécifiées.

* Méthodes optionnelles: 

aggregate(String mode, String groupByColumn, String aggregateColumn) : DataFrame : Cette méthode agrège les données du DataFrame en fonction d'une colonne spécifiée et calcule la somme, la moyenne, le maximum ou le minimum d'une autre colonne spécifiée.

groupBy(String[] columnLabels) : DataFrame : Cette méthode regroupe les données du DataFrame en fonction des colonnes spécifiées.


* Avantages et Limites de notre bibliothèque :

Avantages :

Notre bibliothèque DataFrame fournit des avantages dans la manipulation des données :

Facile d'utilisation : notre bibliothèque fournit une interface simple et intuitive pour travailler avec des données sous forme de tableaux.

Performante : notre bibliothèque DataFrame peut traiter rapidement des opérations telles que le tri, l'agrégation, la sélection et la transformation de données. Nous avons essayé d’avoir une complexité minimale pour chacune de nos méthodes.

Limites :

Cependant, il y a des limites à l'utilisation de cette bibliothèque :

La prise en charge des types de données : notre bibliothèque DataFrame ne prend pas en charge tous les types de données, tels que les données spatiales ou les données géographiques, ce qui peut limiter son utilisation dans certains domaines. Seuls les fichiers CSV sont acceptés.

La complexité des requêtes : bien que notre bibliothèque soit intuitive et facile à utiliser, des requêtes plus complexes peuvent être difficiles à exprimer en utilisant les méthodes fournies. Les utilisateurs peuvent avoir besoin d’utiliser d’autres bibliothèques pour des opérations plus complexes.

La dépendance aux bibliothèques tierces : notre bibliothèque DataFrame dépend d'autres bibliothèques Java. L’utilisateur doit donc installer localement d’autres bibliothèques telles que Java.io ou encore Java.util.



## Feedback
### Eclipse
Pratique, sympa

## Notes

