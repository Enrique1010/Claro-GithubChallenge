# Claro-GithubChallenge
Aplicacion de Android para consumir el Api de github (en esta app solo se obtienen usuarios de lagos que sean de la comunidad java y sus repositorios)

#Cambios realizados en las carpetas:
-
 - java/me.jansv.challenge
  - api
  - model
  - ui.screens
  - util
 - resources/layout
  - activity_repos
  - user_repositories_item
#Archivos Agregados:

 - java/me.jansv.challenge/model
  - Repos.java
 - java/me.jansv.challenge/ui.screens
  - ReposContract.java (interface)
  - ReposPresenter.java
  
#Cambio en los archivos:
-
- **api/GithubService:** implementacion de observable para recibir data de forma reactiva.
- **api/ApiModule:** modificacion para agregar adaptador de rxjava.
- **ui/screens/UserAdapter:** implementacion de onClick en el viewholder para iniciar actividad y enviar el "repo_url"
- **ui/screens/UserActivity:** Eliminacion del metodo sheduleForTitleChange metodo practicamente inutil, tambien eliminacion de metodo fetchUserWhenReady metodo no necesario por ultimo eliminacion del metodo listadoRepositorios metodo no necesario ya que esta funcionalidad esta implementada en el adaptador (UserAdapter).
- **ui/screens/UserPresenter:** Modificado para adaptarse al rxjava.
- **utils/Constants:** agregar key "SP_KEY" valor para enviar repo_url a traves de intent.
- **layout/user_repositories_item:** diseño modificado para adaptar nombre y url del repositorio (unicos Valores mostrados).

#Dispositivos en los que se probo la aplicacion:
-
- Nexus 6p (android Oreo 8.0)
- One plus X (android Oreo 8.0)
- Xiaomi redmi 3s (android Pie 9.0)
- T-Movile RevPlus (android Nougat 7.0)
- Pixel 2 (android Pie 9.0) (Emulador)
- pixel 2 Silver (android Nougat 7.0) (Emulador)
- LG G6 (android Marshmello 6.0)
