# Proyecto para Practicas Profesionales
Aqui se aloja el proyecto realizado para el club alpha, a continuacion se mencionan las caracteristicas del proyecto.
## Uso de Spring Boot
El proyecto se realizo con Spring Boot en su version 3.2.3 y java 17 ya que la pagina de <a href="https://start.spring.io/">Spring Initialzr</a> no ofrece versiones mas antiguas del framework en cuestion.
## Uso de Hibernate
En el desarrollo de la aplicacion, Hibernate se utiliza para las siguientes funciones:
* Para definir las relaciones entre entidades
* Generar automaticamente las tablas de la base de datos desde las entidades definidas
* Hacer uso de los repositorios jpa y para realizar la consulta definida en el repositorio
## Uso de streams de datos
 El uso de streams de datos se hizo al obtener y filtrar los datos devueltos por el repositorio de automoviles
```java
public Stream<Long> getChipsActivosPorClub (Long clubId){
        return automovilRepository.
            findByCliente_Club_IdAndActivoAndCliente_Activo(clubId, true, true).
                stream().
                    map(Automovil::getIdChip);
}
```
Con esta funcion se le pide al repositorio de automoviles una lista que contenga los automoviles donde su cliente pertenezca a cierto id de club, que el estado del automovil sea activo y que el estado del cliente sea activo, finalmente se convirtio en un stream para retornar solo un stream de Longs, donde estaran solo el numero de chip

## Mapeo de relaciones en tablas con hibernate
El mapeo de relaciones se logra indicandole explicitamente a las entidades cuales datos deben ser llaves foraneas:
```java
@Entity
@Table(name = "automoviles")
public class Automovil {
    ...
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    ...
}
```
Con @ManyToOne indicamos que el automovil va a contener un objeto de la clase cliente, el cual equivaldria a ser el dueño.
```java
public class Cliente {
...
    @ManyToOne
    @JoinColumn(name = "id_club")
    private Club club;
...
}
```
Al igual que en la clase Automovil, en la clase Cliente se le dio la misma instruccion de @ManyToOne para indicar que uno o muchos clientes pertenecen a un club y asi almacenar la informacion de dicho club al que pertenece el cliente.
## Manejo de piscinas de conexiones
Para indicarle al framework que queremos manejar piscinas de conexiones, se le indica dentro de el archivo application.properties el tamaño de la piscina, cuantas conexiones estaran sin algun cliente, y el tiempo de espera para eliminar las conexiones que se dejaron de usar
```java
spring.datasource.hikari.maximumPoolSize=20
spring.datasource.hikari.minimumIdle=5
spring.datasource.hikari.connectionTimeout=20000
```
## Creacion de las tablas mediante POJO
Con el uso de Spring Boot y spring data con hibernate es mas facil la creacion automaticas de las tablas, para cubrir este requisito, se creo una clase para cada entidad de la base de datos, En el caso de las clases, se les puso dos notaciones @Entity y @Table indicando asi que la clase pertenecera a una entidad de la base de datos, posteriormente se utilizarion las anotaciones de @Id y @Column para indicar cada uno de los campos de la tabla
```java
@Entity
@Table(name = "automoviles")
public class Automovil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String color;
    @Column
    private String placas;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @Column(name = "id_chip")
    private Long idChip;
    @Column
    private String marca;
    @Column
    private String modelo;
    @Column(name = "fecha_de_alta")
    private Date fechaAlta;
    @Column(name = "fecha_de_actualizacion")
    private Date fechaActualizacion;
    @Column
    private Boolean activo; 
}

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;
    @Column(name = "fecha_de_alta")
    private Date fechaAlta;
    @Column(name = "fecha_de_actualizacion")
    private Date fechaActualizacion;
    @Column
    private Boolean activo;
    @Column
    private Boolean accesoPermitido;
    @ManyToOne
    @JoinColumn(name = "id_club")
    private Club club;
}

@Entity
@Table(name = "clubes")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nombre;  
}
```
## Retornar chips activos de usuarios activos
Para retornar los chips activos mediante el servicio web, se uso un repositorio jpa el cual pide a la base de datos todos los automoviles que su cliente pertenezca a un numero de club, que el estado del automovil sea activo, y que su dueño igual este activo:
```java
public ArrayList<Automovil> 
  findByCliente_Club_IdAndActivoAndCliente_Activo
    (Long clubId, Boolean autoState, Boolean clienteState);
```
Despues se extraen solo los numeros de chips con la siguiente funcion:
```java
public Stream<Long> getChipsActivosPorClub (Long clubId){
        return automovilRepository.
            findByCliente_Club_IdAndActivoAndCliente_Activo(clubId, true, true).
                stream().
                    map(Automovil::getIdChip);
}
```
Y finalmente se mapea un path de tipo get con parametro de id de club:
```java
@GetMapping("/{clubId}")
    public Stream<Long> getChipsPorClub(@PathVariable("clubId") Long clubId) {
        return autoService.getChipsActivosPorClub(clubId);
    }
```
