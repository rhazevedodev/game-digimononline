package br.com.digimon.shared.enums;

public enum EnumAtributosBase {

    AGUMON(100, 80, 12, 7, 9, 6, 10),
    GABUMON(90, 85, 10, 9, 11, 7, 8),
    PALMON(110, 90, 8, 10, 8, 10, 11),
    GOMAMON(95, 100, 8, 8, 12, 9, 8),
    PATAMON(85, 120, 7, 12, 10, 11, 7),
    TENTOMON(100, 90, 7, 11, 8, 12, 11),
    PIYOMON(90, 110, 8, 9, 11, 8, 8);

    private final int baseVida;
    private final int baseEnergia;
    private final int baseForca;
    private final int baseInteligencia;
    private final int baseAgilidade;
    private final int baseConhecimento;
    private final int baseResistencia;

    EnumAtributosBase(int baseVida, int baseEnergia, int baseForca, int baseInteligencia, int baseAgilidade, int baseConhecimento, int baseResistencia){
        this.baseVida = baseVida;
        this.baseEnergia = baseEnergia;
        this.baseForca = baseForca;
        this.baseInteligencia = baseInteligencia;
        this.baseAgilidade = baseAgilidade;
        this.baseConhecimento = baseConhecimento;
        this.baseResistencia = baseResistencia;
    }

    public int getBaseResistencia() {
        return baseResistencia;
    }

    public int getBaseVida() {
        return baseVida;
    }

    public int getBaseEnergia() {
        return baseEnergia;
    }

    public int getBaseForca() {
        return baseForca;
    }

    public int getBaseInteligencia() {
        return baseInteligencia;
    }

    public int getBaseAgilidade() {
        return baseAgilidade;
    }

    public int getBaseConhecimento() {
        return baseConhecimento;
    }
}
