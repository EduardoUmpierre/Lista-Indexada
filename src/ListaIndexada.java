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
		
		ListaEncadeada<Object>.Node s = search(2599);
		
		System.out.println(s.getData());
		System.out.println(s.getNext() != null ? s.getNext().getData() : "");
	}
	
	public static ListaEncadeada<Object>.Node search(int index) {
		System.out.println(" --- -- --- ");
		
		int interacoes = 0;
		Iterador<Object> indexIterator = indice1.iterator();		
		ListaEncadeada<Object>.Node e = null;
		
		while (indexIterator.hasNext()) {
			indexIterator.next();
			
			interacoes++;
			
			if(index <= (int) indexIterator.getCurrent().getData()) {				
				if(index == (int) indexIterator.getCurrent().getData()) {
					System.out.println("Numero de interacoes: " + interacoes);
					
					return indexIterator.getCurrent().getDownLevel();
				}
				
				if(indexIterator.getCurrent().getPrevious() != null) {
					e = indexIterator.getCurrent().getPrevious().getDownLevel();
				} else {
					e = indexIterator.getCurrent().getDownLevel();
				}
				
				while (index > (int) e.getData()) {					
					e = e.getNext();
					
					interacoes++;
				}
				
				break;
			}
		}
		
		if(e == null)
			System.out.println("Item não encontrado.");
		
		System.out.println("Numero de interacoes: " + interacoes);
		
		return e;
	}
}