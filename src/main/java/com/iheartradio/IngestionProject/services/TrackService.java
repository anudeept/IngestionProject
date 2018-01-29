package com.iheartradio.IngestionProject.services;

import com.iheartradio.IngestionProject.domain.Atributes;
import com.iheartradio.IngestionProject.domain.Track;

import java.util.List;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
public interface TrackService {

    public List<Track> findTracks(Atributes attr) ;
}
