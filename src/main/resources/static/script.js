async function cargarEnemigos(){
    try{
        const response = await fetch('/api/enemigo');
        const enemigos = await response.json();
        mostrarEnemigos(enemigos);

    }catch(error){
        console.error("Error Al Cargar Usuarios " + error);
    }

} // FIN CARGAR ENEMIGOS

function mostrarEnemigos(enemigos){
    const tbody = document.getElementById('enemigosBody');
    const table = document.getElementById('enemigosTable');

    tbody.innerHTML = '';

    if (enemigos.length === 0){
        console.log("No hay mas corruptos")
        return;
    }

    enemigos.forEach(enemigo => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${enemigo.id}</td>
            <td>${enemigo.nombre}</td>
            <td>${enemigo.pais}</td>
            <td>${enemigo.afiliacion_politica}</td>
        `;
        tbody.appendChild(tr);
    });

    table.style.display = 'table';
}

// Cargar los enemigos cuando se carga la página
document.addEventListener('DOMContentLoaded', cargarEnemigos);


// Aquí empezamos con la parte de insertar

document.getElementById('formInsertarEnemigo').addEventListener('submit', async function(e) {
    e.preventDefault();
    await insertarEnemigo();
});

async function insertarEnemigo() {
    const nombre = document.getElementById('nombre').value
    const pais = document.getElementById('pais').value
    const afiliacion = document.getElementById('afiliacion').value
    const btnSubmit = document.getElementById('btnSubmit')

    // Esto es mientras se procesa
    btnSubmit.disabled = true
    btnSubmit.textContent = 'Enviando a Cuba...'

    try{
        const response = await fetch('/api/enemigo', {
            method : 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nombre: nombre,
                pais: pais,
                afiliacion_politica: afiliacion
            })
        });

        if (!response.ok) {
            throw new Error('Error en la respuesta del servidor');
        }

        // Recargar la lista de enemigos
        await cargarEnemigos();

        // Limpiar el formulario
        document.getElementById('formInsertarEnemigo').reset();

    }catch(error){
        console.error("Error al insertar enemigo:", error);
        alert("Error al insertar enemigo");
    }finally{
        btnSubmit.disabled = false;
        btnSubmit.textContent = 'Insertar Enemigo';
    }
}

// Funcionalidad para editar enemigo
document.getElementById('btnEdit').addEventListener('click', async function() {
    await editarEnemigo();
});


///////


async function editarEnemigo() {
    const id = document.getElementById('id').value.trim();
    const nombre = document.getElementById('nombre').value;
    const pais = document.getElementById('pais').value;
    const afiliacion = document.getElementById('afiliacion').value;
    const btnEdit = document.getElementById('btnEdit');

    if (!id) {
        alert('Por favor, ingresa el ID del enemigo a editar');
        return;
    }

    // Esto es mientras se procesa
    btnEdit.disabled = true;

    try {
        const response = await fetch(`/api/enemigo/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                nombre: nombre,
                pais: pais,
                afiliacion_politica: afiliacion
            })
        });

        if (!response.ok) {
            throw new Error('Error en la respuesta del servidor');
        }

        const resultado = await response.json();

        if (resultado === null) {
            alert('Enemigo no encontrado con el ID: ' + id);
        } else {
            // Recargar la lista de enemigos
            await cargarEnemigos();
            // Limpiar el formulario
            document.getElementById('formInsertarEnemigo').reset();
        }

    } catch (error) {
        console.error("Error al editar enemigo:", error);
        alert("Error al editar enemigo");
    } finally {
        btnEdit.disabled = false;
        btnEdit.textContent = 'Editar enemigo';
    }
}

// Event listener para el botón de eliminar
document.getElementById('btnEliminar').addEventListener('click', async function() {
    await borrarEnemigo();
});

async function borrarEnemigo(){
    const id = document.getElementById('id').value.trim();
    const btnEliminar = document.getElementById('btnEliminar');

    if (!id) {
        alert('Por favor, ingresa el ID del enemigo a eliminar');
        return;
    }

    btnEliminar.disabled = true;
    btnEliminar.textContent = 'Eliminando...';

    try {
        const response = await fetch(`/api/enemigo/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        });

        if (!response.ok) {
            throw new Error('Error en la respuesta del servidor');
        }

        alert('Enemigo eliminado exitosamente');
        // Recargar la lista de enemigos
        await cargarEnemigos();
        // Limpiar el formulario
        document.getElementById('formInsertarEnemigo').reset();

    } catch (error) {
        console.error("Error al eliminar enemigo:", error);
        alert("Error al eliminar enemigo. Verifica que el ID exista.");
    } finally {
        btnEliminar.disabled = false;
        btnEliminar.textContent = 'Eliminar enemigo';
    }
}