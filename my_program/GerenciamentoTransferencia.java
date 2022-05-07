import java.util.LinkedList;
import java.text.NumberFormat;
import java.util.Locale;





public class GerenciamentoTransferencia {
  
 //Neste problema nao usamos busca e nem remocao, apenas insercao de dados
 //Assim, a complexidade e linear para esse tipo de lista, mas em comparacao com um
 //ArrayList que tambem e linear, mas ela e mais demorada faz muitos acessos load e store a memoria
 //Neste caso, Linkedlist e melhor considerando insercao e remocao, 
 //no entanto, em relacao a acessos o ArrayList e melhor
 LinkedList<DadosTransferencia> listaDosDados;

 //Usado para fazer a validacao do CPF
 private static final String CPF = "[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}";
  
 //Usando para mudar o formato de double para moeda brasileira
 Locale localeBR = new Locale( "pt", "BR" );  
 NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);  


  

  public GerenciamentoTransferencia(){}


  
  /*
      1° Inicializa a operacao de tranferencia, ou seja, comeca aqui
  */
  public void inicializacaoDaTransferencia(){
    
  System.out.println("\n ------- OPERACAO DE TRANSFERENCIA INICIADA ---------- \n");
  this.listaDosDados = LeituraArquivo.abreArquivo();
  validacao();//Inicia a validacao dos dados capturado no arquivo
    
  }



  
  /*
      2° Faco a validacao de cada transferencia da lista
  */
  private void validacao(){
  for(DadosTransferencia dados : this.listaDosDados){ 

  System.out.println();
    
  //Esta validacao considera tudo praticamente, se algum dado for passado incorretamente
  //O catch no metodo conversao captura, entao se faltar dados ou passar uma string para 
  //converter para int ele ja captura aqui, mas se ainda passar, as outras condicoes captura
  if(dados == null){
      System.out.println("Sua transferencia nao foi completada pois (ERRO NA FORMATACAO, DADOS INCOMPLETOS/INCORRETOS)");
      System.out.println("---------------------------------------------------------");
      continue;//Pula o objeto na lista
  }
  

  //Validando o CPF das pessoas
  if(!dados.getEmissor().getCPF().matches(CPF) || !dados.getReceptor().getCPF().matches(CPF)){
      System.out.println("Sua transferencia nao foi completada pois (CPF COM FORMATACAO ERRADA)");
      System.out.println("---------------------------------------------------------");
      continue;//Pula o objeto na lista
  }


  //1.Toda transferência deverá ser feita entre um emissor e um receptor;
  //7.Não serão permitidas transferências para a mesma conta, mas um emissor pode transferir para ele mesmo se for uma conta diferente;
  if(dados.getEmissor().getConta() == dados.getReceptor().getConta()){
  if(dados.getEmissor().getAgencia() == dados.getReceptor().getAgencia()){
      System.out.println("Sua transferencia nao foi completada pois (CONTA E AGENCIA E IGUAL)");
      System.out.println("---------------------------------------------------------");
      continue;//Pula o objeto na lista
   } 
  }


  //Se o tipo de operacao for valido e executado a operacao de transferencia
  if(tipoDeOperacao(dados.getTipo_transferencia(),dados.getValor_transferencia())){
      operacaoTransferencia(dados);
  }

  System.out.println("---------------------------------------------------------");
   
  }
  
  }


  
  /*
       3° Faco a validacao da operacao que vai ser feita, dependendo do tipo passado no arquivo
  */
  private boolean tipoDeOperacao(EnumTipos enumTipos, double valor_transferencia){

  if(enumTipos == null){
      System.out.println("Sua transferencia nao foi completada pois (TIPO DE TRANFERENCIA NAO EXISTE)");
      return false;
  }
    
  //3.As transferências deverão ser executadas de acordo com o seu tipo, sendo 3 os seus tipos: PIX, TED e DOC;
  switch(enumTipos.getDescricao()){
    case "PIX":
      //4.O limite de valor máximo permitido para uma transferência via PIX é de R$ 5 mil;
      if(valor_transferencia > 5000){ 
        System.out.println("Sua transferencia nao foi completada pois (VALOR DO PIX NAO E MAIOR QUE 5 MIL)");
        return false;
    } break;

    case "TED": 
       //5.Transferências via TED só são permitidas para valores acima de R$ 5 mil e até R$ 10 mil;
       if(valor_transferencia < 5000 || valor_transferencia > 10000){ 
         System.out.println("Sua transferencia nao foi completada pois (VALOR DO TED E MENOR QUE 5 MIL OU MAIOR QUE 10 MIL)");
         return false;
    } break;

    case "DOC": 
       //6.Transferências via DOC só são permitidas para valores acima de R$ 10 mil;
       if(valor_transferencia < 10000){ 
          System.out.println("Sua transferencia nao foi completada pois (VALOR DO DOC E MENOR QUE 10 MIL)");
          return false;
    } break;
  }

 
    return true;
  }


  
  /*
      4° Etapa da operacao de transferencia, quando faz as operacoes nos saldos dos clientes
  */
  private void operacaoTransferencia(DadosTransferencia dados){

  //Usando operacoes simples de adicao e subtracao para atualizar o saldo das pessoas   
  dados.getReceptor().setSaldo((dados.getReceptor().getSaldo() + dados.getValor_transferencia()));
  dados.getEmissor().setSaldo((dados.getEmissor().getSaldo() - dados.getValor_transferencia()));

    
  System.out.println("Sua transferencia foi realizada com sucesso!");
  System.out.println("Saldo do emissor: " + dinheiroBR.format(dados.getEmissor().getSaldo()));
  System.out.println("Saldo do receptor: " + dinheiroBR.format(dados.getReceptor().getSaldo()));
    
  }


  
}