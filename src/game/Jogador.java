package game;

public class Jogador {
    private Integer casaAtual;
    private Integer meuNumero;
    
    public Jogador(int numero) {
        this.casaAtual = 0;
        this.meuNumero = numero;
    }
    
    public void jogar(Dado dado) {
        dado.rolar();
        this.casaAtual += dado.getNumeroAtual();
    }
    
    public Integer getCasaAtual() {
        return casaAtual;
    }
    
    public Integer getMeuNumero() {
        return meuNumero;
    }
}