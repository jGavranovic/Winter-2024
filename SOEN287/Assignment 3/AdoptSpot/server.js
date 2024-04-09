const express = require('express')
const app = express()
const path = require('path')
const fs = require('fs')
app.use(express.static(path.join(__dirname,'public')))
app.set('view engine','ejs')

app.get ('/', (req,res)=>{
    res.render('index')
})
app.get('/:page', (req,res)=> {
    res.render(req.params.page)
})

fs.writeFileSync("login.txt","jinxqc:ggetgot")
text = fs.readFileSync('login.txt')
fs.writeFileSync('login.txt',text+'\nbootinikie:leagueisfun')

app.listen(3000)