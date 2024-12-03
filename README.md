<p align="center">
  <img  width="1200" height="250" src="https://portales.inacap.cl/Assets/imagenes/Exalumnos/2023/diciembre/oracle-exalumnos-2024-1200x400.jpg" >
</p>

<hr/>





# Foro Hub 💬🌐

![GitHub Created At](https://img.shields.io/github/created-at/MauriDemasi/foro-hub)   

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Java Version](https://img.shields.io/badge/java-17-blue)
![Spring Boot](https://img.shields.io/badge/spring%20boot-3.4-green)

![GitHub Org's stars](https://img.shields.io/github/stars/MauriDemasi?style=social)



## 📝 Descripción del Proyecto
Foro Hub es una aplicación web backend desarrollada con Spring Boot que simula un foro de discusión, proporcionando funcionalidades completas de gestión de tópicos y autenticación de usuarios.

## 🛠 Tecnologías Utilizadas
- **Lenguaje**: Java
- **Framework**: Spring Boot
- **Seguridad**: Spring Security
- **Autenticación**: JWT (JSON Web Tokens)
- **Persistencia**: JPA/Hibernate
- **Base de Datos**: MySQL


| MySQL                                                                                      | JWT                                                                                     | Spring Boot                                                                                      |
|-------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|
| ![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white) | ![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white) | ![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white) |

| IntelliJ IDEA                                                                                     | Hibernate                                                                                      | Git                                                                                     |
|---------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|
| ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) | ![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white) | ![Git](https://img.shields.io/badge/GIT-E44C30?style=for-the-badge&logo=git&logoColor=white) |

## ✨ Características
- Registro y autenticación de usuarios
- Creación, lectura, actualización y eliminación de tópicos
- Autenticación segura mediante tokens JWT
- Validación de datos de entrada
- Manejo robusto de errores y excepciones


## 🚀 Requisitos Previos
- JDK 17 o superior
- Maven
- MySQL Server

## 💻 Configuración del Proyecto

### Clonar Repositorio
```bash
git clone https://github.com/MauriDemasi/foro-hub.git
cd foro-hub
```

### Configuración de Base de Datos
1. Crear base de datos en MySQL
2. Configurar credenciales en `application.properties`

### Instalación de Dependencias
```bash
mvn clean install
```

## 🔧 Ejecución del Proyecto
```bash
mvn spring-boot:run
```
## 🌍 Endpoints Principales

### Autenticación
- `POST /login`: Iniciar sesión y obtener token JWT
- `POST /register`: Obtener un usuario para interactuar en la app

### Gestión de Tópicos
- `GET /topicos`: Listar todos los tópicos
- `POST /topicos`: Crear nuevo tópico
- `GET /topicos/{id}`: Obtener tópico específico
- `PUT /topicos/{id}`: Actualizar tópico

## 🤝 Contribuciones
¡Las contribuciones son bienvenidas! 

Pasos para contribuir:
1. Haz un fork del proyecto
2. Crea tu rama de características (`git checkout -b feature/nueva-caracteristica`)
3. Commit de tus cambios (`git commit -m 'Añadir nueva característica'`)
4. Push a la rama (`git push origin feature/nueva-caracteristica`)
5. Abre un Pull Request

## 📄 Licencia
Este proyecto está bajo la Licencia MIT.

## 📧 Contacto
- Autor: [Mauricio Demasi](https://github.com/MauriDemasi)
- GitHub: [MauriDemasi](https://github.com/MauriDemasi)
- LinkedIn: [Mauricio Demasi](https://www.linkedin.com/in/mauri-demasi-dev)







