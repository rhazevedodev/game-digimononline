package br.com.digimon.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

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

    public UsuarioEntity() {
    }

    public UsuarioEntity(Long id, String nomeUsuario, String email, String senha, Date dataNascimento, String criadoPor, Date criadoEm, LocalDateTime ultimaAlteracao, String ultimaAlteracaoPor, int pontosDigitais, int slotsDigimon, String indicacao) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
        this.criadoPor = criadoPor;
        this.criadoEm = criadoEm;
        this.ultimaAlteracao = ultimaAlteracao;
        this.ultimaAlteracaoPor = ultimaAlteracaoPor;
        this.pontosDigitais = pontosDigitais;
        this.slotsDigimon = slotsDigimon;
        this.indicacao = indicacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(String criadoPor) {
        this.criadoPor = criadoPor;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Date criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(LocalDateTime ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String getUltimaAlteracaoPor() {
        return ultimaAlteracaoPor;
    }

    public void setUltimaAlteracaoPor(String ultimaAlteracaoPor) {
        this.ultimaAlteracaoPor = ultimaAlteracaoPor;
    }

    public int getPontosDigitais() {
        return pontosDigitais;
    }

    public void setPontosDigitais(int pontosDigitais) {
        this.pontosDigitais = pontosDigitais;
    }

    public int getSlotsDigimon() {
        return slotsDigimon;
    }

    public void setSlotsDigimon(int slotsDigimon) {
        this.slotsDigimon = slotsDigimon;
    }

    public String getIndicacao() {
        return indicacao;
    }

    public void setIndicacao(String indicacao) {
        this.indicacao = indicacao;
    }
}
