package br.com.digimon.domain.enums;



public enum EnumDigimonChampion {

    GREYMON(1, "Greymon", "FOGO", "./imagens/digimons/champions/greymon2.jpg"),
    GARURUMON(2, "Garurumon", "GELO", "./imagens/digimons/champions/garurumon.jpg"),
    BIRDRAMON(3, "Birdramon", "VENTO", "./imagens/digimons/champions/birdramon.jpg"),
    KABUTERIMON(4, "Kabuterimon", "RAIO", "./imagens/digimons/champions/kabuterimon.jpg"),
    TOGEMON(5, "Togemon", "PLANTA", "./imagens/digimons/champions/togemon.jpg"),
    IKKAKUMON(6, "Ikkakumon", "ÁGUA", "./imagens/digimons/champions/ikkakumon.png"),
    ANGEMON(7, "Angemon", "LUZ", "./imagens/digimons/champions/angemon.jpg"),
    TAILMON(8, "Tailmon", "LUZ", "./imagens/digimons/champions/tailmon.jpg"),
    EXVEEMON(9, "Exveemon", "FOGO", "./imagens/digimons/champions/exveemon.jpg"),
    STINGMON(10, "Stingmon", "TERRA", "./imagens/digimons/champions/stingmon.jpg"),
    GATOMON(11, "Gatomon", "LUZ", "./imagens/digimons/champions/gatomon.jpg"),
    DEVIMON(12, "Devimon", "TREVAS", "./imagens/digimons/champions/devimon.jpg"),
    KYUBIMON(13, "Kyubimon", "FOGO", "./imagens/digimons/champions/kyubimon.jpg"),
    GARGOMON(14, "Gargomon", "TERRA", "./imagens/digimons/champions/gargomon.jpg"),
    AQUILAMON(15, "Aquilamon", "VENTO", "./imagens/digimons/champions/aquilamon.jpg"),
    ANKYLOMON(16, "Ankylomon", "TERRA", "./imagens/digimons/champions/ankylomon.jpg"),
    GROWLMON(17, "Growlmon", "TERRA", "./imagens/digimons/champions/growlmon.jpg"),
    ANTYLAMON(18, "Antylamon", "LUZ", "./imagens/digimons/champions/antylamon.jpg"),
    HOOKMON(19, "Hookmon", "TERRA", "./imagens/digimons/champions/hookmon.jpg"),
    GRIZZLYMON(20, "Grizzlymon", "TERRA", "./imagens/digimons/champions/grizzlymon.jpg"),
    DINOREXMON(21, "Dinorexmon", "TERRA", "./imagens/digimons/champions/dinorexmon.jpg"),
    STRIKEDRAMON(22, "Strikedramon", "TERRA", "./imagens/digimons/champions/strikedramon.jpg"),
    MYOTISMON(23, "Myotismon", "TREVAS", "./imagens/digimons/champions/myotismon.jpg"),
    MEGASEADRAMON(24, "Megaseadramon", "ÁGUA", "./imagens/digimons/champions/megaseadramon.jpg"),
    SKULLGREYMON(25, "Skullgreymon", "TREVAS", "./imagens/digimons/champions/skullgreymon.jpg"),
    RISEGREYMON(26, "Risegreymon", "FOGO", "./imagens/digimons/champions/risegreymon.jpg"),
    BOLTMON(27, "Boltmon", "METAL", "./imagens/digimons/champions/boltmon.jpg"),
    DUSKMON(28, "Duskmon", "TREVAS", "./imagens/digimons/champions/duskmon.jpg");




    private int id;
    private String descricao;
    private String elemento;
    private String urlImg;


    EnumDigimonChampion(int id, String descricao, String elemento, String urlImg) {
        this.id = id;
        this.descricao = descricao;
        this.elemento = elemento;
        this.urlImg = urlImg;
    }

    public static String getUrlImgById(int idChampion) {
        for (EnumDigimonChampion digimon : EnumDigimonChampion.values()) {
            if (digimon.getId() == idChampion) {
                return digimon.getUrlImg();
            }
        }
        throw new RuntimeException("ID de Digimon Champion inválido: " + idChampion);
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
    public static EnumDigimonChampion getEnumById(int id) {
        for (EnumDigimonChampion digimon : EnumDigimonChampion.values()) {
            if (digimon.getId() == id) {
                return digimon;
            }
        }
        throw new RuntimeException("ID de Digimon Champion inválido: " + id);
    }

    public static String getDescricaoById(int id) {
        for (EnumDigimonChampion digimon : EnumDigimonChampion.values()) {
            if (digimon.getId() == id) {
                return digimon.getDescricao();
            }
        }
        throw new RuntimeException("ID de Digimon Champion inválido: " + id);
    }

    public static int getIdByDescricao(String descricao) {
        for (EnumDigimonChampion digimon : EnumDigimonChampion.values()) {
            if (digimon.getDescricao().equalsIgnoreCase(descricao)) {
                return digimon.getId();
            }
        }
        throw new RuntimeException("Descrição de Digimon Champion inválida: " + descricao);
    }

}
