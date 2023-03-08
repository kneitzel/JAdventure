package org.jadv.framework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Mock
    Model modelMock;

    @Mock
    View viewMock;

    Controller controllerUnderTest;

    /**
     * Sets up a Controller instance for each test.
     */
    @BeforeEach
    void setUp() {
        controllerUnderTest = new Controller(modelMock, viewMock) {
            @Override
            public View getView() {
                return viewMock;
            }

            @Override
            public Model getModel() {
                return modelMock;
            }

            @Override
            public void handleAction(Object action) {
                // do nothing
            }
        };
    }

    /**
     * Tests the getView method.
     */
    @Test
    void testGetView() {
        assertEquals(viewMock, controllerUnderTest.getView());
    }

    /**
     * Tests the getModel method.
     */
    @Test
    void testGetModel() {
        assertEquals(modelMock, controllerUnderTest.getModel());
    }

    /**
     * Tests the handleAction method.
     */
    @Test
    void testHandleAction() {
        Object action = new Object();
        controllerUnderTest.handleAction(action);
        verify(viewMock).setModel(modelMock);
        verify(viewMock).addController(controllerUnderTest);
        verify(viewMock).init();
    }
}
