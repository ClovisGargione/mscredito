package io.github.clovisgargione.mscartoes.infra.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.github.clovisgargione.mscartoes.domain.Cartao;

@RepositoryRestResource(exported = false)
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

    List<Cartao> findByRendaLessThanEqual(BigDecimal rendaBigDecimal);

}
