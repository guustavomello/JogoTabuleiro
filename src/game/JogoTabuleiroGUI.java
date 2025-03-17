package game;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class TabuleiroPanel extends JPanel {
    private Jogo jogo;
    private static final int CASA_TAMANHO = 30; // Tamanho de cada casa em pixels

    public TabuleiroPanel(Jogo jogo) {
        this.jogo = jogo;
        setPreferredSize(new java.awt.Dimension(600, 100)); // Tamanho fixo do painel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (jogo == null) return;

        int numeroCasas = jogo.getMeuTabuleiro().getNumeroCasas();
        // Desenha as casas do tabuleiro
        for (int i = 0; i < numeroCasas; i++) {
            g.setColor(Color.BLACK);
            g.drawRect(i * CASA_TAMANHO, 0, CASA_TAMANHO, CASA_TAMANHO);
            g.drawString(String.valueOf(i), i * CASA_TAMANHO + 10, CASA_TAMANHO / 2);
        }

        Jogador[] jogadores = jogo.getJogadores();
        for (Jogador j : jogadores) {
            int posicao = j.getCasaAtual();
            if (posicao >= numeroCasas) posicao = numeroCasas - 1; // Limita à última casa
            g.setColor(j.getMeuNumero() == 1 ? Color.RED : Color.BLUE); // Jogador 1 vermelho, Jogador 2 azul
            g.fillOval(posicao * CASA_TAMANHO + 5, CASA_TAMANHO / 2 - 10, 20, 20);
        }
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
        repaint();
    }
}

public class JogoTabuleiroGUI extends JFrame {
    private JPanel contentPane;
    private JTextArea textArea;
    private Jogo jogo;
    private TabuleiroPanel tabuleiroPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JogoTabuleiroGUI frame = new JogoTabuleiroGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JogoTabuleiroGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 687, 560); // Aumentei o tamanho da janela
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Painel do tabuleiro
        tabuleiroPanel = new TabuleiroPanel(null);
        tabuleiroPanel.setBounds(10, 11, 600, 100);
        contentPane.add(tabuleiroPanel);

        // Área de texto
        textArea = new JTextArea();
        textArea.setBounds(10, 120, 600, 150);
        contentPane.add(textArea);

        JButton btnIniciar = new JButton("1 - Iniciar nova partida");
        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jogo = new Jogo(2, 20); // 2 jogadores, tabuleiro com 20 casas
                tabuleiroPanel.setJogo(jogo);
                textArea.setText("Nova partida iniciada!\n2 jogadores, tabuleiro com 20 casas");
            }
        });
        btnIniciar.setBounds(10, 280, 200, 23);
        contentPane.add(btnIniciar);

        JButton btnJogada = new JButton("2 - Executar jogada");
        btnJogada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jogo != null) {
                    jogo.proximaJogada();
                    tabuleiroPanel.repaint(); // Atualiza o desenho do tabuleiro
                    textArea.append("\nJogada executada!");
                } else {
                    textArea.setText("Inicie uma nova partida primeiro!");
                }
            }
        });
        btnJogada.setBounds(10, 314, 200, 23);
        contentPane.add(btnJogada);

        JButton btnPosicoes = new JButton("3 - Informar posições");
        btnPosicoes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jogo != null) {
                    textArea.setText("Posições atuais:\n");
                    for (Jogador j : jogo.getJogadores()) {
                        textArea.append("Jogador " + j.getMeuNumero() + ": Casa " + j.getCasaAtual() + "\n");
                    }
                    tabuleiroPanel.repaint(); // Atualiza o desenho
                } else {
                    textArea.setText("Inicie uma nova partida primeiro!");
                }
            }
        });
        btnPosicoes.setBounds(10, 348, 200, 23);
        contentPane.add(btnPosicoes);

        JButton btnSair = new JButton("0 - Sair");
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnSair.setBounds(410, 348, 200, 23);
        contentPane.add(btnSair);
    }

    // Método getter para acessar o tabuleiro (usado no TabuleiroPanel)
    public Tabuleiro getMeuTabuleiro() {
        return jogo != null ? jogo.getMeuTabuleiro() : null;
    }
}