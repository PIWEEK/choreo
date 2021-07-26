import { useState } from 'react'
import { useHistory } from "react-router-dom";
import './Home.css';

const Home = () => {
  const [codeForm, setCodeForm] = useState('')
  const history = useHistory();
  return (
    <div className="Home">
      <header className="header">
        <div className="logo">
          <img src="./img/logo_choreo.png" alt="Logo Choreo" />
        </div>
        <div className="family-img">
          <img src="./img/family.jpeg" alt="Logo Choreo" />
        </div>
      </header>
      <div className="grid">
        <ul className="features">
          <li>Eliminar los conflictos por las tareas domesticas</li>
          <li>Visibilizar la carga física y mental</li>
          <li>Conseguir un reparto de tareas más justo y equilibrado</li>
        </ul>
        <div className="forms">
          <label>Escribe el código de acceso a tu tablero</label>
          <div className="code-form">
            <input type="text" value={codeForm} onChange={(e) => setCodeForm(e.target.value)} />
            <button onClick={()=>history.push(`board/${codeForm}`)}>Entrar</button>
          </div>
          <div className="separator-container">
            <span className="separator">o</span>
          </div>
          <button onClick={()=>history.push("/new-board")}>Crear un tablero nuevo</button>
        </div>
      </div>
    </div>
  );
}

export default Home;