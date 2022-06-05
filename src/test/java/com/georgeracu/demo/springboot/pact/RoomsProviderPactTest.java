package com.georgeracu.demo.springboot.pact;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget;
import com.georgeracu.demo.springboot.adapter.room.rest.RoomsController;
import com.georgeracu.demo.springboot.domain.room.model.Room;
import com.georgeracu.demo.springboot.port.room.CreateRoomUseCase;
import com.georgeracu.demo.springboot.port.room.GetRoomsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@Provider("HotelsBackend")
@PactFolder("pacts")
@WebMvcTest(controllers = RoomsController.class)
@PactBroker(url = "http://192.168.49.2:31491/")
class RoomsProviderPactTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetRoomsUseCase getRoomsUseCase;
    @MockBean
    private CreateRoomUseCase createRoomUseCase;

    @BeforeEach
    void before(PactVerificationContext context) {
        context.setTarget(new MockMvcTestTarget(mockMvc));
    }

    /*
     * Disabled as it requires a running instance of Pact broker.
     * For demo purposes can be disabled and Pact broker started.
     * TODO: Make the CI server publish to the Pact broker with every build
     */
    @Disabled
    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerification(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State(value = "A room exists")
    void givenARoomExists() {
        when(getRoomsUseCase.execute()).thenReturn(
                List.of(
                        Room.builder().name("Pact room").build()));
    }

    @State(value = "This room doesn't exist")
    void shouldCreateRoom() {
        when(createRoomUseCase.execute(any())).thenReturn(
                Optional.of(
                        Room.builder().name("New Pact room").build()));
    }

}
