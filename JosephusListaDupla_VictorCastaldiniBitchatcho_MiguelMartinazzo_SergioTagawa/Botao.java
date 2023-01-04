import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import model.*;

public class Botao extends JFrame{
    //Colocando nome nos botões.
    private JButton comecar = new JButton("Comecar");
    private JButton sair = new JButton("Sair");
    private ButtonHandler handler;

    public Botao(){
        super("Josephus Royale");
        
        GridLayout grid = new GridLayout(10,1); // Define o layout dos botoes.

        JLabel label = new JLabel("<JOSEPHUS ROYALE>",JLabel.CENTER);
        add(label);
        
        setLayout(new GridLayout(10,1));
        handler=new ButtonHandler(comecar, sair);
        
        //Adicionando handlers nos botões para poderem ser clicados.
        comecar.addActionListener(handler);
        comecar.setBackground(new Color(238, 221, 130));
        add(comecar);

        sair.addActionListener(handler);
        sair.setBackground(new Color(238, 221, 130));
        add(sair);
    }
}