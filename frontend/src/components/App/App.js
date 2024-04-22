import './App.css';
import logo from '../../logo.svg'
import './App.css';
import {Component} from "react";
import {BrowserRouter as Router, Navigate, Redirect, Route, Routes} from 'react-router-dom'
import Header from "../header/header";
import Countries from "../Countries/countries";
import AccommodationService from "../../repository/accommodationRepo";
import Accommodations from "../Accommodations/AccommodationList/accommodations";
import Hosts from "../Hosts/HostList/hosts";
import Categories from "../Categories/categories";
import AccommodationAdd from "../Accommodations/AccommodationAdd/accommodationAdd";
import AccommodationEdit from "../Accommodations/AccommodationEdit/accommodationEdit";
import AccommodationsRented from "../Accommodations/AccommodationList/accommodationsRented";
class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
        accommodations: [],
        categories: [],
        countries: [],
        hosts: [],
        accommodationsRented: [],
        accommodationsUnRented:[],
        selectedAccommodation: {}
    }
  }
  render() {
    return (
        <Router>
          <Header/>
          <main>
            <div className="container">
                <Routes>
                    <Route path={"/countries"} element={ <Countries countries = {this.state.countries}/>}/> {/*lokacija kade shto ke se renderira i shto ke se renderira*/}

                    <Route path={"/hosts"} element={ <Hosts hosts = {this.state.hosts}/>}/>

                    <Route path={"/categories"} element={ <Categories categories = {this.state.categories}/>}/>

                    <Route path={"/accommodations/add"} element={<AccommodationAdd categories = {this.state.categories}
                                                                       hosts = {this.state.hosts} onAddAccommodation={this.addAccommodation}/>}/>
                    <Route path={"/accommodations/edit/:id"} element={<AccommodationEdit categories = {this.state.categories}
                                                                             hosts = {this.state.hosts}
                                                                             onEditAccommodation={this.editAccommodation} accommodation={this.state.selectedAccommodation}/>}/>
                    <Route path={"/accommodations"} element={
                        <Accommodations accommodations={this.state.accommodationsUnRented}
                                        onDelete={this.deleteAccommodation}
                                        onEdit={this.getAccommodation}
                                        onRent={this.rentAccommodation}/>}/>

                    <Route path={"/accommodations/rented"} element={
                        <AccommodationsRented accommodations={this.state.accommodationsRented}
                                        onRent={this.rentAccommodation}/>}/>
                    <Route path="*" element={<Navigate to="/accommodations" />} /> {/*ako site routes failnat*/}
                </Routes>
                </div>
          </main>
        </Router>
    );
  }
  componentDidMount() {
    this.fetchData()
  }

  fetchData = () => {
    this.loadAccommodations();
    this.loadAccommodationsRented();
    this.loadAccommodationsUnRented();
    this.loadCategories();
    this.loadCountries();
    this.loadHosts();
  }
  loadAccommodations = () => {
    AccommodationService.fetchAccommodations()
        .then((data) => {
          this.setState({
            accommodations: data.data
          })
        });
  }

    loadAccommodationsRented = () => {
        AccommodationService.fetchAccommodationsRented()
            .then((data) => {
                this.setState({
                    accommodationsRented: data.data
                })
            });
    }
    loadAccommodationsUnRented = () => {
        AccommodationService.fetchAccommodationsUnRented()
            .then((data) => {
                this.setState({
                    accommodationsUnRented: data.data
                })
            });
    }
    loadCountries = () => {
        AccommodationService.fetchCountries()
            .then((data) => {
                this.setState({
                    countries: data.data
                })
            });
    }
    loadHosts = () => {
        AccommodationService.fetchHosts()
            .then((data) => {
                this.setState({
                    hosts: data.data
                })
            });
    }

  loadCategories = () => {
    AccommodationService.fetchCategories()
        .then((data) => {
          this.setState({
            categories: data.data
          })
        });
  }
  getAccommodation = (id) => {
    AccommodationService.getAccommodation(id)
        .then((data) => {
          this.setState({
            selectedAccommodation: data.data
          })
        })
  }
  deleteAccommodation = (id) => {
    AccommodationService.deleteAccommodation(id)
        .then(() => {
            this.loadAccommodationsUnRented();
            this.loadAccommodationsRented();
            this.loadAccommodations();
        });
  }
  addAccommodation = (name, category, host, numRooms) => {
    AccommodationService.addAccommodation(name, category, host, numRooms)
        .then(() => {
            this.loadAccommodationsUnRented();
            this.loadAccommodationsRented();
            this.loadAccommodations();
        });
  }
  editAccommodation = (id,name, category, host, numRooms) => {
    AccommodationService.editAccommodation(id,name, category, host, numRooms)
        .then(() => {
            this.loadAccommodationsUnRented();
            this.loadAccommodationsRented();
            this.loadAccommodations();
        });
  }

  rentAccommodation = (id) =>{
      AccommodationService.rentAccommodation(id)
          .then(() => {
              this.loadAccommodationsUnRented();
              this.loadAccommodationsRented();
              this.loadAccommodations();
          })
  }

}


export default App;
