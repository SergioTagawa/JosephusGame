package model;
import model.*;

/*
 * @author Victor Castaldini Bitchatcho, Miguel Martinazzo, Sergio Tagawa
 * Função: Classe "ListaDuplamenteLigada", contendo atributos e funçoes da lista, que serão essenciais para o devido funcionamento do programa Josephus
 * version: 23/06/2022
 */



import java.util.Random;

public class ListaDuplamenteLigadaCircular implements IListaDuplamenteLigadaCircular {
    private Pessoa inicio; // ref para primeiro elemento
    private Pessoa fim;    // ref para ultimo posicaoento

    int qtdPessoas; // Quantidade de Pessoas
    private Random r = new Random(System.currentTimeMillis()); //Numero randomico para a geração das ID's

    // -------------------------------------------------------------
    public ListaDuplamenteLigadaCircular() {   //Construtor da Classe: seta inicio e fim como nulos e a quantidade de pessoas na lista é inicialmente igual a 0. 
        setInicio(null);
        setFim(null);
        setQtdPessoas(0);
    }
    // Setters e Getters
    /**
     * Method getInicio
     *
     * @return endereco do primeiro Pessoa
     */
    public Pessoa getInicio() {
        return inicio;
    }

    public void setInicio(Pessoa inicio) {
        this.inicio = inicio;
    }

    /**
     * Method getFim
     *
     * @return endereco do ultimo Pessoa
     */
    public Pessoa getFim() {
        return fim;
    }

    public void setFim(Pessoa fim) {
        this.fim = fim;
    }

    /**
     * Metodo getQtdPessoas
     *
     * @return qtidade de Pessoas
     */
    public int getQtdPessoas(){
        return this.qtdPessoas;
    }

    /**
     * Metodo setQtdPessoas
     *
     * @param qtdPessoas atualiza qtde de Pessoas
     */
    private void setQtdPessoas(int qtdPessoas){
        this.qtdPessoas = qtdPessoas;
    }

    // -------------------------------------------------------------
    public boolean estaVazia() {
        return getInicio() == null; 
    }

    // -------------------------------------------------------------
    public void inserirInicio(int posicao) {         //Método para inserir uma novo objeto da classe "Pessoa", de determinada posição no inicio da lista.

        Pessoa novaPessoa = new Pessoa(posicao, r.nextLong());   //Criação da nova Pessoa

        if( estaVazia() ) { 
            setFim(novaPessoa);   //Se lista esta vazia, o fim da lista é esta nova pessoa.
        
        } else {
            getInicio().setAnterior(novaPessoa); // Se não, o novo início é esta pessoa (anterior do inicio atual).
        }
        novaPessoa.setProximo(getInicio()); //Sendo o novo início, o proximo desta nova pessoa é o antigo inicio!
        setInicio(novaPessoa); // Setando novo início como a nova pessoa
        getFim().setProximo(getInicio()); // Como a lista é circular, o proximo do "fim" é o "inicio"
        getInicio().setAnterior(getFim()); // Seguindo a mesma lógica, o anterior do "início" é o "fim"
        // Decrementa qtidade de Pessoas
        setQtdPessoas(getQtdPessoas() + 1);

    }

    // -------------------------------------------------------------
    public void inserirFim(int posicao)    {                    //Método muito utilizado em nosso programa. Serve para inserir um novo objeto da classe "Pessoa", de determinada posição no final da lista.
        Pessoa novaPessoa = new Pessoa(posicao, r.nextLong());  //Criação da nova Pessoa

        if( estaVazia() ) {
            setInicio(novaPessoa); //Se vazia, o início da lista é a nova pessoa
        } else {
            getFim().setProximo(novaPessoa); // Se não, o proximo do fim é a nova pessoa
            novaPessoa.setAnterior(getFim()); // Assim sendo, o anterior da nova pessoa é o antigo fim
        }
        setFim(novaPessoa); // Definindo a nova pessoa como o novo fim
        getFim().setProximo(getInicio()); // Como a lista é circular, o próximo do fim é o início da lista
        getInicio().setAnterior(getFim()); //Assim sendo, o anterior do início da lista é o seu fim
        // Incrementa quantidade de Pessoas
        setQtdPessoas(getQtdPessoas() + 1);

    }

    // -------------------------------------------------------------
    
    public Pessoa removerInicio() {  //Função que remove a Pessoa do início da lista circular duplamente ligada
        Pessoa temp = null;
        if(getInicio() != null && getFim() != null) {
            temp = getInicio();

            if (getInicio() == getFim()) {
                setInicio(null);
                setFim(null);
            } else {
                getFim().setProximo(getInicio().getProximo());
                getInicio().getProximo().setAnterior(getFim()); 
                setInicio(getInicio().getProximo());
            }
            temp.setAnterior(null);
            temp.setProximo(null);
        }
        // Decrementa qtidade de Pessoas
        setQtdPessoas(getQtdPessoas() - 1);
        return temp;
    }

