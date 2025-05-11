package br.com.digimon.domain.enums;

public enum EnumDigimonRookie {

    AGUMON(1, "Agumon", "FOGO", "../assets/digimons/rookies/agumon.jpg"),
    GABUMON(2, "Gabumon", "FOGO", "../assets/digimons/rookies/gabumon.jpg"),
    PIYOMON(3, "Piyomon", "VENTO", "../assets/digimons/rookies/piyomon.jpg"),
    TENTOMON(4, "Tentomon", "RAIO", "../assets/digimons/rookies/tentomon.jpg"),
    PALMON(5, "Palmon", "PLANTA", "../assets/digimons/rookies/palmon.jpg"),
    GOMAMON(6, "Gomamon", "AGUA", "../assets/digimons/rookies/gomamon.jpg"),
    PATAMON(7, "Patamon", "LUZ", "../assets/digimons/rookies/patamon.jpg"),
    SALAMON(8, "Salamon", "LUZ", "../assets/digimons/rookies/salamon.jpg"),
    VEEMON(9, "Veemon", "FOGO", "../assets/digimons/rookies/veemon.jpg"),
    WORMMON(10, "Wormmon", "TREVAS", "../assets/digimons/rookies/wormmon.jpg"),
    BEARMON(11, "Bearmon", "TERRA", "../assets/digimons/rookies/bearmon.jpg"),
    GUILMON(12, "Guilmon", "FOGO", "../assets/digimons/rookies/guilmon.jpg"),
    TERRIERMON(13, "Terriermon", "VENTO", "../assets/digimons/rookies/terriermon.jpg"),
    RENAMON(14, "Renamon", "VENTO", "../assets/digimons/rookies/renamon.jpg"),
    HAWKMON(15, "Hawkmon", "VENTO", "../assets/digimons/rookies/hawkmon.jpg"),
    ARMADILLOMON(16, "Armadillomon", "TERRA", "../assets/digimons/rookies/armadillomon.jpg"),
    IMPMON(17, "Impmon", "TREVAS", "../assets/digimons/rookies/impmon.jpg"),
    DEVIDEVIMON(18, "Devidevimon", "TREVAS", "../assets/digimons/rookies/devidevimon.jpg"),
    MONODRAMON(19, "Monodramon", "TREVAS", "../assets/digimons/rookies/monodramon.jpg"),
    LOPMON(20, "Lopmon", "GELO", "../assets/digimons/rookies/lopmon.jpg"),
    BETAMON(21, "Betamon", "PLANTA", "../assets/digimons/rookies/betamon.jpg");

    private int id;
    private String descricao;
    private String elemento;
    private String urlImg;


    EnumDigimonRookie(int id, String descricao, String elemento, String urlImg) {
        this.id = id;
        this.descricao = descricao;
        this.elemento = elemento;
        this.urlImg = urlImg;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getElemento() {
        return elemento;
    }

    public static String getDescricaoById(int id) {
        for (EnumDigimonRookie digimon : EnumDigimonRookie.values()) {
            if (digimon.getId() == id) {
                return digimon.getDescricao();
            }
        }
        throw new RuntimeException("ID de Digimon Rookie inválido: " + id);
    }

    public static String getIdByDescricao(String descricao) {
        for (EnumDigimonRookie digimon : EnumDigimonRookie.values()) {
            if (digimon.getDescricao().equals(descricao)) {
                return String.valueOf(digimon.getId());
            }
        }
        throw new RuntimeException("Descrição de Digimon Rookie inválida: " + descricao);
    }

    public static EnumDigimonRookie getEnumById(int id) {
        for (EnumDigimonRookie digimon : EnumDigimonRookie.values()) {
            if (digimon.getId() == id) {
                return digimon;
            }
        }
        throw new RuntimeException("ID de Digimon Rookie inválido: " + id);
    }

    public static String getElementoByEnum(EnumDigimonRookie digimon) {
        return digimon.getElemento();
    }
}
