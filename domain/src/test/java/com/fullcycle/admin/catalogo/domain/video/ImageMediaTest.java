package com.fullcycle.admin.catalogo.domain.video;

import com.fullcycle.admin.catalogo.domain.UnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ImageMediaTest extends UnitTest {

    @Test
    void givenAValidParams_whenCallsWith_shouldReturnInstance() {
        // given
        final var expectedChecksum = "abc";
        final var expectedName = "banner.png";
        final var expectedLocation = "/images/ac";

        // when
        final var actualImage = ImageMedia.with(expectedChecksum, expectedName, expectedLocation);


        // then
        Assertions.assertNotNull(actualImage);
        Assertions.assertEquals(expectedChecksum, actualImage.checksum());
        Assertions.assertEquals(expectedName, actualImage.name());
        Assertions.assertEquals(expectedLocation, actualImage.location());
    }

    @Test
    void givenTwoMediasWithSameChecksumAndLocation_whenCallsEquals_shouldReturnTrue() {
        // given
        final var expectedChecksum = "abc";
        final var expectedLocation = "/images/ac";

        final var img1 = ImageMedia.with(expectedChecksum, "Random", expectedLocation);
        final var img2 = ImageMedia.with(expectedChecksum, "Simple", expectedLocation);

        // when
        final var actualResult = img1.equals(img2);


        // then
        Assertions.assertTrue(actualResult);
        Assertions.assertNotSame(img1, img2);
    }

    @Test
    void givenInvalidParams_whenCallsWith_shouldReturnError() {

        Assertions.assertThrows(
                NullPointerException.class,
                () -> ImageMedia.with(null, "Random", "/images/ac")
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> ImageMedia.with("abc", null, "/images/ac")
        );

        Assertions.assertThrows(
                NullPointerException.class,
                () -> ImageMedia.with("abc", "Random", null)
        );
    }
}