package Step_DB;

import java.util.List;

public interface IDAO<T> {
	public void add(T obj);
	public void update(T obj);
	public void delete(T obj);
	public T getOne(long id);
	public List<T> getAll();
	
}
