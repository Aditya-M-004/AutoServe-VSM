import axiosInstance from "../api/axiosConfig";

const register = async (userData) => {
    const response = await axiosInstance.post("/auth/register", userData);
    return response.data;
};

const login = async (credentials) => {
    const response = await axiosInstance.post("/auth/login", credentials);

    if (response.data.success) {
        localStorage.setItem("token", response.data.data.token);
        localStorage.setItem("name", response.data.data.name);
        localStorage.setItem("email", response.data.data.email);
        localStorage.setItem("role", response.data.data.role);
    }

    return response.data;
};

const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("name");
    localStorage.removeItem("email");
    localStorage.removeItem("role");
};

const authService = {
    register,
    login,
    logout,
};

export default authService;