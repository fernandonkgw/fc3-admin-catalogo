package com.fullcycle.admin.catalogo.infrastructure.genre.models;

import com.fullcycle.admin.catalogo.JacksonTest;
import com.fullcycle.admin.catalogo.infrastructure.category.models.CreateCategoryRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import java.util.List;
import java.util.stream.Collectors;

@JacksonTest
class CreateGenreRequestTest {

    @Autowired
    private JacksonTester<CreateGenreRequest> json;


    @Test
    public void testUnmarshall() throws Exception {
        // given
        final var expectedName = "Acao";
        final var expectedCategories = "123";
        final var expectedIsActive = true;

        final var json = """
        {
            "name": "%s",
            "categories_id": ["%s"],
            "is_active": %s
        }                        
        """.formatted(
                expectedName,
                expectedCategories,
                expectedIsActive
        );

        // when
        final var actualResponse = this.json.parse(json);

        // then
        Assertions.assertThat(actualResponse)
                .hasFieldOrPropertyWithValue("name", expectedName)
                .hasFieldOrPropertyWithValue("categories", List.of(expectedCategories))
                .hasFieldOrPropertyWithValue("active", expectedIsActive);
    }


}