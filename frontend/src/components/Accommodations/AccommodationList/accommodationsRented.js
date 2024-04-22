import React from 'react';
import AccommodationTerm from "../AccommodationTerm/accommodationTerm";
import {Link} from "react-router-dom";

class AccommodationsRented extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className={"container mm-4 mt-5"}>
                <div className={"row"}>
                    <div className={"row"}>
                        <table className={"table table-striped text-center"}>
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Category</th>
                                <th>Host</th>
                                <th>Number of Rooms</th>
                                <th>Rented</th>
                            </tr>
                            </thead>
                            <tbody>
                            {/*{accommodations}*/}
                            {this.props.accommodations.map((term,index) => {
                                if (term.rented){
                                    return (
                                        <AccommodationTerm term={term}
                                                           onRent={this.props.onRent}
                                        rowNumber={index+1}/>
                                    )
                                }
                                else{
                                    return null;
                                }

                            })}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        )
    }
}

export default AccommodationsRented;