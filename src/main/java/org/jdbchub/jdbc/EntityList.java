package org.jdbchub.jdbc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class EntityList<T> {

	final List<T> entities;

	EntityList(List<T> entities) {
		this.entities = entities;
	}

	T entity(int index) {
		return entities.get(index);
	}

	int size() {
		return entities.size();
	}

	<R> Stream<R> map(JdbcFunction<T, R> mapper) {
		return entities.parallelStream().map(mapper);
	}

	boolean allMatch(JdbcFunction<T, Boolean> mapper) {
		return map(mapper).allMatch(r -> r);
	}

	<R> List<R> mapToList(JdbcFunction<T, R> mapper) {
		return map(mapper).collect(Collectors.toList());
	}

	IntStream mapToInt(JdbcFunction<T, Integer> mapper) {
		return map(mapper).mapToInt(r -> r);
	}

	void forEach(JdbcConsumer<T> consumer) {
		entities.parallelStream().forEach(consumer);
	}

	T mainEntity() {
		return entities.get(0);
	}

}
