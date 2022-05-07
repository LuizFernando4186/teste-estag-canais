public class DadosTransferencia {

private int id_transferencia;
private double valor_transferencia;
private EnumTipos tipo_transferencia;
private Pessoa receptor;
private Pessoa emissor;

  

public DadosTransferencia(int id_transferencia,double valor_transferencia, 
EnumTipos tipo_transferencia, Pessoa emissor, Pessoa receptor){

this.id_transferencia = id_transferencia;
this.valor_transferencia = valor_transferencia;
this.tipo_transferencia = tipo_transferencia;
this.receptor = receptor;
this.emissor = emissor;
 
}


//////////////////////////////////////////////
//              GETs e SETs                 //
//////////////////////////////////////////////


  public int getId_transferencia(){
  return this.id_transferencia;
}
  public double getValor_transferencia(){
  return this.valor_transferencia;
}
  public EnumTipos getTipo_transferencia(){
  return this.tipo_transferencia;
}
 public Pessoa getEmissor(){
  return this.emissor;
}
 public Pessoa getReceptor(){
  return this.receptor;
}

  

}