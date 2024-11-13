import { useState, useEffect } from "react";
import CadastroPaciente from "./CadastroPaciente";
import "./PacienteList.css";

const URL = "http://localhost:3000/pacientes";

const PacienteList = () => {
    const [pacientes, setPacientes] = useState([]);
    const [showCadastroPaciente, setShowCadastroPaciente] = useState(false);
    const [novoPaciente, setNovoPaciente] = useState({
        nome: "",
        especie: "",
        idade: "",
        peso: "",
        descricao: ""
    });

    const getPacientes = () => {
        fetch(URL)
            .then((response) => response.json())
            .then((data) => {
                setPacientes(data);
            })
            .catch((error) => {
                console.error(error);
                setPacientes([]);
            });
    };

    const postPaciente = (paciente) => {
        fetch(URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(paciente)
        })
            .then(() => getPacientes())
            .catch((error) => console.error(error));
    }

    const putPaciente = (id, paciente) => {
        fetch(`${URL}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(paciente)
        })
            .then(() => getPacientes())
            .catch((error) => console.error(error));
    }

    const deletePaciente = (id) => {
        fetch(`${URL}/${id}`, { method: "DELETE" })
            .then(() => getPacientes())
            .catch((error) => console.error(error));
    }

    const handleAddPaciente = () => {
        setNovoPaciente({
            nome: "",
            especie: "",
            idade: "",
            peso: "",
            descricao: ""
        });
        setShowCadastroPaciente(true);
    };

    const handleEditPaciente = (id) => {
        const paciente = pacientes.find((paciente) => paciente.id === id);
        setNovoPaciente(paciente);
        setShowCadastroPaciente(true);
    }

    const handleDeletePaciente = (id) => {
        const confirmDelete = window.confirm("Tem certeza de que deseja excluir este paciente?");
        if (confirmDelete) {
            deletePaciente(id);
        }
    }

    const handleCloseCadastroPaciente = () => {
        setShowCadastroPaciente(false);
        setNovoPaciente({
            nome: "",
            especie: "",
            idade: "",
            peso: "",
            descricao: ""
        });
    };

    const handleChange = (e) => {
        setNovoPaciente({
            ...novoPaciente,
            [e.target.name]: e.target.value
        });
    };

    const validate = () => {
        if (!novoPaciente.nome || !novoPaciente.especie || !novoPaciente.idade || !novoPaciente.peso || !novoPaciente.descricao) {
            alert("Preencha todos os campos!");
            return false;
        }
        return true;
    }

    const handleSubmit = () => {
        if (!validate()) return;
        if (novoPaciente.id) {
            putPaciente(novoPaciente.id, novoPaciente);
        } else {
            postPaciente(novoPaciente);
        }
        handleCloseCadastroPaciente();
    };

    useEffect(() => {
        getPacientes();
    }, []);

    return (
        <div className="paciente-list">
            <h1>Lista de Pacientes</h1>
            <div className="paciente-cards">
                {pacientes.map((paciente) => (
                    <div key={paciente.id} className="paciente-card">
                        <h2>{paciente.nome}</h2>
                        <p><strong>Espécie:</strong> {paciente.especie}</p>
                        <p><strong>Idade:</strong> {paciente.idade}</p>
                        <p><strong>Peso:</strong> {paciente.peso}</p>
                        <p><strong>Descrição:</strong> {paciente.descricao}</p>
                        <div className="paciente-actions">
                            <button className="edit-button" onClick={() => handleEditPaciente(paciente.id)}>Editar</button>
                            <button className="delete-button" onClick={() => handleDeletePaciente(paciente.id)}>Remover</button>
                        </div>
                    </div>
                ))}
            </div>
            <button className="add-button" onClick={handleAddPaciente}>Cadastrar</button>

            <CadastroPaciente 
                isOpen={showCadastroPaciente} 
                onClose={handleCloseCadastroPaciente} 
                onSubmit={handleSubmit} 
                pacienteData={novoPaciente} 
                onChange={handleChange} 
            />
        </div>
    );
};

export default PacienteList;
