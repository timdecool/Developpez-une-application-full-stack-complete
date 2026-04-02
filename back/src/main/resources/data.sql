INSERT INTO themes (name, description) VALUES ('Java', 'Java language news');
INSERT INTO themes (name, description) VALUES ('Angular', 'Angular framework best practices');
INSERT INTO users (username, email, password) VALUES ('mirandaaa', 'miranda.portique@yahoo.fr', 'coucou123');
INSERT INTO articles(title, content, created_at, updated_at, created_by, theme_id)
VALUES ('Mon article sur Java !', 'Voici un premier aperçu de mes connaissances sur Java.', '2026-04-01T00:00:00', '2026-04-01T00:00:00', 1, 1);