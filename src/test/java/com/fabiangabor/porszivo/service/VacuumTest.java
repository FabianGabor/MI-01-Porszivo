package com.fabiangabor.porszivo.service;

import com.fabiangabor.porszivo.Direction;
import com.fabiangabor.porszivo.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class VacuumTest {
    private Vacuum underTest;

    @Mock
    private World world;
    @Mock
    private Direction direction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        underTest = new Vacuum(world);
    }

    @Test
    @Disabled
    void testNotDoneShouldReturnTrueWhenDirectionStop() {
        given(direction).willReturn(Direction.STOP);
        boolean result = underTest.notDone();
        assertTrue(result);
    }
}