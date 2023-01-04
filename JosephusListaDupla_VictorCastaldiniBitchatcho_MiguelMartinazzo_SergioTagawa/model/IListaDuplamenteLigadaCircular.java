package model;
import model.*;

/*
 * @author Victor Castaldini Bitchatcho, Miguel Martinazzo, Sergio Tagawa
 * Função: Interface que contém os métodos necessários na classe "ListaDuplamenteLigada"
 * version: 23/06/2022
 */

public interface IListaDuplamenteLigadaCircular{
    public boolean estaVazia(); //Função que retorna um booleano para determinar se a lista encontra-se vazia ou não
    
    public void inserirInicio(int posicao);  //Função que recebe uma posição como argumento e é utilizada para inserir no início da lista

    public void inserirFim(int posicao); //Função mais utilizada na criação dos objetos, recebendo a posição dos mesmos como argumento e, os insere no final da lista.

    public Object removerInicio(); //Remove Pessoa do início da Fila

    public Object removerFim(); //RemovePessoa do final da Fila
    
    public Object executarCruelmente(int posicao); //Esta função remove uma Pessoa da lista, apenas o seu nome é cruel mesmo.
    
    public String toStringDoFim();
}
