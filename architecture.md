# objectifs du refactoring

1 - rendre le code plus facile à tester
2 - appliquer SRP partout: separer les responsabilités
3 - rendre le code plus facile à maintenir et à modifier

# étapes

1 - identifier les responsabilités
2 - décomposer les responsabilités

# découpage des responsabilités

classes de stockage (DTO) : utiliser les records pour faciliter la sérialisation dans un fichier JSON

adatapateur de stockage: sa seule responsabilité est de lire et sauvegarder dans un fichier, et convertir depuis et vers un objet DTO

classes de métier (domaine): assure les invariants et les règles du domaine

classes de gestion des commandes (au sens "Controller" ou "Command Handler)


classes de presentation (UI pure, aucune logique)

    +------------------------------------------------------+
    |                                                      |
    |                  MVC ARCHITECTURE                    |
    |                                                      |
    +------------------------------------------------------+
                            |
        +-------------------+-------------------+
        |                   |                   |
        v                   v                   v
    +----------+      +----------+      +------------+
    |          |      |          |      |            |
    |  MODEL   |<---->|CONTROLLER|<---->|    VIEW    |
    |          |      |          |      |            |
    +----------+      +----------+      +------------+
    |          |            |           |            |
    | Business |            |           |   User     |
    |  Logic   |            |           | Interface  |
    |   Data   |            |           |            |
    +----------+            ^           +------------+
                            ^
    +----------+            ^
    |  User    |            |
    | Commands |------------+
    +----------+

