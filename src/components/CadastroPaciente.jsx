/* eslint-disable react/prop-types */
import "./CadastroPaciente.css";

const CadastroPaciente = ({ isOpen, onClose, onSubmit, pacienteData, onChange }) => {
    if (!isOpen) return null;

    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <form>
                    <input 
                        type="text" 
                        name="nome" 
                        placeholder="Nome" 
                        value={pacienteData.nome} 
                        onChange={onChange} 
                    />
                    <input 
                        type="text" 
                        name="especie" 
                        placeholder="Espécie" 
                        value={pacienteData.especie} 
                        onChange={onChange} 
                    />
                    <input 
                        type="number" 
                        name="idade" 
                        placeholder="Idade" 
                        value={pacienteData.idade} 
                        onChange={onChange} 
                    />
                    <input 
                        type="number" 
                        name="peso" 
                        placeholder="Peso" 
                        value={pacienteData.peso} 
                        onChange={onChange} 
                    />
                    <textarea 
                        name="descricao" 
                        placeholder="Descrição" 
                        value={pacienteData.descricao} 
                        onChange={onChange} 
                    />
                    <button type="button" onClick={onSubmit}>Salvar</button>
                    <button type="button" onClick={onClose}>Cancelar</button>
                </form>
            </div>
        </div>
    );
};

export default CadastroPaciente;
