## Ecopoints 🌳

🗑️ Helping you dispose of waste the right way. 



### Technologies and stack used

**Back-end:** 
- Java 17
- Maven
- Spring Boot
- Spring Framework (Spring Data JPA, Spring Security)
- PostgreSQL
- Flyway Migration
- Lombok

**Front-end:**
- React.js
- Vite.js



### How to install

Click [here](git@github.com:rodrigoge/ecopoints.git) to download the project, and follow instructions:

```bash
  mvn clean install
  mvn clean package
  java -jar target/userservice-1.0.0.0.jar
```
    
### API Documentation

#### Create a new user

```http
  POST /user
```

| Parameter   | Description                           |
| :---------- | :---------------------------------- |
| `name` | User name to be created |
| `email` | User email to be created |
| `password` | User password to be created |

Return a new user created.




### Environment Variables

`db_url` - Url used to connect to database

`db_schema` - Schema name referring the database used

`db_user` - User name for connection

`db_password` - Password for connection

`port` - Port on which the application will run

### Author

- [@rodrigoge](https://www.github.com/rodrigoge)


### Licença

[MIT](https://github.com/rodrigoge/ecopoints/blob/main/LICENSE)

