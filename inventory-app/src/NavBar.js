import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import NewConnection from "./NewConnection";
import "./NavBar.css";
import ImeiManager from "./ImeiManager";
import Records from "./Records";
import ChangeProvider from "./ChangeProvider";
import ReplaceSim from "./ReplaceSim";
import InactiveRecords from "./InactiveRecords";
import ReservationRecords from "./ReservationRecords";

export default function NavBar() {
    return (
        <>
            <BrowserRouter>
                <div>
                    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                        <ul className="nav justify-content-centre">
                            <li>
                                <div className="btn-group">
                                <Link className="nav-link dropdown" role="link" data-bs-toggle="dropdown" aria-expanded="false"> Prepaid </Link>
                                <ul className="dropdown-menu">
                                    <li><Link to="/prepaid" className="dropdown-item" >Buy a new Connection</Link></li>
                                    <li><Link to="/replace-sim-prepaid" className="dropdown-item">Replace Sim</Link></li>
                                    <li><hr className="dropdown-divider" /></li>
                                    <li><Link to="/changeprovider-prepaid" className="dropdown-item" >Change Provider</Link></li>
                                </ul>
                                </div>
                            </li>
                            <li>
                                <div className="btn-group">
                                <Link className="nav-link dropdown" role="link" data-bs-toggle="dropdown" aria-expanded="false"> Postpaid </Link>
                                <ul className="dropdown-menu">
                                    <li><Link to="/postpaid" className="dropdown-item" >Buy a new Connection</Link></li>
                                    <li><Link to="/replace-sim-postpaid" className="dropdown-item">Replace Sim</Link></li>
                                    <li><hr className="dropdown-divider" /></li>
                                    <li><Link to="/changeprovider-postpaid" className="dropdown-item" >Change Provider</Link></li>
                                </ul>
                                </div>
                            </li>
                            <li>
                                <div className="btn-group">
                                <Link  className="nav-link dropdown" role="link" data-bs-toggle="dropdown" aria-expanded="false"> Records </Link>
                                <ul className="dropdown-menu">
                                    <li><Link to="/prepaid-records" className="dropdown-item" >prepaid</Link></li>
                                    <li><Link to="/postpaid-records" className="dropdown-item" >postpaid</Link></li>
                                    <li><hr className="dropdown-divider" /></li>
                                    <li><Link to="/inactive-numbers" className="dropdown-item" >Inactive Numbers</Link></li>
                                    <li><Link to="/reserved-numbers" className="dropdown-item" >Reserved Numbers</Link></li>
                                </ul>
                                </div>
                            </li>
                        </ul>
                    </nav>
                    <hr />
                    <Routes>
                        <Route path="prepaid" element={<NewConnection connection={{ type: 'prepaid'}}/>} />
                        <Route path="postpaid" element={<NewConnection connection={{ type: 'postpaid'}} />} />
                        <Route path="changeProvider-prepaid" element={<ChangeProvider connection={{type : 'prepaid'}}/>} />
                        <Route path="changeProvider-postpaid" element={<ChangeProvider connection={{type : 'postpaid'}}/>} />
                        <Route path="prepaid-records" element={<Records connection={{type : 'prepaid'}}/>} />
                        <Route path="postpaid-records" element={<Records connection={{type : 'postpaid'}}/>} />
                        <Route path="inactive-numbers" element={<InactiveRecords />} />
                        <Route path="replace-sim-prepaid" element={<ReplaceSim connection={{type : 'prepaid'}}/>} />
                        <Route path="replace-sim-postpaid" element={<ReplaceSim connection={{type : 'postpaid'}}/>} />
                        <Route path="reserved-numbers" element={<ReservationRecords />} />
                        <Route path="insert-sim" element={<ImeiManager />}/>
                    </Routes>
                </div>
            </BrowserRouter>
        </>
    );
}