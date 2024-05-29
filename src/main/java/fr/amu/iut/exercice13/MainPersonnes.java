package fr.amu.iut.exercice13;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class MainPersonnes {

    private static ObservableList<Personne> lesPersonnes;
    private static ListChangeListener<Personne> unChangementListener;
    private static ListChangeListener<Personne> plusieursChangementsListener;

    public static void main(String[] args) {
        lesPersonnes = FXCollections.observableArrayList(personne -> new javafx.beans.Observable[] {personne.ageProperty()});

        unChangementListener = change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Personne p : change.getAddedSubList()) {
                        System.out.println("Ajouté: " + p.getNom());
                    }
                }
                if (change.wasRemoved()) {
                    for (Personne p : change.getRemoved()) {
                        System.out.println("Supprimé: " + p.getNom());
                    }
                }
            }
        };

        lesPersonnes.addListener(unChangementListener);
        //question1();
        //question2();
        //question3();
        //question5();
    }

    public static void question1() {
        Personne cedric = new Personne("cedric", 20);
        Personne theo = new Personne("theo", 40);
        Personne estelle = new Personne("estelle", 60);
        lesPersonnes.add(cedric);
        lesPersonnes.add(theo);
        lesPersonnes.add(estelle);
    }

    public static void question2() {
        Personne cedric = new Personne("cedric", 20);
        Personne theo = new Personne("theo", 40);
        Personne estelle = new Personne("estelle", 60);
        lesPersonnes.add(cedric);
        lesPersonnes.add(theo);
        lesPersonnes.add(estelle);
        lesPersonnes.remove(theo);
    }

    public static void question3() {
        Personne cedric = new Personne("cedric", 20);
        Personne theo = new Personne("theo", 40);
        Personne estelle = new Personne("estelle", 60);
        lesPersonnes.add(cedric);
        lesPersonnes.add(theo);
        lesPersonnes.add(estelle);
        theo.setAge(5);
        System.out.println("theo à : " + theo.getAge() + " ans");
    }

    public static void question5() {
        plusieursChangementsListener = change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    for (Personne p : change.getAddedSubList()) {
                        System.out.println("Ajouté: " + p.getNom());
                    }
                }
                if (change.wasRemoved()) {
                    for (Personne p : change.getRemoved()) {
                        System.out.println("Supprimé: " + p.getNom());
                    }
                }
                if (change.wasUpdated()) {
                    for (Personne p : lesPersonnes) {
                        System.out.println(p.getNom() + " a maintenant " + p.getAge() + " ans");
                    }
                }
            }
            System.out.println("Série de changements terminée.");
        };

        lesPersonnes.addListener(plusieursChangementsListener);

        Personne cedric = new Personne("cedric", 20);
        Personne theo = new Personne("theo", 40);
        Personne estelle = new Personne("estelle", 60);
        lesPersonnes.addAll(cedric, theo, estelle);
        for (Personne p : lesPersonnes) {
            p.setAge(p.getAge() + 10);
        }
        lesPersonnes.removeAll(theo, cedric);
    }
}