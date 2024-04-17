package com.fullcycle.admin.catalogo.infrastructure.genre.models;

import com.fullcycle.admin.catalogo.JacksonTest;
import com.fullcycle.admin.catalogo.domain.utils.InstantUtils;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CategoryResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import java.util.List;

@JacksonTest
public class GenreResponseTest {

    @Autowired
    private JacksonTester<GenreResponse> json;

    @Test
    public void testMarshall() throws Exception {
        // given
        final var expectedId = "123";
        final var expectedName = "Acao";
        final var expectedCategories = List.of("123");
        final var expectedIsActive = false;
        final var expectedCreatedAt = InstantUtils.now();
        final var expectedUpdatedAt = InstantUtils.now();
        final var expectedDeletedAt = InstantUtils.now();

        final var response = new GenreResponse(
                expectedId,
                expectedName,
                expectedCategories,
                expectedIsActive,
                expectedCreatedAt,
                expectedUpdatedAt,
                expectedDeletedAt
        );

        // when
        final var actualJson = this.json.write(response);

        // then
        Assertions.assertThat(actualJson)
                .hasJsonPathValue("$.id", expectedId)
                .hasJsonPathValue("$.name", expectedName)
                .hasJsonPathValue("$.categories_id", expectedCategories)
                .hasJsonPathValue("$.is_active", expectedIsActive)
                .hasJsonPathValue("$.created_at", expectedCreatedAt.toString())
                .hasJsonPathValue("$.updated_at", expectedUpdatedAt.toString())
                .hasJsonPathValue("$.deleted_at", expectedDeletedAt.toString());
    }

    @Test
    public void testUnmarshall() throws Exception {
        // given
        final var expectedId = "123";
        final var expectedName = "Acao";
        final var expectedCategories = "456";
        final var expectedIsActive = false;
        final var expectedCreatedAt = InstantUtils.now();
        final var expectedUpdatedAt = InstantUtils.now();
        final var expectedDeletedAt = InstantUtils.now();

        final var json = """
                {
                    "id": "%s",
                    "name": "%s",
                    "categories_id": ["%s"],
                    "is_active": %s,
                    "created_at": "%s",
                    "updated_at": "%s",
                    "deleted_at": "%s"
                }                        
                """.formatted(
                expectedId,
                expectedName,
                expectedCategories,
                expectedIsActive,
                expectedCreatedAt,
                expectedUpdatedAt,
                expectedDeletedAt
        );

        // when
        final var actualResponse = this.json.parse(json);

        // then
        Assertions.assertThat(actualResponse)
                .hasFieldOrPropertyWithValue("id", expectedId)
                .hasFieldOrPropertyWithValue("name", expectedName)
                .hasFieldOrPropertyWithValue("categories", List.of(expectedCategories))
                .hasFieldOrPropertyWithValue("active", expectedIsActive)
                .hasFieldOrPropertyWithValue("createdAt", expectedCreatedAt)
                .hasFieldOrPropertyWithValue("updatedAt", expectedUpdatedAt)
                .hasFieldOrPropertyWithValue("deletedAt", expectedDeletedAt);
    }
}
