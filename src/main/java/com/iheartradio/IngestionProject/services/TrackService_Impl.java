package com.iheartradio.IngestionProject.services;

import com.iheartradio.IngestionProject.domain.Atributes;
import com.iheartradio.IngestionProject.domain.Track;
import com.iheartradio.IngestionProject.redis.services.TrackSearch_Service_Redis_Impl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maruti Anudeep Thirumalasetti
 */
@Service
public class TrackService_Impl implements TrackService{

    private MongoTemplate mongoTemplate;

    private TrackSearch_Service_Redis_Impl searchServiceRedis;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TrackService_Impl.class);

    @Autowired
    public TrackService_Impl(MongoTemplate mongoTemplate, TrackSearch_Service_Redis_Impl searchServiceRedis) {
        this.mongoTemplate = mongoTemplate;
        this.searchServiceRedis = searchServiceRedis;
    }

    /**
     * findTracks - GEts list of tracks based on search key. First checks search key in Cache (Redis) server then go to database (MongoDB). it save tracks to cache after retriving from mongodb
     * @param attr
     * @return
     */
    public List<Track> findTracks(Atributes attr) {
        StringBuilder searchKey_builder = new StringBuilder();
        Query query = new Query();
        if (attr.getArtist() != null) {
            searchKey_builder.append(attr.getArtist());
            query.addCriteria(Criteria.where("PRODUCTINFO.LANGUAGE.ARTIST").is(attr.getArtist()));
        }
        if (attr.getGenre() != null) {
            searchKey_builder.append("/").append(attr.getGenre());
            query.addCriteria(Criteria.where("GENRES.GENRENAME").is(attr.getGenre()));
        }
        if (attr.getTitle() != null) {
            searchKey_builder.append("/").append(attr.getTitle());
            query.addCriteria(Criteria.where("PRODUCTINFO.LANGUAGE.TITLE").is(attr.getTitle()));
        }
        String searchKey = searchKey_builder.toString();
        List<Track> result = null;
        try {
            if (searchServiceRedis.check_SearchKey(searchKey)) {
                result = searchServiceRedis.getTrackFromSearch(searchKey);
            } else {
                result = mongoTemplate.find(query, Track.class);
                searchServiceRedis.addToCache(searchKey, result);
            }
        } catch (NullPointerException ex) {
            result = new ArrayList<>();
            log.error(ex.getStackTrace().toString());
        }
        return result;
    }
}
