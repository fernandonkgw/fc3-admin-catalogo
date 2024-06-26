package com.fullcycle.admin.catalogo.infrastructure.genre.models;

import com.fullcycle.admin.catalogo.JacksonTest;
import com.fullcycle.admin.catalogo.infrastructure.category.models.UpdateCategoryRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import java.util.List;

@JacksonTest
public class UpdateGenreRequestTest {

    @Autowired
    private JacksonTester<UpdateGenreRequest> json;

    @Test
    public void testUnmarshall() throws Exception {
        // given
        final var expectedName = "Acao";
        final var expectedCategory = "123";
        final var expectedIsActive = true;

        final var json = """
        {
            "name": "%s",
            "categories_id": ["%s"],
            "is_active": %s
        }                        
        """.formatted(
                expectedName,
                expectedCategory,
                expectedIsActive
        );

        // when
        final var actualResponse = this.json.parse(json);

        // then
        Assertions.assertThat(actualResponse)
                .hasFieldOrPropertyWithValue("name", expectedName)
                .hasFieldOrPropertyWithValue("categories", List.of(expectedCategory))
                .hasFieldOrPropertyWithValue("active", expectedIsActive);
    }
}
