package br.com.digimon.domain.enums;


public enum EnumDigimonUltimate {

    METALGREYMON(1, "MetalGreymon", "FOGO", "./imagens/digimons/ultimates/metalgreymon.jpg",1),
    WEREGARURUMON(2, "WereGarurumon", "GELO", "./imagens/digimons/ultimates/weregarurumon.jpg",2),
    GARUDAMON(3, "Garudamon", "VENTO", "./imagens/digimons/ultimates/garudamon.jpg",4),
    MEGAKABUTERIMON(4, "Megakabuterimon", "METAL", "./imagens/digimons/ultimates/megakabuterimon.jpg",6),
    LILLIMON(5, "Lillimon", "PLANTA", "./imagens/digimons/ultimates/lillimon.jpg",4),
    ZUDOMON(6, "Zudomon", "ÁGUA", "./imagens/digimons/ultimates/zudomon.jpg",3),
    MAGNAANGEMON(7, "Magnaangemon", "LUZ", "./imagens/digimons/ultimates/magnaangemon.jpg",5);

    private int id;
    private String descricao;
    private String elemento;
    private String urlImg;
    private int idItemDigievolucao;


    EnumDigimonUltimate(int id, String descricao, String elemento, String urlImg, int idItemDigievolucao) {
        this.id = id;
        this.descricao = descricao;
        this.elemento = elemento;
        this.urlImg = urlImg;
        this.idItemDigievolucao = idItemDigievolucao;
    }

    public static String getUrlImgById(int idUltimate) {
        for (EnumDigimonUltimate digimon : EnumDigimonUltimate.values()) {
            if (digimon.getId() == idUltimate) {
                return digimon.getUrlImg();
            }
        }
        throw new RuntimeException("ID de Digimon Ultimate inválido: " + idUltimate);
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

    // Método estático para obter EnumDigimonRookie pelo ID
    public static EnumDigimonUltimate getEnumById(int id) {
        for (EnumDigimonUltimate digimon : EnumDigimonUltimate.values()) {
            if (digimon.getId() == id) {
                return digimon;
            }
        }
        throw new RuntimeException("ID de Digimon Ultimate inválido: " + id);
    }

    public static String getDescricaoById(int id) {
        for (EnumDigimonUltimate digimon : EnumDigimonUltimate.values()) {
            if (digimon.getId() == id) {
                return digimon.getDescricao();
            }
        }
        throw new RuntimeException("ID de Digimon Ultimate inválido: " + id);
    }

    public static int getIdByDescricao(String descricao) {
        for (EnumDigimonUltimate digimon : EnumDigimonUltimate.values()) {
            if (digimon.getDescricao().equals(descricao)) {
                return digimon.getId();
            }
        }
        throw new RuntimeException("Descrição de Digimon Ultimate inválida: " + descricao);
    }

    public int getIdItemDigievolucao() {
        return idItemDigievolucao;
    }

    public static int getIdItemDigievolucaoById(int id) {
        for (EnumDigimonUltimate digimon : EnumDigimonUltimate.values()) {
            if (digimon.getId() == id) {
                return digimon.getIdItemDigievolucao();
            }
        }
        throw new RuntimeException("ID de Digimon Ultimate inválido: " + id);
    }
}
