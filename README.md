# Opis projektu

Aplikacja demonstruje projekt i realizację systemu rozproszonego składającego się z serwera oraz dwóch klientów: **AdminClient** i **UserClient**.  
Głównym celem aplikacji jest zarządzanie zadaniami w sposób współdzielony w czasie rzeczywistym.

---

## Jak działa aplikacja?

### Serwer
Serwer stanowi centralny element aplikacji i odpowiada za zarządzanie zadaniami oraz komunikację między klientami. Obsługuje on następujące funkcje:
- Dodawanie nowych zadań.
- Pobieranie listy zadań.
- Aktualizowanie statusu istniejących zadań.
- Usuwanie zadań (opcjonalnie przez AdminClient).

Serwer wykorzystuje technologię **RMI (Remote Method Invocation)**, która umożliwia zdalne wywoływanie metod na obiektach znajdujących się na innym hoście.

---

### AdminClient
**AdminClient** pozwala administratorowi:
1. Dodawać nowe zadania poprzez podanie nazwy i opisu.
2. Przeglądać wszystkie zadania wraz z ich aktualnym statusem.
3. Usuwać zadania po ich zakończeniu.
4. Odświeżać widok zadań w czasie rzeczywistym.

#### Przykładowe użycie:
<div style="text-align: center;">
Options:<br>
1. Add Task<br>
2. Remove Task<br>
3. Refresh Tasks<br>
q. Quit<br>
Choose an option: 1<br>
Enter task name: zrobic funkcjonalnosc<br>
Enter task description: dodac funkcjonalnosc do canvas<br>
Task added: zrobic funkcjonalnosc<br>
Tasks:<br>
Task{id=1, name='zrobic funkcjonalnosc', description='dodac funkcjonalnosc do canvas', status='W toku'}
</div>

---

### UserClient
**UserClient** pozwala użytkownikowi:
1. Przeglądać listę wszystkich zadań.
2. Zmieniać status zadań na "W toku" lub "Ukończone".
3. Odświeżać widok zadań, aby widzieć zmiany wprowadzone przez innych użytkowników.

#### Przykładowe użycie:
<div style="text-align: center;">
Options:<br>
1. Update task status<br>
2. Refresh task list<br>
3. Exit<br>
Select an option: 1<br>
Enter task ID to update: 1<br>
Enter new status: Ukonczone<br>
Task status updated.<br>
--- Tasks ---<br>
Task{id=1, name='zrobic funkcjonalnosc', description='dodac funkcjonalnosc do canvas', status='Ukonczone'}
</div>

---

## Wykorzystane technologie
- **Java RMI**: Zdalne wywoływanie metod na serwerze z poziomu klientów.
- **Konsola**: Interfejs tekstowy do obsługi aplikacji.
- **Singleton**: Wzorzec projektowy zastosowany do implementacji serwera.
- **Listy**: Przechowywanie i zarządzanie danymi zadań w pamięci.

---

## Menu aplikacji
Aplikacja działa w trybie konsolowym i oferuje intuicyjne opcje dla obu klientów:

- **AdminClient**:
  - Dodawanie zadania.
  - Przeglądanie listy zadań.
  - Usuwanie zadania.
  - Odświeżanie widoku.

- **UserClient**:
  - Przeglądanie listy zadań.
  - Aktualizacja statusu zadania.
  - Odświeżanie widoku.

---

## Problemy napotkane podczas tworzenia
1. **Synchronizacja danych**: Rozwiązano poprzez implementację metody odświeżania widoku u obu klientów.
2. **Komunikacja z serwerem**: Rozwiązano problemy z połączeniem RMI przez odpowiednią konfigurację rejestru i testowanie lokalne.
3. **Obsługa błędów**: Dodano wyświetlanie intuicyjnych komunikatów o błędach.
