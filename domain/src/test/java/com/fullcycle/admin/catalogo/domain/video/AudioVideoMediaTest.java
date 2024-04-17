package com.fullcycle.admin.catalogo.domain.video;

import com.fullcycle.admin.catalogo.domain.UnitTest;
import com.fullcycle.admin.catalogo.domain.utils.IdUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AudioVideoMediaTest extends UnitTest {

    @Test
    void givenAValidParams_whenCallsWith_shouldReturnInstance() {
        // given
        final var expectedId = IdUtils.uuid();
        final var expectedChecksum = "abc";
        final var expectedName = "banner.png";
        final var expectedRawLocation = "/images/ac";
        final var expectedEncodedLocation = "/images/ac";
        final var expectedStatus = MediaStatus.PENDING;

        // when
        final var actualVideo = AudioVideoMedia.with(
                expectedId,
                expectedChecksum,
                expectedName,
                expectedRawLocation,
                expectedEncodedLocation,
                expectedStatus
        );


        // then
        Assertions.assertNotNull(actualVideo);
        Assertions.assertEquals(expectedChecksum, actualVideo.checksum());
        Assertions.assertEquals(expectedName, actualVideo.name());
        Assertions.assertEquals(expectedRawLocation, actualVideo.rawLocation());
        Assertions.assertEquals(expectedEncodedLocation, actualVideo.encodedLocation());
        Assertions.assertEquals(expectedStatus, actualVideo.status());
    }

    @Test
    void givenTwoMediasWithSameChecksumAndLocation_whenCallsEquals_shouldReturnTrue() {
        // given
        final var expectedChecksum = "abc";
        final var expectedLocation = "/images/ac";

        final var video1 = AudioVideoMedia.with(expectedChecksum, "Random", expectedLocation);
        final var video2 = AudioVideoMedia.with(expectedChecksum, "Simple", expectedLocation);

        // when
        final var actualResult = video1.equals(video2);


        // then
        Assertions.assertTrue(actualResult);
        Assertions.assertNotSame(video1, video2);
    }

    @Test
    void givenInvalidParams_whenCallsWith_shouldReturnError() {

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with(null, "123", "Random", "/images/ac", "/images/ac", MediaStatus.PENDING)
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("id", "abc", null, "/images/ac", "/images/ac", MediaStatus.PENDING)
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("id", "abc", "Random", null, "/images/ac", MediaStatus.PENDING)
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("id", "abc", "Random", "/images/ac", null, MediaStatus.PENDING)
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> AudioVideoMedia.with("id", "abc", "Random", "/images/ac", "/images/ac", null)
        );
    }

}