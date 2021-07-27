package br.com.zupacademy.guilherme.proposta.repository;

import br.com.zupacademy.guilherme.proposta.domain.BlockCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BlockCardRepository extends JpaRepository<BlockCard, UUID> {
    @Query("SELECT x FROM BlockCard x WHERE x.cardId.cardId = :cardId")
    BlockCard findByProposalCardId(String cardId);


}
