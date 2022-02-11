package com.github.smoothier.ddd.domain.base;

import org.jetbrains.annotations.NotNull;

public abstract class Entity<E extends DomainEvent> {
	
	protected abstract void emit(final @NotNull E event);
	
}
