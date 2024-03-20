function fade(id){
    var dom = document.getElementById(id);
    var level = 1;
    function step(){
        var h = level.toString(16);
        dom.style.backgroundColor = "#FFFF"+h +h;
        if (level < 15){
            level++;
            setTimeout(step, 200);
        }
    }
    setTimeout(step, 100);
}

fade("body");