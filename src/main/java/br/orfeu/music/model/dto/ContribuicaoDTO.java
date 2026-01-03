package br.orfeu.music.model.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ContribuicaoDTO {
    // Artista
    private String nomeArtista;
    private String nomeVerdadeiro;
    private LocalDate dataNascimento;
    private String paisOrigem;
    private String fotoArtista;
    private String biografia;

    // Album
    private String nomeAlbum;
    private String tipoAlbum;
    private LocalDate dataLancamento;
    private String urlCapa;
    private String gravadora;

    // Faixas
    private List<FaixaContribuicaoDTO> faixas;

    @Data
    public static class FaixaContribuicaoDTO {
        private Integer numero;
        private String nome;
        private String duracao;
        private String compositores;
        private String produtores;
        private String creditos; // Feat
    }
}
