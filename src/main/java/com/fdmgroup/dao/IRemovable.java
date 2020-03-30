package com.fdmgroup.dao;

import com.fdmgroup.model.IStorable;

public interface IRemovable<T extends IStorable> {
	public boolean remove(T t);
}
