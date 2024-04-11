const express = require('express')
const app = express()
const path = require('path')
const fs = require('fs')
const session = require('express-session')
const check = require('syntax-error')
app.use(
    express.json(),
    express.urlencoded(),
    session({
        secret: 'WB5RJI9Rto',
        saveUninitialized: false
    })
)

let counter = 0;

app.use(express.static(path.join(__dirname,'public')))
app.set('view engine','ejs')

app.get ('/', (req,res)=>{
    res.render('index', {authenticated : req.session.authenticated})
})

app.get('/browse', (req,res) => {
    const pets = parsePets();
    const search = req.query
    res.render('browse',{pets: pets, search: search,authenticated : req.session.authenticated})
    // res.send(search)
})
app.get('/giveaway', (req,res)=> {
    if (!req.session.authenticated){
        // console.log(req.session)
        res.render('login', {taken:false, authenticated : req.session.authenticated})
    }else {
        // console.log(req.session)
        res.render('giveaway',{authenticated : req.session.authenticated})
    }
})
app.post('/giveaway', (req,res) => {

    const pet = req.body;
    const currentContent = fs.readFileSync('pets.txt')
    
    fs.writeFileSync('pets.txt',`${currentContent}\r\n${counter}:user:${pet.species}:${Array.isArray(pet.breed)?pet.breed[1]:pet.breed}:${pet.age}:${pet.sex}:${pet.getsAlong}:${pet.email}:${pet.comments}`)
    console.log(pet)
    res.render('giveaway')
})

app.post('/createaccount', (req, res) => {
    const account = req.body;
    if (alreadyExists(account, parseAccountList())) {
        res.render('createaccount',{taken: true, success: false, authenticated:req.session.authenticated})
    } else {
        addAccount(account)
        res.render('createaccount',{taken:false,success:true,authenticated:req.session.authenticated})
    }
})
app.post('/authenticate', (req,res)=>{
    const account = req.body;
    if (validLogin(account)){
        req.session.authenticated = true;
        res.render('giveaway',{authenticated : req.session.authenticated})
    } else {
        console.log('Invalid login, try again')
        res.render('login', {taken:true,authenticated:req.session.authenticated})
    }
    // res.send(Boolean(req.session.authenticated))
})

app.get('/logout', (req,res)=>{
    req.session.authenticated = false;
    res.render('index', {authenticated : req.session.authenticated})
})
app.get('/:page', (req,res)=> {
    res.render(req.params.page, {taken: false, success: false,authenticated:req.session.authenticated})
})

function parsePets() {
    const splitPetsNewline = fs.readFileSync('pets.txt').toString().split('\r\n')
    const splitPetsColon = new Array(splitPetsNewline.length)

    for (i=0;i<splitPetsNewline.length;i++){
        splitPetsColon[i] = splitPetsNewline[i].split(':')
    } 
    const pets = new Array(splitPetsColon.length)

    for (i=0;i<splitPetsColon.length;i++){
        pets[i] = {
            id: splitPetsColon[i][0],
            species : splitPetsColon[i][2],
            breed : splitPetsColon[i][3],
            age : splitPetsColon[i][4],
            sex : splitPetsColon[i][5],
            getsAlong : splitPetsColon[i][6].split(','),
            email: splitPetsColon[i][7],
            comments:splitPetsColon[i][8]
        }
    }

    return pets
}

function parseAccountList() {
    const splitNewline = fs.readFileSync('login.txt').toString().split('\n')
    const splitColon = new Array(splitNewline.length)

    for (i=0;i<splitNewline.length;i++)
        splitColon[i] = splitNewline[i].split(':')
    
    const accounts = new Array(splitColon.length)

    for (i=0;i<splitColon.length;i++){
        accounts[i] = {
            username : splitColon[i][0],
            password : splitColon[i][1]
        }
    }
    return accounts
}

function alreadyExists(account, accountList) {
    let match = false;
    accountList.forEach(function(accountEntry) {
        if (accountEntry.username.toLowerCase() == account.username.toLowerCase()){
            match = true;
        }
    })
    return match;
}

function addAccount(account) {
    const content = fs.readFileSync('login.txt')
    fs.writeFileSync('login.txt',`${content}\n${account.username}:${account.password}`)
}

function validLogin(account){
    let match = false;
    const accountList = parseAccountList()
    accountList.forEach(accountEntry => {
        if (accountEntry.username == account.username && accountEntry.password == account.password){
            match = true;
        }
    })
    return match;
}

app.listen(3000)