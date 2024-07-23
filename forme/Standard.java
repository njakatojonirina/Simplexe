package forme;

public class Standard {
    double[][] forme_Standard;

    public double[][] getForme_Standard() {
        return forme_Standard;
    }

    public void setForme_Standard(double[][] forme_Standard) {
        this.forme_Standard = forme_Standard;
    }
    public void Affichage(){
        int taille1 = forme_Standard.length;
        int taille2 = forme_Standard[0].length;
        for (int i = 0 ; i < taille1 ; i++) {
            for (int j = 0 ; j < taille2 ; j++){
                if (j != 0 && j % (taille2-1) == 0) {
                    System.out.print(forme_Standard[i][j]);
                    System.out.println(" ");
                }
                else{
                    System.out.print(forme_Standard[i][j] + " | " );
                }
            }
        }
    }
}
