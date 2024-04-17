package com.fullcycle.admin.catalogo.infrastructure.services.local;

import com.fullcycle.admin.catalogo.domain.Fixture;
import com.fullcycle.admin.catalogo.domain.utils.IdUtils;
import com.fullcycle.admin.catalogo.domain.video.VideoMediaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class InMemoryStorageServiceTest {

    private InMemoryStorageService target = new InMemoryStorageService();

    @BeforeEach
    public void setUp() {
        target.clear();
    }

    @Test
    void givenValidResource_whenCallsStore_shouldStoreIt() {
        // given
        final var expectedName = IdUtils.uuid();
        final var expectedResource = Fixture.Videos.resource(VideoMediaType.VIDEO);

        // when
        target.store(expectedName, expectedResource);

        // then
        Assertions.assertEquals(expectedResource, target.storage().get(expectedName));
    }

    @Test
    void givenValidResource_whenCallsGet_shouldRetrieveIt() {
        // given
        final var expectedName = IdUtils.uuid();
        final var expectedResource = Fixture.Videos.resource(VideoMediaType.VIDEO);

        target.storage().put(expectedName, expectedResource);

        // when
        final var actualResource = target.get(expectedName).get();

        // then
        Assertions.assertEquals(expectedResource, actualResource);
    }

    @Test
    void givenInvalidResource_whenCallsGet_shouldBeEmpty() {
        // given
        final var expectedName = IdUtils.uuid();

        // when
        final var actualResource = target.get(expectedName);

        // then
        Assertions.assertTrue(actualResource.isEmpty());
    }

    @Test
    void givenValidPrefix_whenCallsList_shouldRetrieveAll() {
        // given
        final var expectedNames = List.of(
                "video_" + IdUtils.uuid(),
                "video_" + IdUtils.uuid(),
                "video_" + IdUtils.uuid()
        );

        final var all = new ArrayList<>(expectedNames);
        all.add("image_" + IdUtils.uuid());
        all.add("image_" + IdUtils.uuid());

        all.forEach(name -> target.storage().put(name, Fixture.Videos.resource(VideoMediaType.VIDEO)));

        Assertions.assertEquals(5, target.storage().size());

        // when
        final var actualNames = target.list("video");

        // then
        Assertions.assertTrue(
                expectedNames.size() == actualNames.size()
                && actualNames.containsAll(expectedNames)
        );
    }

    @Test
    void givenValidnames_whenCallsDelete_shouldDeleteAll() {
        // given
        final var videos = List.of(
                "video_" + IdUtils.uuid(),
                "video_" + IdUtils.uuid(),
                "video_" + IdUtils.uuid()
        );
        final var images = Set.of(
                "image_" + IdUtils.uuid(),
                "image_" + IdUtils.uuid()
        );

        final var all = new ArrayList<>(videos);
        all.addAll(images);

        all.forEach(name -> target.storage().put(name, Fixture.Videos.resource(VideoMediaType.VIDEO)));

        Assertions.assertEquals(5, target.storage().size());

        // when
        target.deleteAll(videos);

        // then
        Assertions.assertEquals(2, target.storage().size());
        Assertions.assertTrue(
                images.size() == target.storage().size()
                && target.storage().keySet().containsAll(images)
        );
    }

}