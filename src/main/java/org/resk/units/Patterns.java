package org.resk.units;

public enum Patterns {
    RAINBOW("Rainbow", "src/main/java/org/resk/patterns/rainbow.jpg"),

    BLACK_TO_YELLOW("Black to yellow", "src/main/java/org/resk/patterns/blackyellow.jpg"),
    BLUE_TO_GREEN("Blue to green", "src/main/java/org/resk/patterns/bluegreen.jpg"),
    BROWN_TO_BLUE("Brown to blue", "src/main/java/org/resk/patterns/brownblue.jpg"),
    PURPLE_TO_ORANGE("Purple to orange","src/main/java/org/resk/patterns/purpleorange.jpg");
    public String name;
    public String path;
    Patterns(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
