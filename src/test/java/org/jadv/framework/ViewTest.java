package org.jadv.framework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ViewTest {

    @Mock
    Model modelMock;

    @Mock
    Controller controllerMock;

    View viewUnderTest;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    void setUp() {
        viewUnderTest = new View() {
            @Override
            public Model getModel() {
                return modelMock;
            }

            @Override
            public void show() {
                // do nothing
            }
        };
        viewUnderTest.addController(controllerMock);
        viewUnderTest.setModel(modelMock);
    }

    /**
     * Tests that sendAction sends the correct action to the controller.
     */
    @Test
    void testSendAction() {
        Object action = new Object();
        viewUnderTest.sendAction(action);
        verify(controllerMock, times(1)).handleAction(action);
    }

    /**
     * Tests updateView exists.
     */
    @Test
    void testUpdateView() {
        viewUnderTest.updateView();
    }

    /**
     * Tests init exists.
     */
    @Test
    void testInit() {
        viewUnderTest.init();
    }

    /**
     * Tests the removeController and addController methods.
     */
    @Test
    void testAddRemoveController() {
        viewUnderTest.removeController(controllerMock);
        viewUnderTest.sendAction(new Object());
        verify(controllerMock, times(0)).handleAction(new Object());

        viewUnderTest.addController(controllerMock);
        Object testObject = new Object();
        viewUnderTest.sendAction(testObject);
        verify(controllerMock, times(1)).handleAction(testObject);
    }
}
