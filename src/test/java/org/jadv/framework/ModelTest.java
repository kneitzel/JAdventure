package org.jadv.framework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.function.Consumer;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for the {@link Model} class.
 */
@ExtendWith(MockitoExtension.class)
class ModelTest {

    @Mock
    Consumer<Model> listenerMock;

    Model modelUnderTest;

    /**
     * Sets up a model for testing.
     */
    @BeforeEach
    void setUp() {
        modelUnderTest = new Model() {};
        modelUnderTest.addChangeListener(listenerMock);
    }

    /**
     * Tests that the change listener is notified when the model changes.
     */
    @Test
    void testHasChangedNotifiesAllListeners() {
        modelUnderTest.hasChanged();
        verify(listenerMock, times(1)).accept(modelUnderTest);
    }

    /**
     * Tests the removeChangeListener method.
     */
    @Test
    void testRemoveChangeListener() {
        modelUnderTest.removeChangeListener(listenerMock);
        modelUnderTest.hasChanged();
        verify(listenerMock, times(0)).accept(modelUnderTest);
    }


}