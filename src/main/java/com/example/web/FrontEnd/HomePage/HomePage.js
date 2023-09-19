document.addEventListener("DOMContentLoaded", function () {
    const welcomeMessageElement = document.getElementById("welcomeMessage");

    function fetchUserInfo() {
        $.ajax({
            url: "/usersRegistration/login",
            method: "GET",
            dataType: "json",
            success: function (data) {
                if (data) {
                    welcomeMessageElement.textContent = "Welcome, " + data.username + "!";
                } else {
                    welcomeMessageElement.textContent = "Welcome!";
                }
            },
            error: function () {
                console.error("Error fetching user information");
            }
        });
    }

    fetchUserInfo();
});
