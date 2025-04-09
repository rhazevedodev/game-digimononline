package br.com.digimon.domain.enums;



public enum EnumDigimonMega {

    WARGREYMON(1, "Wargreymon", "FOGO", "./imagens/digimons/megas/wargreymon.jpg", 27),
    METALGARURUMON(2, "Metalgarurumon", "GELO", "./imagens/digimons/megas/metalgarurumon.jpg", 28),
    PHOENIXMON(3, "Phoenixmon", "VENTO", "./imagens/digimons/megas/phoenixmon.jpg", 32),
    HERCULESKABUTERIMON(4, "Herculeskabuterimon", "METAL", "./imagens/digimons/megas/herculeskabuterimon.jpg", 33),
    ROSEMON(5, "Rosemon", "PLANTA", "./imagens/digimons/megas/rosemon.jpg", 30),
    VIKEMON(6, "Vikemon", "ÁGUA", "./imagens/digimons/megas/vikemon.jpg", 29),
    SERAPHIMON(7, "Seraphimon", "LUZ", "./imagens/digimons/megas/seraphimon.jpg", 31);

    private int id;
    private String descricao;
    private String elemento;
    private String urlImg;
    private int idItemDigievolucao;

    EnumDigimonMega(int id, String descricao, String elemento, String urlImg, int idItemDigievolucao) {
        this.id = id;
        this.descricao = descricao;
        this.elemento = elemento;
        this.urlImg = urlImg;
        this.idItemDigievolucao = idItemDigievolucao;
    }

    public static String getUrlImgById(int idMega) {
        for (EnumDigimonMega digimon : EnumDigimonMega.values()) {
            if (digimon.getId() == idMega) {
                return digimon.getUrlImg();
            }
        }
        throw new RuntimeException("ID de Digimon Mega inválido: " + idMega);
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

    public int getIdItemDigievolucao() {
        return idItemDigievolucao;
    }

    // Método estático para obter EnumDigimonRookie pelo ID
    public static EnumDigimonMega getEnumById(int id) {
        for (EnumDigimonMega digimon : EnumDigimonMega.values()) {
            if (digimon.getId() == id) {
                return digimon;
            }
        }
        throw new RuntimeException("ID de Digimon Mega inválido: " + id);
    }

    public static String getDescricaoById(int id) {
        for (EnumDigimonMega digimon : EnumDigimonMega.values()) {
            if (digimon.getId() == id) {
                return digimon.getDescricao();
            }
        }
        throw new RuntimeException("ID de Digimon Mega inválido: " + id);
    }

    public static int getIdByDescricao(String descricao) {
        for (EnumDigimonMega digimon : EnumDigimonMega.values()) {
            if (digimon.getDescricao().equals(descricao)) {
                return digimon.getId();
            }
        }
        throw new RuntimeException("Descrição de Digimon Mega inválida: " + descricao);
    }

    public static int getIdItemDigievolucaoById(int id) {
        for (EnumDigimonMega digimon : EnumDigimonMega.values()) {
            if (digimon.getId() == id) {
                return digimon.getIdItemDigievolucao();
            }
        }
        throw new RuntimeException("ID de Digimon Ultimate inválido: " + id);
    }

}
