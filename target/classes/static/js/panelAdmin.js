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
        listElement = ['USER','ADMIN']
        // "<li>Admin</a></li> <li>User</a></li>"
       console.log(data) 
        data.forEach(user => {
            var id = "rol-names_" + user.id
            dropdown = '<select class="select" name="Rol" id="' + id + '">'
            for (var i = 0; i < listElement.length;i++){
                var selected = user.role.name == listElement[i] ? ' selected' : ''
                dropdown += '<option value="' + listElement[i] + '"' + selected + '>' + listElement[i] + '</option>'
            }
            dropdown += '</select>'
            const rowHtml = `<tr>
                                <td>${user.id}</td>
                                <td>${user.username}</td>
                                <td>${user.email}</td>
                                <td><button type="button" onclick="deleteUser(${user.id})"><img  class="image" src="../images/borrar.png"></button></td>
                                <td>${dropdown}</td>
                            </tr>`;

            tableBody.insertAdjacentHTML("beforeend", rowHtml); 
            
         
    });
    const selectElements = document.querySelectorAll("select");

    selectElements.forEach(rolSelect => {
        var id = parseInt(rolSelect.id.split('_')[1])
        rolSelect.addEventListener("change", function() {
            const selectedValue = rolSelect.value;
            console.log(selectedValue)
            fetch('http://localhost:8080/user/updateRol/' + id, {
                method: 'POST',
                headers:{
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer " + localStorage.getItem('token')
        
                },
                body:selectedValue
            }).then(response => {
                if(!response.ok){
                    throw new Error("error")
                }
                // return response.json(); 
            })
        })
    })
})



const deleteUser = async (id) => {
    await fetch('http://localhost:8080/user/delete/' + id, {
        method: 'DELETE',
        headers:{
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }).then(response => {
        if (!response.ok) {
            throw new Error('Error en los datos.');
        }
    return response.text();
    })
    location.reload();
}

const menuButton = document.getElementById('menuButton');
// const menu = document.querySelector('.menu');

menuButton.addEventListener('click', () => {
    menu.classList.toggle('active');
});


