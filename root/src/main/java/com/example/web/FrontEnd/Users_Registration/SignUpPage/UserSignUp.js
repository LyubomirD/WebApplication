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
        mode: 'no-cors', // Add this line to use no-cors mode

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
            window.location.href = 'https://lyubomird.github.io/WebApplication/root/src/main/java/com/example/web/FrontEnd/Users_Registration/LoginPage/UserLoginPage.html';
        })

        .catch(error => {
            console.error('Error:', error);
            const errorMessage = document.getElementById('error-message');
            errorMessage.textContent = 'User already exists!';
        });
});
