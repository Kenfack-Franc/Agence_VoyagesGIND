1. Introduction
   
Le développement d’applications informatiques pour la gestion des entreprises est aujourd’hui un 
enjeu central dans l’optimisation des processus administratifs et commerciaux. Dans ce cadre, le 
présent projet vise à concevoir et implémenter une application Java permettant la gestion 
simplifiée d’une agence de voyage, en offrant une interface utilisateur intuitive pour 
l’enregistrement des clients, des destinations, et la gestion des réservations. 
Ce projet pédagogique a pour principal objectif de mettre en œuvre les connaissances en 
programmation orientée objet (POO) en Java, en y intégrant des concepts essentiels comme la 
manipulation de classes, la gestion des événements, et l’utilisation de bibliothèques graphiques 
comme Swing. 
L’application repose sur une architecture simple et modulaire, divisée en plusieurs classes 
principales : 
 La classe Client, représentant les informations personnelles des clients de l’agence ; 
 La classe Destination, définissant les lieux proposés au voyage ; 
 La classe Reservation, permettant de relier un client à une destination avec un nombre 
de personnes donné ; 
 Et enfin, la classe AgenceVoyageGUI, qui constitue le cœur de l’interface graphique de 
l’application, avec ses différentes fonctionnalités accessibles via des boutons et des boîtes 
de dialogue. 
Grâce à cette interface, l'utilisateur peut facilement : 
 Ajouter des clients et des destinations, 
 Créer des réservations, 
 Afficher l’ensemble des réservations effectuées. 
Ce projet met en lumière la capacité de Java à produire des applications interactives 
multiplateformes, et constitue une bonne introduction à la conception logicielle orientée 
utilisateur. Il offre également un aperçu des possibilités de gestion mémoire en local à l’aide de 
collections dynamiques comme ArrayList. 
Dans les sections suivantes, nous présenterons en détail l’analyse fonctionnelle de l’application, 
la structure du code, les fonctionnalités clés, l’interface graphique, les choix techniques, ainsi que 
les perspectives d’amélioration possibles.


2. Analyse fonctionnelle

   
L’analyse fonctionnelle permet de définir les besoins auxquels le programme doit répondre, ainsi 
que les fonctionnalités principales que l’application doit offrir à l’utilisateur. Dans ce projet, il 
s'agit de développer une application de gestion d’agence de voyage avec une interface utilisateur 
simple, accessible via des fenêtres interactives. L’utilisateur final est supposé être un agent 
administratif ou un gestionnaire dans une agence. 

2.1 Objectif général 

L’objectif principal est de permettre à une agence de voyage de : 
 Gérer sa base de clients, 
 Gérer une liste de destinations de voyage, 
 Gérer les réservations des clients vers les destinations choisies. 

2.2 Fonctionnalités attendues 

L’application doit permettre à l’utilisateur : 
  
Ajout d’un client 
Saisie des informations suivantes : 
o Nom 
o Prénom 
o Email 
Chaque client est automatiquement associé à un identifiant unique. 
 Ajout d’une destination 
Saisie des informations suivantes : 
o Ville 
o Pays 
o Prix par personne 
Chaque destination est également identifiée de manière unique. 
 Création d’une réservation 
o Sélection d’un client existant 
o Sélection d’une destination existante 
o Saisie du nombre de personnes 
o Calcul automatique du montant total (prix x nombre de personnes) 
o Enregistrement de la réservation dans la mémoire 
 Affichage des réservations 
Affichage dans une boîte de dialogue de toutes les réservations effectuées, avec les 
informations suivantes : 
o Nom du client 
o Destination choisie 
o Nombre de personnes 
o Montant total 
 Quitter l'application 
Fermeture propre de la fenêtre principale. 

2.3 Contraintes et choix techniques 

 L’interface est développée en Java Swing, ce qui permet une interaction graphique 
simple. 
 Les données sont maintenues en mémoire, à l’aide de ArrayList pour les listes de 
clients, de destinations, et de réservations. 
 L’application ne nécessite pas de connexion Internet, ni de base de données externe. 
 L’ensemble des entités est géré par des classes séparées, selon les principes de la 
programmation orientée objet. 

2.4 Cas d'utilisation typique 

Voici un scénario d’utilisation classique : 
1. L’agent ouvre l’application. 
2. Il ajoute un ou plusieurs clients. 
3. Il enregistre les destinations proposées. 
4. Un client souhaite effectuer une réservation. 
5. L’agent choisit le client et la destination, indique le nombre de personnes, puis valide. 
6. Il consulte la liste des réservations.
   
