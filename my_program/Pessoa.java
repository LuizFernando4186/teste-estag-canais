public class Pessoa {

//Foi feito uma classe Pessoa, pq atributos nome, agencia, conta e cpf
//Sao iguais para o emissor e receptor e seria mais facil manipular e organizar

  private String nome;
  private int agencia;
  private int conta;
  private String cpf;
  private double saldo;

  
  public Pessoa(double saldo, String nome,int agencia,int conta,String cpf){
  this.saldo = saldo;
  this.nome = nome;
  this.agencia = agencia;
  this.conta = conta;
  this.cpf = cpf;
  }


  //////////////////////////////////////////////
  //              GETs e SETs                 //
  //////////////////////////////////////////////
  
  public String getNome(){
  return this.nome;
 }
  public int getConta(){
  return this.conta;
}
  public int getAgencia(){
  return this.agencia;
}
 public double getSaldo(){
  return this.saldo;
}
 public String getCPF(){
  return this.cpf;
}
  public void setSaldo(double saldo){
  this.saldo = saldo;
}

}
