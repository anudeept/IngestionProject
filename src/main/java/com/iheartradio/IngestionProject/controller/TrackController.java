package com.iheartradio.IngestionProject.controller;

import com.iheartradio.IngestionProject.domain.Atributes;
import com.iheartradio.IngestionProject.domain.Track;
import com.iheartradio.IngestionProject.exception.TrackInternalServerException;
import com.iheartradio.IngestionProject.exception.TrackNotFoundException;
import com.iheartradio.IngestionProject.repository.TrackRepository;
import com.iheartradio.IngestionProject.services.TrackService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.smartcardio.ATR;
import java.util.List;

/**
 * @author Maruti Anudeep Thirumalasetti
 */
@RestController
@RequestMapping(value = "/tracks")
public class TrackController {

    @Autowired
    TrackService trackService;

    /**
     * get_All_Apps_All_Metrics - Rest API to search tracks
     *
     * @param atributes - Search parameters
     * @return - Json response
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<Track>> get_All_Apps_All_Metrics(Atributes atributes) throws TrackInternalServerException, TrackNotFoundException {
        List<Track> result = null;
        try {
            result = trackService.findTracks(atributes);
        } catch (Exception ex) {
            throw new TrackInternalServerException("Internal Error", ex.getCause());
        }
        if (result == null || result.isEmpty()) {
            throw new TrackNotFoundException("Track not found...");
        } else {
            return new ResponseEntity<List<Track>>(result, HttpStatus.OK);
        }
    }
}
