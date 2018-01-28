package com.iheartradio.IngestionProject.redis.repositories;

import com.iheartradio.IngestionProject.redis.domain.SearchTrack;
import org.springframework.data.repository.CrudRepository;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
public interface SearchTrack_Repository extends CrudRepository<SearchTrack,String> {

}
