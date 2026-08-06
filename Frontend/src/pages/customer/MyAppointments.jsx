import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { appointmentService } from "../../services/appointmentService";
import StatusBadge from "../../components/StatusBadge";
import LoadingSpinner from "../../components/LoadingSpinner";
import { formatDate } from "../../utils/formatters";

const MyAppointments = () => {
  const [appointments, setAppointments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [filterStatus, setFilterStatus] = useState("ALL");

  useEffect(() => {
    fetchAppointments();
  }, []);

  const fetchAppointments = async () => {
    setLoading(true);
    try {
      const res = await appointmentService.getAppointments();
      const data = res.data || res || [];
      setAppointments(Array.isArray(data) ? data : []);
    } catch (error) {
      console.error("Failed to load appointments", error);
    } finally {
      setLoading(false);
    }
  };

  const filteredAppointments = appointments.filter((app) => {
    if (filterStatus === "ALL") return true;
    return app.status === filterStatus;
  });

  if (loading) return <LoadingSpinner text="Fetching your appointments..." />;

  return (
    <div>
      <div className="d-flex align-items-center justify-content-between mb-4 flex-wrap gap-3">
        <div>
          <h3 className="fw-extrabold text-dark m-0">
            My Service Appointments
          </h3>
          <p className="text-muted small m-0">
            Track live progress and service history for your vehicles
          </p>
        </div>
        <Link
          to="/customer/book-appointment"
          className="btn btn-primary-custom"
        >
          <i className="bi bi-plus-circle me-1"></i> Book New Service
        </Link>
      </div>

      {/* Filter Bar */}
      <div className="card glass-card border-0 p-3 mb-4">
        <div className="d-flex align-items-center gap-2 flex-wrap">
          <span className="fw-semibold text-muted small me-2">
            Filter by Status:
          </span>
          {[
            "ALL",
            "PENDING",
            "ACCEPTED",
            "IN_PROGRESS",
            "COMPLETED",
            "CANCELLED",
          ].map((status) => (
            <button
              key={status}
              className={`btn btn-sm ${
                filterStatus === status
                  ? "btn-primary shadow-sm"
                  : "btn-outline-secondary border-0"
              } rounded-pill px-3`}
              onClick={() => setFilterStatus(status)}
            >
              {status.replace("_", " ")}
            </button>
          ))}
        </div>
      </div>

      {/* Appointments Table / Cards */}
      {filteredAppointments.length === 0 ? (
        <div className="card glass-card border-0 p-5 text-center">
          <i className="bi bi-calendar-x fs-1 text-muted mb-2"></i>
          <h5 className="fw-bold text-dark">No Appointments Found</h5>
          <p className="text-muted">
            No appointments match the selected filter condition.
          </p>
        </div>
      ) : (
        <div className="card glass-card border-0 p-4 shadow-sm">
          <div className="table-responsive">
            <table className="table table-custom mb-0">
              <thead>
                <tr>
                  <th>Appt ID</th>
                  <th>Vehicle Details</th>
                  <th>Date</th>
                  <th>Service Note</th>
                  <th>Assigned Mechanic</th>
                  <th>Status</th>
                  <th className="text-end">Action</th>
                </tr>
              </thead>
              <tbody>
                {filteredAppointments.map((app) => {
                  console.log("Appointment:", app);

                  return (
                    <tr key={app.id || app.appointmentId}>
                      <td className="fw-bold text-primary">
                        #{app.id || app.appointmentId}
                      </td>
                      <td>
                        <div className="fw-bold text-dark">
                          {app.vehicle?.make || app.vehicleMake}{" "}
                          {app.vehicle?.model || app.vehicleModel}
                        </div>
                        <small className="font-monospace text-muted">
                          {app.vehicle?.licensePlate || app.vehicleNumber}
                        </small>
                      </td>
                      <td>{formatDate(app.appointmentDate || app.date)}</td>
                      <td>
                        <span
                          className="text-truncate d-inline-block"
                          style={{ maxWidth: "200px" }}
                          title={app.problemDescription}
                        >
                          {app.problemDescription}
                        </span>
                      </td>
                      <td>
                        {app.mechanic ? (
                          <div className="d-flex align-items-center gap-1">
                            <i className="bi bi-person-badge text-primary"></i>
                            <span>{app.mechanic.name || app.mechanicName}</span>
                          </div>
                        ) : (
                          <span className="text-muted fst-italic">
                            Pending Assignment
                          </span>
                        )}
                      </td>
                      <td>
                        <StatusBadge status={app.status} />
                      </td>
                      <td className="text-end">
                        {app.jobId && (
                          <Link
                            to={`/customer/jobcard/${app.jobId}`}
                            className="btn btn-outline-primary btn-sm me-2"
                          >
                            <i className="bi bi-file-text me-1"></i>
                            Job Card
                          </Link>
                        )}

                        {app.status === "COMPLETED" && app.jobId && (
                          <Link
                            to={`/customer/invoice/${app.jobId}`}
                            className="btn btn-success btn-sm"
                          >
                            <i className="bi bi-receipt me-1"></i>
                            View Invoice
                          </Link>
                        )}

                        {!app.jobId && (
                          <span className="text-muted small">Processing</span>
                        )}
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
};

export default MyAppointments;
