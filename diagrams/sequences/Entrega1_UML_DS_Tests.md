# test01AsignacionAleatoriaDeHexagonosYFichas()

@startuml
actor TestCase01
participant ":Tablero" as tablero
participant "tableroA : Tablero" as tableroA
participant "tableroB : Tablero" as tableroB

TestCase01 -> tablero : new Tablero(1234L)
activate tablero
tablero -> tablero : posicionaTerrenosYNumerosAleatoriamente
tablero --> TestCase01 : tableroA
deactivate tablero
activate tableroA

TestCase01 -> tablero : new Tablero(5678L)
activate tablero
tablero -> tablero : posicionaTerrenosYNumerosAleatoriamente
tablero --> TestCase01 : tableroB
deactivate tablero
activate tableroB

TestCase01 -> tableroA : getTerrenos()
tableroA --> TestCase01 : listaTerrenosA
TestCase01 -> tableroB : getTerrenos()
tableroB --> TestCase01 : listaTerrenosB

TestCase01 -> TestCase01 : verificarCantidadDeTerrenos()
TestCase01 -> TestCase01 : verificarDistribucionDeTerrenos()
TestCase01 -> TestCase01 : verificarDistribucionFichasNumericas()
TestCase01 -> TestCase01 : compararTableros()
@enduml

# test02ReglaDeDistanciaEntrePobladosIniciales

@startuml
actor TestCase02
participant "tablero : Tablero" as Tablero

TestCase02 -> Tablero : colocarPoblado(jugador1, Coordenada(0,0,0))
activate Tablero
Tablero -> Tablero : registrarPoblado(jugador1, Coordenada(0,0,0))

TestCase02 -> Tablero : puedeColocarPoblado(jugador1, Coordenada(0,0,1))
Tablero -> Tablero : verificarReglaDistanciaMinima(jugador1, new Coordenada(0,0,1))
Tablero --> TestCase02 : false

TestCase02 -> TestCase02 : assertFalse()
deactivate Tablero

@enduml

# test03RecursosInicialesDelSegundoPoblado

@startuml
actor TestCase03
participant "tablero : Tablero" as Tablero
participant "jugador1 : Jugador" as Jugador
participant "h1 : Bosque" as H1
participant "h2 : Campo" as H2
participant "h3 : Pastizal" as H3

TestCase03 -> Tablero : colocarPoblado(jugador1, Coordenada(2,2,2))
activate Tablero
activate Jugador
Tablero -> Tablero : registrarPoblado(jugador1, Coordenada(2,2,2))

TestCase03 -> Tablero : otorgarRecursosIniciales(jugador1, Coordenada(2,2,2))
Tablero -> Tablero : getTerrenosAdyacentes(Coordenada(2,2,2))
Tablero --> Tablero : [h1, h2, h3]

Tablero -> H1 : darRecurso()
activate H1
H1 --> Jugador : Madera
Tablero -> H2 : darRecurso()
activate H2
H2 --> Jugador : Cereal
Tablero -> H3 : darRecurso()
activate H3
H3 --> Jugador : Lana

TestCase03 -> Jugador : tieneRecurso(MADERA)
Jugador --> TestCase03 : true
TestCase03 -> Jugador : tieneRecurso(CEREAL)
Jugador --> TestCase03 : true
TestCase03 -> Jugador : tieneRecurso(LANA)
Jugador --> TestCase03 : true
TestCase03 -> Jugador : cantidadDeRecursos()
Jugador --> TestCase03 : 3

TestCase03 -> TestCase03 : assertTrue/assertEquals()
deactivate H1
deactivate H2
deactivate H3
deactivate Jugador
deactivate Tablero

@enduml

# test04LanzamientoDeDadosGeneraNumeroValido

@startuml
actor TestCase04
participant "dados : Dado" as dados

TestCase04 -> dados : tirarDados()
activate dados
dados -> dados : randint(1-6)
dados -> dados : randint(1-6)
dados --> TestCase04 : resultado(2-12)
deactivate dados

