const tableBody = document.querySelector("#tbody");


fetch('http://localhost:8080/user/list', {
        method: 'GET',
        headers:{
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }).then(response => {
        if(!response.ok){
            throw new Error("error")
        }
        return response.json();
    }).then(data => {
        data.forEach(user => {
            var id = localStorage.getItem('userLoggedId')
            var rowHtml = `<tr>
                                <td>${user.id}</td>
                                <td>${user.username}</td>
                                <td>${user.email}</td>`
            if(id == user.id)
                rowHtml +=`<td><button type="button" onclick="deleteUser(${user.id})"><img  class="image" src="../images/borrar.png"></button></td>`
            
            rowHtml += `</tr>`

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
    if(localStorage.getItem("userLoggedId") == id){
        localStorage.setItem('token',"")
        localStorage.setItem('userLoggedId',"")
        window.location.href = "http://localhost:8080/"
    }
    else
        location.reload()
    
}

const menuButton = document.getElementById('menuButton');
// const menu = document.querySelector('.menu');

menuButton.addEventListener('click', () => {
    menu.classList.toggle('active');
});


