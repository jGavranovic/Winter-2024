const express = require('express')
const app = express()

// const bodyParser = require('body-parser')

app.use(
    // bodyParser.json(),
    // bodyParser.urlencoded({extended: true}),
    express.json(),
    express.urlencoded()
)

app.set("view engine", "ejs")


const port = 3000;

app.get('/', (req, res) => {
    res.render('part3')
})

app.post('/', (req, res) => {
    let name = req.body.name;
    let phone = req.body.phone;

    res.send(`Is the phone number valid: ${isValidPhone(phone)}`)
})

function isValidPhone(phone){
    const validPhone = /^\d\d\d-\d\d\d-\d\d\d\d$/

    return validPhone.test(phone)
}

app.listen(port)