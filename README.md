# P6 - Développez une application ful-stack complète

**Auteur :** Timothé DECOOL - **Date :** 19/04/2026

## Présentation projet

Projet réalisé dans le cadre de la formation "Développeur Full-Stack - Java et Angular" sur OpenClassrooms.
L'application "MDD" est un MVP (minimal valuable product) d'un réseau social dédié aux dévelopeurs. Il y est possible de :
- Créer des articles 
- Lire des articles
- Commenter des articles
- S'abonner à des thèmes et se désabonner
- Créer un compte utilisateur, et le mettre à jour

Des données d'exemple sont disponibles en base par défaut et ont été générées par intelligence artificielle.

## Pile technique

### Frontend
- **Angular** 21.2.6
- **Angular Material** 21.2.4
- **TypeScript** 5.9.3
- **Node.js** 18+

### Backend
- **Java** 17
- **Spring Boot** 2.7.3
- **Lombok** 1.18.30
- **JJWT** 0.12.6
- **MySQL** 8.0

### Infrastructure
- **Docker** & **Docker Compose**

## Démarrer l'application

Clonez le projet sur un dépôt local avec Git :
> git clone https://github.com/timdecool/Developpez-une-application-full-stack-complete.git

Démarrez l'application avec Docker en utilisant la commande suivante :
> docker compose up --build

Vous pouvez alors accéder à l'application à l'adresse suivante :
> http://localhost

Pour stopper l'application, utilisez la commande suivante :
> docker compose down -v
