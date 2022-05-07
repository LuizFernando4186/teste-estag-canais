import java.io.IOException;
import java.io.File;
import java.util.*;


public class LeituraArquivo {

  //Objeto Scanner para ler o arquivo entrada.txt
  private static Scanner entrada;  


  //Método para abrir o arquivo arquivo.txt
  public static LinkedList<DadosTransferencia> abreArquivo() {

    File arquivo = new File(".//my_program//entrada.txt");  

      try {
        
        entrada = new Scanner(arquivo);
        
				}
				
      catch (IOException erroES) {
        System.err.println("Erro ao abrir o arquivo. Finalizando.");
        System.exit(1);//terminar o programa
      }

      return lerDados();

  }

  
  //Metodo para ler os registros do arquivo
  public static LinkedList<DadosTransferencia> lerDados() {
      LinkedList<DadosTransferencia> todosDados = new LinkedList<>();

      try {
      

      while (entrada.hasNext()) {//enquanto houver dados para ler, mostrar os registros
      
      String comando = entrada.nextLine();
      //Pular as duas primeiras linhas no arquivo de entrada
      if(comando.isEmpty() || comando.startsWith("id")) continue;
      String[] entradas = comando.split("\\|");
      
      todosDados.add(converte(entradas));//Adiciona o objeto DadosTransferencia na lista
   
      }
     
      fecharArquivo();//Fecha o arquivo depois de todas operacoes

    }
    catch (NoSuchElementException erroElemento) {
      System.err.println("Arquivo com problemas. Finalizando.");
      entrada.nextLine();//Descartar a entrada para que o usuário possa tentar de novo
    }
    catch (IllegalStateException erroEstado) {
      System.err.println("Erro ao ler o arquivo. Finalizando.");
    }


    return todosDados;

  }//fim do método lerDados




  //Metodo para fechar o arquivo aberto
  public static void fecharArquivo() {
    if (entrada != null)
      entrada.close();
  }


  public static DadosTransferencia converte(String[] entradas){

   
  try{ 
  
  //8.As entradas deverão estar sempre com todos os dados preenchidos.
  int id_transferencia = Integer.parseInt(entradas[0]);    
  double valor_transferencia = Double.parseDouble(entradas[1]);
  EnumTipos tipo_transferencia = EnumTipos.getEnum(entradas[2]);
  String nome_emissor = entradas[3];
  int agencia_emissor = Integer.parseInt(entradas[4]);
  int conta_emissor = Integer.parseInt(entradas[5]);
  String cpf_emissor = entradas[6];
  String nome_receptor = entradas[7];
  int agencia_receptor = Integer.parseInt(entradas[8]);
  int conta_receptor = Integer.parseInt(entradas[9]);
  String cpf_receptor = entradas[10];


 //2.Operador e receptor iniciam a operação com um saldo de R$ 0,00;
 DadosTransferencia dados = new DadosTransferencia(id_transferencia,valor_transferencia,tipo_transferencia,new Pessoa(0,nome_emissor,agencia_emissor,conta_emissor,cpf_emissor),new Pessoa(0,nome_receptor,agencia_receptor,conta_receptor,cpf_receptor));

  return dados;

}
  catch(Exception e){ 
  return null;
}

    
}

}