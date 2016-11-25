public class ListaIndexada {
	private static ListaEncadeada<Object> lista = new ListaEncadeada<>();
	private static ListaEncadeada<Object> indice1 = new ListaEncadeada<>();
	
	public static void main(String[] args) {		
		for (int i = 1; i <= 3000; i++) {
			lista.append(i);
		}
		
		Iterador<Object> iterator = lista.iterator();
		
		int i = 1;
		
		while (iterator.hasNext()) {
			iterator.next();
			
			if(
				iterator.getCurrent().getData() == lista.getHead().getData() ||
				iterator.getCurrent().getData() == lista.getTail().getData() ||
				i % 10 == 0
			) {
				indice1.append(iterator.getCurrent().getData(), iterator.getCurrent());
			}
			
			i++;
		}
		
		iterator = lista.iterator();
		
		while (iterator.hasNext()) {
			iterator.next();
		}
	}
}