# SPPMMBaseDades2
Aquest és el repositori on aniré guardant la feina que faci per l'activitat de Bases de Dades de l'assignatura de 
Programació Multimèdia i Dispositius Mòbils

Crec que faré una aplicació per fer la llista de la compra i suggerir receptes. Tendrà tres taules, una de ingredients, una de receptes i una amb la relació
entre receptes i ingredients.
Els ingredients tendran un atribut booleà que indicarà si en queda o no. Suggerirà receptes en funció de si en queda o no.
A més de l'atribut booleà per marcar si en queda, en tendrà un altre per marcar si se n'ha de comprar. Potser això ho poc substituir amb una taula
de llista de la compra on es faci un drop table cada vegada que es confirmi una compra. Si ho faig així, potser alguns ingredients marcats amb un altre
booleà anomenat bàsic, s'inclouran a la llista de la compra cada vegada que es marquin com que no en queda, perquè se  n'haurà de comprar sempre.
A primer cop d'ull necessitaré vàries activitats:
Llista receptes
Afegir recepta
Mostrar recepta
Llista ingredients (aquesta potser se pot fer servir també per la llista de la compra)
Afegir ingredient

Més idees:
A la llista ingredient posar dues icones que marquin si en queda i si està a la llista de la compra, i que pitjant a sobre permeti cnaviar-ne l'estat

30/12
He creat els pojos d'Ingredient i de Recepta. He creat els layouts dels menus principals de navegació i el de la llista d'ingredients. A l'array adapter he posat un listener perque els imageview del layout ingredient_a_llista faci de botó per canviar els booleans dels pojos.
ME QUEDA: 
Que es carregui la llista d'ingredients de la bd. 
Que els canvis que es facin amb els botons passin a la bd.
Activity afegir ingredient
Listview recepta
Activity veurerecepta
Activity AfegirRecepta
Tota la lògica de la bd

++
He començat a crear la lògica SQL. Me qued a la classe DataSourcerebost, a la pag 4 del pdf de l'activitat de persistencia.
Als apunts tenc posat que hi ha d'haver ajudes en la introducció de dades. Ho podria fer en introduir els ingredients a la recepta i en incloure ingredients en llistes d'ingredients.

18/01
He seguit fent feina amb la lógica de BBDD. Estic fent els métodes per introduir, esborrar, actualitzar i recuperar de la BBDD ingredients i receptes. Especial atenció a la gestió de la col·lecció d'ingredients de cada recepta. Crec que al métodes getAllIngs i getAllRecs el while ha de dur una negació, però fins que no faci proves d'execució, no ho vull canviar que després pot ser dificil de trobar. 

27/01
Durant sa setmana a classe he fet els mètodes dels connectors a BD, ara he d'acabar les activities i els layouts per mostrar les dades

29/01
La llista ja mostra el contingut de la taula ingredients. Estic posant un listener a l'array adapter per poder actualitzar la informació de la llista, però el posi com el posi, sempre agafa el darrer ingredient de la llista.

30/01
Finalment he arreglat lallista d'ingredients fent final el paràmetre position per que l'agafi el mètode on click i enlloc de canviar la imatge, actualitzar les dades de l'adapter. 
TODO:
- Afegir ingredient a recepta (acab de posar l'spinner)
- Editar ingredient
- itemClickListener a llista recepta
- Fer servir layout de introduir recepta per editar-la i per mostrar-la
- Mètode suggerir recepta
- Proves!

01/02
Ja he fet tota l'activitat per introduïr ingredients a recepta. He posat l'spinner per seleccionar ingredients dins la base de dades, però per afegir-ne de nous he fet una crida a l'activity d'afegir ingredients. He consiguit afegir ingredients a recepta, pero si vull mostrar la llista de receptes crasheija. Deu ser el mètode per mostrar el nom dels ingredietns que s'han acabat. 
He seguit una mica i el problema es que al mètode getAllRec() per carregar els ingredients feia servir cursorToIng, enlloc de getIng. Ara no mostra receptes sense ingredients, però si que carrega la llista i si que mostra els ingredients que s'han acabat.

2/2
Afegida condicio de bàsic que fa que si s'acaba s'apunti automàticament a la llista de la compra.
He arreglat un bug que feia entrar el cursor en un bucle infinit en llegir els ingredients de les receptes.
itemClickListener a llista recepta
Estic fent llista de la compra. Mostra Els ingredients que ha de mostrar, pero els botons no van bé
TODO:
- Editar ingredient
- Que en entrar a afegir ingredient la casella del nom tengui el focus
- Llista compra
- Mètode suggerir recepta
- Boto conf a menu principal per borra, actualitzar o guardar la BD en xml o altre format 
- Icones
- Gràfics
- Proves!

3/2
Corregit error a ingredient arrayadapter que feia que en canviar compra se canvies queda.
corregir error a la query per fer la llista de la compra, que mirava el boolea queda enlloc de compra datasourcererost getLlistaCompra()
Fet editar ingredient
He estat ajustat els layouts perque faig proves amb el meu mòbil. Té apk 17! he hagut de baixar sdk i baixar el mínim del projecte per poder-ho fer servir
Estic intentant fer que a llista compra quan es canvia compra a false, desaparegui l'item, però això crec que necessita listeners a dos nivells, no se si es podrà fer tal com està
fet info i settings
fet que el nom de recepta o ingredient es seleccioni sencer en clickarhi
fet suggerir recepta
fet delete db a settings
Foto carrito https://commons.wikimedia.org/wiki/File:Shopping_cart_font_awesome.svg
imatge icona https://pixabay.com/es/cocina-estufa-horno-1745688/
-- https://pixabay.com/es/cocina-muebles-casa-dise%C3%B1o-2988766/

Arreglat bug que fa que es pengui si no hi ha receptes per suggerir
fetes Icones
Ficat el text d'info a un scrollview
Arreglat que n afegir ings a rec concatena els noms malament
TODO:

- Que en clickar a recepta s'obri afegir recepte pero sense permetre edicio, que això s'habiliti amb un botó¨(Quan suggereix recepta el titol agafa focus i el teclat tapa el text)
- Boto actualitzar o guardar la BD en xml o altre format 

- En agafar un ing del combobox esborra els introduits com nous ings (no he pogut reproduir aquest error)
- Que es llevin els items de la llista de la compra
- Afegir camps a la BD: imatges de recepta, temps de preparacio, darrera vegada cuinada (+ tota la lògica per registrar-ho)
- Fer classes d0ingredients per permetre receptes com pasta bolonyesa i que accepti macarrons i spaghettis
- Refer interficie en un sol menu
- Gràfics
- Proves!






_______________________________________________________________________________________________________________
Comandes git 
git clone https://github.com/pppaaauuu/SPPMMBaseDades2
git add remote classe https://github.com/iespaucasesnoves/base-de-dades-app-pppaaauuu
git add *
git commit -m "Llegir README.md"
git push origin
pppaaauuu
git push classe
pppaaauuu
