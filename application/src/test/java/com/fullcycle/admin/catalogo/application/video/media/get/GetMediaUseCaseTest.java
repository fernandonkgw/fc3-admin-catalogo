package com.fullcycle.admin.catalogo.application.video.media.get;

import com.fullcycle.admin.catalogo.application.UseCaseTest;
import com.fullcycle.admin.catalogo.application.video.media.get.DefaultGetMediaUseCase;
import com.fullcycle.admin.catalogo.application.video.media.get.GetMediaCommand;
import com.fullcycle.admin.catalogo.domain.Fixture;
import com.fullcycle.admin.catalogo.domain.exceptions.NotFoundException;
import com.fullcycle.admin.catalogo.domain.video.MediaResourceGateway;
import com.fullcycle.admin.catalogo.domain.video.VideoID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

class GetMediaUseCaseTest extends UseCaseTest {

    @InjectMocks
    private DefaultGetMediaUseCase useCase;

    @Mock
    private MediaResourceGateway mediaResourceGateway;

    @Override
    protected List<Object> getMocks() {
        return List.of(mediaResourceGateway);
    }

    @Test
    void givenVideoIdAndType_whenIsValidCmd_shouldReturnResource() {
        // given
        final var expectedId = VideoID.unique();
        final var expectedType = Fixture.Videos.mediaType();
        final var expectedResource = Fixture.Videos.resource(expectedType);

        when(mediaResourceGateway.getResource(expectedId, expectedType))
                .thenReturn(Optional.of(expectedResource));

        final var aCmd = GetMediaCommand.with(expectedId.getValue(), expectedType.name());

        // when
        final var actualResult = this.useCase.execute(aCmd);

        // then
        Assertions.assertEquals(expectedResource.name(), actualResult.name());
        Assertions.assertEquals(expectedResource.contentType(), actualResult.contentType());
        Assertions.assertEquals(expectedResource.content(), actualResult.content());
    }

    @Test
    void givenVideoIdAndType_whenIsNotFound_shouldReturnNotFound() {
        // given
        final var expectedId = VideoID.unique();
        final var expectedType = Fixture.Videos.mediaType();

        when(mediaResourceGateway.getResource(expectedId, expectedType))
                .thenReturn(Optional.empty());

        final var aCmd = GetMediaCommand.with(expectedId.getValue(), expectedType.name());

        // when
        Assertions.assertThrows(NotFoundException.class,
                () -> this.useCase.execute(aCmd)
        );
    }

    @Test
    void givenVideoIdAndType_whenTypeDoesntExist_shouldReturnNotFound() {
        // given
        final var expectedId = VideoID.unique();
        final var expectedErrorMessage = "Media type qualquer doesnt exists";

        final var aCmd = GetMediaCommand.with(expectedId.getValue(), "qualquer");

        // when
        final var actualException = Assertions.assertThrows(NotFoundException.class,
                () -> this.useCase.execute(aCmd)
        );

        // then
        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
