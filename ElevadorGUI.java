import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Elevador {
    private int andarAtual;

    public Elevador() {
        this.andarAtual = 0;
    }

    public int getAndarAtual() {
        return andarAtual;
    }

    public void moverPara(int novoAndar) {
        this.andarAtual = novoAndar;
    }
}

public class ElevadorGUI extends JFrame {
    private Elevador elevador1, elevador2;
    private JButton[] botoes;
    private JLabel[] lblAndarAtual;

    public ElevadorGUI() {
        elevador1 = new Elevador();
        elevador2 = new Elevador();

        // Configurações da janela
        setTitle("Elevadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuração do layout com BorderLayout
        setLayout(new BorderLayout());

        // Painel central para os elevadores e botões
        JPanel centroPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        lblAndarAtual = new JLabel[2];

        // Painel para os elevadores à esquerda
        JPanel elevadoresPanel = new JPanel(new GridLayout(2, 1));
        for (int i = 0; i < 2; i++) {
            lblAndarAtual[i] = new JLabel("Elevador " + (i + 1) + ": " + (i == 0 ? elevador1.getAndarAtual() : elevador2.getAndarAtual()));
            elevadoresPanel.add(lblAndarAtual[i]);
        }
        centroPanel.add(elevadoresPanel);

        // Painel para os botões à direita
        JPanel botoesPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        botoes = new JButton[9];
        for (int i = 0; i < 9; i++) {
            final int andar = i - 2;
            botoes[i] = new JButton(String.valueOf(andar));
            botoes[i].setFont(new Font("Arial", Font.PLAIN, 16));
            botoes[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chamarElevador(andar);
                }
            });
            botoesPanel.add(botoes[i]);
        }
        centroPanel.add(botoesPanel);

        add(centroPanel, BorderLayout.CENTER);

        // Configurações adicionais da janela
        setSize(500, 250);
        setLocationRelativeTo(null); // Centraliza na tela
    }

    private void chamarElevador(int andarChamado) {
        int distanciaElevador1 = Math.abs(andarChamado - elevador1.getAndarAtual());
        int distanciaElevador2 = Math.abs(andarChamado - elevador2.getAndarAtual());

        if (distanciaElevador1 <= distanciaElevador2) {
            elevador1.moverPara(andarChamado);
            atualizarAndarAtual(elevador1, 0);
        } else {
            elevador2.moverPara(andarChamado);
            atualizarAndarAtual(elevador2, 1);
        }
    }

    private void atualizarAndarAtual(Elevador elevador, int indice) {
        lblAndarAtual[indice].setText("Elevador " + (indice + 1) + ": " + elevador.getAndarAtual());
    }

    
}
