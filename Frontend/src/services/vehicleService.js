import axiosInstance from '../api/axiosInstance';

export const vehicleService = {
  // Get vehicles for logged in customer
  getVehicles: async () => {
    const response = await axiosInstance.get('/vehicles');
    return response.data;
  },

  // Get all registered vehicles (Admin access)
  getAllVehicles: async () => {
    const response = await axiosInstance.get('/vehicles/all');
    return response.data;
  },

  getVehicleById: async (id) => {
    const response = await axiosInstance.get(`/vehicles/${id}`);
    return response.data;
  },

  addVehicle: async (vehicleData) => {
    const response = await axiosInstance.post('/vehicles', vehicleData);
    return response.data;
  },

  updateVehicle: async (id, vehicleData) => {
    const response = await axiosInstance.put(`/vehicles/${id}`, vehicleData);
    return response.data;
  },

  deleteVehicle: async (id) => {
    const response = await axiosInstance.delete(`/vehicles/${id}`);
    return response.data;
  },
};
