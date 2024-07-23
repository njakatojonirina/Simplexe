package utilitaire;
import java.util.Vector;

import forme.Canonique;
import forme.Standard;
import forme.TableauInitiale;

public class Utilitaire {
    public void Affichage_Forme_Standard(Canonique canonique){
        try {
            
        } catch (Exception e) {
            throw e;
        }
    }
    public Standard Forme_Standard(Canonique canonique){
        Standard standard = new Standard();
        try {
            double[][] contrainte_vrai = canonique.getContrainte_vrai();
            double[] partie_droite = canonique.getPartie_droite();
            int taille_tab1 = contrainte_vrai.length;
            int taille_tab2 = (contrainte_vrai[0].length)+2;
            double[][] new_cntrte_vrai = new double[taille_tab1][taille_tab2];
            int t = 0;
            for(int i = 0 ; i < new_cntrte_vrai.length ; i++){
                for(int j = 0 ; j < new_cntrte_vrai[0].length ; j++){
                    if (j == taille_tab1) {
                        new_cntrte_vrai[i][j] = 1;
                    }else if(j == (taille_tab2-1)){
                        new_cntrte_vrai[i][j] = partie_droite[t];
                        t++;
                    }else{
                        new_cntrte_vrai[i][j] = contrainte_vrai[i][j];
                    }
                }
            }
            standard.setForme_Standard(new_cntrte_vrai);
        } catch (Exception e) {
            throw e;
        }
        return standard;
    }
    public double[][] MatriceAutomatique(Canonique canonique){
        double[][] contrainte_vrai = canonique.getContrainte_vrai();
        int taille_tab1 = contrainte_vrai.length;
        int taille_tab2 = contrainte_vrai[0].length;
        double[][] Auto_matrice = new double[taille_tab1][taille_tab2];
        for(int i = 0 ; i < Auto_matrice.length ; i++){
            for(int j = 0 ; j < Auto_matrice[0].length ; j++){
                if (j == i) {
                    Auto_matrice[i][j] = 1;
                }else{
                    Auto_matrice[i][j] = 0;
                }
            }
        }
        return Auto_matrice;
    }
    public TableauInitiale Tableau_Simplexe(Canonique canonique){
        TableauInitiale tableau_Initiale = new TableauInitiale();
        try {
            double[][] contrainte_vrai = canonique.getContrainte_vrai();
            double[] partie_droite = canonique.getPartie_droite();
            double[] objective = canonique.getObjective();
            double[][] Auto_matrice = MatriceAutomatique(canonique);
            int t = 0;
            int l = 0;
            int k = 0;
            int taille_tab1 = (contrainte_vrai.length) + 1; 
            int taille_tab2 = (contrainte_vrai.length + Auto_matrice.length) + 1; 
            double[][] Simplexe_Initiale = new double[taille_tab1][taille_tab2];
            for(int i = 0 ; i < Simplexe_Initiale.length ; i++){
                for(int j = 0 ; j < Simplexe_Initiale[0].length ; j++){
                    if (j == (taille_tab2-1) && i != (Simplexe_Initiale.length)-1) {
                        Simplexe_Initiale[i][j] = partie_droite[l];
                        l++;
                    }
                    else if (i == (Simplexe_Initiale.length)-1) {
                        if (k < objective.length) {
                            Simplexe_Initiale[i][j] = objective[k];
                            k++;
                        }else{
                            Simplexe_Initiale[i][j] = 0;
                        }
                    }
                    else if (j > (contrainte_vrai[0].length)-1 && j < (Simplexe_Initiale[0].length)) {
                        Simplexe_Initiale[i][j] = Auto_matrice[i][t];
                        if (t == (contrainte_vrai.length)-1) {
                            t = 0;
                        }else{
                            t++;
                        }
                    }
                    else{
                        Simplexe_Initiale[i][j] = contrainte_vrai[i][j];
                    }
                }
            }
            tableau_Initiale.setTableau_Initiale(Simplexe_Initiale);
            tableau_Initiale.Affichage();
            System.out.println( );
        } catch (Exception e) {
            throw e;
        }
        return tableau_Initiale;
    }
    public int maximum(double[] tab){
        double max = tab[0];
        int indice = 0;
        try {
            for (int i = 1 ; i < tab.length ; i++) {
                if (max < tab[i]) {
                    max = tab[i];
                    indice = i;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return indice;
    }
    public int minimum(double[] tab){
        double min = tab[0];
        int indice = 0;
        try {
            for (int i = 1 ; i < tab.length ; i++) {
                if (min > tab[i]) {
                    min = tab[i];
                    indice = i;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return indice;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public int[] Recherche_intersection(double[][] tableau_entier){
        int[] intersection = new int[2];
        try {
            int indice_dernier_ligne = (tableau_entier.length)-1;
            int dernier_ligne_max = maximum(tableau_entier[indice_dernier_ligne]);
            int indice_dernier_colon = (tableau_entier[0].length)-1;
            Vector tab = new Vector();
            for(int i = 0 ; i < (tableau_entier.length-1) ; i++ ){
                double numerateur = tableau_entier[i][indice_dernier_colon];
                double denominateur = tableau_entier[i][dernier_ligne_max];
                if (denominateur >= 0) {
                    double division = (numerateur/denominateur);
                    tab.add(division);
                }
            }
            double[] new_tab = new double[tab.size()];
            for (int i = 0; i < tab.size(); i++) {
                new_tab[i] = Double.parseDouble(tab.elementAt(i).toString());
            }
            int indice_division_minimum = minimum(new_tab);
            intersection[0] = indice_division_minimum;
            intersection[1] = dernier_ligne_max;
        } catch (Exception e) {
            throw e;
        }
        return intersection;
    }
    public void Affichage(double[][] tab){
        int taille1 = tab.length;
        int taille2 = tab[0].length;
        for (int i = 0 ; i < taille1 ; i++) {
            for (int j = 0 ; j < taille2 ; j++){
                if (j != 0 && j % (taille2-1) == 0) {
                    System.out.print(tab[i][j]);
                    System.out.println(" ");
                }
                else{
                    System.out.print(tab[i][j] + " | " );
                }
            }
        }
        System.out.println(" ");
    }
    public double[][] conversion_en_un(double[][] tab,int[] intersection){
        double[][] new_tab = new double[tab.length][tab[0].length];
        double[] morceaux = new double[tab[0].length];
        try {
            double value = tab[intersection[0]][intersection[1]];
            if (value == 1.0) {
                return tab;
            }else{
                for(int i = 0 ; i < tab[0].length ; i++){
                    morceaux[i] = ((tab[intersection[0]][i])/(value));
                }
                for(int i = 0 ; i < new_tab.length ; i++){
                    for(int j = 0 ; j < new_tab[0].length ; j++){
                        if (i == intersection[0]) {
                            new_tab[i][j] = morceaux[j];
                        }else{
                            new_tab[i][j] = tab[i][j];
                        }
                    }
                }
            }
            // Affichage(new_tab);
        } catch (Exception e) {
            throw e;
        }
        return new_tab;
    }
    @SuppressWarnings("unchecked")
    public TableauInitiale conversion_en_zero(double[][] tab,int[] intersection){
        TableauInitiale result = new TableauInitiale();
        int intersect_ligne = intersection[0];
        int intersect_colon = intersection[1];
        @SuppressWarnings("rawtypes")
        Vector nouveau = new Vector();
        double[][] response = new double[tab.length][tab[0].length];
        try {
            for(int i = 0 ; i < tab.length ; i++){
                if (i != intersect_ligne) {
                    for(int j = 0 ; j < tab[0].length ; j++){
                        nouveau.add((tab[i][j]) - ((tab[i][intersect_colon]) * (tab[intersect_ligne][j])));
                    }
                }
            }
            int k = 0;
            for (int i = 0; i < response.length; i++) {
                for (int j = 0; j < response[0].length; j++) {
                    if (i == intersect_ligne) {
                        response[i][j] = tab[i][j];
                    }else{
                        response[i][j] = Double.parseDouble(nouveau.elementAt(k).toString());
                        k++;
                    }
                }
            }
            Affichage(response);
            result.setTableau_Initiale(response);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    public boolean conditiond_arret(double[] tableau,int probleme){
        boolean result = false;
        try {
            if (probleme > 0) {
                for (int i = 0; i < (tableau.length-1); i++) {
                    if (tableau[i] > 0) {
                        return true;
                    }
                }
            }else{
                for (int i = 0; i < (tableau.length-1); i++) {
                    if (tableau[i] < 0) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    public void Simplexe(TableauInitiale tableauInitiale,int probleme){
        TableauInitiale nouveau_tab = new TableauInitiale();
        try {
            int[] intersection = Recherche_intersection(tableauInitiale.getTableau_Initiale());
            double[][] premier_etape = conversion_en_un(tableauInitiale.getTableau_Initiale(),intersection);
            nouveau_tab = conversion_en_zero(premier_etape,intersection);
            double[][] new_tab = nouveau_tab.getTableau_Initiale();
            int taille = (new_tab.length-1);
            boolean test = conditiond_arret(new_tab[taille],probleme);
            if (test) {
                Simplexe(nouveau_tab,probleme);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    
}
