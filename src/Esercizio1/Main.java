package Esercizio1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		Prodotto p1 = new Prodotto(1, "Libro 1", "Libri", 12.00);
		Prodotto p2 = new Prodotto(2, "LA SACRA BIBBIA", "Libri", 150.00);
		Prodotto p3 = new Prodotto(3, "Libro 3", "Libri", 22.00);
		Prodotto p4 = new Prodotto(4, "Gioco 1", "Baby", 25.00);
		Prodotto p5 = new Prodotto(4, "Gioco 2", "Baby", 18.99);
		Prodotto p6 = new Prodotto(4, "Gioco 3", "Baby", 29.50);
		Prodotto p7 = new Prodotto(4, "Articolo 1", "Boys", 15.50);
		Prodotto p8 = new Prodotto(4, "Articolo 2", "Boys", 10.90);
		Prodotto p9 = new Prodotto(4, "Articolo 3", "Boys", 39.99);
				
		ArrayList<Prodotto> prodotti = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9));
		    
		System.out.println("----------------------------------");
		  System.out.println("Stampo il prodotto più costoso dalla categoria LIBRI: ");
		    Stream<Prodotto> bookStream = prodotti.stream()
		            .filter((prodotto) -> prodotto.getCategoria() == "Libri" && prodotto.getPrezzo() > 100);
		    
		    bookStream.forEach(prodotto -> {
		        System.out.println(prodotto);
		      });
		    
		    ArrayList<Prodotto> ordine1 = new ArrayList<Prodotto>(Arrays.asList(p1, p3, p4));
		    ArrayList<Prodotto> ordine2 = new ArrayList<Prodotto>(Arrays.asList(p2, p7, p8));
		    ArrayList<Prodotto> ordine3 = new ArrayList<Prodotto>(Arrays.asList(p4, p7, p2));
		    
		  Cliente c1 = new Cliente(1, "Gaetano", 1);
		  Cliente c2 = new Cliente(2, "Pasquale", 2);
		  Cliente c3 = new Cliente(3, "Luca", 1);
		  
		  Ordine o1 = new Ordine(1, "in consegna", LocalDate.now() , LocalDate.of(2023, 01, 23) , ordine1 , c1);
		  Ordine o2 = new Ordine(2, "in consegna", LocalDate.of(2021, 03, 25) , LocalDate.of(2023, 01, 25), ordine2 , c2);
		  Ordine o3 = new Ordine(3, "in consegna", LocalDate.now() , LocalDate.of(2023, 01, 28), ordine3 , c3);
		  
		  ArrayList<Ordine> listaOrdini = new ArrayList<Ordine>(Arrays.asList(o1, o2, o3)); 

		  ArrayList<Ordine> listaFiltrata = new ArrayList<>();
		  listaOrdini.stream()
		              .filter((ordine) -> ordine.getProdotti().stream()
		                              .filter((categoriaProdotto) -> categoriaProdotto.getCategoria() == "Baby")
		                              .count() > 0)
		              .forEach((ordine) -> listaFiltrata.add(ordine));
		    
		  System.out.println("----------------------------------");
		  System.out.println("Stampo le liste degli ordini contenente prodotti dalla categoria BABY: ");
		    listaFiltrata.forEach(ordine -> {
		        System.out.println(ordine);
		      });
		    
		    System.out.println("----------------------------------");
			  System.out.println("Stampo una lista di prodotti appartenenti alla categoria BOYS ");
			    prodotti.stream()
			            .filter((prodotto) -> prodotto.getCategoria() == "Boys")
			            .peek(prezzo -> prezzo.setPrezzo(prezzo.getPrezzo() - (prezzo.getPrezzo() * 10) / 100)).
			            forEach((prodotto) -> System.out.println(prodotto));
			           
			    
			    System.out.println( "---------------------------------------" );
				System.out.println( "Ordini di clienti con livello 2, ordinati tra il 01-Feb-2021 e l'01-Apr-2021:" );
		        listaOrdini.stream()
		        	.filter( (o) -> o.getDataOrdine().isAfter(LocalDate.of(2021, 02, 01))
		        	&& o.getDataOrdine().isBefore(LocalDate.of(2021, 04, 01))
		        	&& o.getCliente().getLivello() == 2)
		        	.forEach((o) -> System.out.println(o));
			  
			    
	}
}

class Prodotto {
	
	long id;
	String nome;
	String categoria;
	double prezzo;
	
	public Prodotto(long id, String nome, String categoria, double prezzo) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.prezzo = prezzo;
	}
	
	public Object setPrezzo(double prezzo) {
		return this.prezzo = prezzo;
	}

	public String getCategoria() {
		return this.categoria;
	}
	public double getPrezzo() {
		return this.prezzo;
	}
	
	@Override
	  public String toString() {
	    return "Nome: " + this.nome + " Categoria: " + this.categoria + " Prezzo: €" + this.prezzo;
	  }
}


class Ordine {
	
	long id;
	String stato;
	LocalDate dataOrdine;
	LocalDate dataConsegna;
	List<Prodotto> prodotti;
	Cliente cliente;

	public Ordine(long id, String stato, LocalDate dataOrdine, LocalDate dataConsegna, List<Prodotto> prodotti, Cliente cliente) {
		
		this.id = id;
		this.stato = stato;
		this.dataOrdine = dataOrdine;
		this.dataConsegna = dataConsegna;
		this.prodotti = prodotti;
		this.cliente = cliente;
	}
	
	public List<Prodotto> getProdotti() {
		return this.prodotti;
	}
	
	public LocalDate getDataOrdine() {
		return this.dataOrdine;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	@Override
	  public String toString() {
	    return "ID - " + this.id + " stato ordine: " + this.stato + " data ordine: " + this.dataOrdine + " data consegna: " + this.dataConsegna + " prodotti: " + this.prodotti + " Cliente: " + this.cliente;
	  }
	
	
}

class Cliente {
	
	long id;
	String nome;
	int livello;
	
	public Cliente(long id, String nome, int livello) {
		
		this.id = id;
		this.nome = nome;
		this.livello = livello;
	}
	
	public int getLivello() {
		return this.livello;
	}
	
	@Override
	  public String toString() {
	    return this.nome + " Livello: " + this.livello;
	  }

}

