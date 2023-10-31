package speaking.clock.Speaking.Clock.service;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpeakingClockTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    TestInfo testInfo;
    TestReporter testReporter;
    @InjectMocks
    private SpeakingClockService speakingClockService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.testInfo = testInfo;
        this.testReporter = testReporter;
    }

    @Test
    public void getTimeTest() {

        Assertions.assertThat(speakingClockService.getCurrentTime()).isNotEmpty();
    }

    @Test
    public void getTimeOk() {

        String expectedResult = "It's ten thirty ";

        Assertions.assertThat(speakingClockService.validateTime("10:30")).isEqualTo(expectedResult);
    }

    @Test(expected = RuntimeException.class)
    public void getTimeNotOk() {

        speakingClockService.validateTime("abc");
    }
}
