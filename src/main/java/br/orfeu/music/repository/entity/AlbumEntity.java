package br.orfeu.music.repository.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "album")
public class AlbumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(name = "data_lancamento")
    private LocalDate dataLancamento;

    @Column(name = "capa_url", length = 500)
    private String capaUrl;

    @Column(length = 50)
    private String tipo; // Album, EP, Single

    @Column(length = 255)
    private String gravadora;

    @Column(length = 20)
    private String status = "PENDING";

    @ManyToOne
    @JoinColumn(name = "genero_id")
    private GeneroEntity genero;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private ArtistaEntity artista;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }

    public String getCapaUrl() { return capaUrl; }
    public void setCapaUrl(String capaUrl) { this.capaUrl = capaUrl; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getGravadora() { return gravadora; }
    public void setGravadora(String gravadora) { this.gravadora = gravadora; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public GeneroEntity getGenero() { return genero; }
    public void setGenero(GeneroEntity genero) { this.genero = genero; }

    public ArtistaEntity getArtista() { return artista; }
    public void setArtista(ArtistaEntity artista) { this.artista = artista; }
}
