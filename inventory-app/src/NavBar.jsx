import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import NewConnection from "./NewConnection";
import "./NavBar.scss";
import ImeiManager from "./ImeiManager";
import Records from "./Records";
import ChangeProvider from "./ChangeProvider";
import ReplaceSim from "./ReplaceSim";
import InactiveRecords from "./InactiveRecords";
import ReservationRecords from "./ReservationRecords";
import { useEffect, useRef, useState } from "react";
import Swagger from "./Swagger";


export default function NavBar() {

    const [prepaidDropdownOpen, setPrepaidDropdownOpen] = useState(false);
    const [postpaidDropdownOpen, setPostpaidDropdownOpen] = useState(false);
    const [recordsDropdownOpen, setRecordsDropdownOpen] = useState(false);

    const dropdownRefs = {
        prepaid: useRef(),
        postpaid: useRef(),
        records: useRef(),
    };

    const handleDropdownClick = (dropdownName) => {
        if (dropdownName === "prepaid") {
            setPrepaidDropdownOpen(!prepaidDropdownOpen);
            setPostpaidDropdownOpen(false);
            setRecordsDropdownOpen(false);
        } else if (dropdownName === "postpaid") {
            setPostpaidDropdownOpen(!postpaidDropdownOpen);
            setPrepaidDropdownOpen(false);
            setRecordsDropdownOpen(false);
        } else if (dropdownName === "records") {
            setRecordsDropdownOpen(!recordsDropdownOpen);
            setPrepaidDropdownOpen(false);
            setPostpaidDropdownOpen(false);
        }
    };

    useEffect(() => {
        const handleClickOutside = (event) => {
            if (
                dropdownRefs.prepaid.current &&
                !dropdownRefs.prepaid.current.contains(event.target)
            ) {
                setPrepaidDropdownOpen(false);
            }

            if (
                dropdownRefs.postpaid.current &&
                !dropdownRefs.postpaid.current.contains(event.target)
            ) {
                setPostpaidDropdownOpen(false);
            }

            if (
                dropdownRefs.records.current &&
                !dropdownRefs.records.current.contains(event.target)
            ) {
                setRecordsDropdownOpen(false);
            }
        };

        document.addEventListener("click", handleClickOutside);

        return () => {
            document.removeEventListener("click", handleClickOutside);
        };
    }, [dropdownRefs.prepaid, dropdownRefs.postpaid, dropdownRefs.records]);

    return (
        <BrowserRouter>
            <div className="container">
                <nav className="navbar">
                    <ul className="nav-list">
                        <li>
                            <Link to="/" className="nav-link">
                                <img src="logo.svg" alt="kuchbhi" />
                            </Link>
                        </li>
                        <li>
                            <div className="btn-group" ref={dropdownRefs.prepaid}>
                                <Link
                                    className="nav-link"
                                    onClick={() => handleDropdownClick("prepaid")}
                                >
                                    Prepaid
                                </Link>
                                {prepaidDropdownOpen && (
                                    <ul className="dropdown-menu">
                                        <li>
                                            <Link to="/prepaid" className="dropdown-item">
                                                Buy a new Connection
                                            </Link>
                                        </li>
                                        <li>
                                            <Link to="/replace-sim-prepaid" className="dropdown-item">
                                                Replace Sim
                                            </Link>
                                        </li>
                                        <li>
                                            <hr className="dropdown-divider" />
                                        </li>
                                        <li>
                                            <Link to="/change-provider-prepaid" className="dropdown-item">
                                                Change Provider
                                            </Link>
                                        </li>
                                    </ul>
                                )}
                            </div>
                        </li>
                        <li>
                            <div className="btn-group" ref={dropdownRefs.postpaid}>
                                <Link
                                    className="nav-link"
                                    onClick={() => handleDropdownClick("postpaid")}
                                >
                                    Postpaid
                                </Link>
                                {postpaidDropdownOpen && (
                                    <ul className="dropdown-menu">
                                        <li>
                                            <Link to="/postpaid" className="dropdown-item">
                                                Buy a new Connection
                                            </Link>
                                        </li>
                                        <li>
                                            <Link to="/replace-sim-postpaid" className="dropdown-item">
                                                Replace Sim
                                            </Link>
                                        </li>
                                        <li>
                                            <hr className="dropdown-divider" />
                                        </li>
                                        <li>
                                            <Link to="/change-provider-postpaid" className="dropdown-item">
                                                Change Provider
                                            </Link>
                                        </li>
                                    </ul>
                                )}
                            </div>
                        </li>
                        <li>
                            <div className="btn-group" ref={dropdownRefs.records}>
                                <Link
                                    className="nav-link"
                                    onClick={() => handleDropdownClick("records")}
                                >
                                    Records
                                </Link>
                                {recordsDropdownOpen && (
                                    <ul className="dropdown-menu">
                                        <li>
                                            <Link to="/prepaid-records" className="dropdown-item">
                                                prepaid
                                            </Link>
                                        </li>
                                        <li>
                                            <Link to="/postpaid-records" className="dropdown-item">
                                                postpaid
                                            </Link>
                                        </li>
                                        <li>
                                            <hr className="dropdown-divider" />
                                        </li>
                                        <li>
                                            <Link to="/inactive-numbers" className="dropdown-item">
                                                Inactive Numbers
                                            </Link>
                                        </li>
                                        <li>
                                            <Link to="/reserved-numbers" className="dropdown-item">
                                                Reserved Numbers
                                            </Link>
                                        </li>
                                    </ul>
                                )}
                            </div>
                        </li>
                        <li>
                            <Link to="/insert-sim" className="nav-link">
                                InsertSim
                            </Link>
                        </li>
                    </ul>
                </nav>
                <div className="content-container">
                    <Routes>
                        <Route path="prepaid" element={<NewConnection connection={{ type: 'prepaid' }} />} />
                        <Route path="postpaid" element={<NewConnection connection={{ type: 'postpaid' }} />} />
                        <Route path="change-provider-prepaid" element={<ChangeProvider connection={{ type: 'prepaid' }} />} />
                        <Route path="change-provider-postpaid" element={<ChangeProvider connection={{ type: 'postpaid' }} />} />
                        <Route path="prepaid-records" element={<Records connection={{ type: 'prepaid' }} />} />
                        <Route path="postpaid-records" element={<Records connection={{ type: 'postpaid' }} />} />
                        <Route path="inactive-numbers" element={<InactiveRecords />} />
                        <Route path="replace-sim-prepaid" element={<ReplaceSim connection={{ type: 'prepaid' }} />} />
                        <Route path="replace-sim-postpaid" element={<ReplaceSim connection={{ type: 'postpaid' }} />} />
                        <Route path="reserved-numbers" element={<ReservationRecords />} />
                        <Route path="insert-sim" element={<ImeiManager />} />
                        <Route path="swagger" element={<Swagger />} />
                    </Routes>
                </div>
                {/* <div className="image-container">
                    <ImageContainer />
                </div> */}
            </div>
        </BrowserRouter>
    );
}

