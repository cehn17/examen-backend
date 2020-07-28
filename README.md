# examen-backend

- Para levantar la aplicación uno de los ide que se puede usar es eclipse Spring Tools Suite 4,
El cual pueden descargar de la siguiente pagina: https://spring.io/tools
o se puede agregar el plugin para eclipse Spring Tools Suite 4 en MarcketPlace.

- Una vez que esta el eclipse abierto hacer import del proyecto,

- IMPORTANTE: Antes de levantar la Aplicación, crear la base de datos en MySql y verificar la
configuración aplication.properties, setear datos del user y pass de la base de datos

- Una vez echa la configuraciones necesarias hacer un Maven Update para descargar las dependencias.

- PARA LEVANTAR LA APLICACIÓN: boton derecho del mouse sobre el proyecto -> Run As -> Spring Boot App (hacer click)
y comenzara a la levantar la aplicación

- Una vez levantada la aplicación se habran creado las tablas de la base de datos, en el archivo import.sql
se encuentra un juego de datos el cual utilice para las pruebas, puede esos datos si desea.

- Una vez levantada la aplicación ya puede realizar pruebas con postman.

- ACLARACIÓN IMPORTANTE: Spring boot no soporta este tipo de rutas: "/loans?page={page}&size={size}&user_id={user_id}" ,
las rutas deben tener la siguiente estructura: "/loans/{page}/{size}/{user_id}"

- Muchas Gracias!!
