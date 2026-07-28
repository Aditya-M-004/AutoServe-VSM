import Navbar from "./Navbar";

const Layout = ({ children }) => {
    return (
        <div className="bg-light min-vh-100">

            <Navbar />

            <main className="container py-4">
                {children}
            </main>

        </div>
    );
};

export default Layout;