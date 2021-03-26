const PORT = 8080

async function getLibraries() {
    if (navigator.geolocation) {
        await navigator.geolocation.getCurrentPosition(position => {
             const lat = position.coords.latitude;
             const lon = position.coords.longitude;
             const data = {
                 lat: lat,
                 lon: lon
             }
             const options = {
                 method: 'GET',
                 mode: 'no-cors',
                 headers: {
                    'Content-Type': 'application/json'
                    // 'Content-Type': 'application/x-www-form-urlencoded',
                  }
                }
             fetch(`http://localhost:${PORT}/nearby-library?lat=${lat}&lon=${lon}`, options).then(result => {
                 console.log(result);
             })
        })
    } else {
        return null
    }
    
}

// console.log(getLibraries());

const lib = [
    {
        "libraryName": "Shree Ram",
        "address": "dharoi colony",
        "city": "visnagar",
        "zipcode": "384315",
        "maxSeats": 50,
        "reservedSeats": null,
        "availableSeats": null,
        "lat": 23.693911247414622,
        "lon": 72.53610456623701,
        "booksInLibrary": {
            "You can win": {
                "newBook": {
                    "bookName": "You can win",
                    "authorName": "Shiv Khera",
                    "price": 320,
                    "pages": 260
                },
                "quantity": 4
            }
        },
        "libraryUID": null,
        "admin": {
            "userName": "jenilpatel",
            "password": "jenil",
            "phoneNumber": "9104563659",
            "email": "jenill@patel.com",
            "reservedBooks": null
        }
    }
];

var i = 1;
var table = document.getElementById("librariesTable");
lib.forEach(library => {

// Create an empty <tr> element and add it to the 1st position of the table:
var row = table.insertRow(1);

// Insert new cells (<td> elements) at the 1st and 2nd position of the "new" <tr> element:
var cell1 = row.insertCell(0);
var cell2 = row.insertCell(1);
var cell3 = row.insertCell(2);
var cell4 = row.insertCell(3);
// Add some text to the new cells:

cell1.innerHTML = i++;
cell2.innerHTML = library.libraryName;
cell3.innerHTML = library.address;
cell4.innerHTML = library.admin.userName;

    // $(`#librariesTable > tbody:last-child').append('<tr>${}</tr><tr>${}</tr><tr>${}</tr>`);
});