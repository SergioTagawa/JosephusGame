import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import model.*;

public class ButtonHandler implements ActionListener{

    //Criando os botões.
    private JButton Comecar, Sair;

    private int i = 0;

    //Construtor dos botões para poderem interagir.
    public ButtonHandler(JButton Comecar, JButton Sair){
        this.Comecar = Comecar;
        this.Sair = Sair;
    }

    //Detectando qual botão foi clicado.
    public void actionPerformed(ActionEvent evento) {
        //Criando a interface onde será desenhado o círculo.
        if(evento.getSource() == Comecar){ 
            JFrame frame = new JogoGUI();  
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 700);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        }

        if(evento.getSource() == Sair){

            System.exit(0);
        }
    }
}