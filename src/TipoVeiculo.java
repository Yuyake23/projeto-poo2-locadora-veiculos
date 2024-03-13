public enum TipoVeiculo {
    PEQUENO(100),
    MEDIO(150),
    SUV(200);

    private double valorDiaria;

    TipoVeiculo(double valor) {
        this.valorDiaria = valor;
    }
    public double getValorDiaria(){
        return this.valorDiaria;
    }

}
