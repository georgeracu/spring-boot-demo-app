package com.georgeracu.demo.springboot.pact;

//@ActiveProfiles("test")
//@Provider("HotelsBackend")
//@PactFolder("pacts")
//@WebMvcTest(controllers = RoomsController.class)
//@PactBroker(url = "http://192.168.49.2:31491/")
class RoomsProviderPactTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private GetRoomsUseCase getRoomsUseCase;
//    @MockBean
//    private CreateRoomUseCase createRoomUseCase;
//
//    @BeforeEach
//    void before(PactVerificationContext context) {
//        context.setTarget(new MockMvcTestTarget(mockMvc));
//    }
//
//    /*
//     * Disabled as it requires a running instance of Pact broker.
//     * For demo purposes can be disabled and Pact broker started.
//     * TODO: Make the CI server publish to the Pact broker with every build
//     */
//    @Disabled
//    @TestTemplate
//    @ExtendWith(PactVerificationInvocationContextProvider.class)
//    void pactVerification(PactVerificationContext context) {
//        context.verifyInteraction();
//    }
//
//    @State(value = "A room exists")
//    void givenARoomExists() {
//        when(getRoomsUseCase.execute()).thenReturn(
//                List.of(
//                        Room.builder().name("Pact room").build()));
//    }
//
//    @State(value = "This room doesn't exist")
//    void shouldCreateRoom() {
//        when(createRoomUseCase.execute(any())).thenReturn(Room.builder().name("New Pact room").build());
//    }

}
