import { useState } from 'react'
import './NewBoard.css';

const NewBoard = () => {
  const [step, setStep] = useState(0);
  const [boardName, setboardName] = useState('');
  const [profiles, setProfile] = useState([{avatar:Math.round(Math.random()*10) ,name: ''}])
  const addProfile = ()=> {
    console.log('voy')
    setProfile([...profiles, {avatar:Math.round(Math.random()*10) ,name: ''}])
  }
  return (
    <div className="NewBoard">
      <header className="header">
        {!!step && <span className="arrow">&lt;</span>}
        <h2>Crear un tablero</h2>
        {!step &&<span className="close">x</span>}
      </header>
      {!step &&<div className="grid">
        <label>Escribe el código de acceso a tu tablero</label>
        <input type="text" value={boardName} onChange={(e) => setboardName(e.target.value)} />
        <button disabled={boardName.length < 3 } onClick={()=>setStep(step+1)}>Guardar</button>
      </div>}
      {step===1 &&<div className="grid">
        <label>Escribe los nombres de las personas que van a hacer las tareas</label>
        <div className="profile-form">
          {
            profiles.map((item, index) => (
              <div className="card" key={index}>
                <div className="avatar"><img src={`./img/avatars/avatar${item.avatar}.png`}></img></div>
                <input type="text" />
              </div>
              )
            )}
          <button  onClick={addProfile}>Guardar</button>
        </div>
        <button disabled={boardName.length < 3 } onClick={()=>setStep(step+1)}>Guardar</button>
      </div>}
    </div>
  );
}

export default NewBoard;