2.5 Améliorations envisagées
   
 Sauvegarde des données dans un fichier texte ou une base de données. 
 Ajout d’un système de modification / suppression de clients ou de réservations. 
 Mise en place d’un système de recherche ou de tri des réservations. 
 Ajout d’un onglet statistique : nombre de clients, destinations les plus demandées, 
chiffre d’affaires.


3. Structure du programme et architecture générale

   
L’application "Agence de Voyage" est structurée de manière modulaire, en respectant les 
principes fondamentaux de la programmation orientée objet (POO). Chaque entité du domaine 
(client, destination, réservation) est représentée par une classe spécifique, tandis que la gestion de 
l'interface utilisateur est centralisée dans une classe principale. Cette approche facilite la 
maintenance, la lisibilité du code et l’évolution future de l’application. 

3.1 Organisation globale 

Le programme contient quatre classes principales : 
Classe 
Rôle 
Client 
Destination 
Reservation 
Représente un client avec ses informations personnelles 
Représente un lieu de voyage avec son coût 
Lie un client à une destination, en tenant compte du nombre de personnes 
AgenceVoyageGUI Contient la fenêtre principale, les boutons, les événements, et les listes de 
gestion 

3.2 Diagramme structurel simplifié

   
+--------------------+        +---------------------+        ------+ 
+---------------
|      Client        |        |    Destination      |        |     Reservation      
| 
|--------------------|        |---------------------|        |---------------- 
| - id : int         |        | - id : int          |        | - id : int           
| 
| - nom : String     |        | - ville : String    |        | - client : 
Client    | 
| - prenom : String  |        | - pays : String     |        | - destination : 
Dest | 
| - email : String   |        | - prix : double     |        | - nbPersonnes : 
int  | 
+--------------------+        +---------------------+        | - montantTotal 
: dbl | 
+----------------------+ 
↑ Utilisés dans 
| 
| 
+-----------------------------+ 
|     AgenceVoyageGUI         | 
|-----------------------------| 
| - clients : List<Client>    | 
| - destinations : List<Dest> | 
| - reservations : List<Res>  | 
+-----------------------------+ 
| + main(String[])            | 
| + ajouterClient()           | 
| + ajouterDestination()      | 
| + faireReservation()        | 
| + afficherReservations()    | 
+-----------------------------+ 

3.3 Rôle et responsabilité des classes 

➤ Client 
 Attributs : identifiant, nom, prénom, email 
 Utilisé pour stocker l’identité d’un voyageur 
 Affiché dans la liste déroulante de sélection lors d’une réservation 
➤ Destination 
 Attributs : identifiant, ville, pays, prix par personne 
 Utilisé pour définir les lieux disponibles 
 Le prix unitaire permet de calculer le montant de la réservation 
➤ Reservation 
 Attributs : identifiant, client, destination, nombre de personnes 
 Calcule le montant total en multipliant le prix par le nombre de personnes 
 Stockée dans la liste reservations pour affichage 
➤ AgenceVoyageGUI 
 Interface graphique principale 
 Déclare les listes dynamiques (ArrayList) pour stocker les objets 
 Gère les événements déclenchés par les boutons : 
o Ajouter un client 
o Ajouter une destination 
o Faire une réservation 
o Voir les réservations 
o Quitter 

3.4 Gestion des identifiants 

Chaque classe (client, destination, réservation) utilise un identifiant unique, généré 
automatiquement grâce à un compteur statique (clientId, destinationId, reservationId) 
pour éviter les doublons. 

3.5 Données en mémoire 

 L’application ne fait appel à aucune base de données externe. 
 Toutes les informations sont stockées temporairement en mémoire vive via des objets 
ArrayList. 
 Cela signifie que les données sont perdues à la fermeture du programme, ce qui peut 
être amélioré dans une version future.


4. Détail des classes Java
   
   
4.1 Classe Client – Représentation d’un client 

class Client { 
int id; 
String nom, prenom, email; 
public Client(int id, String nom, String prenom, String email) { 
this.id = id; 
this.nom = nom; 
this.prenom = prenom; 
this.email = email; 
} 
public String toString() { 
return id + ": " + nom + " " + prenom; 
} 
} 
But : représenter un client de l’agence avec ses informations personnelles. 
Explication ligne par ligne : 
 int id; → identifiant unique du client. 
 String nom, prenom, email; → stockent respectivement son nom, prénom et adresse 
