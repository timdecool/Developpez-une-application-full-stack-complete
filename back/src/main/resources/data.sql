INSERT INTO themes (name, description) VALUES
('Java', 'Actualités, bonnes pratiques et nouveautés autour du langage Java et de son écosystème.'),
('Angular', 'Maîtrisez le framework Angular : composants, services, RxJS et architecture front-end.'),
('Spring Boot', 'Développement backend avec Spring Boot : REST, sécurité, JPA et microservices.'),
('DevOps', 'CI/CD, Docker, Kubernetes et automatisation pour des livraisons rapides et fiables.'),
('JavaScript', 'Le langage du web : ES6+, TypeScript, frameworks et patterns modernes.'),
('UX/UI Design', 'Conception d''interfaces utilisateur intuitives et accessibles pour le web et le mobile.'),
('Base de données', 'SQL, NoSQL, optimisation de requêtes et modélisation de données.'),
('Sécurité web', 'Bonnes pratiques de sécurité : OWASP, authentification, chiffrement et gestion des vulnérabilités.');

INSERT INTO users (username, email, password) VALUES
('mirandaaa', 'miranda.portique@yahoo.fr', '$2b$10$OSMrBXhgUx2CdYJATNbsZOMllkebSBaDzpE8WC8JHgmuOU6D0USUC'),
('thomas_dev', 'thomas.martin@gmail.com', '$2b$10$OSMrBXhgUx2CdYJATNbsZOMllkebSBaDzpE8WC8JHgmuOU6D0USUC'),
('sophie_codes', 'sophie.bernard@outlook.com', '$2b$10$OSMrBXhgUx2CdYJATNbsZOMllkebSBaDzpE8WC8JHgmuOU6D0USUC'),
('kevin_fullstack', 'kevin.dupont@protonmail.com', '$2b$10$OSMrBXhgUx2CdYJATNbsZOMllkebSBaDzpE8WC8JHgmuOU6D0USUC');

INSERT INTO articles (title, content, created_at, updated_at, created_by, theme_id) VALUES
('Java 21 : les nouveautés à connaître',
'Java 21 est une version LTS majeure qui apporte de nombreuses fonctionnalités attendues depuis longtemps. Parmi les plus notables, les virtual threads issus de Project Loom permettent de gérer des milliers de connexions simultanées sans saturer le pool de threads OS. Les record patterns et le pattern matching for switch arrivent également en version stable, rendant le code plus expressif et moins verbeux.\n\nCette version marque aussi une maturité croissante de l''écosystème Java moderne. Les sequenced collections introduisent une interface unifiée pour accéder aux premiers et derniers éléments d''une collection ordonnée. Pour les équipes qui hésitaient encore à migrer depuis Java 11 ou 17, Java 21 offre un argument de poids : la stabilité d''une LTS combinée à des fonctionnalités qui réduisent réellement la complexité du code quotidien.',
'2026-01-15 10:00:00', '2026-01-15 10:00:00', 1, 1),

('Les virtual threads en Java : révolution ou gadget ?',
'Project Loom a mis des années à arriver dans le JDK, mais le résultat en vaut la peine. Les virtual threads sont des threads légers gérés par la JVM plutôt que par le système d''exploitation. Là où un thread OS consomme environ 1 Mo de mémoire, un virtual thread n''en consomme que quelques Ko. Concrètement, une application qui gérait 200 threads simultanés peut désormais en gérer des millions sans modification majeure du code existant.\n\nL''adoption dans Spring Boot est particulièrement simple depuis la version 3.2 : une seule propriété de configuration suffit à basculer l''exécuteur vers les virtual threads. Cela dit, il faut garder en tête que les virtual threads ne sont pas une solution miracle. Les opérations CPU-bound ne bénéficient pas de ce mécanisme, et certaines librairies qui utilisent des synchronized blocs peuvent provoquer du "pinning" et annuler les bénéfices. Une bonne compréhension du modèle reste indispensable avant de l''adopter en production.',
'2026-01-20 14:30:00', '2026-01-20 14:30:00', 2, 1),

