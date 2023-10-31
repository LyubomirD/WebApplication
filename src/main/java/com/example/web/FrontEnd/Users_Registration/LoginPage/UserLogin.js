document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const formData = new FormData(event.target);

    const email = formData.get('email');
    const password = formData.get('password');

    const credentials = btoa(email + ':' + password);

    fetch('http://localhost:8081/usersRegistration/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': "Basic " + credentials
        },
    })
        .then(response => {
            console.log(response.statusText + " and " + response.status);
            if (response.ok) {
                console.log('Login successful!');
                window.location.href = 'http://localhost:63342/web/com/example/web/FrontEnd/HomePage/HomePage.html';
            } else {
                throw new Error('Login failed. Invalid credentials.');
            }
        })
        .catch(error => {
            console.error('Error:', error);
            const errorMessage = document.getElementById('error-message');
            errorMessage.textContent = 'Invalid email or password!';
        });
});

//TODO to encrypt the payload of the user