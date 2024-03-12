// function plane(make, model, year){
//     this.make = make;
//     this.model = model;
//     this.year = year;
// }
class plane {
    make;
    model;
    year;

    constructor(make, model,year){
        this.make = make;
        this.model = model;
        this.year = year;
    }
}
let plane1 = new plane("Boeing", "747", 2000);

plane1.color = "red";

plane.prototype.price = 1000;



for (var variable in plane1){
    document.write(variable +": "+plane1[variable]+"<br>");
}
plane.prototype.mileage = function(){return (2024-this.year)*1000};

document.write(plane1.mileage());