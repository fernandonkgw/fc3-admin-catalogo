package com.fullcycle.admin.catalogo.infrastructure.castmember.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CastMemberRepository extends JpaRepository<CastMemberJpaEntity, String> {


    Page<CastMemberJpaEntity> findAll(Specification<CastMemberJpaEntity> whereClause, Pageable page);

    @Query("SELECT m.id FROM CastMember m WHERE m.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);
}
