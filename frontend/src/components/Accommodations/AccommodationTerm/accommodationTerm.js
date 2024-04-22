import React from "react";
import {Link} from "react-router-dom";
import accommodations from "../AccommodationList/accommodations";

const accommodationTerm = (props) => {
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.category}</td>
            <td>{props.term.host.name} {props.term.host.surname}</td>
            <td>{props.term.numRooms}</td>
            <td>{props.term.rented ? 'true' : 'false'}</td>
            <td className={"text-right"}>
                {/*{!props.term.rented && (*/}
                {/*    <>*/}
                {/*        <a title={"Delete"} className={"btn btn-danger me-3"}*/}
                {/*           onClick={() => props.onDelete(props.term.id)}*/}
                {/*        >Delete</a>*/}

                {/*        <Link className={"btn btn-info me-3"} to={`/accommodations/edit/${props.term.id}`}*/}
                {/*              onClick={() => props.onEdit(props.term.id)}*/}
                {/*        >Edit</Link>*/}
                {/*    </>*/}
                {/*)}*/}
                {/*<a title={"Rent"} className={"btn btn-success"}*/}
                {/*onClick={()=>props.onRent(props.term.id)}>Rent</a>*/}

                {!props.term.rented ? (
                        <>
                            <a title={"Delete"} className={"btn btn-danger me-3"}
                               onClick={() => props.onDelete(props.term.id)}
                            >Delete</a>

                            <Link className={"btn btn-info me-3"} to={`/accommodations/edit/${props.term.id}`}
                                  onClick={() => props.onEdit(props.term.id)}
                            >Edit</Link>
                            <a title={"Rent"} className={"btn btn-success"} onClick={() => props.onRent(props.term.id)}>Rent</a>
                        </>
                ) : (
                    <a title={"Unrent"} className={"btn btn-warning"} onClick={() => props.onRent(props.term.id)}>Unrent</a>
                )}
            </td>
        </tr>
    )
}

export default accommodationTerm;