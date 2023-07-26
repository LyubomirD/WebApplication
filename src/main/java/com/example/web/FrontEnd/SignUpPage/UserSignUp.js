document.getElementById("signUpForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const formData = new FormData(event.target);

    const username = formData.get('username');
    const email = formData.get('email');
    const password = formData.get('password');

    const userDate = {
        username: username,
        email: email,
        password: password
    };

    fetch('http://localhost:8081/login/post', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userDate)

    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Login failed. Invalid credentials.');
            }
        })
        .then(data => {
            console.log('Login successful!', data);
            window.location.href = 'http://localhost:63342/web/com/example/web/FrontEnd/LoginPage/UserLoginPage.html?_ijt=eg5jt299cu9oq2f4hk4n49gjps&_ij_reload=RELOAD_ON_SAVE';
        })

        //TODO check if email already exist if yes -> error message  if no -> create new user
        .catch(error => {
            console.error('Error:', error);
            const errorMessage = document.getElementById('error-message');
            errorMessage.textContent = 'User already exists!';
        });
});
