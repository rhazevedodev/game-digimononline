package br.com.digimon.shared.enums;

public enum EnumElementos {

    FOGO(1, "FOGO"),
    AGUA(2, "AGUA"),
    TERRA(3, "TERRA"),
    VENTO(4, "VENTO"),
    LUZ(5, "LUZ"),
    TREVAS(6, "TREVAS"),
    RAIO(7, "RAIO"),
    GELO(8, "GELO"),
    METAL(9, "METAL");

    private final int id;
    private final String nomeElemento;

    EnumElementos(int id, String nomeElemento) {
        this.id = id;
        this.nomeElemento = nomeElemento;
    }

    public int getId() {
        return id;
    }

    public String getNomeElemento() {
        return nomeElemento;
    }

    public static int getIdElemento(String nomeElemento) {
        for (EnumElementos elemento : EnumElementos.values()) {
            if (elemento.getNomeElemento().equals(nomeElemento)) {
                return elemento.getId();
            }
        }
        return 0;
    }
}
