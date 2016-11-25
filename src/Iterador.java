import java.util.Iterator;

public interface Iterador<T> extends Iterator<T> {
	void append(T dado);
	void insert(T dado);
	ListaEncadeada<T>.Node getCurrentNode();
}