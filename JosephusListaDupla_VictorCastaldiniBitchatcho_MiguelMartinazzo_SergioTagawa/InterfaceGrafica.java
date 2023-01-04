import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import model.*;
public class InterfaceGrafica {
    public static void main(String[] args) {
        //Criando a variável do tipo botão.
        Botao botoes = new Botao();

        botoes.setBounds(500, 300, 400, 450); // Seta posição e tamanho
        botoes.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        botoes.getContentPane().setBackground(new Color(0, 178, 238));
        botoes.setVisible(true);
    }
}