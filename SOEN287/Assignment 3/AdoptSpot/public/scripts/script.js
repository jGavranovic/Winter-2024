
const days = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
const months = ['January', 'February', 'March', 'April', 'May','June','July','August','September','November','December']
function setDate(){
    const currentDate = new Date();
    let output=days[currentDate.getDay()]+", "+months[currentDate.getMonth()]+" "+currentDate.getDate()+", "+currentDate.getFullYear()+", "+currentDate.getHours()+":"+currentDate.getMinutes()+":"+currentDate.getSeconds();
    document.getElementById("date").innerHTML = output;
}
setDate();
setInterval(setDate, 1000);
function validateSearch(form){
    if (form.species.value == ""){
        alert("Please select a species");
        return false;
    }
    else if (form.breed[0].value == "" && !document.getElementById("dontCareBreed").checked){
        alert("Please select a breed preference")
        return false;
    }
    else if (form.sex.value == ""){
        alert("Please select a sex preference");
        return false;
    } 
    else {
        return true;
    }

}

function validateSubmit(form){
    const emailRegex = new RegExp(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g);
    if (form.species.value==""){
        alert("Please select a species");
        return false;
    } else if (form.breed[0].value == "" && !form.breed[1].checked){
        alert("Please enter a breed");
        return false;
    } else if(form.sex.value==""){
        alert("Please select a sex");
        return false;
    } else if (isNaN(form.age.value)) {
        alert("Please enter a valid age")
        return false;
    } else if (form.ownerName.value == ""){
        alert("Please enter an owner name");
        return false;
    } else if (!emailRegex.test(form.email.value)){
        alert("Please enter a valid email");
        return false
    } else {
        return true;
    }
}

function validAccountCreation(form){
    const usernameRegex = /^[A-Za-z0-9]+$/
    const passwordRegex = /^(?=.*[0-9])(?=.*[A-Za-z])[A-Za-z0-9]{4,}$/


    if (usernameRegex.test(form.username.value) && passwordRegex.test(form.password.value))
        return true;
    else {
        alert('Invalid username or password');
        return false;
    }
}