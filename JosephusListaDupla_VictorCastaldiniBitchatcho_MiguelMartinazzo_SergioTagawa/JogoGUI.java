/*
 * @author Victor Castaldini Bitchatcho, Miguel Martinazzo, Sergio Tagawa
 * Função: Classe "JogoGUI", principal classe, contendo frames, labels, imagens, bem como o programa de Josephus e sua lógica
 * version: 23/06/2022
 */

//IMPORTS
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import model.*;

class JogoGUI extends JFrame {                                      
    //Variaveis de imagem de jogador.
    private Image image1;
    private Image image2;
    private Image image3;
    //Variaveis auxiliares do programa.
    private int quant = 0;
    private int participantes = 0;    
    private int intervalo = 0;
    private int posWinner = 0;

    
    public void jogo(Graphics g, int posicaoX, int posicaoY,int raio, int quantidadeDePontos, Color cor) {

        ListaDuplamenteLigadaCircular lista = new ListaDuplamenteLigadaCircular(); //criação de uma nova lista duplamente ligada, que conterá todas as Pessoas do Josephus.
        for(int aux1=0;aux1<quant;aux1++){  //Loop que preenche a lista, inserindo a quantidade desejada de novas pessoas vivas, sempre ao final da lista ligada.
            lista.inserirFim(aux1); //passando a informação "posicao" como argumento para a identificação das pessoas (neste caso ela é igual ao contador aux1)
        }

        //Criando o primeiro circulo de vivos do programa (Ps: Não é possivel vê-lo pois não conseguimos dar sleep nessa área.)
        try{
            //Essas próximas linhas de código que possuem File estão criando as imagens de Vivo, Morto e Sobrevivente. 
            //OBSERVAÇÃO: Em cada computador que este código for executado, a pessoa deverá salvar as imagens contidas na pasta e colocar os respectivos endereços das imagens no endereço dos files abaixo.
            File fileImg1 = new File("C:/Users/ra00297765/Desktop/JosephusListaDupla_VictorCastaldiniBitchatcho_MiguelMartinazzo_SergioTagawa/Imagens/vivo.png");
            BufferedImage bufferedImage1 =  ImageIO.read(fileImg1); 
            image1 = bufferedImage1.getScaledInstance(60, 60, Image.SCALE_DEFAULT);// "image1": ícone da pessoa viva (gladiador)

            File fileImg2 = new File("C:/Users/ra00297765/Desktop/JosephusListaDupla_VictorCastaldiniBitchatcho_MiguelMartinazzo_SergioTagawa/Imagens/morto.png");
            BufferedImage bufferedImage2 =  ImageIO.read(fileImg2); 
            image2 = bufferedImage2.getScaledInstance(60, 60, Image.SCALE_DEFAULT);//"image2": ícone da pessoa morta (caveira)

            File fileImg3 = new File("C:/Users/ra00297765/Desktop/JosephusListaDupla_VictorCastaldiniBitchatcho_MiguelMartinazzo_SergioTagawa/Imagens/sobrevivente.png");
            BufferedImage bufferedImage3 =  ImageIO.read(fileImg3); 
            image3 = bufferedImage3.getScaledInstance(90, 90, Image.SCALE_DEFAULT);//"image3": ícone do último vivo -> sobrevivente! (Elmo dourado vitorioso)

            //Desenhando o círculo.
            JLabel label = new JLabel();
            double distanciaEntrePontos = 2 * Math.PI / quantidadeDePontos;

            //Percorrem a lista ligada para descobrir a respectiva pessoa, através da comparação de sua posição com a variável i do For.
            Pessoa pessoaAux = lista.getInicio();
            Pessoa pessoaAux00 = lista.getInicio();

            //Calcula o X e Y (através das funções cosseno e seno) de cada pessoa e as desenham no círculo.
            for (int i = 0; i < quant; i++) {
                double cos = Math.cos(i * distanciaEntrePontos);
                double sin = Math.sin(i * distanciaEntrePontos);

                int x = (int) ( cos * raio + posicaoX );
                int y = (int) ( sin * raio + posicaoY );
                
                
                    //Quando encontrar a pessoa cuja posição for igual ao contador i e ela não for a pessoa final, seu x e y recebe os valores respectivos calculados de x e y.
                for(int aux3 = 0;aux3 < quant;aux3++){ 
                    if(pessoaAux.getPosicao()== i && pessoaAux.getPosicao()!=posWinner){

                        pessoaAux.x = x;
                        pessoaAux.y = y;  
                    }else{
                        pessoaAux = pessoaAux.getProximo(); //Avança até o encontro
                    }

                }
                //Colocando-a no círculo
                g.setColor(cor);
                g.drawImage(image2,x,y,this);
                pessoaAux = lista.getInicio();
            }

            // Desenhando o sobrevivente após as execuções.
            // Da mesma forma, calculamos seu X e Y e os identificamos para seu desenho
            
            double cos2 = Math.cos(posWinner * distanciaEntrePontos);
            double sin2 = Math.sin(posWinner * distanciaEntrePontos);

            int x2 = (int) ( cos2 * raio + posicaoX );
            int y2 = (int) ( sin2 * raio + posicaoY );

            for(int aux00 = 0;aux00 < quant;aux00++){

                if(pessoaAux00.getPosicao()== posWinner){

                    pessoaAux00.x = x2;
                    pessoaAux00.y = y2;  
                }else{
                    pessoaAux00 = pessoaAux00.getProximo();
                }

            }

            g.drawImage(image3,x2,y2,this);

        }catch(IOException e) {
            e.printStackTrace();
        }

        //Lógica antiga de matança, percorre a lista e mata a cada x, sendo x o intervalo de execução.
        /* Pessoa pessoaMorta = lista.getInicio();
        int tmp=1;
        while(participantes != 1){
        for(int contador = 1; contador <= intervalo; contador++){
        pessoaMorta = pessoaMorta.getProximo();
        }

        lista.executarCruelmente(pessoaMorta.getPosicao());

        double distanciaEntrePontos = 2 * Math.PI / quantidadeDePontos;
        for (int i = 0; i < quantidadeDePontos; i++) {

        for(int aux7 = 0;aux7 < quant;aux7++){
        Pessoa pessoaAux4 = lista.getInicio();
        if(pessoaAux4.getPosicao()== i){

        int x = pessoaAux4.getX();
        int y = pessoaAux4.getY();

        if(pessoaAux4.getStatus()==0){
        g.setColor(cor);
        g.drawImage(image1,x,y,null);
        }
        else if(pessoaAux4.getStatus()==1){
        g.setColor(cor);
        g.drawImage(image2,x,y,null);
        participantes--;
        }
        else if(participantes == 1 && pessoaAux4.getStatus()==0){
        g.setColor(cor);
        g.drawImage(image3,x,y,this);
        participantes--;
        }
        }
        else{
        pessoaAux4 = pessoaAux4.getProximo();
        }
        }
        }

        }*/

    }
    
    //Função josephus pega o id do ultimo vivo.
    static int josephus(int n, int k)
    {
        if (n == 1)
            return 1;
        else
        /* A posição retornada por josephus(n - 1, k)
        é ajustada pela chamada recursiva
        josephus(n - 1, k) considera a posição
        inicial k%n + 1 com a posição 1 */
            return ((josephus(n - 1, k) + k - 1) % n + 1);
    }

    //Função principal que chama o jogo.
    @Override
    public void paint(Graphics g) {

        quant = Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade de gladiadores: "));
        intervalo = Integer.parseInt(JOptionPane.showInputDialog("Insira o indice de execucoes: "));
        participantes = quant;
        posWinner = josephus(quant,intervalo);
        super.paint(g);
        jogo(g, 340, 350, 200, quant, Color.RED);
    }
}