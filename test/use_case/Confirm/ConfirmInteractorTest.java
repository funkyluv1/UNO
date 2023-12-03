package use_case.Confirm;

public class ConfirmInteractorTest {
    void successTest() {
        ConfirmOutputDataBoundary successPresenter =; // Make a presenter here that asserts things
        ConfirmInputData confirmInputData = new ConfirmInputData(1);
        ConfirmInputDataBoundary interactor = new Conf(
                userRepository, successPresenter, new CommonUserFactory());
        interactor.execute(inputData); // This will eventually send Output Data to the successPresenter
    }
    SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
        @Override
        public void prepareSuccessView(SignupOutputData user) {
// 2 things to check: the output data is correct, and the user has been created in the DAO.
            assertEquals("Paul", user.getUsername());
            assertNotNull(user.getCreationTime()); // any creation time is fine.
            assertTrue(userRepository.existsByName("Paul"));
        }
        @Override
        public void prepareFailView(String error) {
            fail("Use case failure is unexpected.");
        }
    };



}
