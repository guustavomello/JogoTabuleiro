package game;

public class Jogo {
    private Tabuleiro meuTabuleiro;
    private Jogador[] meusJogadores;
    private Dado meuDado;
    private int jogadorAtual;
    
    public Jogo(int numeroJogadores, int casasTabuleiro) {
        meuTabuleiro = new Tabuleiro(casasTabuleiro);
        meusJogadores = new Jogador[numeroJogadores];
        for(int i = 0; i < numeroJogadores; i++) {
            meusJogadores[i] = new Jogador(i + 1);
        }
        meuDado = new Dado();
        jogadorAtual = 0;
    }
    
    public void proximaJogada() {
        meusJogadores[jogadorAtual].jogar(meuDado);
        jogadorAtual = (jogadorAtual + 1) % meusJogadores.length;
    }
    
    public Tabuleiro getMeuTabuleiro() {
        return meuTabuleiro;
    }
    
    public Jogador[] getJogadores() {
        return meusJogadores;
    }
}
