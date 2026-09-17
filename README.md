# Mi Carrito TECSUP

**Autor:** Clemente Huaraka Nilton Javier

## Descripción

Aplicación desarrollada en **Kotlin utilizando Jetpack Compose** que permite registrar productos y administrar un carrito de compras.


## Capturas de pantalla

### Carrito vacío

<img width="367" height="803" alt="image" src="https://github.com/user-attachments/assets/55ca775f-3f9d-437a-8712-44b90d78e471" />


### Carrito con productos

<img width="366" height="805" alt="image" src="https://github.com/user-attachments/assets/62cda734-2a77-412f-8659-82467a1ddfd6" />




## Preguntas

### (a) ¿Por qué `mutableStateListOf` y no una `MutableList` normal?

Por que con mutableStateListOf tenemos la ventaja que con cada modificacion de la lista , esta sea notificada y permita el cambio automatico de la interfaz

### (b) ¿Por qué la lista es val?

Por que no estamos cambiando la referencia de la lista, sino su contenido, asi que es valido usar val 

### (c) ¿Qué hace weight(1f) en la LazyColumn?

Weight(1f) hace que la LazyColumn ocupe todo el espacio disponible que queda dentro del Column. Esto permite que la lista se adapte al espacio disponible y deje lugar para otros elementos, como el botón y el panel de totales
