package com.example.leaflog.bc.sharedkernel.outbox.repository;

import com.example.leaflog.bc.sharedkernel.outbox.Outbox;
import com.example.leaflog.bc.sharedkernel.outbox.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutboxRepository extends JpaRepository<Outbox, Long> {

    List<Outbox> findTop100ByStatusOrderByCreatedAt(Status status);
}
