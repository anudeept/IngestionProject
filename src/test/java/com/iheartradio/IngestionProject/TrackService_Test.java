package com.iheartradio.IngestionProject;

import com.iheartradio.IngestionProject.domain.*;
import com.iheartradio.IngestionProject.redis.services.TrackSearch_Service_Redis;
import com.iheartradio.IngestionProject.redis.services.TrackSearch_Service_Redis_Impl;
import com.iheartradio.IngestionProject.services.TrackService;
import com.iheartradio.IngestionProject.services.TrackService_Impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrackService_Test {

    @Mock
    TrackSearch_Service_Redis_Impl trackSearch_service_redis;

    @Mock
    MongoTemplate mongoTemplate;

    private TrackService_Impl trackService;
    private Track track;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        trackService = new TrackService_Impl(mongoTemplate, trackSearch_service_redis);
        track = new Track();
        ProductInfo productInfo = new ProductInfo();
        Language language = new Language();
        language.setAMWKEY("111-SAMPLE-ABC");
        language.setCOPYRIGHTYEAR("1984");
        language.setCOPYRIGHT("(P) 1984 Epic Records, a division of Sony Music Entertainment");
        language.setPUBLISHER("(P) 1984 Epic Records, a division of Sony Music Entertainment");
        language.setEXPLICITLYRICS("false");
        language.setTITLE("Scuttle Buttin");
        language.setDURATION(112);
        language.setTRACKNUMBER(1);
        language.setARTIST("Stevie Ray Vaughan &amp; Double Trouble");

        productInfo.setLANGUAGE(language);

        Genre genre = new Genre();
        genre.setGENRENAME("Rock");

        FileInfo fileInfo = new FileInfo();
        fileInfo.setACCESSSTRING("resources/111-SAMPLE-ABC.mp3");

        File file = new File();
        file.setFILEINFO(fileInfo);

        track.setFILES(Arrays.asList(file));
        track.setGENRES(Arrays.asList(genre));
        track.setPRODUCTINFO(productInfo);
    }

    @Test
    public void findTracks_DB_Test() {
        Atributes atributes= new Atributes();
        atributes.setArtist("Stevie Ray Vaughan &amp; Double Trouble");
        String key=atributes.getArtist();
        List<Track> tracks= new ArrayList<>();
        tracks.add(track);
        Mockito.when(trackSearch_service_redis.check_SearchKey(key)).thenReturn(false);
        Mockito.when(mongoTemplate.find(new Query(Criteria.where("PRODUCTINFO.LANGUAGE.ARTIST").is(atributes.getArtist())), Track.class)).thenReturn(tracks);
        Mockito.when(trackSearch_service_redis.addToCache(key,tracks)).thenReturn(true);
        Assert.assertEquals(tracks.toArray(),trackService.findTracks(atributes).toArray());
    }
    @Test
    public void findTracks_Cache_Test() {
        Atributes atributes= new Atributes();
        atributes.setArtist("Stevie Ray Vaughan &amp; Double Trouble");
        String key=atributes.getArtist();
        List<Track> tracks= new ArrayList<>();
        tracks.add(track);
        Mockito.when(trackSearch_service_redis.check_SearchKey(key)).thenReturn(true);
        Mockito.when(trackSearch_service_redis.getTrackFromSearch(key)).thenReturn(tracks);
        Assert.assertEquals(tracks.toArray(),trackService.findTracks(atributes).toArray());
    }


}
