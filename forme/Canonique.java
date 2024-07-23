package forme;
public class Canonique {
    double[] objective;
    double[][] contrainte_vrai;
    double[] contrainte_de_positivite;
    double[] partie_droite;

    public double[] getPartie_droite() {
        return partie_droite;
    }
    public void setPartie_droite(double[] partie_droite) {
        this.partie_droite = partie_droite;
    }
    public double[] getObjective() {
        return objective;
    }
    public void setObjective(double[] objective) {
        this.objective = objective;
    }
    public double[][] getContrainte_vrai() {
        return contrainte_vrai;
    }
    public void setContrainte_vrai(double[][] contrainte_vrai) {
        this.contrainte_vrai = contrainte_vrai;
    }
    public double[] getContrainte_de_positivite() {
        return contrainte_de_positivite;
    }
    public void setContrainte_de_positivite(double[] contrainte_de_positivite) {
        this.contrainte_de_positivite = contrainte_de_positivite;
    }
}
