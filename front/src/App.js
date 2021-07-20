import logo from './logo.svg';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1 className="logo"><span>Choreo</span></h1>
        <img src="./img/family.jpg" alt="familia abstracta" />
        <ul className="features">
          <li>Eliminar los conflictos por las tareas domesticas</li>
          <li>Visibilizar la carga física y mental</li>
          <li>Conseguir un reparto de tareas más justo y equilibrado</li>
        </ul>
      </header>
      <div className="container">
        <label>Escribe el código de acceso a tu tablero</label>
        
        <div className="code-form">
          <input type="text"/>
          <button>Entrar</button>
        </div>
        <hr/>
        <button>Crear un tablero nuevo</button>
      </div>
    </div>
  );
}

export default App;
