import axiosInstance from "../api/axiosConfig";

const getAllAppointments = async () => {
    const response = await axiosInstance.get("/appointments");
    return response.data;
};

const getAppointmentById = async (id) => {
    const response = await axiosInstance.get(`/appointments/${id}`);
    return response.data;
};

const bookAppointment = async (appointmentData) => {
    const response = await axiosInstance.post("/appointments", appointmentData);
    return response.data;
};

const appointmentService = {
    getAllAppointments,
    getAppointmentById,
    bookAppointment,
};

export default appointmentService;