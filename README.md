
<h1 align="center">Projet_DevOps_2023</h1>
<p align="center">
    <a href="#" alt="Maven tests">
        <img src="https://github.com/DrankRock/Projet_DevOps_2023/actions/workflows/maven.yml/badge.svg" /></a>
    <a href="#" alt="Coverage">
        <img src="https://img.shields.io/endpoint?url=https://gist.githubusercontent.com/DrankRock/f1612eac8207539733a64b7a531f31f5/raw/devops_coverage.json" /></a>
    <a href="#" alt="Docker latest release">
        <img src="https://img.shields.io/endpoint?url=https://gist.githubusercontent.com/DrankRock/27b1a09f92f620914ebb1a1b5dbf8a36/raw/devops_docker_extension.json" /></a>
    <a href="https://drankrock.github.io/Projet_DevOps_2023/" alt="Javadoc">
        <img src="https://img.shields.io/badge/javadoc-Available Here-brightgreen" /></a>
</p>

## Auteurs
* Deschamps Clarisse ([deschcla](https://github.com/deschcla))
* Deschamps Thibaud ([ZeloXThib](https://github.com/ZeloXThib))
* Pavlov Matvei ([DrankRock](https://github.com/DrankRock))
* Pereira Evan ([Evan1700](https://github.com/Evan1700))

## Workflows
### maven.yml
Nous avons créés deux workflows Github. Un des deux à pour objectif de compiler avec Maven, lancer les tests, les tests de Couverture et verifier que tout fonctionne bien. Ce Workflow est dans `maven.yml`. A la fin de l'execution, il créé un badge contenant le pourcentage de couverture de la classe DataFrame.java. Nous avons souhaité ne verifier que ce fichier, car la couverture etait autrement peu représentative, car la classe de Démonstration n'est jamais appelé. 

Pour créer le badge, nous utilisons l'action [Dynamic Badges](https://github.com/marketplace/actions/dynamic-badges) qui permet de récuperer un résultat, créer un json dans un fichier sur gist.github.com, et convertir ce fichier en un badge avec [shields.io](https://shields.io/).

### docker.yml
Un second workflow permet d'effectuer des actions docker en fonction du message de commit. Dans un premier temps, les tests sont lancés afin de vérifier que la nouvelle version n'a pas de problèmes. Ensuite, si le message de commit contient une chaine de caractères de la forme `:docker:v[0-9]+\.[0-9]+:`, alors la nouvelle version indiqué est construite avec docker build, puis poussé sur dockerhub.

## Docker

Nous avons mis en place un fichier Dockerfile, qui permet de construire un containeur docker montrant les parties principales de notre DataFrame à l'aide d'une classe de démonstration. 

Vous pouvez construire ce docker à l'aide de `docker build`, ou récupérer la dernière version sur [la page dockerhub](https://hub.docker.com/repository/docker/drankrock/devops_project_2023/general), ou directement avec la commande `docker pull drankrock/devops_project_2023:tagname`. Vous pouvez trouver le tag le plus récent dans le badge dédié en haut de cette page.

La mise à jour de la page dockerhub est automatisé à l'aide d'un workflow github. Si le message du commit contient `:docker:v0.1:`, un nouveau docker va être créé et poussé avec le tag `v0.1`. Le tag doit respecter le format `v[0-9]+\.[0-9]+`. Ce workflow ne fonctionne que sur les branches `develop` et `main`.

## Infrastructure as code
### Terraform
  Nous n'avons pas réussi à créer une configuration avec Terraform qui instancie une machine virtuelle avec Docker préinstallé, ainsi que le container docker de notre projet. Cependant, il est quand meme possible d'utiliser Terraform avec notre fichier d'initialisation. Il faudra ensuite installer docker et récupérer le container docker sur dockerhub.   

Voici les commandes à suivre :
```
$ cd Projet_DevOps_2023/terraform-demo/
$ terraform init
$ terraform validate
$ terraform apply -auto-approve
```

Vous pouvez installer docker à l'aide des commandes [sur le site de docker](https://docs.docker.com/engine/install/).
Puis vous pouvez tester le container docker comme ceci :
```
$ sudo docker pull drankrock/devops_project_2023:v0.3 \
$ sudo docker run drankrock/devops_project_2023:v0.3 
```  
 
### Ansible 
Nous avons réussi à créer une configuration qui puisse télécharger par exemple docker et même réussi à faire copier un fichier depuis le local jusq'à la VM mais nous n'arrivons pas à généraliser cela pour d'autres VM.


## DataFrame
Aucune bibliothèque n’existe en Java pour manipuler des DataFrame à partir de fichiers CSV.
Nous avons donc implémenté une bibliothèque semblable à celle déjà existante en Python nommée Pandas.

La classe DataFrame permet de manipuler des données sous forme de tableaux en deux dimensions directement traduits d’un fichier CSV.

### Attributs de la classe :

`List<String[]> data` : une liste de tableaux de chaînes de caractères contenant les données du DataFrame.

`String[] headers` : un tableau de chaînes de caractères représentant les noms des colonnes.

Nous avons donc implémenté les méthodes suivantes pour notre bibliothèque :

### Constructeur :

`DataFrame(String filename)` : construit un DataFrame à partir d'un fichier CSV dont le nom de fichier est passé en argument.

`DataFrame(String[] headers, List<String[]> data)` : construit un DataFrame à partir d'un tableau de noms de colonnes et d'une liste de tableaux de chaînes de caractères représentant les données.

### Méthodes obligatoires : 

`toString() : String` : retourne une chaîne de caractères représentant le contenu du DataFrame.

`equals(DataFrame obj) : boolean` : compare le contenu de ce DataFrame avec un autre DataFrame passé en argument. Retourne vrai si les deux DataFrames sont égaux.

`getColumnIndex(String columnLabel) : int` : retourne l'indice de la colonne ayant pour nom columnLabel. Si la colonne n'est pas trouvée, retourne -1.

`head(int n) : String` : retourne les n premières lignes du DataFrame.

`tail(int n) : String` : retourne les n dernières lignes du DataFrame.

`selectRows(int[] rowIndices) : DataFrame` : retourne un nouveau DataFrame contenant les lignes sélectionnées par les indices passés en argument.

`selectColumns(String[] columnLabels) : DataFrame` : retourne un nouveau DataFrame contenant les colonnes sélectionnées par les noms passés en argument.

`min(String columnLabel) : double` : Cette méthode renvoie la valeur minimale de la colonne spécifiée.

`max(String columnLabel) : double` : Cette méthode renvoie la valeur maximale de la colonne spécifiée.

`mean(String columnLabel) : double` : Cette méthode renvoie la moyenne des valeurs de la colonne spécifiée.

`loc(String[] ColumnsLabels, int[] rowIndices) : DataFrame` : Cette méthode renvoie un sous-DataFrame en sélectionnant seulement les colonnes et les lignes spécifiées.

### Méthodes optionnelles: 

`aggregate(String mode, String groupByColumn, String aggregateColumn) : DataFrame` : Cette méthode agrège les données du DataFrame en fonction d'une colonne spécifiée et calcule la somme, la moyenne, le maximum ou le minimum d'une autre colonne spécifiée.

`groupBy(String[] columnLabels) : DataFrame` : Cette méthode regroupe les données du DataFrame en fonction des colonnes spécifiées.


### Avantages et Limites de notre bibliothèque :

Notre bibliothèque DataFrame fournit des avantages dans la manipulation des données :

- Facile d'utilisation : notre bibliothèque fournit une interface simple et intuitive pour travailler avec des données sous forme de tableaux.

- Performante : notre bibliothèque DataFrame peut traiter rapidement des opérations telles que le tri, l'agrégation, la sélection et la transformation de données. Nous avons essayé d’avoir une complexité minimale pour chacune de nos méthodes.

Cependant, il y a des limites à l'utilisation de cette bibliothèque :

- La prise en charge des types de données : notre bibliothèque DataFrame ne prend pas en charge tous les types de données, tels que les données spatiales ou les données géographiques, ce qui peut limiter son utilisation dans certains domaines. Seuls les fichiers CSV sont acceptés.

- La complexité des requêtes : bien que notre bibliothèque soit intuitive et facile à utiliser, des requêtes plus complexes peuvent être difficiles à exprimer en utilisant les méthodes fournies. Les utilisateurs peuvent avoir besoin d’utiliser d’autres bibliothèques pour des opérations plus complexes.

- La dépendance aux bibliothèques tierces : notre bibliothèque DataFrame dépend d'autres bibliothèques Java. L’utilisateur doit donc installer localement d’autres bibliothèques telles que Java.io ou encore Java.util.

## Tests
La qualité des tests est un élément crucial dans tout projet de développement de logiciel. Elle fait référence à la mesure dans laquelle les tests effectués sur un logiciel ou une application sont efficaces et fiables pour identifier les erreurs et les bugs. La qualité des tests dépend de plusieurs facteurs, notamment de la stratégie de test mise en place. 

Nous avons dans un premier temps réalisé une couverture de code afin d’identifier les problèmes principaux présents lors de notre développement, celle-ci est à 100% pour nos classes DataFrame. La couverture de code n’est pas suffisante pour s'assurer d'une bonne qualité de test. Notamment, elle ne prend pas en compte les interactions entre les composants du système : le fait qu'une ligne de code soit exécutée ne garantit pas que toutes les interactions avec d'autres parties du système ont été testées. Il faut alors trouver un autre moyen permettant de tester au mieux notre programme. 

L'analyse mutationnelle avec PITEST a été la méthode que nous avons sélectionné. Cette méthode consiste à créer des mutants, un mutant est une version modifiée du code, c’est à dire que nous avons changé certains opérateurs (exemple : transformation d’un “i = i + 1” en “i = i - 1”).  Dans notre cas 78 mutants ont été produits, 71 mutants sont tués, ce qui signifie que nos tests identifient une majorité des mutants mais qu’ils pourraient encore être améliorés. Nous avons cherché les cas critiques dans notre implémentation. Cependant il est évident que nous n’avons pas pu tout tester.

## Notes
Ce cours nous a beaucoup appris, beaucoup de techniques que nous pourrons réutiliser dans notre vie professionnelle et dans nos projets personnels. Merci beaucoup !
Seul bémol, le rendu pendant la semaine de révision des exams, qui nous force a dormir peu.
