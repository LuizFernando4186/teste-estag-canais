public enum EnumTipos{
  
  PIX(1, "PIX"),
  TED(2, "TED"),
  DOC(3, "DOC");
  

    private int valor;
    private String descricao;
  
    private EnumTipos(int valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    //Este metodo foi feito para saber o tipo de operacao, assim consiguimos 
    //Separar bem qual operacao temos neste banco
    public static EnumTipos getEnum(String descricao) {
        for (EnumTipos elementoEnum : EnumTipos.values()) {
            if (elementoEnum.getDescricao().equals(descricao)) {
                return elementoEnum;
            }
        }

        return null;
    }

   public int getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

  

}