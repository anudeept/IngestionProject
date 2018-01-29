package com.iheartradio.IngestionProject.redis.services;

import com.iheartradio.IngestionProject.domain.Track;
import com.iheartradio.IngestionProject.redis.domain.SearchTrack;
import com.iheartradio.IngestionProject.redis.domain.Track_R;
import com.iheartradio.IngestionProject.redis.repositories.SearchTrack_Repository;
import com.iheartradio.IngestionProject.redis.repositories.TrackList_Reository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Maruti Anudeep Thirumalasetti
 */

@Service
public class TrackSearch_Service_Redis_Impl implements TrackSearch_Service_Redis {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TrackSearch_Service_Redis_Impl.class);

    SearchTrack_Repository searchTrack_repository;


    TrackList_Reository trackList_reository;

    @Autowired
    public TrackSearch_Service_Redis_Impl(SearchTrack_Repository searchTrack_repository, TrackList_Reository trackList_reository) {
        this.searchTrack_repository = searchTrack_repository;
        this.trackList_reository = trackList_reository;
    }

    /**
     * check_SearchKey -Search key in Redis Server
     *
     * @param key
     * @return
     * @throws NullPointerException
     */
    @Override
    public boolean check_SearchKey(String key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException("Search Key is null ");
        }
        return searchTrack_repository.exists(key);
    }

    /**
     * check_Track - Search for track Key
     *
     * @param trackId
     * @return
     */
    public boolean check_Track(String trackId) {
        return trackList_reository.exists(trackId);
    }

    /**
     * getTrack - gets track from track list
     *
     * @param trackId
     * @return
     */
    @Override
    public Track getTrack(String trackId) {
        return trackList_reository.findOne(trackId).getTrack();
    }

    /**
     * saveTrack -saves track in redis
     *
     * @param trackKey
     * @param track
     */
    @Override
    public void saveTrack(String trackKey, Track track) {
        trackList_reository.save(new Track_R(trackKey, track));
    }

    /**
     * saveSearch - saves search with list of track ids
     *
     * @param key
     * @param trackIds
     */
    @Override
    public void saveSearch(String key, List<String> trackIds) {
        searchTrack_repository.save(new SearchTrack(key, trackIds));
    }

    /**
     * getTrackFromSearch - Gets Tracks from redis server based on search key
     *
     * @param searchKey
     * @return
     */
    @Override
    public List<Track> getTrackFromSearch(String searchKey) {
        if (check_SearchKey(searchKey)) {
            List<String> keys = searchTrack_repository.findOne(searchKey).getTrackIds();
            Iterable<Track_R> iterable = trackList_reository.findAll(keys);
            return StreamSupport.stream(iterable.spliterator(), false).map(x -> x.getTrack()).collect(Collectors.toList());
        } else {
            return new ArrayList<Track>();
        }
    }

    /**
     * addToCache - adds serach key and tracks to redis server.
     * @param searchKey
     * @param tracks
     * @return
     */
    @Override
    public boolean addToCache(String searchKey, List<Track> tracks) {
        List<String> trackIds = new ArrayList<>();
        try {
            tracks.stream().forEach(track -> {
                String trackkey = track.getPRODUCTINFO().getLANGUAGE().getAMWKEY();
                trackIds.add(trackkey);
                saveTrack(trackkey, track);
            });
            saveSearch(searchKey, trackIds);
        } catch (Exception ex) {
            log.error(ex.getStackTrace().toString());
            return false;
        }
        return true;
    }
}
