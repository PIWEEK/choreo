import { useState } from 'react'
import AvatarSelector from '../../components/AvatarSelector/AvatarSelector'
import TasksList from '../../components/TasksList/TasksList'
import './NewBoard.css';
import { useHistory } from "react-router-dom";
import api from '../../services/api';

const NewBoard = () => {
  const [step, setStep] = useState(0);
  const [boardName, setboardName] = useState('');
  const [preselectedTasksList, setPreselectedTasksList] = useState([])
  const [showAvatars, setShowAvatars] = useState({visible:false, index:0});
  const [profiles, setProfile] = useState([{avatarId:Math.round(Math.random()*10) ,name: ''}])
  const history = useHistory();
  const addProfile = ()=> {
    setProfile([...profiles, {avatarId:Math.round(Math.random()*10) ,name: ''}])
  }
  const openAvatarSelector=(index)=>{
    setShowAvatars({visible:true, index})
  }
  const handlerEditName = (e, index) => {
    if(profiles.length===1) {
      setProfile([{...profiles[0], name: e.target.value}]);
      return;
    } else if(index === profiles.length-1){
      setProfile([...profiles.slice(0,index), {...profiles[index], name: e.target.value}])
    } else {
      setProfile([...profiles.slice(0,index), {...profiles[index], name: e.target.value}, ...profiles.slice(index+1)])
    }
  }
  const createBoard = () => {
    const filterProfiles =  profiles.filter(item=>item.name.length)
    const newBoard = {
      name: boardName,
      people: JSON.stringify(filterProfiles),
      taskIds: preselectedTasksList
    }
    api.board.createBoard(newBoard).then(data=>history.push(`/board/${data.pinCode}/true`))
  }
  const selectAvatars = (index) => {
    if(profiles.length===1) {
      setProfile([{...profiles[0], avatar: index}]);
      setShowAvatars({visible:false, index: profiles.length-1})
      return;
    } else if(showAvatars.index === profiles.length-1){
      setProfile([...profiles.slice(0, showAvatars.index), {...profiles[showAvatars.index], avatarId: index}])
    } else {
      setProfile([...profiles.slice(0,showAvatars.index), {...profiles[showAvatars.index], avatarId: index}, ...profiles.slice(showAvatars.index+1)])
    }
    setShowAvatars({visible:false, index: profiles.length-1})
  }

  return (
    <div className="NewBoard">
      <header className="header">
        {!!step && <span className="arrow" onClick={()=>setStep(step-1)}>&lt;</span>}
        <h2>Crear un tablero</h2>
        {!step &&<span className="close" onClick={()=>history.push("/")}>x</span>}
      </header>
      {!step &&<div className="grid">
        <label>Ponle un nombre a tu tablero</label>
        <input type="text" value={boardName} onChange={(e) => setboardName(e.target.value)} />
        <button disabled={boardName.length < 4 } onClick={()=>setStep(step+1)}>Guardar</button>
      </div>}
      {step===1 &&<div className="grid">
        <label>Escribe los nombres de las personas que van a hacer las tareas</label>
        <div className="profile-form">
          {
            profiles.map((item, index) => (
              <div className="card" key={index}>
                <div className="avatar" onClick={()=>openAvatarSelector(index)}><img src={`./img/avatars/avatar${item.avatarId}.png`}></img></div>
                <input type="text" value={item.name} onChange={(e)=>handlerEditName(e, index)}/>
              </div>
              )
            )}
          <button  onClick={addProfile}>+</button>
        </div>
        <button  disabled={!profiles.length & profiles[0].name.length<3 } onClick={()=>setStep(step+1)}>Guardar</button>
      </div>}
      {step===2 && <TasksList preselectedTasksList={preselectedTasksList} setPreselectedTasksList={setPreselectedTasksList} createBoard={createBoard}/>}
      {showAvatars.visible && <AvatarSelector handler={selectAvatars} />}
    </div>
  );
}

export default NewBoard;
