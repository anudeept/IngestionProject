package com.iheartradio.IngestionProject.redis.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
@RedisHash("Track_Search")
public class SearchTrack {

    @Id
    private String id;
    private List<String> trackIds;

    public SearchTrack() {
    }

    public SearchTrack(String id, List<String> trackIds) {
        this.id = id;
        this.trackIds = trackIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTrackIds() {
        return trackIds;
    }

    public void setTrackIds(List<String> trackIds) {
        this.trackIds = trackIds;
    }
}