    // -------------------------------------------------------------
    public Pessoa removerFim() {     //Função que remove a Pessoa do fim da lista circular duplamente ligada
        Pessoa temp = null;
        if(getFim() != null && getInicio() != null) {
            temp = getFim();

            if(getFim() == getInicio()) {
                setFim(null);
                setInicio(null); 
            }
            else {
                getInicio().setAnterior(getFim().getAnterior());
                setFim(getFim().getAnterior());
                getFim().setProximo(getInicio());
            }
            temp.setAnterior(null);
            temp.setProximo(null);
        }
        // Decrementa qtidade de Pessoas
        setQtdPessoas(getQtdPessoas() - 1);
        return temp;
    }

    // -------------------------------------------------------------
    public boolean inserirApos(long key, int posicao) { //Insere uma nova pessoa após a busca de uma determinada posição, encontrada através de uma chave(ID).
        Pessoa pessoaAtual = getInicio(); 

        while((Long)pessoaAtual.getId() != key) {
            if(pessoaAtual == getFim()) {
                return false; 
            }
            pessoaAtual = pessoaAtual.getProximo(); 
        }
        Pessoa novaPessoa = new Pessoa(posicao, r.nextLong());

        if(pessoaAtual==getFim()) {
            novaPessoa.setProximo(getInicio()); 
            setFim(novaPessoa); 
        }
        else {
            novaPessoa.setProximo(pessoaAtual.getProximo());
            pessoaAtual.getProximo().setAnterior(novaPessoa);
        }
        novaPessoa.setAnterior(pessoaAtual);
        pessoaAtual.setProximo(novaPessoa); 
        // Incrementa quantidade de Pessoas
        setQtdPessoas(getQtdPessoas() + 1);
        return true; 
    }

    // -------------------------------------------------------------
    public Pessoa executarCruelmente(int posicao) {  //Esta é a função que elimina as pessoas mortas no programa Josephus!
        Pessoa pessoaAtual = null; //Objeto pessoa auxiliar que percorre a lista
        if(getInicio() != null) {
            pessoaAtual = getInicio(); // Pessoa auxiliar inicia como sendo a pessoa inicial da lista

            while(pessoaAtual.getPosicao() != posicao) {     //enquanto nao achar a pessoa desejada através da comparação de posições, a pessoa auxiliar se transforma na sua próxima pessoa, para percorrer a lista
                if(pessoaAtual == getFim()) { //Se chegar até o final e não achou, retorna nulo
                    return null;                 
                }
                pessoaAtual = pessoaAtual.getProximo(); //Enquanto nao achar, vai para o proximo objeto da lista
            }

            if(pessoaAtual == getInicio()) { //Se a pessoa desejada for a inicial, use a funcao removerInicio()
                pessoaAtual = removerInicio();
            } else if (pessoaAtual == getFim()) { //Se a pessoa desejada for a final, use a funcao removerFim()
                pessoaAtual = removerFim();
            }
            else { 
                pessoaAtual.getAnterior().setProximo(pessoaAtual.getProximo()); //Se não, o próximo da pessoa buscada passa a ser a proxima pessoa da pessoa anterior
                pessoaAtual.getProximo().setAnterior(pessoaAtual.getAnterior()); //Assim sendo, o anterior do proximo é igual ao anterior da Pessoa removida
            }
            pessoaAtual.setAnterior(null); //Nao há mais pessoa proxima e nem anterior à pessoa removida
            pessoaAtual.setProximo(null);

        }
        // Decrementa qtidade de Pessoas
        setQtdPessoas(getQtdPessoas() - 1);
        return pessoaAtual; 
    }

    // -------------------------------------------------------------
    public String toString() {
        String s = "[ ";
        Pessoa pessoaAtual = getInicio();  // inicia do inicio
        if(pessoaAtual != null) {
            do {   
                s = s + pessoaAtual.toString() + " ";  
                pessoaAtual = pessoaAtual.getProximo();   
            } while(pessoaAtual != getInicio());
        }
        s = s + "]";
        return s;
    }

    // -------------------------------------------------------------
    public String toStringDoFim() {
        String s = "[ ";
        Pessoa pessoaAtual = getFim();  // inicia Pessoa fim

        if(pessoaAtual != null) {
            do {
                s = s + pessoaAtual.toString() + " "; 
                pessoaAtual = pessoaAtual.getAnterior(); 
            }while(pessoaAtual != getFim());
        }
        s = s + "]";
        return s;
    }

    public void limparLista() { //Função que limpa a lista

        Pessoa temp = getInicio();

        while (temp != getFim()) {
            removerFim();
        }

        setInicio(null);
        setFim(null);

    }
}  
