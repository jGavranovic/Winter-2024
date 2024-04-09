const express = require('express');
const app = express();
const cookieParser = require('cookie-parser')
const PORT = 3000;
app.use(cookieParser())
app.listen(PORT)


app.get('/', (req, res) => {
    if (typeof req.cookies.visits == 'undefined'){
        res.cookie('visits',1);
        res.cookie('lastVisited', getCurrentDate());
        res.send("Welcome to my webpage! It is your first time that you are here.")
    } else {
        numVisits = parseInt(req.cookies.visits) + 1;
        res.cookie('visits', numVisits);
        res.cookie('lastVisited', getCurrentDate());
        
        res.writeHead(200,{'Content-Type':'Text/Html'});
        res.write(`Hello, this is the ${numVisits} time that you are visitng my webpage.<br>`);
        res.write(`The last visit was on: ${req.cookies.lastVisited}`);
        res.end();
    }
})

function getCurrentDate(){
    const days = ['Sun','Mon','Tues','Wed','Thurs','Fri','Sat'];
    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May','Jun','Jul','Aug','Sep','Nov','Dec']
    let currentDate = new Date();
    let output=days[currentDate.getDay()]+" "+months[currentDate.getMonth()]+" "+currentDate.getDate()+" "+currentDate.getHours()+":"+currentDate.getMinutes()+":"+currentDate.getSeconds()+' '+" EST "+currentDate.getFullYear();

    return output;
}