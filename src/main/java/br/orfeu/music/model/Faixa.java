package br.orfeu.music.model;

import br.orfeu.music.repository.entity.AlbumEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "faixas")
public class Faixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String duracao;

    @Transient // Temporary, until we fully migrate to AlbumEntity
    private String album;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumEntity albumEntity;

    private String creditos;

    @Column(name = "numero_faixa")
    private Integer numeroFaixa;

    @Column(length = 500)
    private String compositores;

    @Column(length = 500)
    private String produtores;

    @Column(columnDefinition = "TEXT")
    private String letra;

    @Column(length = 20)
    private String status = "PENDING";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getAlbum() {
        if (albumEntity != null) {
            return albumEntity.getTitulo();
        }
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public AlbumEntity getAlbumEntity() { return albumEntity; }
    public void setAlbumEntity(AlbumEntity albumEntity) { this.albumEntity = albumEntity; }

    public Integer getNumeroFaixa() { return numeroFaixa; }
    public void setNumeroFaixa(Integer numeroFaixa) { this.numeroFaixa = numeroFaixa; }

    public String getCompositores() { return compositores; }
    public void setCompositores(String compositores) { this.compositores = compositores; }

    public String getProdutores() { return produtores; }
    public void setProdutores(String produtores) { this.produtores = produtores; }

    public String getLetra() { return letra; }
    public void setLetra(String letra) { this.letra = letra; }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
