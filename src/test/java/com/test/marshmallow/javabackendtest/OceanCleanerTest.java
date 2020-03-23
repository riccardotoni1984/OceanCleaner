package com.test.marshmallow.javabackendtest;

import com.test.marshmallow.javabackendtest.domain.Coordinates;
import com.test.marshmallow.javabackendtest.domain.OceanCleaner;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OceanCleanerTest {


    @Test
    void acceptanceTest() {
        OceanCleaner oceanCleaner = new OceanCleaner(
                new Coordinates(5, 5),
                new Coordinates(1, 2),
                Arrays.asList(
                        new Coordinates(1, 0),
                        new Coordinates(2, 2),
                        new Coordinates(2, 3)
                )
        );

        oceanCleaner.navigateAndClean("NNESEESWNWW");

        Coordinates finalPosition =  oceanCleaner.getCleanerPosition();
        assertEquals(3,finalPosition.getX() );
        assertEquals(3,finalPosition.getY() );

        int finalNumberOfPatchesInCOean = oceanCleaner.numberOfPatchesInArea();

        assertEquals(2, finalNumberOfPatchesInCOean);
    }

    @Test
    void cleanerStopAtNorthBorders() {
        OceanCleaner oceanCleaner = new OceanCleaner(
                new Coordinates(5, 5),
                new Coordinates(1, 1),
                Arrays.asList(
                        new Coordinates(0, 0)
                )
        );

        oceanCleaner.navigateAndClean("NNNNNNNNNNNN");

        Coordinates finalPosition =  oceanCleaner.getCleanerPosition();
        assertEquals(1,finalPosition.getX() );
        assertEquals(4,finalPosition.getY() );
    }

    @Test
    void cleanerStopAtSouthBorders() {
        OceanCleaner oceanCleaner = new OceanCleaner(
                new Coordinates(5, 5),
                new Coordinates(1, 1),
                Arrays.asList(
                        new Coordinates(0, 0)
                )
        );

        oceanCleaner.navigateAndClean("SSSSSSSSSSS");

        Coordinates finalPosition =  oceanCleaner.getCleanerPosition();
        assertEquals(1,finalPosition.getX() );
        assertEquals(0,finalPosition.getY() );
    }

    @Test
    void cleanerStopAtWestBorders() {
        OceanCleaner oceanCleaner = new OceanCleaner(
                new Coordinates(5, 5),
                new Coordinates(1, 1),
                Arrays.asList(
                        new Coordinates(0, 0)
                )
        );

        oceanCleaner.navigateAndClean("WWWWWWWWWWWW");

        Coordinates finalPosition =  oceanCleaner.getCleanerPosition();
        assertEquals(4,finalPosition.getX() );
        assertEquals(1,finalPosition.getY() );
    }

    @Test
    void cleanerStopAtEastBorders() {
        OceanCleaner oceanCleaner = new OceanCleaner(
                new Coordinates(5, 5),
                new Coordinates(1, 1),
                Arrays.asList(
                        new Coordinates(0, 0)
                )
        );

        oceanCleaner.navigateAndClean("EEEEEEEEEEEEEEE");

        Coordinates finalPosition =  oceanCleaner.getCleanerPosition();
        assertEquals(0,finalPosition.getX() );
        assertEquals(1,finalPosition.getY() );
    }

    @Test
    void cleanerCleanPatches() {
        OceanCleaner oceanCleaner = new OceanCleaner(
                new Coordinates(5, 5),
                new Coordinates(1, 1),
                Arrays.asList(
                        new Coordinates(2, 1),
                        new Coordinates(3, 1),
                        new Coordinates(4, 1)
                )
        );

        oceanCleaner.navigateAndClean("WWWWWWWWWWWWW");

        int finalNumberOfPatchesInCOean = oceanCleaner.numberOfPatchesInArea();
        assertEquals(0, finalNumberOfPatchesInCOean);
    }
}
