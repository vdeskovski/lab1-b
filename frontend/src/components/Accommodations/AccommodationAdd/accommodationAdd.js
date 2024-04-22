import React from "react";
import { useNavigate } from 'react-router-dom'; // redirekcija? napred, nazad
import accommodations from "../AccommodationList/accommodations";
const AccommodationAdd = (props) => {

    const history = useNavigate();
    const [formData, updateFormData] = React.useState({
        name: "",
        category: 0,
        host: 1,
        numRooms: 1,
        //manufacturer: 1
    }) // vo stateless komponenta, ke imam state

    const handleChange = (e) =>{ // e is event from input
        updateFormData({
            ...formData,
            [e.target.name]: e.target.value.trim()
        })
    }

    const onFormSubmit = (e) => {
        e.preventDefault(); // ne pravi post method, wait here
        const name = formData.name;
        const host = formData.host;
        const category = formData.category;
        const numRooms = formData.numRooms;

        //props.onAddAccommodation(name,host,category,numRooms)
        props.onAddAccommodation(name,category,host,numRooms)
        history("/accommodations") // vrati me tuka
    }

    return(
        <div className="row mt-5">
            <div className="col-md-5">
                <form onSubmit={onFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="name">Name</label>
                        <input type="text"
                               className="form-control"
                               id="name"
                               name="name"
                               required
                               placeholder="Enter a name"
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="numRooms">Number of rooms</label>
                        <input type="number"
                               className="form-control"
                               id="numRooms"
                               name="numRooms"
                               required
                               onChange={handleChange}
                        />
                    </div>
                    <div className="form-group">
                        <label>Category</label>
                        <select name="category" className="form-control" onChange={handleChange}>
                            {props.categories.map((term) =>
                                <option value={term}>{term}</option>
                            )}
                        </select>
                    </div>
                    <div className="form-group">
                        <label>Host</label>
                        <select name="host" className="form-control" onChange={handleChange}>
                            {props.hosts.map((term) =>
                                <option value={term.id}>{term.name} {term.surname}</option>
                            )}
                        </select>
                    </div>
                    <button id="submit" type="submit" className="btn btn-success mt-3">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default AccommodationAdd;