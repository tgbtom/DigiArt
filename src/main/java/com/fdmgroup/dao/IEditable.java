package com.fdmgroup.dao;

import com.fdmgroup.model.IStorable;

public interface IEditable<T extends IStorable> {
	public T update(T t);
}
