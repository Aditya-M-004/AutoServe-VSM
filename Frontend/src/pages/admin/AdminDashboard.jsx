import { useNavigate } from "react-router-dom";

const AdminDashboard = () => {

    const navigate = useNavigate();

    return (

        <div className="container-fluid">

            <div className="mb-4">

                <h2 className="fw-bold">
                    Admin Dashboard
                </h2>

                <p className="text-muted">
                    Welcome, {localStorage.getItem("name")}
                </p>

            </div>

            {/* Statistics */}

            <div className="row g-4 mb-5">

                <div className="col-md-3">
                    <div className="card shadow-sm border-0 rounded-4">
                        <div className="card-body text-center">
                            <h5>Total Customers</h5>
                            <h2 className="fw-bold text-primary">0</h2>
                        </div>
                    </div>
                </div>

                <div className="col-md-3">
                    <div className="card shadow-sm border-0 rounded-4">
                        <div className="card-body text-center">
                            <h5>Total Mechanics</h5>
                            <h2 className="fw-bold text-success">0</h2>
                        </div>
                    </div>
                </div>

                <div className="col-md-3">
                    <div className="card shadow-sm border-0 rounded-4">
                        <div className="card-body text-center">
                            <h5>Appointments</h5>
                            <h2 className="fw-bold text-warning">0</h2>
                        </div>
                    </div>
                </div>

                <div className="col-md-3">
                    <div className="card shadow-sm border-0 rounded-4">
                        <div className="card-body text-center">
                            <h5>Completed Jobs</h5>
                            <h2 className="fw-bold text-info">0</h2>
                        </div>
                    </div>
                </div>

            </div>

            {/* Quick Actions */}

            <div className="row g-4">

                <div className="col-md-4">

                    <div
                        className="card shadow-sm border-0 rounded-4 h-100"
                        style={{ cursor: "pointer" }}
                        onClick={() => navigate("/admin/mechanics/add")}
                    >

                        <div className="card-body text-center">

                            <h4>👨‍🔧</h4>

                            <h5 className="mt-3">
                                Add Mechanic
                            </h5>

                            <p className="text-muted">
                                Register a new mechanic.
                            </p>

                        </div>

                    </div>

                </div>

                <div className="col-md-4">

                    <div
                        className="card shadow-sm border-0 rounded-4 h-100"
                        style={{ cursor: "pointer" }}
                        onClick={() => navigate("/admin/mechanics")}
                    >

                        <div className="card-body text-center">

                            <h4>🛠</h4>

                            <h5 className="mt-3">
                                Manage Mechanics
                            </h5>

                            <p className="text-muted">
                                View and manage mechanics.
                            </p>

                        </div>

                    </div>

                </div>

                <div className="col-md-4">

                    <div
                        className="card shadow-sm border-0 rounded-4 h-100"
                        style={{ cursor: "pointer" }}
                        onClick={() => navigate("/admin/customers")}
                    >

                        <div className="card-body text-center">

                            <h4>👥</h4>

                            <h5 className="mt-3">
                                Manage Customers
                            </h5>

                            <p className="text-muted">
                                View registered customers.
                            </p>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    );

};

export default AdminDashboard;