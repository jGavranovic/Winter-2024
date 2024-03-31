const webProgPrice = 19.99;
const phpPrice = 86;
const jqueryPrice = 55;
const number = new RegExp(/^\d+$/); //regex for int

function calculateOrder(){



    let webProgAmount = document.getElementById("webprog").value;

    if (!number.test(webProgAmount)) {
        alert("Invalid entry for web programming");
        return;
    }
    let webProgTotal = webProgAmount*webProgPrice;
    document.getElementById("webOutput").innerHTML = "<b>Basic Web Programming (Quantity = "+webProgAmount+"):</b> $"+webProgTotal;

    let phpAmount = document.getElementById("php").value;
    if (!number.test(phpAmount)) {
        alert("Invalid entry for php");
        return;
    }
    let phpTotal = phpAmount*phpPrice;
    document.getElementById("phpOutput").innerHTML = "<b>Intro to PHP (Quantity = "+phpAmount+"):</b> $"+phpTotal;

    let jqueryAmount = document.getElementById("jquery").value;
    if (!number.test(jqueryAmount)) {
        alert("Invalid entry for jquery");
        return;
    }
    let jqueryTotal = jqueryAmount*jqueryPrice;
    document.getElementById("jqueryOutput").innerHTML = "<b>Advanced JQuery (Quantity = "+jqueryAmount+"):</b> $"+jqueryTotal;

    let totalPrice = webProgTotal+phpTotal+jqueryTotal;
    document.getElementById("totalOutput").innerHTML = "<b>Final Total: </b> $"+totalPrice;
}