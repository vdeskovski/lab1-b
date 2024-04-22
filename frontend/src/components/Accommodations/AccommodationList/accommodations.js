import React from 'react';
import AccommodationTerm from "../AccommodationTerm/accommodationTerm";
import {Link} from "react-router-dom";
import ReactPaginate from "react-paginate";

class Accommodations extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            page:0,
            size:5
        }
    }

    render() {
        const offset = this.state.size * this.state.page;
        const nextPageOffset = offset + this.state.size;
        const pageCount = Math.ceil(this.props.accommodations.length / this.state.size);
        const accommodations = this.getAccommodationsPage(offset, nextPageOffset);
        console.log(accommodations, pageCount)
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
                            {accommodations}
                            {/*{this.props.accommodations.map((term) => {*/}
                            {/*    if (!term.rented){*/}
                            {/*        return (*/}
                            {/*            <AccommodationTerm term={term}*/}
                            {/*                               onDelete={this.props.onDelete}*/}
                            {/*                               onEdit={this.props.onEdit}*/}
                            {/*                               onRent={this.props.onRent}/>*/}
                            {/*        )*/}
                            {/*    }*/}
                            {/*    else{*/}
                            {/*        return null;*/}
                            {/*    }*/}

                            {/*})}*/}
                            </tbody>
                        </table>
                    </div>
                    <div className="col mb-3">
                        <div className="row">
                            <div className="col-sm-12 col-md-12">
                                <Link className={"btn btn-block btn-dark w-100"} to={"/accommodations/add"}>Add new accommodation</Link>
                            </div>
                        </div>
                    </div>
                </div>
                <ReactPaginate previousLabel={"Back "}
                               nextLabel={"Next "}
                               breakLabel={<a href="/#">...</a>}
                               breakClassName={"break-me"}
                               pageClassName={"ml-1"}
                               pageCount={pageCount}
                               marginPagesDisplayed={2}
                               pageRangeDisplayed={5}
                               onPageChange={this.handlePageClick}
                               containerClassName={"pagination m-4 justify-content-center"}
                               activeClassName={"active"}
                               //
                               pageLinkClassName={"page-link"} // Add style to (1,2,3...)
                               previousClassName={"page-item"} // Add style to prev
                               previousLinkClassName={"page-link"} // Add style to prev
                               nextClassName={"page-item"} // Add style to next
                               nextLinkClassName={"page-link"} // Add style to next
                />
            </div>
        )
    }
    handlePageClick = (data) => {
        let selected = data.selected;
        //console.log(selected)
        this.setState({
            page: selected
        })
    }

    getAccommodationsPage = (offset, nextPageOffset) => {
        //console.log(offset, nextPageOffset)
        return this.props.accommodations.map((term, index) => {
            if (!term.rented){
                return (
                    <AccommodationTerm term={term}
                                       onDelete={this.props.onDelete}
                                       onEdit={this.props.onEdit}
                                       onRent={this.props.onRent}
                                        rowNumber={index+1}/>
                )
            }
            else{
                return null;
            }
        }).filter((product, index) => {
            return index >= offset && index < nextPageOffset;
        })
    }
}

export default Accommodations;