import axiosInstance from "../api/axiosConfig";

const getAllVehicles = async () => {
    const response = await axiosInstance.get("/vehicles");
    return response.data;
};

const getVehicleById = async (id) => {
    const response = await axiosInstance.get(`/vehicles/${id}`);
    return response.data;
};

const addVehicle = async (vehicleData) => {
    const response = await axiosInstance.post("/vehicles", vehicleData);
    return response.data;
};

const vehicleService = {
    getAllVehicles,
    getVehicleById,
    addVehicle,
};

export default vehicleService;