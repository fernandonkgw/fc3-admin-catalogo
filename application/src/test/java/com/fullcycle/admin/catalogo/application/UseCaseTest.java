package com.fullcycle.admin.catalogo.application;

import com.fullcycle.admin.catalogo.domain.Identifier;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
@Tag("unitTest")
public abstract class UseCaseTest implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        Mockito.reset(getMocks());
    }

    protected abstract List<Object> getMocks();

    protected List<String> asString(final List<? extends Identifier> categories) {
        return categories.stream()
                .map(Identifier::getValue)
                .toList();
    }

    protected Set<String> asString(final Set<? extends Identifier> categories) {
        return categories.stream()
                .map(Identifier::getValue)
                .collect(Collectors.toSet());
    }
}
