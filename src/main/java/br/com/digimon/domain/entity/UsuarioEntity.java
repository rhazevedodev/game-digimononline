package br.com.digimon.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuario")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nomeUsuario;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column
    private Date dataNascimento;

    @Column(length = 50)
    private String criadoPor = "usuario";

    @Column
    private Date criadoEm = new Date();

    @Column
    private LocalDateTime ultimaAlteracao;

    @Column(length = 50)
    private String ultimaAlteracaoPor;

    @Column
    private int pontosDigitais = 0;

    @Column
    private int slotsDigimon = 1;

    @Column(length = 50)
    private String indicacao;

    @Column
    private boolean primeiroAcesso = true;

}
