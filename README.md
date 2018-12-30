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

