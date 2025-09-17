# CLASE-16-09-2025-JUAN-DAVID-MORENO-VELEZ-409443
# Taller 1 – Gestión Académica con Lista Enlazada Simple

JUAN-DAVID-MORENO-VELEZ-409443

Descripción
Este proyecto lo desarrollé como parte del Taller 1 de **Estructuras de Datos**.  
El objetivo fue implementar un sistema sencillo para gestionar clases y estudiantes usando únicamente **listas enlazadas simples**.  

La idea principal es que cada clase maneje a sus alumnos como nodos dentro de una lista, sin utilizar estructuras de librerías de Java como `ArrayList` o `HashMap`.  
De esta forma, todas las operaciones (agregar, buscar, actualizar o eliminar estudiantes) se hacen recorriendo la lista nodo por nodo.



Cómo funciona
- La clase **CourseClass** representa una materia. Tiene sus atributos (`id`, `name`, `credits`) y un puntero `head` que apunta al primer estudiante inscrito.  
- La clase **Student** representa cada nodo de la lista. Incluye la información del alumno (`firstName`, `lastName`, `idNumber`, `semester`, `program`) y un puntero `next` al siguiente estudiante.



Operaciones implementadas

Sobre las clases
- Crear nuevas clases con nombre, id y créditos.  
- Validar que los créditos sean un número positivo.  
- Eliminar una clase junto con toda su lista de estudiantes.  

 Sobre los estudiantes
- **Inscripción**: añadir un estudiante en orden (por apellido y luego por ID).  
- **Consulta**:  
  - Buscar un estudiante por `idNumber`.  
  - Listar todos los estudiantes de una clase.  
- **Actualización**: cambiar datos de un estudiante si existe en la lista.  
- **Eliminación**: quitar un estudiante de la lista considerando casos como:  
  - borrar el primero,  
  - borrar uno intermedio,  
  - borrar el último.



Validaciones importantes
- No se pueden inscribir dos estudiantes con el mismo `idNumber`.  
- Al actualizar, el sistema informa si el estudiante fue encontrado.  
- Al eliminar, se ajusta correctamente la referencia `head` y los punteros `next` para que la lista siga funcionando bien.  

