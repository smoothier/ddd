package com.github.smoothier.ddd.domain.base;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AggregateRoot<DOMAIN_EVENT extends DomainEvent> extends Aggregate<DOMAIN_EVENT> {
	private final transient @NotNull List<@NotNull DOMAIN_EVENT> domainEvents = new LinkedList<>();
	
	protected AggregateRoot() {}
	
	protected final void emit(final @NotNull DOMAIN_EVENT event) {
		domainEvents.add(event);
	}
	
	public final @NotNull Collection<@NotNull DOMAIN_EVENT> domainEvents() {
		return Collections.unmodifiableList(domainEvents);
	}
	
	void clearDomainEvents() {
		domainEvents.clear();
	}
}
