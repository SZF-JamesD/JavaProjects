package LE_09._04.music;

import LE_09._04.utils.TimeUtils;

public record Track(int track_id, String track_title, int track_duration, String mp3_file_name) {
    @Override
    public String toString() {
        return String.format("Track ID: %d -- Track Title: %s -- Duration: %s -- MP3File: %s", track_id, track_title, TimeUtils.formatDuration(track_duration), mp3_file_name);
    }
}

