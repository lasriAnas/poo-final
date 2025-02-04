import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;

public class GestionEmployes {

    static Employe[] employes = new Employe[50];

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Employe E1 = new Employe(1, "John Doe", "Manager", 5000);
        Employe E2 = new Employe(2, "Jane Doe", "Developer", 4000);
        Employe E3 = new Employe(3, "Bob Smith", "Designer", 3000);
        Employe E4 = new Employe(4, "Alice Johnson", "Tester", 2000);
        Employe E5 = new Employe(5, "Charlie Brown", "Manager", 6000);

        employes[0] = E1;
        employes[1] = E2;
        employes[2] = E3;
        employes[3] = E4;
        employes[4] = E5;

        int choix;


        do {
            printMenu();
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterEmploye();
                    break;
                case 2:
                    modifierEmploye();
                    break;
                case 3:
                    supprimerEmploye();
                    break;
                case 4:
                    afficherEmployes();
                    break;
                case 5:
                    rechercherEmploye();
                    break;
                case 6:
                    calculerMasseSalariale();
                    break;
                case 7:
                    trierEmployesParSalaire();
                    break;
                case 8:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez sélectionner une option valide.");
            }
        } while (choix != 8);

        scanner.close();

    }

    public static void printMenu() {
        System.out.println("---------------------------");
        System.out.println("Menu de gestion des employés :");
        System.out.println("---------------------------");
        System.out.println("1. Ajouter un nouveau employe");
        System.out.println("2. Modifier un employe");
        System.out.println("3. Supprimer un employe");
        System.out.println("4. Afficher les employes");
        System.out.println("5. Rechercher un employe");
        System.out.println("6. calculer la masse salariale");
        System.out.println("7. Trier les employés par salaire");
        System.out.println("8. Quitter");
        System.out.print("Entrez votre choix : ");
    }
    //this checks if the array is empty and if the id is already in the table before adding the new employee

    public static void ajouterEmploye() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'id de l'employé : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Entrez le nom de l'employé : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez le poste de l'employé : ");
        String poste = scanner.nextLine();
        System.out.print("Entrez le salaire de l'employé : ");
        double salaire = scanner.nextDouble();
        scanner.nextLine(); // Consume newline left-over

        Employe newEmploye = new Employe(id, nom, poste, salaire);

        for (Employe employe : employes) {
            if (employe != null && employe.getId() == id) {
                System.out.println("L'id de l'employé existe déjà !");
                return;
            }
        }

        for (int i = 0; i < employes.length; i++) {
            if (employes[i] == null) {
                employes[i] = newEmploye;
                System.out.println("Employé ajouté avec succès !");
                return;
            }
        }
        System.out.println("La liste des employés est déjà pleine !");
    }

    //this checks if the employe with a certain id exists and if it does, it allows you to modify its information
    public static void modifierEmploye() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'id de l'employé : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        for (Employe employe : employes) {
            if (employe != null && employe.getId() == id) {
                System.out.print("Entrez le nouveau nom de l'employé : ");
                String nom = scanner.nextLine();
                System.out.print("Entrez le nouveau poste de l'employé : ");
                String poste = scanner.nextLine();
                System.out.print("Entrez le nouveau salaire de l'employé : ");
                double salaire = scanner.nextDouble();
                scanner.nextLine(); // Consume newline left-over

                employe.setNom(nom);
                employe.setPoste(poste);
                employe.setSalaire(salaire);

                System.out.println("Employé modifié avec succès !");
                return;
            }
        }
        System.out.println("Employé non trouvé !");
    }

    //this checks if the employe exists and if it does, it sets it to null
    public static void supprimerEmploye() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'id de l'employé : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        for (int i = 0; i < employes.length; i++) {
            if (employes[i] != null && employes[i].getId() == id) {
                employes[i] = null;
                System.out.println("Employé supprimé avec succès !");
                return;
            }
        }
        System.out.println("Employé non trouvé !");
    }

    // this loops over the employes array and prints each non-null employe
    public static void afficherEmployes() {
        for (Employe employe : employes) {
            if (employe != null) {
                System.out.println(employe);
            }
        }
    }
    //this searches using nom and poste (instead of nom or poste that was requested) because there could be multiple people with same name or job description
    public static void rechercherEmploye() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom de l'employé : ");
        String nom = scanner.nextLine();
        System.out.print("Entrer le poste de l'employé : ");
        String poste = scanner.nextLine();

        boolean found = false;
        for (Employe employe : employes) {
            if (employe != null) {
                if (Objects.equals(employe.getNom(), nom) && Objects.equals(employe.getPoste(), poste)) {
                    System.out.println(employe);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Employé non trouvé !");
        }
    }

    //calculates masseSalaire by iterating over the array and adding each emplyee's salaire together
    public static void calculerMasseSalariale() {
        double masseSalaire = 0;

        for (Employe employe:employes){
            if (employe != null){
                masseSalaire += employe.getSalaire();
            }

        }
        System.out.println("La masse salariale est de : " + masseSalaire);
    }

    //it uses array stream sorted method to treat the array based on the user input, then outputs the sorted objects
    public static void trierEmployesParSalaire() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Voulez-vous trier les employés par salaire en ordre croissant (1) ou décroissant (2) ? ");
        int choix = scanner.nextInt();

        if (choix == 1) {
            Arrays.stream(employes)
                    .filter(Objects::nonNull)
                    .sorted((e1, e2) -> Double.compare(e1.getSalaire(), e2.getSalaire()))
                    .forEach(System.out::println);
        } else if (choix == 2) {
            Arrays.stream(employes)
                    .filter(Objects::nonNull)
                    .sorted((e1, e2) -> Double.compare(e2.getSalaire(), e1.getSalaire()))
                    .forEach(System.out::println);
        } else {
            System.out.println("Choix invalide.");
            return;
        }

    }
}