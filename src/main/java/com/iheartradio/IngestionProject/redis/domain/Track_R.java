package com.iheartradio.IngestionProject.redis.domain;

import com.iheartradio.IngestionProject.domain.Track;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
/**
 * @author Maruti Anudeep Thirumalasetti
 */
@RedisHash("Track_R")
public class Track_R {

    @Id
    private String id;
    private Track track;

    public Track_R() {
    }

    public Track_R(String id, Track track) {
        this.id = id;
        this.track = track;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }
}
