package br.com.zupacademy.guilherme.proposta.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_blockrequests")
public class BlockCard {

    @Id
    private String uuid = UUID.randomUUID().toString();
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "cardId")
    private Proposal cardId;
    @NotBlank
    private String userIp;
    @NotBlank
    private String userAgent;
    private LocalDateTime blockDate = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private BlockStatus status = BlockStatus.UNBLOCKED;

    @Deprecated
    public BlockCard (){}

    public BlockCard(Proposal proposal, String userIp, String userAgent) {
        this.cardId = proposal;
        this.userIp = userIp;
        this.userAgent = userAgent;
    }

    public Proposal getProposal() {
        return cardId;
    }

    public boolean isBlocked() {
        return this.status == BlockStatus.BLOCKED;
    }
}
