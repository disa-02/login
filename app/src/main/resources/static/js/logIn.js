const form = document.getElementById('form');

form.addEventListener('submit', e => {
    e.preventDefault();
    const data = new FormData(form);
    const user = {
        email: data.get('email'),
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
        console.log(data)
        localStorage.setItem('token',data)
        window.location.href = "http://localhost:8080/panel"
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

