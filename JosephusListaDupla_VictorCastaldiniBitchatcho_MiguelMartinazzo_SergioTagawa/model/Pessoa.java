package model;
import model.*;

/*
 * @author Victor Castaldini Bitchatcho, Miguel Martinazzo, Sergio Tagawa
 * Função: Pessoa da lista duplamente ligada circular
 * version: 23/06/2022
 */

public class Pessoa{
    /**
     * Atributos
     */
    public int posicao; // posicao da Pessoa (de 0 até a quantidade escolhida)
    Pessoa proximo;  // proxima Pessoa
    Pessoa anterior; // Pessoa antecessora
    public long id;     // id da Pessoa
    public int status; //0: vivo, 1: morto e, 2: vencedor (último vivo)
    public int x,y; // Coordenadas (x,y) para confecção do desenho

    /**
     * Construtor de um novo objeto da classe "Pessoa" através da atribuição da sua posição
     */
    public Pessoa(int posicao){
        setPosicao(posicao);
        setProximo(null); //Será definido nas funções "inserir/remover", presentes na classe "ListaDuplamenteLigada"
        setAnterior(null);//Idem
        setId(0); 
        setStatus(0); //Todos os objetos da classe "Pesso" iniciam vivos!
    }
    /**
     * Construtor de um novo objeto da classe "Pessoa" através da atribuição da sua posição e um id
     */
    public Pessoa(int posicao, long id){
        setPosicao(posicao); // Igual ao construtor acima
        setProximo(null);
        setAnterior(null);
        setId(id);
        setStatus(0);
    }
    /**
     * setters e getters
     */
    public void setPosicao(int posicao){
        this.posicao = posicao;
    }
    
    public void setProximo(Pessoa proximo){
        this.proximo = proximo;
    }
    
    public void setAnterior(Pessoa anterior){
        this.anterior = anterior;
    }

    public int getPosicao(){
        return(this.posicao);
    }
    
    public Pessoa getProximo(){
        return(this.proximo);
    }

    public Pessoa getAnterior(){
        return(this.anterior);
    }
    /**
     * Método setId
     *
     * @param id Um parâmetro
     */
    public void setId(long id){
        this.id = id;
    }
    
    public void setStatus(int status){
        this.status = status;
    }
    
    public int getStatus(){
        return (this.status);
    }
    
    /**
     * Método getId
     */
    public long getId(){
        return (this.id);
    }
    
    public int getX(){
        return (this.x);
    }
    
    public int getY(){
        return (this.y);
    }
    
    /*
    public String toString(){
        //return "ID: " + getId() + " " + getPosicao().toString(); 
        return(posicao.toString());
    }
    */
    
}
