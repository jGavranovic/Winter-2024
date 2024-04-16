const express = require('express');
const app = express();

app.set("view engine", "ejs")

app.get("/", (req,res) => {
    res.render('part1')
})

app.listen(3000, () => {
    console.log("Server started at http://localhost:3000");
})

app.get('/findSummation', (req ,res)=>{
    res.send(200, findSummation(parseInt(req.query.sumInput)))
})

app.get('/upperCaseFirstandLast', (req ,res)=>{
    res.send(200, uppercaseFirstandLast(req.query.uppercaseInput))
})

app.get('/findAverageAndMedian', (req ,res)=>{
    let input = req.query.findAverageAndMedian.split(" ");
    var nums = new Array(input.length)
    for (i=0;i<nums.length;i++){
        nums[i] = parseFloat(input[i])
    }
    res.send(200, findAverageAndMedian(nums))
})

app.get('/find4Digits', (req,res)=>{
    res.send(200, find4Digits(req.query.find4Digits))
})

function findSummation(num){
    let sum = 0;
    for (i=num;i>=1;i--){
        sum+=i;
    }
    return sum;
}

function uppercaseFirstandLast(string){
    let splitString = string.split(" ");
    for (i =0; i<splitString.length;i++){
        if (splitString[i].length <=1){
            splitString[i] =  splitString[i].toUpperCase();
        }else {
            splitString[i] = splitString[i][0].toUpperCase() + splitString[i].substring(1,splitString[i].length-1) + splitString[i][splitString[i].length-1].toUpperCase();
        }
        }

    return splitString.join(" ");
}

function findAverageAndMedian(nums){
    let average = 0, median = 0;

    nums.forEach( (num) => {
        average += num;
    })

    average /= nums.length;

    nums.sort((a,b) => a-b)

    if (nums.length % 2 == 1){
        median = nums[Math.floor(nums.length/2) ];
    } else {
        median = (nums[Math.floor(nums.length / 2) -1 ] + nums[Math.floor(nums.length / 2) ]) / 2;
    }

    return {"average": average, "median":median};
}

function find4Digits(string){
    let nums = string.split(" ");
    const fourDigits = /^\d{4}$/;

    // nums.forEach((num) => {
    //     if (fourDigits.test(num)){
    //         return num;
    //     }
    // })

    for (i=0;i<nums.length;i++){
        if (fourDigits.test(nums[i]))
            return nums[i];
    }

    return false;

}
