# 📚 LiterAlura

Este es un proyecto desarrollado en **Java con Spring Boot** que permite gestionar información sobre **libros y autores**.  
Se implementa una base de datos relacional y operaciones CRUD a través de repositorios JPA.  
La búsqueda se implementa desde la API https://gutendex.com/
Además, cuenta con un menú interactivo en consola para consultar y manipular datos.

---

## 🚀 Tecnologías utilizadas
- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA**
- **Hibernate**
- **PostgreSQL** (puede adaptarse a otro motor relacional)
- **Maven**

---

## ⚙️ Funcionalidades principales

El sistema ofrece las siguientes opciones en un menú de consola:

1. 📖 **Registrar libro**  
   Permite ingresar un nuevo libro en la base de datos junto con su autor.  

2. 👨‍💻 **Registrar autor**  
   Registra un nuevo autor.  

3. 📚 **Listar todos los libros**  
   Muestra un listado completo de los libros almacenados.  

4. 🌍 **Cantidad de libros por idioma**  
   Permite consultar cuántos libros hay agrupados por idioma.  

5. 🕰️ **Autores vivos en un año específico**  
   Consulta los autores que estaban vivos en el año ingresado por el usuario.  

---
## 🛠️ Configuración e instalación

1. Clonar el repositorio:

```bash
git clone https://github.com/SaraCarol/Challenge-LiterAlura.git
cd Challenge-LiterAlura

mvn spring-boot:run
```

## 👨‍💻 Autor

Desarrollado por Sara Mendoza ✨
Proyecto académico con fines de práctica en Spring Boot y JPA.
