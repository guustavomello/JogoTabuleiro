package game;

public class Dado {
    private Integer numeroAtual;
    
    public Dado() {
        this.numeroAtual = 0;
    }
    
    public Integer getNumeroAtual() {
        return numeroAtual;
    }
    
    public void rolar() {
        this.numeroAtual = (int)(Math.random() * 6) + 1; // Gera número de 1 a 6
    }
}