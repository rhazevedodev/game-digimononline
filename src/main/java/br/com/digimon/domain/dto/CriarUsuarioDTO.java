package br.com.digimon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarUsuarioDTO {

    private String nomeUsuario;
    private String email;
    private String senha;
    private Date dataNascimento;
}
