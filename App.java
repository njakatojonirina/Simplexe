import forme.Canonique;
import forme.TableauInitiale;
import utilitaire.Utilitaire;

public class App {
    public static void main(String[] args) throws Exception {
        Canonique canonique = new Canonique();
        double[] objective = {10,14,12};
        double[] contrainte_de_positivite = {1,1,1};
        double[][] contrainte_vrai = new double[3][3];
        double[] partie_droite = {40,45,38};
        contrainte_vrai[0][0] = 1;
        contrainte_vrai[0][1] = 3;
        contrainte_vrai[0][2] = 1;

        contrainte_vrai[1][0] = 3;
        contrainte_vrai[1][1] = 2;          
        contrainte_vrai[1][2] = 1;

        contrainte_vrai[2][0] = 1;
        contrainte_vrai[2][1] = 1;
        contrainte_vrai[2][2] = 4;
        canonique.setObjective(objective);
        canonique.setContrainte_vrai(contrainte_vrai);
        canonique.setContrainte_de_positivite(contrainte_de_positivite);
        canonique.setPartie_droite(partie_droite);

        Utilitaire utilitaire = new Utilitaire();
        TableauInitiale tableauInitiale = utilitaire.Tableau_Simplexe(canonique);
        utilitaire.Simplexe(tableauInitiale,1);
    }

}
