const c = document.getElementById("myCanvas");
const ctx = c.getContext("2d");

ctx.moveTo(100,0);
ctx.lineTo(100,100);
ctx.lineTo(200,0);
ctx.strokeStyle = "red";

ctx.stroke();

c.ondbclick = alert("You double clicked the canvas");
