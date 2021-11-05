package tb.soft;

import java.util.*;

/**
 * Program: Aplikacja działająca w oknie konsoli, która umożliwia testowanie 
 *          operacji wykonywanych na obiektach klasy Person.
 *    Plik: PersonConsoleApp.java
 *          
 *   Autor: Paweł Rogaliński
 *    Data: październik 2018 r.
 */
public class PersonConsoleApp {

	private static final String GREETING_MESSAGE = 
			"Program Person - wersja konsolowa\n" + 
	        "Autor: Paweł Rogaliński\n" +
			"Data:  październik 2018 r.\n";

	private static final String MENU = 
			"    M E N U   G Ł Ó W N E  \n" +
			"1 - Podaj dane nowej osoby \n" +
			"2 - Usuń dane osoby        \n" +
			"3 - Modyfikuj dane osoby   \n" +
			"4 - Wczytaj dane z pliku   \n" +
			"5 - Zapisz dane do pliku   \n" +
			"0 - Zakończ program        \n";	
	
	private static final String CHANGE_MENU = 
			"   Co zmienić?     \n" + 
	        "1 - Imię           \n" + 
			"2 - Nazwisko       \n" + 
	        "3 - Rok urodzenia  \n" + 
			"4 - Stanowisko     \n" +
	        "0 - Powrót do menu głównego\n";
	private static final String COL_MENU =
			" Właściwości jakiej kolekcji pokazać? \n"+
			"1 - HashSet         \n" +
			"2 - TreeSet       \n" +
			"3 - ArrayList  \n" +
			"4 - LinkedList     \n" +
			"5 - HashMap \n" +
			"6 - TreeMap \n" +
			"0 - Powrót do menu głównego\n";
	
