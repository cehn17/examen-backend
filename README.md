# Examen Backend
🚀 Configuración y ejecución de la aplicación
🔧 Requisitos previos
IDE recomendado: Spring Tools Suite 4 o IntelliJ IDEA.
Puedes descargar Spring Tools Suite 4 desde: https://spring.io/tools
También puedes agregar el plugin de Spring Tools Suite 4 desde el Eclipse Marketplace.
🛠️ Importación y configuración
Abre tu IDE y haz un import del proyecto como un Proyecto Maven.
La aplicación usa una base de datos en memoria H2 (ya configurada en application.properties), por lo que NO es necesario crear manualmente una base de datos.
Si deseas modificar las credenciales o configuración de la base de datos, puedes hacerlo en el archivo:
css
Copiar código
src/main/resources/application.properties
📥 Carga de datos inicial
La carga inicial de datos se realiza automáticamente desde el archivo import.sql ubicado en:
css
Copiar código
src/main/resources/import.sql
Puedes modificar estos datos o agregar nuevos según tus necesidades.
▶️ Levantando la aplicación
👉 Desde el IDE

Haz clic derecho sobre el proyecto → Run As → Spring Boot App
👉 Desde la consola

Abre una terminal en la raíz del proyecto.
Ejecuta el siguiente comando:
bash
Copiar código
./mvnw spring-boot:run
(En Windows usa mvnw.cmd spring-boot:run)
🖥️ Pruebas de la API
Una vez levantada la aplicación, puedes probar los endpoints directamente desde Swagger:
Abre el navegador y accede a:
bash
Copiar código
http://localhost:8080/swagger-ui/index.html
Swagger te permitirá interactuar con los endpoints, realizar pruebas y ver la documentación de la API.
✅ Estado de la base de datos
También puedes acceder a la consola de H2 para inspeccionar los datos directamente:
URL:
bash
Copiar código
http://localhost:8080/h2-console
Datos de conexión (deben coincidir con application.properties):
URL: jdbc:h2:mem:testdb
Usuario: sa
Password: (dejar en blanco)
🎯 ¡Todo listo!
Ya puedes realizar pruebas y validar los datos cargados en la base de datos desde Swagger o directamente en H2.

💡 ¡Muchas gracias! 😎
