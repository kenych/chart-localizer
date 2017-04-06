package ken.kata.popular.service;

import ken.kata.popular.domain.Language;
import ken.kata.popular.domain.LocalizedChart;
import ken.kata.popular.domain.NonLocalizedChart;
import ken.kata.popular.domain.Request;
import ken.kata.popular.infrastructure.Marshaller;
import ken.kata.utils.Exceptions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class PopularTracksServiceFacadeTest {

    PopularTracksService popularTracksService;

    @Mock
    Marshaller marshalleKMock;
    @Mock
    Localizer localizerMock;
    @Mock
    OutputFilePathPolicy outputFilePathPolicyMock;
    @Mock
    Request requestMock;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(requestMock.getLanguage()).thenReturn(Arrays.asList(Language.EN,Language.IT));
        popularTracksService = new PopularTracksServiceFacade(marshalleKMock, localizerMock, outputFilePathPolicyMock);
    }

    @Test
    public void testWhenUnmarshalFails() {
        when(marshalleKMock.unmarshal(anyString())).thenThrow(new RuntimeException());

        Exceptions.assertThat(new Runnable() {
            @Override
            public void run() {
                popularTracksService.localise(requestMock);
            }
        })
                .throwsException(RuntimeException.class);

        verify(localizerMock, never()).localize(any(NonLocalizedChart.class), any(Language.class));
    }

    @Test
    public void testWhenLocalizeFails() {
        when(localizerMock.localize(any(NonLocalizedChart.class), any(Language.class))).thenThrow(new RuntimeException());

        Exceptions.assertThat(new Runnable() {
            @Override
            public void run() {
                popularTracksService.localise(requestMock);
            }
        })
                .throwsException(RuntimeException.class);

        verify(localizerMock, times(1)).localize(any(NonLocalizedChart.class), any(Language.class));
        verify(marshalleKMock, never()).marshal(any(LocalizedChart.class),anyString());
    }

    @Test
    public void testWhenOk() {
        popularTracksService.localise(requestMock);

        verify(localizerMock, times(1)).localize(any(NonLocalizedChart.class), any(Language.class));
        verify(marshalleKMock, times(1)).marshal(any(LocalizedChart.class),anyString());
    }
}
