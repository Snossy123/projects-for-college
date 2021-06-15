//start of login page
var adminsEmail = [
    {Email: "omar@gmail.com", password: "1234"},
    {Email: "ahmed", password: "123456"}];
var usersEmail = [{name: "user", Email: "user", password: "4321"}];
function check(arr1, arr2) {
    var emailInput, passwordInput, i, j;
    
    emailInput = document.getElementById("emailInput").value;
    passwordInput = document.getElementById("passwordInput").value;
    
    for (i = 0; i < adminsEmail.length; i++) {
        if (emailInput === arr1[i].Email && passwordInput ===       arr1[i].password) {
            window.open("admin.html");
            arr1[i].isOpen = true;
            break;
        } else if (emailInput !== arr1[i].Email &&                 passwordInput !== arr1[i].password) {
            for (j = 0; j < usersEmail.length; j++) {
                if (emailInput === arr2[j].Email && passwordInput === arr2[j].password) {
                    window.open();
                    arr2[j].isOpen = true;
                    break;
                }
            }
        }
    }
}
document.getElementById("button").onclick = function () {check(adminsEmail, usersEmail); };