('Angular 17 : le nouveau control flow',
'Angular 17 introduit une nouvelle syntaxe de control flow directement dans les templates, remplaçant les directives structurelles NgIf, NgFor et NgSwitch. La syntaxe @if, @for et @switch est plus proche du JavaScript natif, plus lisible, et surtout plus performante car elle est traitée au niveau du compilateur plutôt qu''à l''exécution. La directive @for impose désormais l''utilisation de track, ce qui force les bonnes pratiques et améliore les performances de rendu.\n\nCette évolution s''inscrit dans une tendance plus large de simplification du framework. Combiné aux standalone components devenus la norme, Angular se débarrasse progressivement de sa réputation de framework verbeux et complexe. La migration depuis l''ancienne syntaxe est assistée par des schematics automatiques fournis par l''équipe Angular, ce qui facilite grandement la transition pour les projets existants.',
'2026-02-01 09:00:00', '2026-02-01 09:00:00', 3, 2),

('Standalone components : pourquoi abandonner les NgModules',
'Les NgModules ont longtemps été l''une des principales sources de confusion pour les développeurs qui découvrent Angular. Déclarer un composant, l''ajouter à un module, importer ce module ailleurs... la cérémonie était fastidieuse et souvent source d''erreurs difficiles à déboguer. Les standalone components, stables depuis Angular 15 et recommandés par défaut depuis Angular 17, suppriment cette indirection en permettant à chaque composant de déclarer directement ses dépendances.\n\nL''impact sur l''architecture est significatif. Le tree-shaking devient plus efficace puisque le compilateur sait exactement ce que chaque composant utilise. Le lazy loading se simplifie également, car on peut charger un composant standalone directement sans avoir à créer un module dédié. Pour les nouveaux projets, l''adoption est évidente. Pour les projets existants, la migration peut se faire progressivement composant par composant, les deux approches étant compatibles.',
'2026-02-10 11:00:00', '2026-02-10 11:00:00', 1, 2),

('Spring Security 6 : la nouvelle configuration',
'Spring Security 6, livré avec Spring Boot 3, rompt avec l''approche historique basée sur l''extension de WebSecurityConfigurerAdapter. Cette classe est désormais supprimée, et la configuration se fait entièrement via des beans déclarés dans une classe annotée @Configuration. La SecurityFilterChain remplace l''override de configure(HttpSecurity), ce qui rend la configuration plus explicite et plus facile à tester unitairement.\n\nLa migration peut sembler intimidante au premier abord, surtout sur des projets avec une sécurité complexe. Mais une fois la nouvelle approche assimilée, le code est souvent plus lisible et mieux organisé. Les lambda expressions rendent la chaîne de configuration fluide et concise. Un point d''attention : la gestion des CORS a également évolué et nécessite désormais la déclaration d''un bean CorsConfigurationSource pour fonctionner correctement avec le filtre de sécurité.',
'2026-02-15 16:00:00', '2026-02-15 16:00:00', 2, 3),

('Docker en production : les erreurs à éviter',
'Docker est devenu incontournable dans les workflows de développement moderne, mais son utilisation en production réserve quelques pièges. La première erreur classique est d''utiliser des images trop lourdes. Partir d''une image ubuntu ou debian pour faire tourner une application Node.js ou Java, c''est embarquer des centaines de mégaoctets inutiles. Les images alpine ou distroless réduisent drastiquement la surface d''attaque et le temps de transfert. Le multi-stage build est également indispensable pour ne pas inclure les outils de compilation dans l''image finale.\n\nLa gestion des secrets est un autre point critique souvent négligé. Passer des mots de passe en variables d''environnement en clair dans un docker-compose.yml commité sur Git est une erreur de sécurité majeure. Les solutions comme Docker Secrets, les fichiers .env exclus du versioning, ou les outils de gestion de secrets comme Vault ou AWS Secrets Manager sont à privilégier. Enfin, l''absence de healthchecks est une omission fréquente qui empêche les orchestrateurs de détecter un conteneur en état de fonctionnement dégradé.',
'2026-03-01 08:00:00', '2026-03-01 08:00:00', 4, 4),

