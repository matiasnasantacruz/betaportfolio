function toggleButtonText(){

    let btn = document.getElementById("jumbotronButton");
    let buttonText = btn.textContent;
    let caption = document.getElementById("jumbotronCaption");

    if(buttonText == "Show more"){
        btn.textContent="Less"
        caption.textContent = "";
    } 
    else{
        btn.textContent="Show more";
        caption.textContent = "Click 'Show more' to know how to get started with Bootstrap 4.";
    }
       

}

function carouselBehavior(){
    $('.carousel').carousel({
        pause : "hover",interval: 5000 
    })
}

function checkPassword(){

    var firstPW = document.getElementById("passwordField1").value;
    var secondPW = document.getElementById("passwordField2").value;
    let checkPW = document.getElementById("passwordVerify");

    if(firstPW!=secondPW)
        checkPW.textContent = "The passwords are not the same!";
    else
        checkPW.textContent = "Passwords match";

}

function setMaxPasswordLength(){

    document.getElementById("passwordField1").maxLength = 24;
    document.getElementById("passwordField2").maxLength = 24;

}