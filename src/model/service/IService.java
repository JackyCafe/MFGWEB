package model.service;

import java.util.List;

public interface IService<T> {
	public abstract T insert(T bean);
	public abstract T select(int id);
	public abstract List<T> select();
	public abstract Boolean delete(int id); 
	public abstract T update(T bean); 
}
