package LE_09._04.music;


import LE_09._04.utils.TimeUtils;

public record Album (int album_id, String album_name, String artist, int total_duration) {
    @Override
    public String toString() {
        return String.format("\n--- Album: %s ---\nArtist: %s\nTotal Playtime: %s\n", album_name, artist, TimeUtils.formatDuration(total_duration));
    }
}
