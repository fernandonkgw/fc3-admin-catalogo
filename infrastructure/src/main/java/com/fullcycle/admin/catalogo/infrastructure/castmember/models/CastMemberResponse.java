package com.fullcycle.admin.catalogo.infrastructure.castmember.models;

import com.fasterxml.jackson.annotation.JsonProperty;

// no teste E2E usa essa classe para fazer a deserialiacao e nao so serializacao, por isso precisa usar @JsonProperty
public record CastMemberResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("type") String type,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("updated_at")String updatedAt
) {
}
