package br.com.zupacademy.guilherme.proposta.repository;

import br.com.zupacademy.guilherme.proposta.domain.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal, UUID> {
    @Query("SELECT p FROM Proposal p WHERE p.cardId = NULL")
    public List<Proposal> findAllCardIdIsNull();

    @Query("SELECT p FROM Proposal p WHERE p.cardId = cardId")
    Proposal findByCardId(String cardId);
}
