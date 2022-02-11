package com.github.smoothier.ddd.domain.base;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class LeafEntity<E extends DomainEvent> extends Entity<E> {
	protected final Aggregate<? super E> context;
	
	protected LeafEntity(final @NotNull Aggregate<? super E> context) {
		this.context = Objects.requireNonNull(context);
	}
	
	protected final void emit(final @NotNull E event) {
		context.emit(event);
	}
}
