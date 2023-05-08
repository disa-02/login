const tableBody = document.querySelector("#tbody");


fetch('http://localhost:8080/list', {
        method: 'GET',
        headers:{
            'Authorization': localStorage.getItem('token')
        }
    }).then(response => {
        if(!response.ok){
            throw new Error("error")
        }
        return response.json();
    }).then(data => {
        data.forEach(user => {

            const rowHtml = `<tr>
                                <td>${user.id}</td>
                                <td>${user.name}</td>
                                <td>${user.email}</td>
                                <td><button type="button" onclick="deleteUser(${user.id})"><img  class="image" src="../images/borrar.png"></button></td>
                            </tr>`;

            tableBody.insertAdjacentHTML("beforeend", rowHtml);        
    });
})



const deleteUser = async (id) => {
    await fetch('http://localhost:8080/delete/' + id, {
        method: 'DELETE',
        headers:{
            'Authorization': localStorage.getItem('token')
        }
    }).then(response => {
        if (!response.ok) {
            throw new Error('Error en los datos.');
        }
    return response.text();
    })
    location.reload();
}