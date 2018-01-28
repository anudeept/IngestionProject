package com.iheartradio.IngestionProject.repository;

import com.iheartradio.IngestionProject.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
public interface TrackRepository extends MongoRepository<Track,String> {

    @Query("{'PRODUCTINFO.LANGUAGE.AMWKEY':?0}")
    Track findByKey(String artist);
}
