function addNumbers(nums) {
    let sum = 0;
    nums.forEach((num) => sum+=num);
    return sum;
}

function findMaxNumber() {

}

function addOnlyNumbers(list){
    let sum = 0;
    list.forEach((entry) => sum+= isNaN(parseFloat(entry))?0:parseFloat(entry));
    return sum;
}

function getDigits(string){
    let output = "";
    for (i=0; i<string.length;i++){
        if (string.substring(i,i+1).match(/\d/) != null)
            output+=string.substring(i,i+1);
    }
    return output;
}

function reverseString(string){
    let output = "";
    for (i=string.length-1;i>=0;i--){
        output+=string.charAt(i);
    }
    return output;
} 

function getCurrentDate(){
    const days = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
    const months = ['January', 'February', 'March', 'April', 'May','June','July','August','September','November','December']
    let currentDate = new Date();
    let output=days[currentDate.getDay()]+", "+months[currentDate.getMonth()]+" "+currentDate.getDate()+", "+currentDate.getFullYear();
    //return currentDate.toString().substring(0,currentDate.toString().indexOf("2024")+4);


    return output;
}

document.getElementById("addnums").innerHTML = "addNumbers([1,5,-2,7]) = "+addNumbers([1,5,-2,7]);

document.getElementById("addOnlyNum").innerHTML = "addOnlyNumbers([4,5,\"3.0 birds\", true, \"birds2\"]) = "+ addOnlyNumbers([4,5,"3.0 birds", true, "birds2"]);

document.getElementById("getDigits").innerHTML = "getDigits(\"h5dm-19n6\") = " + getDigits("h5dm-19n6");

document.getElementById("reverseString").innerHTML = "reveseString(\"element\") = "+reverseString("element");

document.getElementById("date").innerHTML = getCurrentDate();