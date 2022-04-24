package com.ciandt.summit.bootcamp2022.controller;

import com.ciandt.summit.bootcamp2022.entity.Musicas;
import com.ciandt.summit.bootcamp2022.entity.PlaylistMusicas;
import com.ciandt.summit.bootcamp2022.entity.PlaylistMusicasKey;
import com.ciandt.summit.bootcamp2022.entity.Playlists;
import com.ciandt.summit.bootcamp2022.service.MusicasService;
import com.ciandt.summit.bootcamp2022.service.PlaylistsMusicasService;
import com.ciandt.summit.bootcamp2022.service.PlaylistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistsController {

    @Autowired
    private PlaylistsMusicasService playlistsMusicasService;

    @Autowired
    private PlaylistsService playlistsService;

    @Autowired
    private MusicasService musicasService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping( value = "/{playlistId}/musicas")
    public ResponseEntity<PlaylistMusicas> adicionarMusica(@PathVariable("playlistId") String playlistId,@Valid @RequestBody Musicas musicaId){

        try {
            Optional<Playlists> playlists = playlistsService.buscarPlaylist(playlistId);
            Optional<Musicas> musicas = musicasService.buscarMusicaPorId(musicaId.getId());


            if (playlists.isPresent() && musicas.isPresent()) {
                PlaylistMusicasKey playlistMusicasKey = new PlaylistMusicasKey(playlistId,musicaId.getId());
                PlaylistMusicas playlistMusicas = new PlaylistMusicas(playlistMusicasKey);
                playlistsMusicasService.salvarPlayList(playlistMusicas);
                return playlistsMusicasService.salvarPlayList(playlistMusicas);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            throw new RuntimeException("Erro ao adicionar música");
        }
    }


    }



