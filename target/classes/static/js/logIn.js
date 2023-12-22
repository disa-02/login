const form = document.getElementById('form');

form.addEventListener('submit', e => {
    e.preventDefault();
    const data = new FormData(form);
    const user = {
        username: data.get('username'),
        password: data.get('password')
    }

    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(response => {
      if (!response.ok) {
        throw new Error('Error en los datos.');
      }
      return response.text();
    })
    .then( data => {
        if (!data) {
            throw new Error('Credenciales incorrectas.');
        }
        data = JSON.parse(data)
        localStorage.setItem('token',data.token)
        console.log(data.token)
        localStorage.setItem('userLoggedId',data.id)

        fetch("http://localhost:8080/redirect?id=" + data.id,  {
            method: 'GET',
            headers: {
                'Authorization': "Bearer " + data.token,
            },
            }).then(response => {return response})
            .then(data =>{
                console.log(data)
                window.location.href = data.url
            });


   

        // 
    }
    )
    .catch(error => {
      console.error(error);
    });
})


const signInButton = document.getElementById('signIn');

signInButton.addEventListener('click', () => {
    window.location.href = "http://localhost:8080/signIn"
})

