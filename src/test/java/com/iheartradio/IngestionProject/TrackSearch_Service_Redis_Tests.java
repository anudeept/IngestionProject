package com.iheartradio.IngestionProject;

import com.iheartradio.IngestionProject.domain.*;
import com.iheartradio.IngestionProject.redis.domain.SearchTrack;
import com.iheartradio.IngestionProject.redis.domain.Track_R;
import com.iheartradio.IngestionProject.redis.repositories.SearchTrack_Repository;
import com.iheartradio.IngestionProject.redis.repositories.TrackList_Reository;
import com.iheartradio.IngestionProject.redis.services.TrackSearch_Service_Redis;
import com.iheartradio.IngestionProject.redis.services.TrackSearch_Service_Redis_Impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackSearch_Service_Redis_Tests {

    @Mock
    private SearchTrack_Repository searchTrack_repository;

    @Mock
    private TrackList_Reository trackList_reository;

    private TrackSearch_Service_Redis_Impl searchServiceRedis;
    Track track;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
        searchServiceRedis = new TrackSearch_Service_Redis_Impl(searchTrack_repository, trackList_reository);

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
    public void searchKey_Test() {
        String key = "anudeep";
        when(searchTrack_repository.exists(key)).thenReturn(true);
        Assert.assertEquals(searchServiceRedis.check_SearchKey(key), true);
    }

    @Test(expected = NullPointerException.class)
    public void searchKey_Test_Exception() {
        String key = null;
        try {
            searchServiceRedis.check_SearchKey(key);
        } catch (NullPointerException e) {
            throw e;
        }
    }

    @Test
    public void getTrackFromSearch_Test() {
        List<String> ids = Arrays.asList("001", "002");
        String key = "key";
        Track_R track_r = new Track_R("001", track);
        when(searchTrack_repository.exists(key)).thenReturn(true);
        when(searchTrack_repository.findOne(key)).thenReturn(new SearchTrack(key, ids));
        when(trackList_reository.findAll(ids)).thenReturn(Arrays.asList(track_r));
        List<Track> res = searchServiceRedis.getTrackFromSearch(key);
        Assert.assertEquals(track, res.get(0));
    }

    @Test
    public void addToCache_Test() {
        Track_R track_r=new Track_R("001", track);
        SearchTrack searchTrack=new SearchTrack("/artist", Arrays.asList("001", "002"));
        when(trackList_reository.save(track_r)).thenReturn(track_r);
        when(searchTrack_repository.save(searchTrack)).thenReturn(searchTrack);
        boolean res = searchServiceRedis.addToCache("key", Arrays.asList(track));
        Assert.assertEquals(res, true);
    }

}
