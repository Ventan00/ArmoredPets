
[![ServerNet](https://i.imgur.com/EwBly0J.png)](https://servernet.pl/)
# ArmoredPets

- ArmoredPets jest to plugin na własne latające zwierzaki w minecrafcie!
- Rozbuduj mechaniki RPG Twojego serwera za pomocą tego pluginu!

# Mechanika

- Twórz zwierzaki które mają statystyki takie jak: Szczęście, Atak, Obrona, Szansa na Drop
- Zwiększaj poziom zwierzaków by wzmocnić ich statystyki
- Twórz własne zasady zwiększania poziomu zwierzaka w skripcie za pomocą placeholderów!
- Zamieniaj zwierzaki na ulepszenia do innych zwierzaków by zwiększyć ich statystki

# Instalacja
**Zależności:
Essentials, NPCs**

Instrukcja:
- Obecnie plugin działa na silniku papermc w wersji 1.12.2
- Konieczne jest zaimportowanie pluginu https://github.com/Ventan00/NPCs by tworzyć NPCty zarządzające ulepszeniem/ściąganiem zwierzaka
- Pobierz najnowsze wydanie z karty https://github.com/Ventan00/ArmoredPets/releases oraz wrzuć je do folderu plugins na serwerze
- Ciesz się pluginem na swoim serwerze!

# Komendy:
| Komenda | Opis | Permisje |
| ------ | ------ | ------ |
| /petspawn | spawnuje zwierzaka. aktualne typy zwierzaków: KURCZAK,PSZCZOLKA,SLIMACZEK,LIS,MROWKA,PTASZEK | armoredpets.spawn |
| /despawnPet | Zciąga zwierzaka i daje go do ręki | brak |
| /zalozPet | Zakłada zwierzaka który jest aktualnie w głównej ręce (alternatywnie shift + PPM) | brak |
| /spawnPetNpc | Spawnuje NPC który zarządza ulepszeniem zwierzaków oraz ich ściągnięciem | armoredpets.spawnNPC |
| /removePetNpc | Usuwa NPC który zarządza ulepszeniem zwierzaków. ID NPC można sprawdzić za pomocą komendy /npc info która jest częścią pluginu NPCs | armoredpets.spawnNPC |
| /spawnNpcHelper | Opcje: "LVL", "niezidentyfikowany", "Kamien". Spawnuje w zależności od argumentu przedmiot o nazwie Kamień poziomu peta/Niezidentyfikowany zwierzak/Kamień ulepszenia peta który może zostać użyty u NPC zarządzającego zwierzakami | armoredpets.spawnhelper |

# Placeholdery
| Placeholder | Opis |
| ------ | ------ |
| %pet_type% | zwraca typ zwierzaka |
| %pet_lvl% | zwraca poziom zwierzaka  |
| %pet_exp% | zwraca aktualny exp zwierzaka |
| %pet_maxExp% | zwraca ile expa trzeba zdobyć by zdobyć kolejny poziom |
| %pet_allExp% | zwraca aktualny exp zwierzaka / ile expa trzeba zdobyć by zdobyć kolejny poziom |
| %pet_luck% | zwraca szczęście zwierzaka |
| %pet_attack% | zwraca atak zwierzaka |
| %pet_defence% | zwraca obronę zwierzaka |
| %pet_drop% | zwraca szansę na drop skrzyni peta |

# W następnych updateach będą dodane następujące mechaniki:
- plik konfiguracyjny do statystyk peta
- plik konfiguracyjny do nazw peta i skórek peta
- plik konfiguracyjny do nazw przedmiotów ulepszania
- wsparcie od wersji 1.8 do 1.16.2
- wsparcie dla Citizensów
- oddzielny, konfigurowalny plugin do zarządzania systemem RPG/ekonomią serwera do którego ArmoredPets będzie dodatkiem
- powstanie API do pluginu opisane w Javie
- oddzielny plik z komunikatami
- wersja angielska
- zostaną dodane następujące placeholdery: 

| Placeholder | Opis |
| ------ | ------ |
| %pet_lvlup% | zwiększa poziom zwierzaka o 1 dla danego użytkownika |
| %pet_dodajexp_liczba% | zwiększa exp zwierzaka o podaną liczbę |
| %pet_dodaj_atak_liczba% | dodaje liczbę do ataku peta |
| %pet_dodaj_obrona_liczba% | dodaje liczbę do obrony peta |
| %pet_dodaj_szczescie_liczba% | dodaje liczbę do szczęścia peta |
| %pet_dodaj_drop_liczba% | dodaje liczbę do szansy na drop skrzyni peta |


# Licencja

 **GPL-3.0 License**

