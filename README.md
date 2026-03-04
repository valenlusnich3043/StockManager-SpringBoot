# StockManager API REST

API REST para gestión de inventario de productos desarrollada con Java y Spring Boot.

## Tecnologías utilizadas
- Java 17
- Spring Boot 3
- Spring Data JPA
- MySQL 8
- Maven
- Postman (para testing)

## Funcionalidades
- CRUD completo de productos
- CRUD completo de categorías
- Relación entre productos y categorías
- Base de datos persistente con MySQL

## Endpoints

### Categorías
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /categorias | Listar todas las categorías |
| POST | /categorias | Crear una categoría |
| DELETE | /categorias/{id} | Eliminar una categoría |

### Productos
| Método | URL | Descripción |
|--------|-----|-------------|
| GET | /productos | Listar todos los productos |
| POST | /productos | Crear un producto |
| PUT | /productos/{id} | Actualizar un producto |
| DELETE | /productos/{id} | Eliminar un producto |

## Cómo ejecutar el proyecto

### Requisitos
- Java 17+
- MySQL 8+
- Maven

### Configuración
1. Cloná el repositorio
```bash
git clone https://github.com/valenlusnich3043/StockManager-SpringBoot.git
```

2. Creá la base de datos en MySQL
```sql
CREATE DATABASE stockmanager;
```

3. Configurá las credenciales en `src/main/resources/application.properties`
```properties
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

4. Ejecutá el proyecto desde IntelliJ o con Maven
```bash
./mvnw spring-boot:run
```

5. La API estará disponible en `http://localhost:8080`

## Ejemplo de uso

### Crear una categoría
```json
POST /categorias
{
    "nombre": "Alimentos",
    "descripcion": "Productos alimenticios básicos"
}
```

### Crear un producto
```json
POST /productos
{
    "nombre": "Arroz",
    "precio": 150.0,
    "stock": 100,
    "categoria": {
        "id": 1
    }
}
```

## Autor
Valentín Lusnich — [GitHub](https://github.com/valenlusnich3043)
