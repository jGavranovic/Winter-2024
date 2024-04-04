const express = require('express')
const app = express()

// Route to handle query paramters
app.get('/search', (req, res) => {
    const searchTerm = req.query.q;
    const term2 = req.query.p;
    res.send(`Searching for: ${searchTerm} as well as ${term2}`);
});

// Route with URL parameters
app.get('/users/:id', (req, res) => {
    const userId = req.params.id;
    // Fetch user details based on the URL parameter
    //res.send(`Fetching user with ID: ${userId} ${req.params.lastName}`);
    

    
})
//Start the server
const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
})