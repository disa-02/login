# login
Aplicacion realizada con Spring Boot. Permite registrar usuarios e iniciar sesion. Al iniciar sesion el usuario puede ver los demas usuarios registrados y eliminarlos.
Pendiente: Crear roles de usuarios:
	- Admin: Puede ver y borrar cualquier usuario
	- Ayudante: Puede ver todos los usuarios pero solo se puede borrar a si mismo
	- Comun: No puede ver otros usuarios y puede borrarse a si mismo

# security
Se utilizo jwtoken para crear las sesiones de usuario

# swagger
La aplicacion se encuentra integada con swagger lo que permite utilizar y ver los endpoint de la restApi accediendo a: http://localhost:8080/swagger-ui/index.html

# ejecucion
Una vez ejecutada la aplicacion, el acceso se realiza a travez de: http://localhost:8080
La base de datos corre sobre el puerto 3306 con el nombre de login
