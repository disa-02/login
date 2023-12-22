# ecommerce
Aplicacion realizada con Spring Boot. Aplicacion de administracion de usuarios. Posee frontEnd y backEnd

# funcionalidades
 - Creacion de usuarios.
 - Cada usuario puede ver el resto de usuarios.
 - Cada usuario puede eliminarse a si mismo.
 - Los usuarios con rol "admin" pueden cambiar el rol de los demas usuarios.
 - Los usuarios con rol "admin" pueden borrar cualquier usuario.
 
 
# security
 - Se integro SpringSegurity a la aplicacion.
 - Se utilizo jwtoken para crear las sesiones de usuario.
 - Posee autenticacion y autorizacion de usuarios.
 - Posee roles de usuario, "admin" y "user".
 - Por default se crea un usuario con username= "admin" y password = "admin".

# ejecucion
Una vez ejecutada la aplicacion, el acceso se realiza a travez de: http://localhost:8080
La base de datos corre sobre el puerto 3306 con el nombre de login.

# docker
Se encuentra docker integrado en la aplicacion.
Con el comando "docker compose up" se inicializa autimaticamente la aplicacion.
Tener en cuenta que se puede requerir permisos de superusuario dependiendo la configuracion del sistema operativo donde esta corriendo.
Deben estar libres los siguientes puertos:
 - 8080: puerto donde corre la aplicacion
 - 3306: puerto donde corre la base de datos
Los puertos pueden ser modificados en el archivo "docker-compose.yml"

