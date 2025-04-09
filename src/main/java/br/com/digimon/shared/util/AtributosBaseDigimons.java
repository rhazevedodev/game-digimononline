package br.com.digimon.shared.util;

import br.com.digimon.domain.entity.AtributosBaseEntity;
import br.com.digimon.shared.enums.EnumAtributosBase;
import br.com.digimon.shared.enums.EnumDigimonRookie;

public class AtributosBaseDigimons {

    public static AtributosBaseEntity definirAtributosBaseDigimonsRookies(AtributosBaseEntity atributosBase, EnumDigimonRookie rookie) {
        EnumAtributosBase atributosBaseDigimon = EnumAtributosBase.valueOf(rookie.name());

        return new AtributosBaseEntity(
                atributosBaseDigimon.getBaseResistencia(),
                atributosBaseDigimon.getBaseVida(),
                atributosBaseDigimon.getBaseEnergia(),
                atributosBaseDigimon.getBaseForca(),
                atributosBaseDigimon.getBaseInteligencia(),
                atributosBaseDigimon.getBaseAgilidade(),
                atributosBaseDigimon.getBaseConhecimento()
        );
    }
}
