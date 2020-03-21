package com.test.marshmallow.javabackendtest.domain;

import java.util.List;
import java.util.stream.Collectors;

public class OceanCleaner {
    private Coordinates areaSize;
    private Coordinates cleanerPosition;
    private List<Coordinates> oilPatches;

    public OceanCleaner(Coordinates areaSize, Coordinates cleanerStartingPosition, List<Coordinates> oilPatches) {
        this.areaSize = areaSize;
        this.cleanerPosition = cleanerStartingPosition;
        this.oilPatches = oilPatches;
    }

    public void navigateAndClean(String navigationInstructions) {
        for (char currentChar : navigationInstructions.toCharArray()) {
            move(currentChar);
            clean();
        }
    }

    public Coordinates getCleanerPosition() {
        return cleanerPosition;
    }

    public int numberOfPatchesInArea() {
        return oilPatches.size();
    }

    private void move(char move) {
        if ('N' == move && cleanerPosition.getY() < areaSize.getY() -1) {
            cleanerPosition =
                    new Coordinates(cleanerPosition.getX(),
                            cleanerPosition.getY() + 1
                    );
        }

        if ('W' == move && cleanerPosition.getX() < areaSize.getX() -1) {
            cleanerPosition =
                    new Coordinates(cleanerPosition.getX() + 1,
                            cleanerPosition.getY()
                    );
        }

        if ('S' == move && cleanerPosition.getY() > 0) {
            cleanerPosition =
                    new Coordinates(cleanerPosition.getX(),
                            cleanerPosition.getY() - 1
                    );
        }

        if ('E' == move && cleanerPosition.getX() > 0) {
            cleanerPosition =
                    new Coordinates(cleanerPosition.getX() - 1,
                            cleanerPosition.getY()
                    );
        }
    }

    private void clean() {
        oilPatches = oilPatches
                .stream()
                .filter( p -> !isCleaningShipOnThePatch(p))
                .collect(Collectors.toList());
    }

    private boolean isCleaningShipOnThePatch(Coordinates coordinates) {
        return coordinates.getX() == cleanerPosition.getX() && coordinates.getY() == cleanerPosition.getY();
    }
}
