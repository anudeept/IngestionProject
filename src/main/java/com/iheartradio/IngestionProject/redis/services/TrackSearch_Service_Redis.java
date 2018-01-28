package com.iheartradio.IngestionProject.redis.services;

import com.iheartradio.IngestionProject.domain.Track;


import java.util.List;

/**
 * @author Maruti Anudeep Thirumalasetti
 */

public interface TrackSearch_Service_Redis {

    public boolean check_SearchKey(String key);

    public boolean check_Track(String trackId);

    public Track getTrack(String trackId);

     void saveTrack(String trackKey, Track track);

    public void saveSearch(String key, List<String> trackIds);

    public List<Track> getTrackFromSearch(String searchKey);

    public boolean addToCache(String searchKey, List<Track> tracks);
}
