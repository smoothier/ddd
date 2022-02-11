package com.github.smoothier.ddd.domain.base;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class CompositeEntity<E extends DomainEvent> extends Aggregate<E> {
	protected final Aggregate<? super E> context;
	
	protected CompositeEntity(final @NotNull Aggregate<? super E> context) {
		this.context = Objects.requireNonNull(context);
	}
	
	protected final void emit(final @NotNull E event) {
		context.emit(event);
	}
}
