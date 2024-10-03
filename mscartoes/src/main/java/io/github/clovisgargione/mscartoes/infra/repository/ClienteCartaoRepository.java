package io.github.clovisgargione.mscartoes.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import io.github.clovisgargione.mscartoes.domain.ClienteCartao;

@RepositoryRestResource(exported = false)
public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Integer> {

    List<ClienteCartao> findByCpf(String cpf);
}
