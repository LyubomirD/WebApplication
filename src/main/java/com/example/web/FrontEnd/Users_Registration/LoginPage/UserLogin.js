document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const formData = new FormData(event.target);

    const email = formData.get('email');
    const password = formData.get('password');

    fetch(`https://a8e0-79-124-18-222.ngrok.io/login/get/${encodeURIComponent(email)}/${encodeURIComponent(password)}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        mode: 'no-cors'
    })
        // .then(response => {
        //     if (response.ok) {
        //         return response.json();
        //     } else {
        //         throw new Error('Login failed. Invalid credentials.');
        //     }
        // })
        .then(data => {
            console.log('Login successful!', data);
            window.location.href = '../../nextPage.html';
        })
        .catch(error => {
            console.error('Error:', error);
            const errorMessage = document.getElementById('error-message');
            errorMessage.textContent = 'Invalid email or password!';
        });
});