('TypeScript 5 : les decorators enfin standardisés',
'Après des années de cohabitation entre les decorators expérimentaux d''TypeScript et la proposition TC39, TypeScript 5 implémente enfin les decorators selon le standard ECMAScript. La différence est fondamentale : les anciens decorators s''exécutaient à la déclaration de la classe, les nouveaux s''exécutent après. Cette évolution casse la compatibilité avec certaines librairies comme TypeORM ou des versions anciennes de NestJS qui reposaient sur l''implémentation expérimentale.\n\nPour les projets Angular, la bonne nouvelle est que le framework gère cette transition de manière transparente depuis Angular 17. Les decorators Angular (@Component, @Injectable, etc.) continuent de fonctionner normalement. Pour NestJS, une mise à jour vers la version 10+ est nécessaire pour bénéficier de la compatibilité avec les nouveaux decorators. L''effort de migration est généralement limité, mais une lecture attentive des changelogs des librairies tierces reste indispensable avant de mettre à jour.',
'2026-03-10 13:00:00', '2026-03-10 13:00:00', 3, 5),

('Optimiser ses requêtes SQL : les index expliqués',
'Les index sont l''un des outils les plus puissants pour améliorer les performances d''une base de données, et l''un des plus mal compris. Un index permet à la base de données de trouver rapidement les lignes correspondant à une condition sans scanner toute la table. Concrètement, un index sur une colonne email transforme une recherche en O(n) en O(log n). La commande EXPLAIN (ou EXPLAIN ANALYZE sur PostgreSQL) est votre meilleure alliée pour comprendre comment la base exécute vos requêtes et identifier les full table scans problématiques.\n\nCependant, les index ne sont pas gratuits. Chaque index ralentit les opérations d''écriture (INSERT, UPDATE, DELETE) car la base doit maintenir la structure de l''index en plus des données. Sur une table avec beaucoup d''écritures, multiplier les index peut dégrader les performances globales. La règle d''or : indexez les colonnes utilisées dans les clauses WHERE, JOIN et ORDER BY de vos requêtes les plus fréquentes, et surveillez l''utilisation réelle de chaque index avec les vues système de votre SGBD.',
'2026-03-20 10:30:00', '2026-03-20 10:30:00', 1, 7),

('JWT vs Sessions : que choisir en 2026 ?',
'Le débat JWT versus sessions côté serveur est loin d''être tranché, et le choix dépend fortement du contexte de votre application. Les JWT ont l''avantage d''être stateless : le serveur n''a pas besoin de stocker quoi que ce soit, ce qui facilite le passage à l''échelle horizontale. Chaque token contient toutes les informations nécessaires et est vérifié cryptographiquement. C''est particulièrement adapté aux architectures microservices où plusieurs services doivent authentifier les requêtes sans partager une session centralisée.\n\nMais les JWT ont des inconvénients souvent sous-estimés. La révocation d''un token avant son expiration est complexe : sans blacklist côté serveur, un token volé reste valide jusqu''à son expiration. Les sessions côté serveur permettent une invalidation immédiate, ce qui est crucial pour des fonctionnalités comme la déconnexion sur tous les appareils. Pour une application monolithique classique avec des besoins de sécurité stricts, les sessions restent souvent le meilleur choix. Pour une API consommée par des clients mobiles et des SPAs, les JWT avec des tokens de courte durée et un système de refresh tokens offrent un bon équilibre.',
'2026-04-01 09:00:00', '2026-04-01 09:00:00', 2, 8),

