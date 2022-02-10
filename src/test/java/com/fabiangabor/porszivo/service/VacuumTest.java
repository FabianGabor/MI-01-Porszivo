package com.fabiangabor.porszivo.service;

import com.fabiangabor.porszivo.Direction;
import com.fabiangabor.porszivo.World;
import com.fabiangabor.porszivo.datastore.Datastore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class VacuumTest {
    private final World WORLD = new World();

    private Vacuum underTest;

    @Mock
    private Datastore datastore;
    @Mock
    private World world;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        underTest = new Vacuum(world);
    }

    @Test
    void testNotDoneShouldReturnTrueWhenDirectionStop() {
        given(datastore.getDirection()).willReturn(Direction.STOP);
        given(datastore.allRoomsAreClear()).willReturn(false);
        boolean result = underTest.notDone();
        assertTrue(result);
    }

    @Test
    void testNotDoneShouldReturnTrueWhenAllRoomsAreClear() {
        given(datastore.getDirection()).willReturn(Direction.RIGHT);
        given(datastore.allRoomsAreClear()).willReturn(true);
        boolean result = underTest.notDone();
        assertTrue(result);
    }
}