-- TABLE: users (User accounts)
INSERT INTO users (id, username, email, password) VALUES
    (1, 'John Doe', 'john.doe@example.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoO/7R.4WTeX6ZyAZT3gF8akaj4nFQx1/K'),
    (2, 'Jane Smith', 'jane.smith@example.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoO/7R.4WTeX6ZyAZT3gF8akaj4nFQx1/K'),
    (3, 'Alice Johnson', 'alice.johnson@example.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoO/7R.4WTeX6ZyAZT3gF8akaj4nFQx1/K'),
    (4, 'Bob Brown', 'bob.brown@example.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoO/7R.4WTeX6ZyAZT3gF8akaj4nFQx1/K'),
    (5, 'Charlie Green', 'charlie.green@example.com', '$2a$10$7EqJtq98hPqEX7fNZaFWoO/7R.4WTeX6ZyAZT3gF8akaj4nFQx1/K');

-- TABLE: topics (Topics of posts)
INSERT INTO topics (id, name, description) VALUES
    (1, 'Programmation Java', 'Discussions sur le langage Java, les meilleures pratiques et les frameworks comme Spring et Jakarta EE.'),
    (2, 'Développement Frontend', 'Tout sur Angular, React et Vue.js, de la création d\'UI modernes à l\'optimisation des performances.'),
    (3, 'Bases de Données et SQL', 'Optimisation des requêtes, indexation et gestion des bases de données SQL et NoSQL.'),
    (4, 'Cloud et DevOps', 'Déploiement, conteneurisation avec Docker et orchestration Kubernetes dans le cloud AWS, GCP, Azure.'),
    (5, 'Sécurité et Cybersécurité', 'Techniques de sécurisation des applications, gestion des vulnérabilités et meilleures pratiques en cybersécurité.');

-- TABLE: users_topics (User subscriptions to topics)
INSERT INTO users_topics (user_id, topic_id) VALUES
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5),  -- John Doe is subscribed to all topics
    (2, 2), (2, 3), (2, 5),                 -- Jane Smith is subscribed to Frontend, Databases, and Security
    (3, 1), (3, 4), (3, 5),                 -- Alice Johnson is subscribed to Java, Cloud, and Security
    (4, 1), (4, 2), (4, 3), (4, 4);         -- Bob Brown is subscribed to Java, Frontend, Databases, and Cloud
                                                                                                -- Charlie Green is not subscribed to any topic

-- TABLE: posts (Articles on associated topics)
INSERT INTO posts (id, creation_date, title, content, author_id, topic_id) VALUES
    (1, '2023-08-03 20:25:25.000000', 'Spring Boot : Guide des bonnes pratiques', 'Spring Boot est un framework populaire qui facilite le développement Java. Pour optimiser son utilisation, il est recommandé d\'adopter une approche modulaire, de structurer le projet de manière claire et de configurer correctement les Beans. Ensuite, l\'utilisation d\'annotations comme @Service et @Repository permet de séparer la logique métier des accès aux données. Enfin, la mise en cache avec Redis et l\'optimisation des requêtes avec JPA peuvent considérablement améliorer les performances d\'une application.', 1, 1),
    (2, '2024-03-17 21:48:36.000000', 'Optimisation des performances SQL', 'Les bases de données relationnelles peuvent ralentir une application si elles ne sont pas bien optimisées. L\'indexation des colonnes souvent filtrées et l\'utilisation de vues matérialisées permettent d\'améliorer la rapidité des requêtes. Il est aussi recommandé de bien choisir les types de données et de limiter l\'utilisation des jointures complexes. L\'analyse des requêtes via EXPLAIN et l\'utilisation de caches comme Redis peuvent grandement réduire la charge sur la base de données.', 2, 3),
    (3, '2024-05-03 22:43:01.000000', 'Sécuriser son API avec OAuth2', 'L\'authentification des API est essentielle pour protéger les données des utilisateurs. OAuth2 est un protocole couramment utilisé pour sécuriser les accès. Il permet d\'émettre des tokens JWT qui garantissent une authentification sécurisée sans nécessiter de stocker des sessions côté serveur. Une bonne configuration des scopes et la validation des tokens côté backend assurent un haut niveau de sécurité dans les échanges de données.', 3, 5),
    (4, '2024-01-28 18:26:21.000000', 'Microservices et Kubernetes', 'Le déploiement de microservices avec Kubernetes permet une scalabilité et une résilience accrues. L\'utilisation d\'Ingress pour gérer le routage des requêtes et de ConfigMaps pour stocker les configurations permet d\'améliorer la maintenance. Mettre en place des probes de santé (liveness et readiness) assure que les pods déployés restent fonctionnels et réactifs face aux erreurs internes.', 4, 4),
    (5, '2024-09-12 03:35:01.000000', 'Les erreurs à éviter en développement Angular', 'Angular est un framework puissant, mais certaines erreurs courantes peuvent impacter les performances. Ne pas utiliser OnPush pour l\'optimisation du rendu, mal gérer les Observables avec RxJS, ou encore manipuler directement le DOM sont des pièges fréquents. L\'utilisation d\'un bon state management comme NgRx et le lazy loading des modules peuvent améliorer la performance et la maintenabilité des applications.', 1, 2),
    (6, '2023-11-02 07:58:53.000000', 'Automatiser les tests avec Jest et Cypress', 'Les tests sont un élément clé du développement moderne. Jest permet d\'écrire des tests unitaires efficaces en JavaScript tandis que Cypress est idéal pour les tests end-to-end. L\'utilisation de mocks et de fixtures permet d\'éviter la dépendance aux données réelles. Automatiser ces tests dans un pipeline CI/CD garantit la stabilité du code en production.', 2, 2),
    (7, '2024-10-09 15:00:10.000000', 'Git : Les bonnes pratiques de gestion de branches', 'Utiliser Git de manière efficace implique d\'adopter une stratégie de branches claire comme Git Flow. Les branches feature, develop et main permettent une gestion organisée du code. Les commits doivent être atomiques et les messages explicites pour assurer une bonne traçabilité des changements.', 3, 5),
    (8, '2025-02-05 11:44:33.000000', 'Introduction à Docker et aux conteneurs', 'Docker permet d\'isoler les applications dans des conteneurs légers. L\'utilisation d\'images optimisées, de multi-stage builds et de volumes persistants permet de réduire la taille des conteneurs et d\'améliorer la gestion des données. Les orchestrateurs comme Kubernetes prennent en charge ces conteneurs pour les déployer de manière scalable et résiliente.', 4, 4),
    (9, '2024-10-25 05:16:39.000000', 'L\'importance du monitoring en production', 'Avoir un système de monitoring en production permet d\'anticiper et de réagir rapidement aux incidents. Des outils comme Prometheus et Grafana permettent de collecter et d\'afficher des métriques clés sur les performances des services. L\'intégration d\'alertes automatiques avec PagerDuty ou Slack permet une réaction rapide en cas de problème critique.', 1, 3),
    (10, '2024-08-12 16:52:08.000000', 'La gestion des dépendances avec Maven et Gradle', 'Maven et Gradle sont deux outils populaires pour gérer les dépendances des projets Java. Maven utilise un fichier pom.xml tandis que Gradle utilise un build.gradle plus flexible. Il est conseillé d\'utiliser des versions figées et de surveiller les mises à jour de sécurité pour éviter les failles potentielles dans les bibliothèques utilisées.', 2, 1);