e-mail. 
 Le constructeur Client(...) permet d’initialiser les valeurs dès la création d’un client. 
 toString() est une méthode Java spéciale : elle permet d’afficher le client sous forme de 
texte lisible (ex : 1: Dupont Jean) dans les listes déroulantes. 

4.2 Classe Destination – Représentation d’un lieu de voyage 

class Destination { 
int id; 
String ville, pays; 
double prix; 
public Destination(int id, String ville, String pays, double prix) { 
this.id = id; 
this.ville = ville; 
this.pays = pays; 
this.prix = prix; 
} 
public String toString() { 
return id + ": " + ville + ", " + pays + " - " + prix + " €"; 
} 
} 
But : représenter une destination disponible pour les voyages. 
Explication : 
 ville et pays définissent la localisation. 
 prix représente le coût par personne pour aller à cette destination. 
 toString() permet d’afficher les destinations de façon conviviale dans l’interface (2: 
Rome, Italie - 200.0 €). 

4.3 Classe Reservation – Gestion d’une réservation 

class Reservation { 
int id; 
Client client; 
Destination destination; 
int nbPersonnes; 
double montantTotal; 
public Reservation(int id, Client client, Destination destination, int 
nbPersonnes) { 
this.id = id; 
this.client = client; 
this.destination = destination; 
this.nbPersonnes = nbPersonnes; 
this.montantTotal = destination.prix * nbPersonnes; 
} 
public String toString() { 
return "Res " + id + ": " + client.nom + " vers " + destination.ville 
+ 
} 
" x" + nbPersonnes + " = " + montantTotal + " €"; 
} 
But : représenter une réservation entre un client et une destination. 
Explication : 
 Client client → lien avec le client qui réserve. 
 Destination destination → lieu choisi pour le voyage. 
 nbPersonnes → nombre de personnes concernées par la réservation. 
 montantTotal → prix total (automatiquement calculé dans le constructeur). 
 toString() permet d’afficher la réservation sous une forme lisible : 
Res 3: Kenfack vers Paris x2 = 600.0 € 
Synthèse des responsabilités 
Classe 
Client 
Rôle principal 
Méthode clé 
Stocker les informations d’un voyageur toString() 
Destination Stocker les données d’un lieu 
toString() 
Reservation Relier client + destination + personnes toString() 


5. Classe AgenceVoyageGUI – Interface graphique et logique de

   
gestion 
Cette classe est le cœur de l'application graphique. Elle gère : 
 L'affichage de la fenêtre principale, 
 Les boutons et leurs actions (ajouter client, destination, faire réservation...), 
 Les listes de stockage des objets (clients, destinations, réservations). 

5.1 Déclaration des listes et compteurs 

static java.util.List<Client> clients = new ArrayList<>(); 
static java.util.List<Destination> destinations = new ArrayList<>(); 
static java.util.List<Reservation> reservations = new ArrayList<>(); 
static int clientId = 1, destinationId = 1, reservationId = 1; 
�
� Ces listes contiennent tous les objets créés dynamiquement pendant l'utilisation : 
 clients → liste de tous les clients enregistrés, 
 destinations → liste des destinations proposées, 
 reservations → toutes les réservations effectuées. 
�
� Les compteurs clientId, destinationId, reservationId permettent de générer 
automatiquement des identifiants uniques pour chaque nouvel objet. 

5.2 Méthode main() – Lancement de l’application 

