var scope = "global scope";
function checkscope(){
    var scope = "local scope";
    function nested(){
        var scope = "nested scope";
        alert("1-" + scope);
    }
    nested();
    // scope = "local scope";
    // for(var i=0;i<10;i++){
    //     var scope = "nested scope";
    // }
    alert("2-"+scope);
}

checkscope();
alert("3-"+scope);