package com.fullcycle.admin.catalogo.infrastructure.castmember.models;

import com.fullcycle.admin.catalogo.domain.Fixture;
import com.fullcycle.admin.catalogo.JacksonTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@JacksonTest
class UpdateCastMemberRequestTest {

    @Autowired
    private JacksonTester<UpdateCastMemberRequest> json;

    @Test
    void testUnmarshall() throws IOException {
        // given
        final var expectedName = Fixture.name();
        final var expectedType = Fixture.CastMembers.type();

        final var json = """
                {
                    "name": "%s",
                    "type": "%s"
                }
                """.formatted(expectedName, expectedType);

        final var actualJson = this.json.parse(json);

        // then
        Assertions.assertThat(actualJson)
                .hasFieldOrPropertyWithValue("name", expectedName)
                .hasFieldOrPropertyWithValue("type", expectedType);
    }
}