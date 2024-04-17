package com.fullcycle.admin.catalogo.domain.video;

import com.fullcycle.admin.catalogo.domain.UnitTest;
import com.fullcycle.admin.catalogo.domain.castmember.CastMemberID;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import com.fullcycle.admin.catalogo.domain.exceptions.DomainException;
import com.fullcycle.admin.catalogo.domain.genre.GenreID;
import com.fullcycle.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.Set;

public class VideoValidatorTest extends UnitTest {

    @Test
    void givenNullTitle_whenCallsValidate_shouldReceiveError() {
        // given
        final String expectedTitle = null;
        final var expectedDescription = """
            System design questions have become a standard part of the software engineering interview process. 
            Performance in these interviews reflects upon your ability to work with complex systems and translates 
            into the position and salary the interviewing company offers you. In other words, 
            it's worth taking seriously.
            """;
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' should not be null";
        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate(new ThrowsValidationHandler())
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    void givenEmptyTitle_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = "   ";
        final var expectedDescription = """
            System design questions have become a standard part of the software engineering interview process. 
            Performance in these interviews reflects upon your ability to work with complex systems and translates 
            into the position and salary the interviewing company offers you. In other words, 
            it's worth taking seriously.
            """;
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' should not be empty";
        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate(new ThrowsValidationHandler())
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    void givenTitleWithLengthGreaterThan255_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = "a".repeat(256);
        final var expectedDescription = """
            System design questions have become a standard part of the software engineering interview process. 
            Performance in these interviews reflects upon your ability to work with complex systems and translates 
            into the position and salary the interviewing company offers you. In other words, 
            it's worth taking seriously.
            """;
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'title' must be between 1 and 255 characters";
        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate(new ThrowsValidationHandler())
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    void givenEmptyDescription_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = "System Design Interviews";
        final var expectedDescription = "";
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' should not be empty";
        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate(new ThrowsValidationHandler())
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    void givenDescriptionWithLengthGreaterThan4000_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = "System Design Interviews";
        final var expectedDescription = "a".repeat(4001);
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'description' must be between 1 and 4000 characters";
        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate(new ThrowsValidationHandler())
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    void givenNullLaunchedAt_whenCallsValidade_shouldReceiveError() {
        // given
        final var expectedTitle = "System Design Interviews";
        final var expectedDescription = """
            System design questions have become a standard part of the software engineering interview process. 
            Performance in these interviews reflects upon your ability to work with complex systems and translates 
            into the position and salary the interviewing company offers you. In other words, 
            it's worth taking seriously.
            """;
        final Year expectedLaunchedAt = null;
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final var expectedRating = Rating.L;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'launchedAt' should not be null";
        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate(new ThrowsValidationHandler())
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }

    @Test
    void givenNullRating_whenCallsValidate_shouldReceiveError() {
        // given
        final var expectedTitle = "System Design Interviews";
        final var expectedDescription = """
            System design questions have become a standard part of the software engineering interview process. 
            Performance in these interviews reflects upon your ability to work with complex systems and translates 
            into the position and salary the interviewing company offers you. In other words, 
            it's worth taking seriously.
            """;
        final var expectedLaunchedAt = Year.of(2022);
        final var expectedDuration = 120.10;
        final var expectedOpened = false;
        final var expectedPublished = false;
        final Rating expectedRating = null;
        final var expectedCategories = Set.of(CategoryID.unique());
        final var expectedGenres = Set.of(GenreID.unique());
        final var expectedMembers = Set.of(CastMemberID.unique());

        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'rating' should not be null";
        final var actualVideo = Video.newVideo(
                expectedTitle,
                expectedDescription,
                expectedLaunchedAt,
                expectedDuration,
                expectedOpened,
                expectedPublished,
                expectedRating,
                expectedCategories,
                expectedGenres,
                expectedMembers
        );

        // when
        final var actualError = Assertions.assertThrows(
                DomainException.class,
                () -> actualVideo.validate(new ThrowsValidationHandler())
        );

        // then
        Assertions.assertEquals(expectedErrorCount, actualError.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualError.getErrors().get(0).message());
    }
}
