package com.iheartradio.IngestionProject.redis.repositories;

import com.iheartradio.IngestionProject.redis.domain.Track_R;
import org.springframework.data.repository.CrudRepository;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
public interface TrackList_Reository extends CrudRepository<Track_R,String> {

}
