// Banco de dados

export const loadDB = async () => {
    try {
        const response = await fetch("db.json");
        if (!response.ok) {
            throw new Error(`Erro ao carregar o banco de dados: ${response.statusText}`);
        }

        const db = await response.json();

        return db;
    } catch (error) {
        console.error("Erro ao carregar o banco de dados:", error);
    }
}
