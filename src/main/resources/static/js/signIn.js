const form = document.getElementById('form');

form.addEventListener('submit', e => {
    e.preventDefault();
    const data = new FormData(form);
    const user = {
        username: data.get('username'),
        email: data.get('email'),
        password: data.get('password')
    }

    fetch('http://localhost:8080/user/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(response => {
      if (!response.ok) {
        throw new Error('Error al guardar los datos.');
      }
      window.location.href = "http://localhost:8080/"
      // return response.json();
    })
    // .then(data => {
    // })
    .catch(error => {
      console.error(error);
    });
})