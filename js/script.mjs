// Inicializa o site

import { route } from "./routes.mjs";

window.addEventListener("hashchange", route);

route();
