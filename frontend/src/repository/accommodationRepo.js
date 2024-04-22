import axios from '../custom-axios/axios'

const AccommodationService = {
    fetchAccommodations: () => {
        return axios.get("/accommodation");
    },
    fetchAccommodationsRented: () => {
        return axios.get("/accommodation/rented");
    },
    fetchAccommodationsUnRented: () => {
        return axios.get("/accommodation/unrented");
    },
    fetchCountries: () => {
        return axios.get("/country");
    },
    fetchHosts: () => {
        return axios.get("/host");
    },
    fetchCategories: () => {
        return axios.get("/category")
    },
    deleteAccommodation: (id) => {
        return axios.delete(`/accommodation/delete/${id}`);
    },
    addAccommodation: (name, category, host, numRooms) => {
        return axios.post("/accommodation/add", {
            "name": name,
            "category": category,
            //"host": host,
            "host":{
                "id":host
            },
            "numRooms": numRooms
        }).catch(error => {
            // Handle error
            console.error("Error adding accommodation!!!!!!!!!!!!!:", error);
            throw error; // Rethrow the error to propagate it to the caller
        });
    },
    editAccommodation: (id, name, category, host, numRooms) => {
        return axios.put(`/accommodation/edit/${id}`, {
            "name": name,
            "category": category,
            //"host": host,
            "host":{
                "id":host
            },
            "numRooms": numRooms
        });
    },
    getAccommodation: (id) => {
        return axios.get(`/accommodation/${id}`);
    },
    rentAccommodation: (id) =>{
        return axios.put(`/accommodation/rent/${id}`)
    }
    // login: (username, password) => {
    //     return axios.post("/login", {
    //         "username": username,
    //         "password": password
    //     });
    // },
}

export default AccommodationService;
