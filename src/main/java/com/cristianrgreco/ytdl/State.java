package com.cristianrgreco.ytdl;

import java.util.regex.Pattern;

public enum State {
    NONE,
    RESOLVING,
    DOWNLOADING,
    CONVERTING,
    COMPLETE;

    private static final Pattern RESOLVING_PATTERN = Pattern.compile("\\[youtube\\].*");
    private static final Pattern DOWNLOADING_PATTERN = Pattern.compile("\\[download\\]");
    private static final Pattern CONVERTING_PATTERN = Pattern.compile("\\[ffmpeg\\].*");
    private static final Pattern VALID_STATE_MESSAGE = Pattern.compile("\\[(youtube|download|ffmpeg)\\]");

    public static State parse(String stateMessage) {
        if (RESOLVING_PATTERN.matcher(stateMessage).find()) {
            return State.RESOLVING;
        } else if (DOWNLOADING_PATTERN.matcher(stateMessage).find()) {
            return State.DOWNLOADING;
        } else if (CONVERTING_PATTERN.matcher(stateMessage).find()) {
            return State.CONVERTING;
        }
        throw new IllegalArgumentException("Unable to parse: " + stateMessage);
    }

    public static boolean isValidStateMessage(String stateMessage) {
        return VALID_STATE_MESSAGE.matcher(stateMessage).find();
    }
}