public static void main(String[] args) { 
JFrame frame = new JFrame("Agence de Voyage"); 
frame.setSize(500, 400); 
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
frame.setLayout(new GridLayout(5, 1)); 
�
� Création de la fenêtre principale (JFrame) : 
 Titre : "Agence de Voyage" 
 Taille : 500x400 pixels 
 Disposition : GridLayout(5,1) signifie 5 lignes verticales, 1 seule colonne. 

5.3 Ajout des boutons et de leurs actions 

JButton btnClient = new JButton("Ajouter un client"); 
JButton btnDestination = new JButton("Ajouter une destination"); 
JButton btnReservation = new JButton("Faire une réservation"); 
JButton btnAfficher = new JButton("Voir les réservations"); 
JButton btnQuitter = new JButton("Quitter"); 
�
� Ces boutons déclenchent des actions spécifiques grâce aux ActionListener : 
btnClient.addActionListener(e -> ajouterClient()); 
btnDestination.addActionListener(e -> ajouterDestination()); 
btnReservation.addActionListener(e -> faireReservation()); 
btnAfficher.addActionListener(e -> afficherReservations()); 
btnQuitter.addActionListener(e -> System.exit(0)); 
Chaque clic appelle la méthode associée : 
 ajouterClient() : ouvre un formulaire client 
 ajouterDestination() : ouvre un formulaire destination 
 faireReservation() : permet de créer une réservation 
 afficherReservations() : affiche les réservations 
 System.exit(0) : quitte proprement le programme 

5.4 Méthode ajouterClient() 

JTextField nom = new JTextField(), prenom = new JTextField(), email = new 
JTextField(); 
Object[] message = { 
"Nom:", nom, 
"Prénom:", prenom, 
"Email:", email 
}; 
int option = JOptionPane.showConfirmDialog(null, message, "Ajouter Client", 
JOptionPane.OK_CANCEL_OPTION); 
�
� Utilise une boîte de dialogue avec champs de texte (JTextField) pour saisir les infos du 
client. 
Si l'utilisateur clique sur OK : 
clients.add(new Client(clientId++, nom.getText(), prenom.getText(), 
email.getText())); 
JOptionPane.showMessageDialog(null, "Client ajouté."); 
 Le client est créé avec les infos remplies, 
 Ajouté à la liste clients, 
 Et un message de confirmation est affiché. 

5.5 Méthode ajouterDestination() 

Principe identique à ajouterClient() mais avec des champs : 
 Ville 
 Pays 
 Prix par personne (à convertir en double) 
Conversion : 
double p = Double.parseDouble(prix.getText()); 

5.6 Méthode faireReservation() 

Fonction clé du programme : 
Client client = (Client) JOptionPane.showInputDialog(null, "Choisir un 
client", ..., clients.toArray(), clients.get(0)); 
Destination destination = (Destination) JOptionPane.showInputDialog(null, 
"Choisir une destination", ..., destinations.toArray(), destinations.get(0)); 
�
� L'utilisateur choisit un client et une destination via des listes déroulantes générées 
automatiquement à partir des listes existantes. 
Puis il saisit le nombre de personnes : 
String input = JOptionPane.showInputDialog("Nombre de personnes:"); 
int nb = Integer.parseInt(input); 
Ensuite, la réservation est créée et ajoutée à la liste. 

5.7 Méthode afficherReservations() 

StringBuilder sb = new StringBuilder(); 
for (Reservation r : reservations) { 
sb.append(r.toString()).append("\n"); 
} 
JOptionPane.showMessageDialog(null, sb.toString(), "Liste des réservations", 
JOptionPane.INFORMATION_MESSAGE); 
�
� Affiche toutes les réservations actuelles sous forme de texte dans une boîte de message. 
Récapitulatif des composants Swing utilisés 
Composant 
JFrame 
JButton 
JTextField Champs de texte 
Rôle 
Fenêtre principale 
Boutons d'action 
JOptionPane Boîtes de dialogues (saisie, liste, message) 
GridLayout Mise en forme verticale des composants 


6.Conclusion 


Ce programme Java propose une solution simple mais fonctionnelle pour gérer une petite agence 
de voyage à travers une interface graphique conçue avec Swing. Il permet l’enregistrement de 
clients, la création de destinations, la gestion de réservations, et l’affichage récapitulatif des 
opérations réalisées. 
Grâce à une structure bien organisée, reposant sur une séparation claire des classes (Client, 
Destination, Reservation) et une interface utilisateur basique mais intuitive 
(AgenceVoyageGUI), le programme constitue une excellente base pédagogique pour 
l’apprentissage de la programmation orientée objet et des interfaces graphiques en Java. 
Toutefois, comme toute application initiale, il présente certaines limites fonctionnelles et 
techniques : 
 Absence de persistance des données, 
 Interface graphique minimale, 
 Manque de contrôle sur les entrées utilisateur, 
 Aucune option de modification ou suppression des données enregistrées. 
C’est pourquoi une évolution du programme est fortement envisageable, en intégrant des 
fonctionnalités supplémentaires telles que : 
 La validation des saisies, 
 La sauvegarde automatique des données, 
 Une interface graphique enrichie (avec JTable, menus, onglets), 
 Et des opérations plus complètes sur les objets (modification, suppression, tri, recherche, 
statistiques).
