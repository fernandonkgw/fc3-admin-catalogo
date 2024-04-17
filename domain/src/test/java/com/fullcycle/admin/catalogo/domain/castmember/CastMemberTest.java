package com.fullcycle.admin.catalogo.domain.castmember;

import com.fullcycle.admin.catalogo.domain.UnitTest;
import com.fullcycle.admin.catalogo.domain.exceptions.NotificationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CastMemberTest extends UnitTest {

    @Test
    public void givenAValidParams_whenCallsNewMember_thenInstantiateACastMember() {
        // given
        final var expectedName = "Vin Diesel";
        final var expectedType = CastMemberType.ACTOR;

        // when
        final var actualMember = CastMember.newMember(expectedName, expectedType);

        // then
        Assertions.assertNotNull(actualMember);
        Assertions.assertNotNull(actualMember.getId());
        Assertions.assertEquals(expectedName, actualMember.getName());
        Assertions.assertEquals(expectedType, actualMember.getType());
        Assertions.assertNotNull(actualMember.getCreatedAt());
        Assertions.assertNotNull(actualMember.getUpdatedAt());
        Assertions.assertEquals(actualMember.getCreatedAt(), actualMember.getUpdatedAt());
    }

    @Test
    public void givenAInvalidNullName_whenCallsNewMember_shouldReceiveANotification() {
        // given
        final String expectedName = null;
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class,
                () -> CastMember.newMember(expectedName, expectedType)
        );

        // then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidEmptyName_whenCallsNewMember_shouldReceiveANotification() {
        // given
        final var expectedName = "   ";
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class,
                () -> CastMember.newMember(expectedName, expectedType)
        );

        // then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidNameWithLengthMoreThan255_whenCallsNewMember_shouldReceiveANotification() {
        // given
        final var expectedName = """
                A prática cotidiana prova que a constante divulgação das informações assume importantes posições no estabelecimento dos procedimentos normalmente adotados. Nunca é demais lembrar o peso e o significado destes problemas, uma vez que a 
                competitividade nas transações comerciais causa impacto indireto na reavaliação do sistema de formação de quadros que corresponde às necessidades. As experiências acumuladas demonstram que o desenvolvimento contínuo de distintas formas de 
                atuação obstaculiza a apreciação da importância das condições inegavelmente apropriadas. Neste sentido, a execução dos pontos do programa oferece uma interessante oportunidade para verificação do levantamento das variáveis envolvidas.
                """;
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class,
                () -> CastMember.newMember(expectedName, expectedType)
        );

        // then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAInvalidNullType_whenCallsNewMember_shouldReceiveANotification() {
        // given
        final var expectedName = "Vin Diesel";
        final CastMemberType expectedType = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'type' should not be null";

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class,
                () -> CastMember.newMember(expectedName, expectedType)
        );

        // then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidCastMember_whenCallsUpdate_shouldReceiveUpdated() {
        // given
        final var expectedName = "Vin Diesel";
        final var expectedType = CastMemberType.ACTOR;

        final var actualMember =
                CastMember.newMember("vind", CastMemberType.DIRECTOR);

        Assertions.assertNotNull(actualMember);
        Assertions.assertNotNull(actualMember.getId());

        final var actualCreatedAt = actualMember.getCreatedAt();
        final var actualUpdatedAt = actualMember.getUpdatedAt();

        // when
        actualMember.update(expectedName, expectedType);

        // then
        Assertions.assertEquals(expectedName, actualMember.getName());
        Assertions.assertEquals(expectedType, actualMember.getType());
        Assertions.assertEquals(actualCreatedAt, actualMember.getCreatedAt());
        Assertions.assertTrue(actualUpdatedAt.isBefore(actualMember.getUpdatedAt()));
    }

    @Test
    public void givenAValidCastMember_whenCallsUpdateWithNullName_shouldReceiveNotification() {
        // given
        final String expectedName = null;
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualMember =
                CastMember.newMember("vind", CastMemberType.DIRECTOR);

        Assertions.assertNotNull(actualMember);
        Assertions.assertNotNull(actualMember.getId());

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class, () -> actualMember.update(expectedName, expectedType));

        // then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidCastMember_whenCallsUpdateWithEmptyName_shouldReceiveNotification() {
        // given
        final var expectedName = " ";
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualMember =
                CastMember.newMember("vind", CastMemberType.DIRECTOR);

        Assertions.assertNotNull(actualMember);
        Assertions.assertNotNull(actualMember.getId());

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class, () -> actualMember.update(expectedName, expectedType));

        // then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidCastMember_whenCallsUpdateWithNullType_shouldReceiveNotification() {
        // given
        final var expectedName = "Vin Diesel";
        final CastMemberType expectedType = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'type' should not be null";

        final var actualMember =
                CastMember.newMember("vind", CastMemberType.DIRECTOR);

        Assertions.assertNotNull(actualMember);
        Assertions.assertNotNull(actualMember.getId());

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class, () -> actualMember.update(expectedName, expectedType));

        // then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAValidCastMember_whenCallsUpdateWithNameLengthMoreThan255_shouldReceiveNotification() {
        // given
        final var expectedName = """
                A prática cotidiana prova que a constante divulgação das informações assume importantes posições no estabelecimento dos procedimentos normalmente adotados. Nunca é demais lembrar o peso e o significado destes problemas, uma vez que a 
                competitividade nas transações comerciais causa impacto indireto na reavaliação do sistema de formação de quadros que corresponde às necessidades. As experiências acumuladas demonstram que o desenvolvimento contínuo de distintas formas de 
                atuação obstaculiza a apreciação da importância das condições inegavelmente apropriadas. Neste sentido, a execução dos pontos do programa oferece uma interessante oportunidade para verificação do levantamento das variáveis envolvidas.
                """;
        final var expectedType = CastMemberType.ACTOR;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var actualMember =
                CastMember.newMember("vind", CastMemberType.DIRECTOR);

        Assertions.assertNotNull(actualMember);
        Assertions.assertNotNull(actualMember.getId());

        // when
        final var actualException = Assertions.assertThrows(
                NotificationException.class, () -> actualMember.update(expectedName, expectedType));

        // then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
}