	/**
	 * ConsoleUserDialog to pomocnicza klasa zawierająca zestaw
	 * prostych metod do realizacji dialogu z użytkownikiem
	 * w oknie konsoli tekstowej.
	 */
	private static final ConsoleUserDialog UI = new ConsoleUserDialog();
	
	
	public static void main(String[] args){
		// Utworzenie obiektu aplikacji konsolowej
		// oraz uruchomienie głównej pętli aplikacji.
		PersonConsoleApp application = new PersonConsoleApp();
		application.runMainLoop_2();

	} 

	
	/*
	 *  Referencja do obiektu, który zawiera dane aktualnej osoby.
	 */
	private Person currentPerson = null;
	
	
	/*
	 *  Metoda runMainLoop wykonuje główną pętlę aplikacji.
	 *  UWAGA: Ta metoda zawiera nieskończoną pętlę,
	 *         w której program się zatrzymuje aż do zakończenia
	 *         działania za pomocą metody System.exit(0); 
	 */
	public void runMainLoop() {
		UI.printMessage(GREETING_MESSAGE);

		while (true) {
			UI.clearConsole();
			showCurrentPerson();

			try {
				switch (UI.enterInt(MENU + "==>> ")) {
				case 1:
					// utworzenie nowej osoby
					currentPerson = createNewPerson();
					break;
				case 2:
					// usunięcie danych aktualnej osoby.
					currentPerson = null;
					UI.printInfoMessage("Dane aktualnej osoby zostały usunięte");
					break;
				case 3:
					// zmiana danych dla aktualnej osoby
					if (currentPerson == null) throw new PersonException("Żadna osoba nie została utworzona.");
					changePersonData(currentPerson);
					break;
				case 4: {
					// odczyt danych z pliku tekstowego.
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					currentPerson = Person.readFromFile(file_name);
					UI.printInfoMessage("Dane aktualnej osoby zostały wczytane z pliku " + file_name);
				}
					break;
				case 5: {
					// zapis danych aktualnej osoby do pliku tekstowego 
					String file_name = UI.enterString("Podaj nazwę pliku: ");
					Person.printToFile(file_name, currentPerson);
					UI.printInfoMessage("Dane aktualnej osoby zostały zapisane do pliku " + file_name);
				}

					break;
				case 0:
					// zakończenie działania programu
					UI.printInfoMessage("\nProgram zakończył działanie!");
					System.exit(0);
				} // koniec instrukcji switch
			} catch (PersonException e) { 
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		} // koniec pętli while
	}
/*
*	Metoda runMainLoop_2 główna pętla programu do ilustracji działania różnych typów kolekcji
 */
	public void runMainLoop_2() {
		//deklaracja osób dla klasy bez definicji metod equals i hashcode

		try {
			Person a = new Person("Jakub", "Wirwis", 2001);
			a.setJob("Student");
			Person b = new Person("Marian", "Was", 1977);
			b.setJob("Nauczyciel");
			Person c = new Person("Paweł", "Grok", 1988);
			c.setJob("Kierownik");
			Person d = new Person("Jan", "Kowalski", 1944);
			d.setJob("Dyrektor");
			Person e = new Person("Adam", "Kowalski", 1988);
			e.setJob("Kierownik");
			Person f = new Person("Adam", "Kowalski", 1945);
			f.setJob("Gość");
			Person g = new Person("Jakub", "Wirwis", 2001);
			g.setJob("Student");
			//deklaracja osób dla klasy z definicją metod equals i hashcode
			Person2 a1 = new Person2("Jakub", "Wirwis", 1900);
			a1.setJob("Student");
			Person2 b1 = new Person2("Marian", "Was", 1977);
			b1.setJob("Nauczyciel");
			Person2 c1 = new Person2("Paweł", "Grok", 1988);
			c1.setJob("Kierownik");
			Person2 d1 = new Person2("Jan", "Kowalski", 1944);
			d1.setJob("Dyrektor");
			Person2 e1 = new Person2("Adam", "Kowalski", 1988);
			e1.setJob("Kierownik");
			Person2 f1 = new Person2("Adam", "Kowalski", 1988);
			f1.setJob("Gość");

			System.out.println("Program ilustrujący działanie różnych typów kolekcji:");
			UI.printInfoMessage("By przejść dalej:");
			while (true) {
				switch (UI.enterInt(COL_MENU + "==>> ")) {
					case 1:    //obsługa HashSet
						UI.printInfoMessage("HashSet:");

						Set<Person> z = new HashSet<>();
						Set<Person2> z1 = new HashSet<>();
						System.out.println("Dodawanie elementów do kolekcji bez powtórzeń:\n");
						z.add(a);
						z.add(b);
						z.add(f);

						z1.add(a1);
						z1.add(b1);
						z1.add(f1);
						for (Person i : z) {
							showPerson(i);
						}
						UI.printInfoMessage("Powtórne wielokrotne dodanie zmiennych a do stosu:");
						z.add(a);
						z.add(a);
						z.add(b);
						z.add(e);
						z1.add(a1);
						z1.add(b1);
						z1.add(e1); // dodanie obiektu o danych obiektu f1, ale z inną nazwą pracy
						UI.printInfoMessage("Stos obiektów klasy bez nadpisania metod equals i hashcode:\n");
						for (Person i : z) {
							showPerson(i);
						}
						UI.printInfoMessage("Stos obiektów klasy z nadpisaniem metod equals i hashcode:\n");
						for (Person i : z1) {
							showPerson(i);
						}
						UI.printInfoMessage("Usuwanie:");
						z.remove(a);
						for (Person i : z) {
							showPerson(i);
						}
						break;
					case 2: //Obsługa TreeSet
						UI.printInfoMessage("TreeSet:");
						Set<Person> t = new TreeSet<>();
						Set<Person2> t1 = new TreeSet<>();
						System.out.println("Dodawanie elementów do kolekcji bez powtórzeń:\n");
						t.add(a);
						t.add(b);
						t.add(f);

						t1.add(a1);
						t1.add(b1);
						t1.add(f1);
						for (Person i : t) {
							showPerson(i);
						}
						UI.printInfoMessage("Powtórne wielokrotne dodanie zmiennych a do stosu:");
						t.add(a);
						t.add(a);
						t.add(b);
						t.add(e);
						t1.add(a1);
						t1.add(b1);
						t1.add(e1); // dodanie obiektu o danych obiektu f1, ale z inną nazwą pracy
						UI.printInfoMessage("Stos obiektów klasy bez nadpisania metod equals i hashcode:\n");
						for (Person i : t) {
							showPerson(i);
						}
						UI.printInfoMessage("Stos obiektów klasy z nadpisaniem metod equals i hashcode:\n");
						for (Person i : t1) {
							showPerson(i);
						}
						UI.printInfoMessage("Usuwanie:");
						t.remove(a);
						for (Person i : t) {
							showPerson(i);
						}
						break;
					case 3: //Obsługa ArrayList
						UI.printInfoMessage("ArrayList:");
						List<Person> array = new ArrayList<>();
						List<Person2> array1 = new ArrayList<>();
						System.out.println("Dodawanie elementów do kolekcji bez powtórzeń:\n");
						array.add(a);
						array.add(b);
						array.add(0,f);

						array1.add(a1);
						array1.add(b1);
						array1.add(f1);
						for (Person i : array) {
							showPerson(i);
						}
						UI.printInfoMessage("Powtórne wielokrotne dodanie zmiennych do listy:");
						array.add(a);
						array.add(a);
						array.add(b);
						array.add(e);
						array1.add(a1);
						array1.add(b1);
						array1.add(e1); // dodanie obiektu o danych obiektu f1, ale z inną nazwą pracy
						UI.printInfoMessage("Lista obiektów klasy bez nadpisania metod equals i hashcode:\n");
						for (Person i : array) {
							showPerson(i);
						}
						UI.printInfoMessage("Lista obiektów klasy z nadpisaniem metod equals i hashcode:\n");
						for (Person i : array1) {
							showPerson(i);
						}
						UI.printInfoMessage("Usuwanie:");
						array.remove(a);
						for (Person i : array) {
							showPerson(i);
						}
						break;
					case 4: //LinkedList
						UI.printInfoMessage("LinkedList:");
						List<Person> linked = new LinkedList<>();
						List<Person2> linked1 = new LinkedList<>();
						System.out.println("Dodawanie elementów do kolekcji bez powtórzeń:\n");
						linked.add(a);
						linked.add(b);
						linked.add(0,f);

						linked1.add(a1);
						linked1.add(b1);
						linked1.add(f1);
						for (Person i : linked) {
							showPerson(i);
						}
						UI.printInfoMessage("Powtórne wielokrotne dodanie zmiennych do listy:");
						linked.add(a);
						linked.add(b);
						linked.add(e);
						linked1.add(a1);
						linked1.add(b1);
						linked1.add(e1); // dodanie obiektu o danych obiektu f1, ale z inną nazwą pracy
						UI.printInfoMessage("Lista obiektów klasy bez nadpisania metod equals i hashcode:\n");
						for (Person i : linked) {
							showPerson(i);
						}
						UI.printInfoMessage("Lista obiektów klasy z nadpisaniem metod equals i hashcode:\n");
						for (Person i : linked1) {
							showPerson(i);
						}
						UI.printInfoMessage("Usuwanie:");
						linked.remove(a);
						List<Person> remove = new LinkedList<>();
						remove.add(a);
						remove.add(b);
						linked.removeAll(remove);
						for (Person i : linked) {
							showPerson(i);
						}
						break;
					case 5: //HashMap
						UI.printInfoMessage("HashMap:");
						Map<Integer, Person> hash=new HashMap<>();
						System.out.println("Dodawanie elementów do kolekcji bez powtórzeń:\n");
						hash.put(1, a);
						hash.put(2, b);
						hash.put(1, f); //dodanie elementu na klucz zajęty
						for (Person i : hash.values()) {
							showPerson(i);
						}
						UI.printInfoMessage("Powtórne wielokrotne dodanie zmiennych do listy:");
						hash.put(3, a);
						hash.put(4, b);
						hash.put(6, a);
						hash.put(5, e);

						UI.printInfoMessage("Lista obiektów:\n");
						for (Person i : hash.values()) {
							showPerson(i);
						}
						UI.printInfoMessage("Usuwanie:");
						hash.remove(1);
						hash.remove(2);
						for (Person i : hash.values()) {
							showPerson(i);
						}
						break;
					case 6: //TreeMap
						UI.printInfoMessage("TreeMap:");
						Map<Integer, Person> tree=new TreeMap<>();
						System.out.println("Dodawanie elementów do kolekcji bez powtórzeń:\n");
						tree.put(1, a);
						tree.put(2, b);
						tree.put(1, f); //dodanie elementu na klucz zajęty
						for (Person i : tree.values()) {
							showPerson(i);
						}
						UI.printInfoMessage("Powtórne wielokrotne dodanie zmiennych do listy:");
						tree.put(3, a);
						tree.put(4, b);
						tree.put(6, a);
						tree.put(5, e);

						UI.printInfoMessage("Lista obiektów:\n");
						for (Person i : tree.values()) {
							showPerson(i);
						}
						UI.printInfoMessage("Usuwanie:");
						tree.remove(1);
						tree.remove(2);
						for (Person i : tree.values()) {
							showPerson(i);
						}
						break;
					case 0:
						UI.printInfoMessage("\nProgram zakończył działanie!");
						System.exit(0);
				}
			}
		}catch(PersonException e){
			e.printStackTrace();
		}
	}

	/*
	 *  Metoda wyświetla w oknie konsoli dane aktualnej osoby 
	 *  pamiętanej w zmiennej currentPerson.
	 */
	void showCurrentPerson() {
		showPerson(currentPerson);
	} 

	
	/* 
	 * Metoda wyświetla w oknie konsoli dane osoby reprezentowanej 
	 * przez obiekt klasy Person
	 */ 
	static void showPerson(Person person) {
		StringBuilder sb = new StringBuilder();
		
		if (person != null) {
			//sb.append("Aktualna osoba: \n")
			  sb.append("      Imię: ").append(person.getFirstName()).append("\n")
			  .append("  Nazwisko: ").append(person.getLastName()).append("\n")
			  .append("   Rok ur.: ").append(person.getBirthYear()).append("\n")
			  .append("Stanowisko: ").append(person.getJob()).append("\n");
		} else
			sb.append( "Brak danych osoby\n" );
		UI.printMessage( sb.toString() );
	}

	
	/* 
	 * Metoda wczytuje w konsoli dane nowej osoby, tworzy nowy obiekt
	 * klasy Person i wypełnia atrybuty wczytanymi danymi.
	 * Walidacja poprawności danych odbywa się w konstruktorze i setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static Person createNewPerson(){
		String first_name = UI.enterString("Podaj imię: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		String birth_year = UI.enterString("Podaj rok ur.: ");
		UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
		String job_name = UI.enterString("Podaj stanowisko: ");
		Person person;
		try { 
			// Utworzenie nowego obiektu klasy Person oraz
			// ustawienie wartości wszystkich atrybutów.
			person = new Person(first_name, last_name);
			person.setBirthYear(birth_year);
			person.setJob(job_name);
		} catch (PersonException e) {    
			// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
			// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
			// poszczególnych atrybutów.
			// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}



	/* 
	 * Metoda pozwala wczytać nowe dane dla poszczególnych atrybutów 
	 * obiekty person i zmienia je poprzez wywołanie odpowiednich setterów z klasy Person.
	 * Walidacja poprawności wczytanych danych odbywa się w setterach
	 * klasy Person. Jeśli zostaną wykryte niepoprawne dane,
	 * to zostanie zgłoszony wyjątek, który zawiera komunikat o błędzie.
	 */
	static void changePersonData(Person person)
	{
		while (true) {
			UI.clearConsole();
			showPerson(person);

			try {		
				switch (UI.enterInt(CHANGE_MENU + "==>> ")) {
				case 1:
					person.setFirstName(UI.enterString("Podaj imię: "));
					break;
				case 2:
					person.setLastName(UI.enterString("Podaj nazwisko: "));
					break;
				case 3:
					person.setBirthYear(UI.enterString("Podaj rok ur.: "));
					break;
				case 4:
					UI.printMessage("Dozwolone stanowiska:" + Arrays.deepToString(PersonJob.values()));
					person.setJob(UI.enterString("Podaj stanowisko: "));
					break;
				case 0: return;
				}  // koniec instrukcji switch
			} catch (PersonException e) {     
				// Tu są wychwytywane wyjątki zgłaszane przez metody klasy Person,
				// gdy nie są spełnione ograniczenia nałożone na dopuszczalne wartości
				// poszczególnych atrybutów.
				// Drukowanie komunikatu o błędzie zgłoszonym za pomocą wyjątku PersonException.
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	
	
}  // koniec klasy PersonConsoleApp
