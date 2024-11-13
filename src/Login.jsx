import { useNavigate } from "react-router-dom";
import "./Login.css";

const Login = () => {
    const navigate = useNavigate();

    const handleSubmit = (event) => {
        event.preventDefault();
        navigate("/app");
    }

    return (
        <>
            <div className="login">
                <form onSubmit={handleSubmit} className="login-form">
                    <label>E-mail:</label>
                    <input type="email" name="email" required />
                    
                    <label>Senha:</label>
                    <input type="password" name="senha" required />
                    
                    <button type="submit">Login</button>
                </form>
            </div>
        </>
    );
}

export default Login;
