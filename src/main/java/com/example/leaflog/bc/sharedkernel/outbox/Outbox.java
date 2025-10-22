package com.example.leaflog.bc.sharedkernel.outbox;

import com.example.leaflog.bc.sharedkernel.outbox.enums.AggTypes;
import com.example.leaflog.bc.sharedkernel.outbox.enums.EventTypes;
import com.example.leaflog.bc.sharedkernel.outbox.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name = "outbox")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Outbox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventTypes eventTypes;

    @Column(nullable = false)
    private String routingKey;

    @Column(nullable = false, columnDefinition = "json")
    private String eventJson;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AggTypes aggTypes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    void prePersist(){
        this.createdAt = LocalDateTime.now();
        this.status = Status.NEW;
    }

    public void failed(){
        this.status = Status.FAILED;
    }

    public void sent(){
        this.status = Status.SENT;
    }
}