('Accessibilité web : les bases que tout dev doit connaître',
'L''accessibilité web est trop souvent traitée comme une contrainte légale ou une feature optionnelle, alors qu''elle devrait être une composante naturelle du développement. Les personnes en situation de handicap représentent environ 15% de la population mondiale, et une interface inaccessible les exclut purement et simplement. Les bases sont pourtant simples à intégrer dès le départ : utiliser les balises HTML sémantiques correctes (button pour les actions, a pour la navigation), assurer un contraste suffisant entre le texte et le fond, et s''assurer que toutes les interactions sont accessibles au clavier.\n\nLes attributs ARIA (Accessible Rich Internet Applications) permettent d''enrichir la sémantique des éléments HTML natifs pour les lecteurs d''écran. Mais attention : un mauvais usage d''ARIA est souvent pire que pas d''ARIA du tout. La règle numéro un est de toujours préférer l''élément HTML natif approprié avant de recourir à ARIA. Des outils comme Lighthouse, axe ou WAVE permettent d''auditer automatiquement l''accessibilité de vos pages et d''identifier les problèmes les plus courants. Intégrez ces audits dans votre pipeline CI/CD pour éviter les régressions.',
'2026-04-05 14:00:00', '2026-04-05 14:00:00', 3, 6);

INSERT INTO subscriptions (user_id, theme_id, subscribed_at) VALUES
(1, 1, '2026-01-01 00:00:00'),
(1, 2, '2026-01-01 00:00:00'),
(2, 1, '2026-01-05 00:00:00'),
(2, 3, '2026-01-05 00:00:00'),
(3, 2, '2026-01-10 00:00:00'),
(3, 4, '2026-01-10 00:00:00'),
(4, 5, '2026-01-15 00:00:00'),
(4, 8, '2026-01-15 00:00:00');

INSERT INTO comments (content, created_by, article_id, created_at, updated_at) VALUES
('Super article, les virtual threads changent vraiment la donne pour nos APIs haute charge !', 2, 1, '2026-01-16 09:00:00', '2026-01-16 09:00:00'),
('Merci pour cet article, j''avais du mal à saisir l''intérêt de Project Loom. C''est beaucoup plus clair maintenant.', 3, 1, '2026-01-17 11:00:00', '2026-01-17 11:00:00'),
('Tu as des benchmarks comparatifs avec les threads classiques ? J''aimerais bien voir les chiffres sur une API REST typique.', 4, 2, '2026-01-21 10:00:00', '2026-01-21 10:00:00'),
('La migration vers le nouveau control flow est vraiment simple, je l''ai fait en une après-midi sur un projet moyen.', 1, 3, '2026-02-03 14:00:00', '2026-02-03 14:00:00'),
('Attention, @switch ne supporte pas encore toutes les expressions complexes dans certaines versions. Vérifiez bien votre version d''Angular avant de migrer.', 4, 3, '2026-02-04 09:30:00', '2026-02-04 09:30:00'),
('J''ai migré mon projet Angular 14 vers les standalone components, le gain en lisibilité est immédiat. Le code est tellement plus simple à appréhender.', 2, 4, '2026-02-12 16:00:00', '2026-02-12 16:00:00'),
('La nouvelle config Spring Security est bien plus intuitive, mais la doc officielle manque encore d''exemples concrets pour les cas complexes.', 1, 5, '2026-02-17 10:00:00', '2026-02-17 10:00:00'),
('En production on a aussi eu des soucis avec les logs qui exposaient des infos sensibles, pensez à configurer Logback correctement pour masquer les données critiques.', 3, 6, '2026-03-02 08:30:00', '2026-03-02 08:30:00'),
('Excellent rappel sur les index, on sous-estime vraiment leur impact jusqu''au jour où la prod ralentit et qu''on se retrouve à investiguer en urgence.', 4, 8, '2026-03-22 11:00:00', '2026-03-22 11:00:00'),
('Pour les projets stateless, JWT reste mon choix par défaut, mais la gestion de la révocation est un vrai casse-tête que beaucoup de projets ne résolvent pas correctement.', 1, 9, '2026-04-02 10:00:00', '2026-04-02 10:00:00'),
('Très bon article sur l''accessibilité. On devrait tous intégrer ces pratiques dès le début et pas en fin de projet quand tout est à refaire.', 2, 10, '2026-04-06 09:00:00', '2026-04-06 09:00:00');