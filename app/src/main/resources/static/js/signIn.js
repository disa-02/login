const form = document.getElementById('form');

form.addEventListener('submit', e => {
    e.preventDefault();
    const data = new FormData(form);
    const user = {
        name: data.get('name'),
        email: data.get('email'),
        password: data.get('password')
    }

    fetch('http://localhost:8080/save', {
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
    //   console.log(data.text());
    // })
    .catch(error => {
      console.error(error);
    });
})