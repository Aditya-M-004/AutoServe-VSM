import { NavLink, useNavigate } from "react-router-dom";
import authService from "../services/authService";

const Navbar = () => {

    const navigate = useNavigate();

    const handleLogout = () => {
        authService.logout();
        navigate("/login");
    };

    return (

        <nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">

            <div className="container">

                <NavLink
                    className="navbar-brand fw-bold fs-4"
                    to="/dashboard"
                >
                    🚗 AutoServe
                </NavLink>

                <button
                    className="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                >
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div
                    className="collapse navbar-collapse"
                    id="navbarNav"
                >

                    <ul className="navbar-nav mx-auto">

                        <li className="nav-item">
                            <NavLink
                                to="/dashboard"
                                className="nav-link"
                            >
                                Dashboard
                            </NavLink>
                        </li>

                        <li className="nav-item">
                            <NavLink
                                to="/vehicles"
                                className="nav-link"
                            >
                                Vehicles
                            </NavLink>
                        </li>

                        <li className="nav-item">
                            <NavLink
                                to="/vehicles/add"
                                className="nav-link"
                            >
                                Add Vehicle
                            </NavLink>
                        </li>

                        <li className="nav-item">
                            <NavLink
                                to="/appointments"
                                className="nav-link"
                            >
                                Appointments
                            </NavLink>
                        </li>

                        <li className="nav-item">
                            <NavLink
                                to="/appointments/book"
                                className="nav-link"
                            >
                                Book Service
                            </NavLink>
                        </li>

                    </ul>

                    <div className="d-flex align-items-center">

                        <span className="text-white me-3 fw-semibold">

                            👋 {localStorage.getItem("name")}

                        </span>

                        <button
                            className="btn btn-outline-light btn-sm"
                            onClick={handleLogout}
                        >
                            Logout
                        </button>

                    </div>

                </div>

            </div>

        </nav>

    );

};

export default Navbar;