TestCase04 -> TestCase04 : assertTrue(resultado >= 2 && resultado <= 12)

@enduml

# test05ProduccionCorrectaDeRecursos

@startuml
actor TestCase05
participant "dados : Dado" as dados
participant "produccion : Produccion" as Produccion
participant "bosque : Terreno" as bosque

TestCase05 -> dados : tirarDados()
activate dados
dados --> TestCase05 : 8
deactivate dados
TestCase05 -> Produccion : producirRecursos(8, bosque)
activate Produccion

Produccion -> bosque : obtenerNumero()
activate bosque
bosque --> Produccion : 8

Produccion -> bosque : tienePobladoDe(jugador1)
bosque --> Produccion : true

Produccion -> bosque : tieneCiudadDe(jugador1)
bosque --> Produccion : false

Produccion -> bosque : obtenerRecurso()
bosque --> Produccion : Madera

TestCase05 -> TestCase05 : assertEquals(1, produccion.get(jugador1).size())
TestCase05 -> TestCase05 : assertEquals(Madera, produccion.get(jugador1).get(0))

deactivate Produccion
deactivate bosque
@enduml

# test06TerrenoBajoLadronNoProduceRecursos

@startuml
actor TestCase06
participant "dados : Dado" as dados
participant "tablero : Tablero" as tablero
participant "bosque : Terreno" as bosque

TestCase06 -> dados : tirarDados()
activate dados
dados --> TestCase06 : 8
deactivate dados

TestCase06 -> tablero : producirRecursos(8, listaTerrenos)
activate tablero
tablero -> bosque : obtenerNumero()
activate bosque
bosque --> tablero : 8
tablero -> bosque : tienePobladoDe(jugador1)
bosque --> tablero : true
tablero -> bosque : tieneLadron()
bosque --> tablero : true

TestCase06 -> TestCase06 : assertTrue(jugador1NoTieneRecursos)
deactivate tablero
deactivate bosque

@enduml

# test07JugadorDescartaLaMitadAlLanzar7

@startuml
actor TestCase07
participant "dados : Dado" as dados
participant "jugador : Jugador" as jugador

TestCase07 -> jugador : agregarRecursos(Madera, Madera, Cereal, Lana, Mineral, Madera, Lana)
activate jugador
TestCase07 -> dados : tirarDados()
activate dados
dados --> TestCase07 : 7
deactivate dados

TestCase07 -> jugador : cantidadDeRecursos()
jugador --> TestCase07 : 7
TestCase07 -> jugador : descartarPorLadron()
jugador -> jugador : descartarMitadRecursos()
TestCase07 -> jugador : cantidadDeRecursos()
jugador --> TestCase07 : 4
TestCase07 -> TestCase07 : assertEquals(esperado, jugador.cantidadDeRecursos())
deactivate jugador

@enduml

# test08MoverLadronYRobarCartaAleatoria

@startuml
actor TestCase08
participant "ladron : Ladron" as ladron

participant "jugador2 : Jugador" as jugador2
participant "jugador1 : Jugador" as jugador1

TestCase08 -> jugador2 : agregarRecursos(Lana, Madera)
activate jugador2
activate jugador1
TestCase08 -> ladron : moverA(Coordenadas)
activate ladron
TestCase08 -> ladron : tieneJugadorAdyacente(Coordenadas)
ladron --> TestCase08 : jugador2
TestCase08 -> ladron : robarCartaAleatoria(jugador2, jugador1)
ladron -> jugador2 : robarCarta()
jugador2 --> ladron : cartaRobada
TestCase08 -> ladron : assertNotNull(cartaRobada)
ladron --> jugador1 : cartaRobada
TestCase08 -> jugador1 : assertTrue(jugador1.tieneRecurso(cartaRobada))
TestCase08 -> jugador2 : assertFalse(jugador1.tieneRecurso(cartaRobada))

deactivate jugador1
deactivate jugador2
deactivate ladron

@enduml