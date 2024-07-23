package forme;

public class TableauInitiale {
    double[][] Tableau_Initiale;

    public double[][] getTableau_Initiale() {
        return Tableau_Initiale;
    }

    public void setTableau_Initiale(double[][] tableau_Initiale) {
        Tableau_Initiale = tableau_Initiale;
    }
    public void Affichage(){
        int taille1 = Tableau_Initiale.length;
        int taille2 = Tableau_Initiale[0].length;
        for (int i = 0 ; i < taille1 ; i++) {
            for (int j = 0 ; j < taille2 ; j++){
                if (j != 0 && j % (taille2-1) == 0) {
                    System.out.print(Tableau_Initiale[i][j]);
                    System.out.println(" ");
                }
                else{
                    System.out.print(Tableau_Initiale[i][j] + " | " );
                }
            }
        }
    }

}
