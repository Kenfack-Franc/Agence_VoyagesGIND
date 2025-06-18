import javax.swing.*;
import java.awt.*;
import java.util.*;

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

class Destination {
    int id;
    String ville, pays;
    double prix;
    int placesDisponibles;
    String typeAvion;

    public Destination(int id, String ville, String pays, double prix, int placesDisponibles, String typeAvion) {
        this.id = id;
        this.ville = ville;
        this.pays = pays;
        this.prix = prix;
        this.placesDisponibles = placesDisponibles;
        this.typeAvion = typeAvion;
    }

    public String toString() {
        return id + ": " + ville + ", " + pays + " - " + prix + " € (" + typeAvion + ", places: " + placesDisponibles + ")";
    }
}

class Reservation {
    int id;
    Client client;
    Destination destination;
    int nbPersonnes;
    double montantTotal;
    String date;

    public Reservation(int id, Client client, Destination destination, int nbPersonnes, String date) {
        this.id = id;
        this.client = client;
        this.destination = destination;
        this.nbPersonnes = nbPersonnes;
        this.date = date;
        this.montantTotal = destination.prix * nbPersonnes;
        destination.placesDisponibles -= nbPersonnes;
    }

    public String toString() {
        return "Res " + id + ": " + client.nom + " vers " + destination.ville +
                " x" + nbPersonnes + " = " + montantTotal + " € (date: " + date +
                ", places restantes: " + destination.placesDisponibles + ")";
    }
}

class AgenceVoyageGUI {
    static java.util.List<Client> clients = new ArrayList<>();
    static java.util.List<Destination> destinations = new ArrayList<>();
    static java.util.List<Reservation> reservations = new ArrayList<>();

    static int clientId = 1, destinationId = 1, reservationId = 1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Agence de Voyage");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1));

        JButton btnClient = new JButton("Ajouter un client");
        JButton btnDestination = new JButton("Ajouter une destination");
        JButton btnReservation = new JButton("Faire une réservation");
        JButton btnAfficher = new JButton("Voir les réservations");
        JButton btnQuitter = new JButton("Quitter");

        frame.add(btnClient);
        frame.add(btnDestination);
        frame.add(btnReservation);
        frame.add(btnAfficher);
        frame.add(btnQuitter);

        btnClient.addActionListener(e -> ajouterClient());
        btnDestination.addActionListener(e -> ajouterDestination());
        btnReservation.addActionListener(e -> faireReservation());
        btnAfficher.addActionListener(e -> afficherReservations());
        btnQuitter.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    static void ajouterClient() {
        JTextField nom = new JTextField();
        JTextField prenom = new JTextField();
        JTextField email = new JTextField();

        Object[] message = {
                "Nom:", nom,
                "Prénom:", prenom,
                "Email:", email
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Ajouter Client", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            clients.add(new Client(clientId++, nom.getText(), prenom.getText(), email.getText()));
            JOptionPane.showMessageDialog(null, "Client ajouté.");
        }
    }

    static void ajouterDestination() {
        JTextField ville = new JTextField();
        JTextField pays = new JTextField();
        JTextField prix = new JTextField();

        String[] typesAvion = {"Commercial", "Privé"};
        JComboBox<String> typeAvion = new JComboBox<>(typesAvion);

        JTextField places = new JTextField();
        places.setEditable(false);

        typeAvion.addActionListener(e -> {
            String selection = (String) typeAvion.getSelectedItem();
            if ("Privé".equals(selection)) {
                places.setText("15");
            } else if ("Commercial".equals(selection)) {
                places.setText("70");
            }
        });

        places.setText("70");

        Object[] message = {
                "Ville:", ville,
                "Pays:", pays,
                "Prix par personne:", prix,
                "Type d'avion:", typeAvion,
                "Places disponibles:", places
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Ajouter Destination", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                double p = Double.parseDouble(prix.getText());
                String type = (String) typeAvion.getSelectedItem();
                int dispo = Integer.parseInt(places.getText());

                destinations.add(new Destination(destinationId++, ville.getText(), pays.getText(), p, dispo, type));
                JOptionPane.showMessageDialog(null, "Destination ajoutée.\nPlaces disponibles: " + dispo);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erreur : prix invalide.");
            }
        }
    }

    static void faireReservation() {
        if (clients.isEmpty() || destinations.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ajoutez d'abord un client et une destination.");
            return;
        }

        Client client = (Client) JOptionPane.showInputDialog(null, "Choisir un client:", "Client",
                JOptionPane.QUESTION_MESSAGE, null, clients.toArray(), clients.get(0));

        Destination destination = (Destination) JOptionPane.showInputDialog(null, "Choisir une destination:", "Destination",
                JOptionPane.QUESTION_MESSAGE, null, destinations.toArray(), destinations.get(0));

        if (destination.placesDisponibles == 0) {
            JOptionPane.showMessageDialog(null, "Plus de places disponibles pour ce vol.");
            return;
        }

        String input = JOptionPane.showInputDialog("Nombre de places à réserver (max " + destination.placesDisponibles + "):");
        int nb;
        try {
            nb = Integer.parseInt(input);
            if (nb <= 0 || nb > destination.placesDisponibles) {
                JOptionPane.showMessageDialog(null, "Nombre de places invalide.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrée invalide.");
            return;
        }

        String[] mois = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet",
                "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
        Integer[] jours = new Integer[31];
        for (int i = 0; i < 31; i++) jours[i] = i + 1;

        JComboBox<String> moisBox = new JComboBox<>(mois);
        JComboBox<Integer> jourBox = new JComboBox<>(jours);

        Object[] dateFields = {"Jour:", jourBox, "Mois:", moisBox};
        int option = JOptionPane.showConfirmDialog(null, dateFields, "Date de Réservation", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String date = jourBox.getSelectedItem() + " " + moisBox.getSelectedItem();
            reservations.add(new Reservation(reservationId++, client, destination, nb, date));
            JOptionPane.showMessageDialog(null, "Réservation enregistrée pour le " + date +
                    ".\nPlaces restantes: " + destination.placesDisponibles);
        }
    }

    static void afficherReservations() {
        if (reservations.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Aucune réservation trouvée.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Reservation r : reservations) {
            sb.append(r.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Liste des réservations", JOptionPane.INFORMATION_MESSAGE);
    }
}
