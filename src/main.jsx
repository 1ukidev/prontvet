import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import App from "./App.jsx";
import Login from "./Login.jsx";

const rotas = createBrowserRouter([
    { path: '/', element: <Login /> },
    { path: "/app", element: <App /> }
]);

createRoot(document.getElementById("root")).render(
    <StrictMode>
        <RouterProvider router={rotas} />
    </StrictMode>
);