-- TABLE: comments (User comments on posts)
INSERT INTO comments (id, creation_date, content, author_id, post_id) VALUES
    (1, '2023-04-08 00:24:59.000000', 'Très bon article, merci pour les explications claires !', 2, 1),
    (2, '2023-05-02 20:52:25.000000', 'Je trouve que Spring Boot est vraiment puissant, surtout avec JPA.', 3, 1),
    (3, '2024-06-30 11:52:02.000000', 'Les optimisations SQL m\'ont beaucoup aidé, merci pour ces astuces.', 1, 2),
    (4, '2024-10-23 23:14:05.000000', 'Une approche pragmatique pour améliorer la sécurité des API.', 4, 3),
    (5, '2024-09-24 11:36:09.000000', 'Les microservices avec Kubernetes sont une vraie révolution.', 1, 4),
    (6, '2024-11-20 19:45:42.000000', 'Angular peut être compliqué, ces erreurs sont bonnes à connaître.', 2, 5),
    (7, '2024-09-04 23:27:45.000000', 'Jest et Cypress sont devenus indispensables dans mon workflow de test.', 3, 6),
    (8, '2023-05-07 13:34:55.000000', 'J\'aime bien Git Flow, ça aide beaucoup dans les projets d\'équipe.', 4, 7),
    (9, '2023-09-12 07:05:07.000000', 'Docker simplifie énormément le déploiement d\'applications.', 1, 8),
    (10, '2023-08-06 06:39:14.000000', 'Le monitoring en production est essentiel pour la stabilité.', 2, 9),
    (11, '2023-02-27 04:33:33.000000', 'Maven et Gradle ont chacun leurs avantages, bon comparatif.', 3, 10),
    (12, '2024-07-21 23:55:24.000000', 'J\'ajouterais que Redis peut aussi être utile en cache SQL.', 4, 2),
    (13, '2024-01-24 02:21:31.000000', 'La sécurité OAuth2 est parfois compliquée, mais bien expliquée ici.', 1, 3),
    (14, '2023-04-13 12:39:29.000000', 'Bon article sur Kubernetes, j\'ai découvert Ingress grâce à toi.', 2, 4),
    (15, '2023-08-14 05:34:45.000000', 'Le lazy loading dans Angular améliore vraiment les perfs.', 3, 5),
    (16, '2023-11-04 01:04:41.000000', 'Excellente explication sur les tests avec Jest et Cypress !', 2, 6),
    (17, '2024-09-02 23:29:23.000000', 'Je ne savais pas que Git Flow était aussi efficace, merci !', 3, 7),
    (18, '2024-09-12 13:10:30.000000', 'L\'utilisation des multi-stage builds dans Docker est un must.', 4, 8),
    (19, '2023-12-18 11:08:16.000000', 'Un bon monitoring peut sauver une prod, bien détaillé.', 1, 9),
    (20, '2023-05-26 01:02:06.000000', 'Les dépendances mal gérées sont une vraie plaie, bon rappel.', 2, 10);